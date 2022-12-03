package com.example.bookcontactsapp.ui.database;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookcontactsapp.R;

public class DatabaseFragment extends Fragment {

    private DatabaseViewModel mViewModel;
    RecyclerView recyclerView;

    public static DatabaseFragment newInstance() {
        return new DatabaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

}