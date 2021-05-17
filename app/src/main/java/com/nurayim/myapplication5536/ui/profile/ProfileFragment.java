package com.nurayim.myapplication5536.ui.profile;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nurayim.myapplication5536.R;


public class ProfileFragment extends Fragment {

    private ImageView imgGetGallery;


    ActivityResultLauncher<String> imGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {

                    imgGetGallery.setImageURI(uri);

                }
            });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgGetGallery = view.findViewById(R.id.im_view);
        imgGetGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imGetContent.launch("image/*");

            }
        });

    }






}