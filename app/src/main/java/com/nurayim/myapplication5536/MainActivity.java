package com.nurayim.myapplication5536;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nurayim.myapplication5536.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.profile_fragment)
                .build();
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration);
         NavigationUI.setupWithNavController(navView, navController);

        navController.navigate(R.id.on_board_Fragment);

        // Он слушает изменения фрагментов какой открывается какой закрывается
         navController.navigate(R.id.phoneFragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {

            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {

                ArrayList<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
                list.add(R.id.profile_fragment);
                // Здесь видимость и не видимость табов
               // если лист содержит  фрагмент который мы только что открли
                if (list.contains(destination.getId())){
                    navView.setVisibility(View.VISIBLE);
                } else {
                    navView.setVisibility(View.GONE);
                }
                  // Если равен destination.getId() равен R.id.on_board_Fragment)
                if (destination.getId() == R.id.on_board_Fragment){
                    //ActionBar() это и есть toolbar
                    getSupportActionBar().hide();
                } else {
                    getSupportActionBar().show();
                }
            }
        });

    }


    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}
//Урок 1
// 1. Добавить 4й таб (ProfileFragment)
//2. Добавить туда imageView, при нажатии открыть галерею (без onActivityResult deprecated)
//3. Показать выбранную из галереи в imageView
//Урок2
// 1. Сделать зебру для списка
//2. При нажатии на элемент, показать в тосте его position ("0: asd")
//3. При долгом нажатии удалить элемент из списка с помощью AlertDialog
//Урок3
// 1. Добавить картинки для страниц
//2. Добавить кнопку на последнюю страницу, по нажатию открыть Home
//3. TabLayout
//4. Добавить кнопку SKIP на самый верх страницы (она не должна двигаться)(navController чтобы в HomeFragment)

//Урок 4
//1. В профиле добавить поле для ввода имени без кнопки сохранения
//2. Дизайн профиля
//3. Добавить в меню для  u  очистки preferences
//Урок 5
// 1. Добавить кнопку (menu) сортировки списка по алфавиту
//2. На долгое нажатие удаление из бд
//Урок 6
// 1. Показать вью для ввода кода смс
//2. Обратный отсчет, по истечению показать обратно первое вью
//Урок7
// 1. В Dashboard показывать данные из FireStore
//2. Чтоб можно было удалять через AlertDialog
//Урок8
// 1. При нажатии на картинку открыть на весь экран
//2. Сохранить аватарку Firebase Storage

