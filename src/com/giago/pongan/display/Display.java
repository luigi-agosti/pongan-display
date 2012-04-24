package com.giago.pongan.display;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.giago.pongview.GameListener;
import com.giago.pongview.PongView;

public class Display extends Activity {

    //private static final String URL = "http://novoda.github.com/ImageLoader/test3.html";
    private static final String URL = "http://pongan-experiment.appspot.com";

    private PongView pongView;
    private WebView webView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        setMoveListener();  
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        setPongBoard();
    }
    
    @Override
    protected void onPause() {
        pongView.stop();
        pongView = null;
        try {
            webView.destroy();            
            webView = null;
        } catch (Exception e) {
            //
        }
        super.onPause();
    }

    private void setPongBoard() {
        pongView = (PongView)findViewById(R.id.pongview);
        pongView.setGameListener(new GameListener() {
            @Override
            public void onReady() {
                pongView.prepare();
                pongView.start();
            }
            
            @Override
            public void onCollision() {
                PonganApplication.getSoundEffect().ping();
            }
            
            @Override
            public void onCompleted(boolean right) {
                PonganApplication.getSoundEffect().crash();
                
                String result = "Red win";
                if (right) {
                    result = "Blue win";   
                }
                Toast.makeText(Display.this, result, Toast.LENGTH_SHORT).show();
                pongView.prepare();
                pongView.start();
            }
        });
    }

    private void setMoveListener() {
        webView = (WebView)findViewById(R.id.channel);
        webView.setWebChromeClient(new WebChromeClient() {
          @Override
          public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
              if(pongView != null) {
                  pongView.move(consoleMessage.message());
              }
              return super.onConsoleMessage(consoleMessage);
          }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
    }
}