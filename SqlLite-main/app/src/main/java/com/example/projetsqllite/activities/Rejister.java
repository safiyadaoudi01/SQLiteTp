package com.example.projetsqllite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetsqllite.R;
import com.example.projetsqllite.beans.Marque;
import com.example.projetsqllite.beans.User;
import com.example.projetsqllite.services.MarqueService;
import com.example.projetsqllite.services.UserService;

public class Rejister extends AppCompatActivity {

      EditText fnm,lnm,email,pswd;
      Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejister);

        fnm=findViewById(R.id.nom);
        lnm=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        pswd=findViewById(R.id.pswd);
        add=findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserService sv = new UserService(Rejister.this);
                User user=new User(fnm.getText().toString(),lnm.getText().toString(),email.getText().toString(),pswd.getText().toString());
                sv.create(user);
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
    public void onRegisterClick(View View){
        startActivity(new Intent(this, Login.class));
    }
}