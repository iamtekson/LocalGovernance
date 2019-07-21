package com.digitalized_nepal.localgovernance;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GoveUpdateAdapter extends RecyclerView.Adapter<GoveUpdateAdapter.SearchViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> descrip = new ArrayList<>();
    ArrayList<String> imageid = new ArrayList<>();
    ArrayList<String> titile = new ArrayList<>();

    DatabaseReference reference;

    String describe;
    String topic_title;


    private SharedPreferences sharedPreferences;





    public GoveUpdateAdapter(RecyclerView recyclerView, Context context, ArrayList<String> descrip, ArrayList<String> imageid, ArrayList<String> titile) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.descrip = descrip;
        this.imageid = imageid;
        this.titile = titile;

    }

    @Override
    public GoveUpdateAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.governmentupdate, parent, false);

        return new GoveUpdateAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder viewHolder, int i) {
      //  holder.full_name.setText(fullNameList.get(position));
        viewHolder.description.setText(descrip.get(i));
        viewHolder.title.setText(titile.get(i));



        Picasso.get().load(imageid.get(i)).into(viewHolder.imageView);
    }




    @Override
    public int getItemCount() {
        return descrip.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder
    {
        TextView title ;
        TextView description ;
        ImageView imageView ;
        TextView sendfeedback;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            description = itemView.findViewById(R.id.description_title);
            sendfeedback = itemView.findViewById(R.id.feedback);
            imageView = itemView.findViewById(R.id.governproject);




            sendfeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topic_title = title.getText().toString();

                    sendto();


                }
            });
        }
    }

    public void sendto()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.feedback_page, null);

       // price_livestock = view.findViewById(R.id.price_livestock);

        builder.setView(view);
        final AlertDialog ad = builder.show();




        view.findViewById(R.id.problem_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText editText ;
                editText = (EditText)view.findViewById(R.id.report_post);
                describe = editText.getText().toString();
                sharedPreferences = context.getSharedPreferences("UserData",0);

                userfeedback user = new userfeedback(sharedPreferences.getString("fullname", null),topic_title,describe);

                reference = FirebaseDatabase.getInstance().getReference("Municipality");

                reference.child("Kathmandu")
                        .child("Wardno1")
                        .child("UserFeedback")
                        .child(topic_title)
                        .setValue(user);





                ad.dismiss();




            }
        });






    }


}
