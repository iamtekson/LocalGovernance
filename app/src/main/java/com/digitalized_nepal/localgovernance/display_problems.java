package com.digitalized_nepal.localgovernance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class display_problems extends Fragment {






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.choose_an_issue,container,false);





        rootview.findViewById(R.id.health).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchProblems.class);
                intent.putExtra("issues","health");
                startActivity(intent);
            }
        });

        rootview.findViewById(R.id.education).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchProblems.class);
                intent.putExtra("issues","education");
                startActivity(intent);
            }
        });

        rootview.findViewById(R.id.social).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchProblems.class);
                intent.putExtra("issues","social");
                startActivity(intent);
            }
        });

        rootview.findViewById(R.id.water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchProblems.class);
                intent.putExtra("issues","water");
                startActivity(intent);

            }
        });

        rootview.findViewById(R.id.trans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FetchProblems.class);
                intent.putExtra("issues","trans");
                startActivity(intent);
            }
        });


        return rootview;
    }

   }