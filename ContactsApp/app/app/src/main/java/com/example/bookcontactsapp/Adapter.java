package com.example.bookcontactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adapter extends FirestoreRecyclerAdapter<Client , Adapter.NoteViewHolder> {
    android.content.Context context;


    public Adapter(@NonNull FirestoreRecyclerOptions<Client> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Client client) {
        holder.firstNameTextView.setText(client.firstName);
        holder.lastNameTextView.setText(client.lastName);
        holder.emailTextView.setText(client.email);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView firstNameTextView, lastNameTextView, emailTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameTextView = itemView.findViewById(R.id.first_name_database);
            lastNameTextView = itemView.findViewById(R.id.last_name_database);
            emailTextView = itemView.findViewById(R.id.email_database);
        }
    }
}
