package com.example.bookcontactsapp.ui.form;

import androidx.lifecycle.ViewModelProvider;

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

import com.example.bookcontactsapp.R;

public class FormFragment extends Fragment {

    private FormViewModel mViewModel;
    EditText FirstNameText, LastNameText, EmailAddressText, AddressText, PhoneNumberText, NotesText;
    Button CreateAccountBtn;

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
        CreateAccountBtn = view.findViewById(R.id.submitBtn);

        CreateAccountBtn.setOnClickListener(v-> createAccount());

        return view;
    }

    void createAccount(){
        String firstName = FirstNameText.getText().toString();
        String lastName = LastNameText.getText().toString();
        String email = EmailAddressText.getText().toString();
        String address = AddressText.getText().toString();
        String phoneNumber = PhoneNumberText.getText().toString();
        String notes = NotesText.getText().toString();

        boolean isValidated = validateData(firstName, lastName, email, address, phoneNumber, notes);
//        if(!isValidated){
//            return;
//        }
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