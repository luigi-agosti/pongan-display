package com.giago.pongan.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class QRCode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        findViewById(R.id.qrcode_image_button).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                starDisplayActivity();
            }
        });
        
        
    }
    
    private void starDisplayActivity(){
        Intent i = new Intent(this,Display.class);
        startActivity(i);
    }
}
