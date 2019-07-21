package com.digitalized_nepal.localgovernance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    //TextViews
    private TextView mAppNameView;
    private TextView mAppVersionView;
    private ImageView mAppLogoView;

    //Time delays
    private static final Long SPLASH_DELAY = 2500L;

    private Handler mDelayHandler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFinishing()) {
                Intent intent;
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    intent = new Intent(SplashScreen.this, UserLoginActivity.class);
                } else {
//                    if (Injection.getSharedPreference().getBoolean(SharedPrefConstants.IS_USER_DETAILS_ENTERED)) {
                    intent = new Intent(SplashScreen.this, MainPage.class);

                }
                finish();
                overridePendingTransition(R.anim.enter, R.anim.exit);
                startActivity(intent);
            }
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        mDelayHandler.postDelayed(runnable, SPLASH_DELAY);



    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelayHandler.removeCallbacks(runnable);
    }
}
