package com.digitalized_nepal.localgovernance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class governmentupdates extends Fragment {

    RecyclerView recyclerView;

    ArrayList<String> descrip;
    ArrayList<String> imageid;
    ArrayList<String> titile;

    GoveUpdateAdapter goveUpdateAdapter;
    private SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.govern_recycler, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.myrecylerView);
        //custom adapter always
        //populate recycler view with pdf_items...
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));//            MyAdapter myAdapter = new MyAdapter(recyclerView, farmlandrent.this,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
//            recyclerView.setAdapter(myAdapter);
        descrip = new ArrayList<>();
        imageid = new ArrayList<>();
        titile = new ArrayList<>();

        sharedPreferences = getActivity().getSharedPreferences("UserData",0);


        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Municipality")
                .child("Kathmandu")
                .child("Wardno"+sharedPreferences.getString("wardno", null))
                .child("GovUpdates");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String title = snapshot.child("Title").getValue(String.class);
                    String des = snapshot.child("Description").getValue(String.class);
                    String imge = snapshot.child("ImageId").getValue(String.class);

                    descrip.add(des);
                    titile.add(title);
                    imageid.add(imge);

                    goveUpdateAdapter = new GoveUpdateAdapter(recyclerView, getContext(), descrip, imageid, titile);
                    recyclerView.setAdapter(goveUpdateAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return rootview;
    }
}
