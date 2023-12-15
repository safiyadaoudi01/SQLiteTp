package com.example.projetsqllite.ui.machine;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetsqllite.Adapter.MachineAdapter;
import com.example.projetsqllite.Adapter.MarqueAdapter;
import com.example.projetsqllite.R;
import com.example.projetsqllite.activities.Add_machine;
import com.example.projetsqllite.beans.Machine;
import com.example.projetsqllite.beans.Marque;
import com.example.projetsqllite.services.MachineService;
import com.example.projetsqllite.services.MarqueService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MachineF extends Fragment {
    private FloatingActionButton  button;
    private RecyclerView recyclerView;
    private List<Machine> machines;
    private MachineAdapter machineAdapter;
    private MachineService service;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_machine, container, false);
        recyclerView = view.findViewById(R.id.machineRecyclerView);

        button=view.findViewById(R.id.add_machine_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_machine.class);
                startActivityForResult(intent, 1);
            }
        });
                return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storeDataInArrays();
        machineAdapter = new MachineAdapter(requireActivity(), requireContext(), machines);
        recyclerView.setAdapter(machineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void storeDataInArrays() {
        machines = new ArrayList<>();
        service = new MachineService(getActivity());
        machines = service.findAll();
    }

}