package com.example.android.nytimesseachengine.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.nytimesseachengine.R;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ButtonViewHolder>{
    ArrayList<String> cities=new ArrayList<>();
    ListItemClickListener listItemClickListenerr;
    public interface ListItemClickListener
    {
        void OnListItemClick(int clickedItemIndex);
    }
    public void settingUpList(ArrayList<String> cities)
    {
        this.cities.clear();
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }
    public RecycleAdapter(ListItemClickListener listItemClickListener)
    {
        this.listItemClickListenerr=listItemClickListener;
    }
    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForItem=R.layout.recycle_layout;
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(layoutIdForItem,parent,false);
        ButtonViewHolder buttonViewHolder=new ButtonViewHolder(view);
        return buttonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Button button;
        public ButtonViewHolder(View buttonView)
        {
            super(buttonView);
            button=buttonView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }
        public void bind(int listIndex)
        {
            button.setText(cities.get(listIndex));
        }

        @Override
        public void onClick(View view) {
            listItemClickListenerr.OnListItemClick(getAdapterPosition());
        }
    }
}
