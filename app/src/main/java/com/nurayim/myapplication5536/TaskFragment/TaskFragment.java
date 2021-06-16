package com.nurayim.myapplication5536.TaskFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nurayim.myapplication5536.App;
import com.nurayim.myapplication5536.R;
import com.nurayim.myapplication5536.models.Task;


public class TaskFragment extends Fragment {

    private EditText editText;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                save();
            }
        });

// инструмент для передачи данных
    }// Вопрос как получить по нажатию кнопки содержимое с EditText
    // получение содержимого с EditText
    private void save() {
        String text = editText.getText().toString();// Вот это чась вытаскивает содержимое с EditTexta
        Task task = new Task(text);

        App.getAppDatabase().taskDao().insert(task);
        Bundle bundle = new Bundle();//Это типа мы собираем данные в упаковку типо упаковываем
        bundle.putString("text", text);// И в эту упаковку добавляем стринг по ключи текст
        getParentFragmentManager().setFragmentResult("rk_task",bundle);
        //  Вот этот метод он при нажатии на save он устанавливает результатом  этот bundle
        //  когда он будет возврощаться он его отправит
        close();


    }

    private void close(){// нужно закрывать фрагменты
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }
}