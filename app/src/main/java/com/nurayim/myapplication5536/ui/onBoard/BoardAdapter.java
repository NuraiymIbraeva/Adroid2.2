package com.nurayim.myapplication5536.ui.onBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nurayim.myapplication5536.Interfaces.onItemClickListener;
import com.nurayim.myapplication5536.MainActivity;
import com.nurayim.myapplication5536.R;


public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[]{"Класс", "Контакты", "Дом"};
    private int[] images = new int[]{R.drawable.first2, R.drawable.second2, R.drawable.third3};

    private onItemClickListener onItemClickListener;
    private OnStart onStart;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return // здесь можно написать определенное
                // число например 3 и будет в начале 3 viewPager или можно titles.length;
                titles.length;
    }


    public void setOnStart(OnStart onStart) {
        this.onStart = onStart;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        ImageView img;
        Button btnStr;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.text_title);
            img = itemView.findViewById(R.id.image);
            btnStr = itemView.findViewById(R.id.btn_str);
            btnStr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  onStart.onStartClick();
                }
            });
        }


        public void bind(int position) {
            //он из массива по позиции получает
            txtTitle.setText(titles[position]);
            img.setImageResource(images[position]);
            if (position == titles.length - 1) {


                btnStr.setVisibility(View.VISIBLE);
            } else {
                btnStr.setVisibility(View.GONE);
                //в адаптере обязательно добавить else чтобы был вариант
            }

        }

    }
}

interface OnStart{
    void onStartClick();
}
