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
    TextInputEditText citz;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    SharedPreferences preferences;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        fname = (TextInputEditText) findViewById(R.id.FullName);
        citz = (TextInputEditText) findViewById(R.id.citizenship_number);

        preferences = getSharedPreferences("UserData",0);






        mFirebaseInstance = FirebaseDatabase.getInstance();
        initspinner();



        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    fullname = fname.getText().toString().trim();
                    citizenno = citz.getText().toString().trim();

                SharedPreferences.Editor editor = preferences.edit();


                editor.putString("fullname", fullname);
                editor.putString("wardno", wardno);
                editor.putString("provinceno", province);
                editor.putString("citizenno", citizenno);
                editor.putString("municipality", municipality);
                editor.commit();



                    createUser(fullname, wardno, citizenno, province, municipality);
                    Intent intent = new Intent(UserDetailActivity.this, MainPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

            }



        });





    }

















    public void createUser(String fname, String ward, String citno ,String provincn, String munic) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing fir;ebase auth

        mFirebaseDatabase = mFirebaseInstance.getReference("Municipality")
                .child("Kathmandu")
                .child("Wardno1")
                .child("users");

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

        Spinner spinner2 = findViewById(R.id.spinner_wardno);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this,
                R.array.wardno, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter12);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wardno = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }









}
