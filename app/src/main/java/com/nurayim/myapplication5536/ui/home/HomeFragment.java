package com.nurayim.myapplication5536.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nurayim.myapplication5536.App;
import com.nurayim.myapplication5536.R;
import com.nurayim.myapplication5536.models.Task;

import java.util.List;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        roomInit();
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskFragment();

            }
        });
        setResultListener();


    }


    private void initList(List<Task>list) {
        adapter = new TaskAdapter();
        adapter.addItems(list);
        recyclerView.setAdapter(adapter);


    }
    private void roomInit() {
        App.getAppDatabase().taskDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> list) {
                initList(list);
            }
        });
    }

    private void setResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_task",
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String text = result.getString("text");//(Вот этот ключт и ключ в PutString должны совподатть
                        Task task = new Task(text);
                        adapter.addItem(task);
                        Log.e("Home", "text" + text);
                    }
                });

    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_home_fragment,menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort:
                sortRoom();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortRoom() {
        App.getAppDatabase().taskDao().sortByAsc().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Toast.makeText(requireContext(), "sorted", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                initList(tasks);
            }
        });

    }
    private void openTaskFragment() {
        //Открытие Фрагмента берем из MainActivity первую линию сраз копируем

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.taskFragment);
    }
}
// RecyclerView и адаптер они все есть внутри firebase