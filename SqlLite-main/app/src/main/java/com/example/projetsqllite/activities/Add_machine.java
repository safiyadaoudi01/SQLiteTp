package com.example.projetsqllite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projetsqllite.R;
import com.example.projetsqllite.beans.Machine;
import com.example.projetsqllite.beans.Marque;
import com.example.projetsqllite.services.MachineService;
import com.example.projetsqllite.services.MarqueService;
import com.example.projetsqllite.ui.machine.MachineF;
import com.example.projetsqllite.ui.marque.MarqueF;

import java.util.ArrayList;
import java.util.List;

public class Add_machine extends AppCompatActivity {
          EditText reference,prix,date;
          Button add;
          Spinner  marque;
          String r,p,d,m;

          List<Marque> marques;
          List<String> libelles=new ArrayList<>();
    MachineService ms;
    MarqueService mss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);
        reference=findViewById(R.id.reference);
        prix=findViewById(R.id.prix);
        date=findViewById(R.id.date);
        add=findViewById(R.id.add);
        marque=findViewById(R.id.spe);

         ms=new MachineService(this);
         mss=new MarqueService(this);

        marques=mss.findAll();

        for(Marque marque:marques){
            libelles.add(marque.getLibelle());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, libelles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marque.setAdapter(spinnerAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMachine();
            }
        });

    }

    void addMachine(){
        r=reference.getText().toString().trim();
        p= prix.getText().toString().trim();
        d= date.getText().toString().trim();
        if (r.isEmpty() || p.isEmpty() || d.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

        }
        int pi=Integer.parseInt(p);

         m =  marque.getSelectedItem().toString();

         Machine machine=new Machine(r,pi,d,m);
         ms.create(machine);
        startActivity(new Intent(getApplicationContext(), MachineF.class));



    }
}