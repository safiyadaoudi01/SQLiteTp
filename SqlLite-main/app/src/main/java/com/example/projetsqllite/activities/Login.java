package com.example.projetsqllite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetsqllite.MainActivity;
import com.example.projetsqllite.R;
import com.example.projetsqllite.beans.User;
import com.example.projetsqllite.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText email,pswd;
    Button login;

    List<User> users;

    boolean b=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        pswd=findViewById(R.id.pswd);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserService sv = new UserService(Login.this);
                users=sv.findAll();
                for(User user:users){
                    if(user.getEmail().equals(email.getText().toString()) && user.getPassword().equals(pswd.getText().toString())){

                        b=true;
                        break;


                    }else {

                        b=false;
                    }




                }

                if(b){
                    Toast.makeText(getApplicationContext(), "Login succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Password or Email incorrect", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }



            }
        });

    }
    public void onLoginClick(View View){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onLogineClick(View View){
        startActivity(new Intent(this, Rejister.class));
    }
}