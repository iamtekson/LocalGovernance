package com.digitalized_nepal.localgovernance;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class LocalIssueAdapter extends RecyclerView.Adapter<LocalIssueAdapter.SearchViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> descrip = new ArrayList<>();
    ArrayList<String> namee = new ArrayList<>();
    ArrayList<String> topi = new ArrayList<>();



    public LocalIssueAdapter(RecyclerView recyclerView, Context context, ArrayList<String> descrip, ArrayList<String> myname, ArrayList<String> topics)
    {
        this.recyclerView = recyclerView;
        this.context = context;
        this.descrip = descrip;
        this.namee = myname;
        this.topi = topics;

    }

    @Override
    public LocalIssueAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.localissues, parent, false);
        return new LocalIssueAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder viewHolder, int i) {
      //  holder.full_name.setText(fullNameList.get(position));
        viewHolder.description.setText(descrip.get(i));
        viewHolder.topic.setText(topi.get(i));
        viewHolder.naam.setText(namee.get(i));

    }




    @Override
    public int getItemCount() {
        return descrip.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder
    {
        TextView naam ;
        TextView topic ;
        TextView description ;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            naam = itemView.findViewById(R.id.issuer_name);
            topic = itemView.findViewById(R.id.issue_topic);
            description = itemView.findViewById(R.id.issue_desc);

        }
    }


}
