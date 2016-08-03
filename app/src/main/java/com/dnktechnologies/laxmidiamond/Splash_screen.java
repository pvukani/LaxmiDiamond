package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by parth on 7/19/2016.
 */
public class Splash_screen extends Activity {
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.spash_scrren);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 5000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }
                    ;
                    Intent in = new Intent(Splash_screen.this, Login.class);
                    startActivity(in);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}
