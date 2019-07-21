package com.digitalized_nepal.localgovernance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class stateproblem extends AppCompatActivity {
    String topics_issue;
    String topic_des;
    EditText des;
    DatabaseReference reference;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateproblem);

        initspinner();

        des = findViewById(R.id.report_post);



        findViewById(R.id.submit_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("UserData",0);


                topic_des = des.getText().toString().trim();
                Random random = new Random();

                int num = random.nextInt(1000);
                String convert = String.valueOf(num);

                if (topics_issue == "Health")
                {
                    user_issueclass user = new user_issueclass(sharedPreferences.getString("fullname", null),topic_des,topics_issue);


                    reference = FirebaseDatabase.getInstance().getReference("Municipality");

                    reference.child("Kathmandu")
                            .child("Wardno1")
                            .child("Problems")
                            .child(topics_issue)
                            .child(convert)
                            .setValue(user);


                } else if (topics_issue == "Education")
                {
                    user_issueclass user = new user_issueclass(sharedPreferences.getString("fullname", null),topic_des,topics_issue);




                    reference = FirebaseDatabase.getInstance().getReference("Municipality");

                    reference.child("Kathmandu")
                            .child("Wardno1")
                            .child("Problems")
                            .child(topics_issue)
                            .child(convert)
                            .setValue(user);

                }
                else if (topics_issue == "Drinking Water")
                {
                    user_issueclass user = new user_issueclass(sharedPreferences.getString("fullname", null),topic_des,topics_issue);




                    reference = FirebaseDatabase.getInstance().getReference("Municipality");

                    reference.child("Kathmandu")
                            .child("Wardno1")
                            .child("Problems")
                            .child(topics_issue)
                            .child(convert)
                            .setValue(user);

                }

                else if (topics_issue == "Transparency")
                {
                    user_issueclass user = new user_issueclass(sharedPreferences.getString("fullname", null),topic_des,topics_issue);




                    reference = FirebaseDatabase.getInstance().getReference("Municipality");

                    reference.child("Kathmandu")
                            .child("Wardno1")
                            .child("Problems")
                            .child(topics_issue)
                            .child(convert)
                            .setValue(user);

                }
                else
                {
                    user_issueclass user = new user_issueclass(sharedPreferences.getString("fullname", null),topic_des,topics_issue);




                    reference = FirebaseDatabase.getInstance().getReference("Municipality");

                    reference.child("Kathmandu")
                            .child("Wardno1")
                            .child("Problems")
                            .child(topics_issue)
                            .child(convert)
                            .setValue(user);

                }


                startActivity(new Intent(stateproblem.this,MainPage.class));

            }
        });




    }

    public void initspinner()
    {
        Spinner spinner = findViewById(R.id.spinner_issue);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.issues_topic, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topics_issue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
