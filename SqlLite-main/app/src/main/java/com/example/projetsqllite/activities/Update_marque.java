package com.example.projetsqllite.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetsqllite.R;
import com.example.projetsqllite.beans.Marque;
import com.example.projetsqllite.services.MarqueService;
import com.example.projetsqllite.ui.marque.MarqueF;

public class Update_marque extends AppCompatActivity {
     EditText code,libelle;
     Button btn1,btn2;
     String id,codee,libellee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_marque);

        code=findViewById(R.id.code);
        libelle=findViewById(R.id.libelle);
        btn1=findViewById(R.id.update);
        btn2=findViewById(R.id.delete);
        getData();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarqueService srv = new MarqueService(Update_marque.this);
                codee = code.getText().toString().trim();
                libellee = libelle.getText().toString().trim();
                Marque marque = new Marque(codee, libellee);
                srv.update(marque, Integer.valueOf(id));
                startActivity(new Intent(getApplicationContext(), MarqueF.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("Code") &&
                getIntent().hasExtra("Libelle")) {

            id = getIntent().getStringExtra("id");
            codee = getIntent().getStringExtra("Code");
            libellee = getIntent().getStringExtra("Libelle");
            code.setText(codee);
            libelle.setText(libellee);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + codee + " ?");
        builder.setMessage("Are you sure you want to delete " + codee + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MarqueService srv = new MarqueService(Update_marque.this);
                srv.delete(Integer.valueOf(id));
                startActivity(new Intent(getApplicationContext(), MarqueF.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), MarqueF.class));
            }
        });
        builder.create().show();
    }
}