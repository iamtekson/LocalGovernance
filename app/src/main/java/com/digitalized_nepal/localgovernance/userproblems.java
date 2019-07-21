package com.digitalized_nepal.localgovernance;

import android.content.Context;
import android.content.Intent;
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

public class userproblems extends Fragment {

    RecyclerView recyclerView;

    ArrayList<String> descrip4 ;
    ArrayList<String> namee ;
    ArrayList<String> topi ;

    LocalIssueAdapter localIssueAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.localissue_recycler, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.myrecylerView);
        //custom adapter always
        //populate recycler view with pdf_items...
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));//            MyAdapter myAdapter = new MyAdapter(recyclerView, farmlandrent.this,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
//            recyclerView.setAdapter(myAdapter);
        descrip4 = new ArrayList<>();
        namee = new ArrayList<>();
        topi = new ArrayList<>();


        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Municipality")
                .child("Kathmandu")
                .child("Wardno1")
                .child("Problems");
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



                    localIssueAdapter = new LocalIssueAdapter(recyclerView, getContext(), descrip4, namee, topi);
                    recyclerView.setAdapter(localIssueAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootview;
    }


}