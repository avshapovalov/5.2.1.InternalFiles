package com.example.a521internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText loginText;
    private TextView passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginText = findViewById(R.id.loginText);
        passwordText = findViewById(R.id.passwordText);
    }

    public void clickToCheck(View view) {
        try {
            FileInputStream fileInputStreamLogin = openFileInput("loginfile");
            InputStreamReader isrlogin = new InputStreamReader(fileInputStreamLogin);
            BufferedReader loginReader = new BufferedReader(isrlogin);
            String lineLogin = loginReader.readLine();
            StringBuilder loginBuilder = new StringBuilder();

            FileInputStream fileInputStreamPassword = openFileInput("passwordfile");
            InputStreamReader isrpassword = new InputStreamReader(fileInputStreamPassword);
            BufferedReader passwordReader = new BufferedReader(isrpassword);
            String linePassword = passwordReader.readLine();
            StringBuilder passwordBuilder = new StringBuilder();

            if (lineLogin.toString().equals(loginText.getText().toString()) && linePassword.toString().equals(passwordText.getText().toString())){
                Toast.makeText(getApplicationContext(), "Авторизация прошла успешно", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Неверные логин или пароль", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickToRegister(View view) {
        String loginToFile = loginText.getText().toString();
        String passwordToFile = passwordText.getText().toString();
        if (loginToFile.isEmpty()){
            Toast.makeText(getApplicationContext(), "Требуется указать логин!", Toast.LENGTH_SHORT).show();
            return;
        } else if (passwordToFile.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Требуется ввести пароль!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileOutputStream fileOutputStreamLogin = openFileOutput("loginfile", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriterLogin = new OutputStreamWriter(fileOutputStreamLogin);
            BufferedWriter bwlogin = new BufferedWriter(outputStreamWriterLogin);
            bwlogin.write(loginToFile);
            bwlogin.close();

            FileOutputStream fileOutputStreamPassword = openFileOutput("passwordfile", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriterPassword = new OutputStreamWriter(fileOutputStreamPassword);
            BufferedWriter bwpassword = new BufferedWriter(outputStreamWriterPassword);
            bwpassword.write(passwordToFile);
            bwpassword.close();

            Toast.makeText(getApplicationContext(), "Данные сохранены", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
