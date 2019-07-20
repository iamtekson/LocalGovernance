package com.digitalized_nepal.localgovernance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetailActivity extends AppCompatActivity {
    String fullname;
    String wardno;
    String citizenno;
    String province;
    String municipality;


    private String currentuser;





    TextInputEditText fname;
    TextInputEditText war;
    TextInputEditText citz;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    private String mobileeno;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        fname = (TextInputEditText) findViewById(R.id.FullName);
        war = (TextInputEditText) findViewById(R.id.ward_number);
        citz = (TextInputEditText) findViewById(R.id.citizenship_number);


        mobileeno = getIntent().getStringExtra("phonenumber");



        mFirebaseInstance = FirebaseDatabase.getInstance();
        initspinner();

        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    fullname = fname.getText().toString().trim();
                    wardno = war.getText().toString().trim();
                    citizenno = citz.getText().toString().trim();



                    createUser(fullname, wardno, citizenno, province, municipality);
                    Intent intent = new Intent(UserDetailActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

            }



        });





    }

















    public void createUser(String fname, String ward, String citno ,String provincn, String munic) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing fir;ebase auth
        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        User user = new User(fname,ward,citno,provincn,munic);

        mFirebaseDatabase.child(currentuser).setValue(user);


    }


    public void initspinner()
    {
        Spinner spinner = findViewById(R.id.spinner_province);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinner1 = findViewById(R.id.spinner_municipality);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.municipality, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                municipality = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }









}
