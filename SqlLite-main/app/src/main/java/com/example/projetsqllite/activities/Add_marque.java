package com.example.projetsqllite.activities;

import androidx.appcompat.app.AppCompatActivity;

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

public class Add_marque extends AppCompatActivity {

    EditText code,libelle;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marque);

        code=findViewById(R.id.code);
        libelle=findViewById(R.id.libelle);
        add=findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarqueService sv = new MarqueService(Add_marque.this);
                if(code.getText().toString().isEmpty() || libelle.getText().toString().isEmpty()  ){
                    Toast.makeText(Add_marque.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }else {
                    Marque marque = new Marque(code.getText().toString(), libelle.getText().toString());
                    sv.create(marque);
                    startActivity(new Intent(getApplicationContext(), MarqueF.class));
                }
            }
        });


    }
}