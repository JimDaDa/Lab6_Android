package com.example.bai_3;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter3  extends RecyclerView.Adapter<Adapter3.ViewHolder>{
    private List<Event> item;

    public Adapter3(List<Event> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_3, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Event event = item.get(position);
        holder.name.setText(event.getName());
        holder.place.setText(event.getPlace());
        holder.datetime.setText(event.getDate() + " " + event.getName());
        holder.eventSwitch.setChecked(event.getDone());
        holder.eventSwitch.setTag(position);
        holder.eventSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Event event1 = item.get(position);
                isChecked=event1.getDone();
                event1.setDone(!isChecked);
                Toast.makeText(buttonView.getContext(), "click", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
//        View view = null;
//        boolean check= ((CheckBox) view).isChecked();
//        event.setDone(check);
//        notifyDataSetChanged();
//        holder.eventSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Event event1 = item.get(position);
//                boolean check= ((CheckBox)v).isChecked();
//                event1.setDone(check);
//                notifyDataSetChanged();
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return item.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name;
        TextView place;
        TextView datetime;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch eventSwitch;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
           name = itemView.findViewById(R.id.tv_name);
           place = itemView.findViewById(R.id.tv_place);
           datetime = itemView.findViewById(R.id.tv_datetime);
           eventSwitch = itemView.findViewById(R.id.sw_switch);

        }
    }
}
