package com.ap.group38.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.group38.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    OnClickListener onClickListener;

    public BookingAdapter(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_ships , parent , false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView , OnClickListener Listener) {
            super(itemView);
            onClickListener = Listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
}
