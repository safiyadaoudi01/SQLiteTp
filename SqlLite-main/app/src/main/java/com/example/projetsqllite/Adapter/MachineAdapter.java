package com.example.projetsqllite.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetsqllite.activities.Update_machine;
import com.example.projetsqllite.beans.Machine;

import java.util.List;
import com.example.projetsqllite.R;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<Machine> machines;

    public MachineAdapter(Activity activity, Context context, List<Machine> machines) {
        this.activity = activity;
        this.context = context;
        this.machines = machines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the provided context parameter instead of the context field
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_machine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Machine machine = machines.get(position);
        holder.id.setText(String.valueOf(machine.getId()));
        holder.reference.setText(machine.getReference());
        holder.prix.setText(String.valueOf(machine.getPrix()));
        holder.date.setText(machine.getDate());
        holder.marque.setText(machine.getMarqueCode());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_machine.class);
                intent.putExtra("id", String.valueOf(machine.getId()));
                intent.putExtra("Reference", String.valueOf(machine.getReference()));
                intent.putExtra("Prix", String.valueOf(machine.getPrix()));
                intent.putExtra("Date", String.valueOf(machine.getDate()));
                intent.putExtra("Marque", String.valueOf(machine.getMarqueCode()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        RelativeLayout mainLayout;
        TextView reference;
        TextView prix;
        TextView marque;
        TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            reference = itemView.findViewById(R.id.reference);
            prix= itemView.findViewById(R.id.prix);
            date=itemView.findViewById((R.id.date));
            marque=itemView.findViewById((R.id.marque));
            mainLayout = itemView.findViewById(R.id.itmachine);
        }
    }
}

