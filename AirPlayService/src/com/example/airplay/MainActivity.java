package com.example.airplay;

import nz.co.iswe.android.airplay.AirPlayServer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.airplay.util.Constants;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(AirPlayServer.getIstance(this)).start();
        
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Log.e("hefeng", "PlayerState : "+MyApplication.getInstance().getPlayerState()) ;
//			    MainActivity.this.sendBroadcast(new Intent(Constants.IKEY_MEDIA_PLAY_OR_PAUSE));
			}
		});
    }

}
