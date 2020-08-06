package com.roomdatabaseexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.roomdatabaseexample.R;
import com.roomdatabaseexample.room.db.AppExecutors;
import com.roomdatabaseexample.room.db.UserDatabase;
import com.roomdatabaseexample.room.entity.UserDetaill;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username,et_address,et_contact,et_email,et_password;
    private Button btn_register;
    private UserDatabase userDatabase;
    private String username, address, contact, email, password;
    private String USER_ID = "user_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase = UserDatabase.getInstance(RegisterActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //method calls
        init();
        onClick();
        //method calls
    }

    private void onClick() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterIntoDatabase();
            }
        });
    }

    private void init() {
        et_username = findViewById(R.id.et_username);
        et_address = findViewById(R.id.et_address);
        et_contact = findViewById(R.id.et_contact);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
    }

    private void enterIntoDatabase(){
        username = et_username.getText().toString();
        address = et_address.getText().toString();
        contact = et_contact.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        final UserDetaill detaill = new UserDetaill();
        detaill.setUsername(username);
        detaill.setAddress(address);
        detaill.setContact(contact);
        detaill.setEmail(email);
        detaill.setPassword(password);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDatabase.UserDao().insertId(detaill);
            }
        });

        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}