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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nurayim.myapplication5536.Preferences.Prefs;
import com.nurayim.myapplication5536.R;

import java.sql.Struct;


public class ProfileFragment extends Fragment {

    private ImageView imgGetGallery;
    private EditText firstEdit, secondEdit, thirdEdit, fourEdit, fiveEdit;



    ActivityResultLauncher<String> imGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {

                    setImageGlide(uri);

                }

                private void setImageGlide(Uri uri) {
                    Glide.with(requireActivity())
                            .load(uri)
                            .circleCrop()
                            .into(imgGetGallery);
                    imgGetGallery.setImageURI(uri);
                }
            });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgGetGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imGetContent.launch("image/*");
            }
        });



        initItems(view);
        saveEditProfile();


    }

    private void initItems(@NonNull View view) {
        imgGetGallery = view.findViewById(R.id.im_view);
        firstEdit = view.findViewById(R.id.ed_text1);
        secondEdit = getView().findViewById(R.id.ed_text2);
        thirdEdit = getView().findViewById(R.id.ed_text3);
        fourEdit = getView().findViewById(R.id.ed_text4);
        fiveEdit = getView().findViewById(R.id.ed_text5);
    }

    private void saveEditProfile() {

        Prefs prefs = new Prefs(getContext());
        firstEdit.setText(prefs.getString("saveName"));
        firstEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.putString("saveName",s.toString());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    //1. Добавить картинки для страниц
    //2. Добавить кнопку на последнюю страницу, по нажатию открыть Home
    //3. TabLayout
    //4. Добавить кнопку SKIP на самый верх страницы (она не должна двигаться)

    //Урок 4
//1. В профиле добавить поле для ввода имени без кнопки сохранения
//2. Дизайн профиля
//3. Добавить в меню для  u  очистки preferences


}