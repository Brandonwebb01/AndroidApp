package com.example.bookcontactsapp.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookcontactsapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FormFragment extends Fragment {

    private FormViewModel mViewModel;
    EditText FirstNameText, LastNameText, EmailAddressText, AddressText, PhoneNumberText, NotesText;
    Button enterDataBtn;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        FirstNameText = view.findViewById(R.id.firstNameText);
        LastNameText = view.findViewById(R.id.lastNameText);
        EmailAddressText = view.findViewById(R.id.emailAddressText);
        AddressText = view.findViewById(R.id.addressText);
        PhoneNumberText = view.findViewById(R.id.phoneNumberText);
        NotesText = view.findViewById(R.id.multiLineNote);
        enterDataBtn = view.findViewById(R.id.submitBtn);

        enterDataBtn.setOnClickListener(v-> enterData());

        return view;
    }

    void enterData(){
        String firstName = FirstNameText.getText().toString();
        String lastName = LastNameText.getText().toString();
        String email = EmailAddressText.getText().toString();
        String address = AddressText.getText().toString();
        String phoneNumber = PhoneNumberText.getText().toString();
        String notes = NotesText.getText().toString();

        boolean isValidated = validateData(firstName, lastName, email, address, phoneNumber, notes);

        if (isValidated){
            HashMap<String , String> userMap = new HashMap<>();
            userMap.put("firstName" , firstName);
            userMap.put("lastName" , lastName);
            userMap.put("email" , email);
            userMap.put("address" , address);
            userMap.put("phoneNumber" , phoneNumber);
            userMap.put("notes" , notes);

            root.push().setValue(userMap);

            FirstNameText.getText().clear();
            LastNameText.getText().clear();
            EmailAddressText.getText().clear();
            AddressText.getText().clear();
            PhoneNumberText.getText().clear();
            NotesText.getText().clear();

            Toast.makeText(getActivity(),"Data Sent", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(getActivity(),"Error in form", Toast.LENGTH_SHORT).show();
        }
    }

    boolean validateData(String firstName, String lastName, String email, String address, String phoneNumber, String notes){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailAddressText.setError("Email is invalid");
            return false;
        }
        if(!Patterns.PHONE.matcher(phoneNumber).matches()){
            PhoneNumberText.setError("Phone Number invalid");
            return false;
        }
        return true;
    }

}