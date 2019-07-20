package com.digitalized_nepal.localgovernance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rilixtech.CountryCodePicker;

import rezwan.pstu.cse12.view.CircularMorphLayout;

public class UserLoginActivity extends AppCompatActivity {

    EditText editTextCode;

    CountryCodePicker ccp;


    TextView tv_proceed;
    CircularMorphLayout circularMorphLayout;







    String phonenumber;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        editTextCode = (EditText)findViewById(R.id.editTextCode);
        ccp = (CountryCodePicker) findViewById(R.id.cc_country_code);
        ccp.registerPhoneNumberTextView(editTextCode);






        tv_proceed = (TextView) findViewById(R.id.tv_proceed);

        circularMorphLayout = (CircularMorphLayout)findViewById(R.id.cml_proceed_layout);


        phonenumber = ccp.getNumber();






        tv_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (GeneralUtil.isNetworkAvailable(UserLoginActivity.this)) {
                    if (editTextCode.getText().toString().isEmpty() || editTextCode.getText().toString().length() < 10)  {
                        showSnackBar(getString(R.string.msg_invalid_phone_number));


                    } else {


                        String phoneNumber = ccp.getNumber();



                        //duration between 100 to 1000
                        circularMorphLayout.revealFrom(tv_proceed.getWidth() / 2f,
                                tv_proceed.getHeight() / 2f,
                                tv_proceed.getWidth() / 2f,
                                tv_proceed.getHeight() / 2f).setListener(
                                () -> {
                                    tv_proceed.setVisibility(View.GONE);
                                    findViewById((R.id.pb_processing)).setVisibility(View.VISIBLE);
                                }).start();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(UserLoginActivity.this, UserVerifyingAcitivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("phonenumber", phoneNumber);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }
                        }, 2000);




                    }
                } else {
                    showSnackBar(getString(R.string.msg_no_internet_connection));
                }

            }
        });




    }













    public void showSnackBar(String message) {
        showSnackBar(message, findViewById(android.R.id.content));
    }

    public void showSnackBar(String message, View view) {
        Snackbar snackBar =
                Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        View sbView = snackBar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackBar.show();
    }


}

