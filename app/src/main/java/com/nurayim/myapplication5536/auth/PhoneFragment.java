package com.nurayim.myapplication5536.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.nurayim.myapplication5536.R;

import java.util.concurrent.TimeUnit;


public class PhoneFragment extends Fragment {

    private EditText editPhone;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private EditText editCode;
    private Button btnContinue;
    private Button btnCheck;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editPhone = view.findViewById(R.id.edit_Phone);
        editCode = view.findViewById(R.id.edit_Code);
        btnContinue = view.findViewById(R.id.btnContinue);
        btnCheck = view.findViewById(R.id.btnCheck);
        progressBar = view.findViewById(R.id.progress_bar);


        view.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(requireContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show();
                    return;
                }

                editPhone.setVisibility(View.GONE);
                editCode.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.GONE);
                btnCheck.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                requestSms();

            }


        });
        initCallBacks();
    }

    private void initCallBacks() {
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.e("Phone", "onVerificationCompleted");
                //phoneAuthCredential.getSmsCode();
                progressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("Phone", "onVerificationFailed" + e.getMessage());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);

                progressBar.setVisibility(View.GONE);

                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editCode.getText().toString().trim().isEmpty()) {
                            Toast.makeText(requireContext(), "Введите код пожалуйста!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String code = editCode.getText().toString();
                        String verifId = verificationId;
                        if (verifId != null) {
                            progressBar.setVisibility(View.VISIBLE);
                            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifId, code);
                            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        progressBar.setVisibility(View.GONE);
                                        Log.e("Phone", "complete");
                                        Toast.makeText(requireContext(), "Вы зарегистрировались!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(requireContext(), "не правильно введен код!", Toast.LENGTH_SHORT).show();
                                       progressBar.setVisibility(View.GONE);
                                        return;
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Phone", "Error" + e.getMessage());
                                    Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                });
            }
        };
    }


    private void requestSms() {
        String phone = editPhone.getText().toString();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}




