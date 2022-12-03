package com.example.bookcontactsapp.ui.database;

import androidx.lifecycle.ViewModelProvider;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.bookcontactsapp.Adapter;
import com.example.bookcontactsapp.Client;
import com.example.bookcontactsapp.R;
import com.example.bookcontactsapp.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.Query;

public class DatabaseFragment extends Fragment {

    private DatabaseViewModel mViewModel;
    RecyclerView recyclerView;
    //FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;
    Adapter adapter;

    public static DatabaseFragment newInstance() {
        return new DatabaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        setUpRecyclerView();
        return view;
    }

    void setUpRecyclerView(){
        Query query = Utility.getCollectionReferenceForNotes().orderBy(String.valueOf(Query.Direction.DESCENDING));
        FirestoreRecyclerOptions<Client> options = new FirestoreRecyclerOptions.Builder<Client>()
                .setQuery(query, Client.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(options, getActivity());
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}