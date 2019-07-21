package com.digitalized_nepal.localgovernance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchProblems extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<String> descrip4;
    ArrayList<String> namee;
    ArrayList<String> topi;

    LocalIssueAdapter localIssueAdapter;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isConnected(FetchProblems.this)) buildDialog(FetchProblems.this).show();
        else {
            setContentView(R.layout.activity_fetch_problems);
            recyclerView = (RecyclerView) findViewById(R.id.myrecylerView);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(FetchProblems.this));
            recyclerView.addItemDecoration(new DividerItemDecoration(FetchProblems.this, LinearLayoutManager.VERTICAL));

            descrip4 = new ArrayList<>();
            namee = new ArrayList<>();
            topi = new ArrayList<>();
            Intent intent = getIntent();
            String getRequest = intent.getStringExtra("issues");
            sharedPreferences = getSharedPreferences("UserData",0);

            if (getRequest.equals("health")) {

                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Municipality")
                        .child("Kathmandu")
                        .child("Wardno"+sharedPreferences.getString("wardno", null))
                        .child("Problems")
                        .child("Health");
                ;
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String yourname = snapshot.child("iss_name").getValue(String.class);
                            String yourdes = snapshot.child("des").getValue(String.class);
                            String yourtopic = snapshot.child("topic_name").getValue(String.class);

                            descrip4.add(yourdes);
                            namee.add(yourname);
                            topi.add(yourtopic);



                            localIssueAdapter = new LocalIssueAdapter(recyclerView, getApplicationContext(), descrip4, namee, topi);
                            recyclerView.setAdapter(localIssueAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else if (getRequest.equals("education")) {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Municipality")
                        .child("Kathmandu")
                        .child("Wardno"+sharedPreferences.getString("wardno", null))
                        .child("Problems")
                        .child("Education");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String yourname = snapshot.child("iss_name").getValue(String.class);
                            String yourdes = snapshot.child("des").getValue(String.class);
                            String yourtopic = snapshot.child("topic_name").getValue(String.class);

                            descrip4.add(yourdes);
                            namee.add(yourname);
                            topi.add(yourtopic);



                            localIssueAdapter = new LocalIssueAdapter(recyclerView, getApplicationContext(), descrip4, namee, topi);
                            recyclerView.setAdapter(localIssueAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } else if (getRequest.equals("social")) {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Municipality")
                        .child("Kathmandu")
                        .child("Wardno"+sharedPreferences.getString("wardno", null))
                        .child("Problems")
                        .child("Social Issues");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String yourname = snapshot.child("iss_name").getValue(String.class);
                            String yourdes = snapshot.child("des").getValue(String.class);
                            String yourtopic = snapshot.child("topic_name").getValue(String.class);

                            descrip4.add(yourdes);
                            namee.add(yourname);
                            topi.add(yourtopic);



                            localIssueAdapter = new LocalIssueAdapter(recyclerView, getApplicationContext(), descrip4, namee, topi);
                            recyclerView.setAdapter(localIssueAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else if (getRequest.equals("water")) {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Municipality")
                        .child("Kathmandu")
                        .child("Wardno"+sharedPreferences.getString("wardno", null))
                        .child("Problems")
                        .child("Drinking Water");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String yourname = snapshot.child("iss_name").getValue(String.class);
                            String yourdes = snapshot.child("des").getValue(String.class);
                            String yourtopic = snapshot.child("topic_name").getValue(String.class);

                            descrip4.add(yourdes);
                            namee.add(yourname);
                            topi.add(yourtopic);



                            localIssueAdapter = new LocalIssueAdapter(recyclerView, getApplicationContext(), descrip4, namee, topi);
                            recyclerView.setAdapter(localIssueAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Municipality")
                        .child("Kathmandu")
                        .child("Wardno"+sharedPreferences.getString("wardno", null))
                        .child("Problems")
                        .child("Transparency");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String yourname = snapshot.child("iss_name").getValue(String.class);
                            String yourdes = snapshot.child("des").getValue(String.class);
                            String yourtopic = snapshot.child("topic_name").getValue(String.class);

                            descrip4.add(yourdes);
                            namee.add(yourname);
                            topi.add(yourtopic);



                            localIssueAdapter = new LocalIssueAdapter(recyclerView, getApplicationContext(), descrip4, namee, topi);
                            recyclerView.setAdapter(localIssueAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }
    }

        public boolean isConnected(Context context) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netinfo = cm.getActiveNetworkInfo();

            if (netinfo != null && netinfo.isConnectedOrConnecting()) {
                android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
                else return false;
            } else

                return false;
        }

        public AlertDialog.Builder buildDialog(Context c) {

            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("No Internet Connection");
            builder.setCancelable(false);
            builder.setMessage("You need to have Mobile Data or Wifi to access this. Press ok to Exit");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            return builder;
        }
    }
