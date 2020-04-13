package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import static com.example.calculator.RegisterActivity.APP_PREFERENCES;
import static com.example.calculator.RegisterActivity.APP_PREFERENCES_NAME;
import static com.example.calculator.RegisterActivity.APP_PREFERENCES_PASSWORD;
import static com.example.calculator.RegisterActivity.APP_PREFERENCES_USERNAME;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnKeyListener{

    Button btnLogin;
    Button btnRegister;
    EditText textUsername, textPassword;
    TextView viewUsername, viewPassword, textError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(this);

        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(this);

        textUsername = (EditText) findViewById(R.id.username);

        textPassword = (EditText) findViewById(R.id.password);

        viewPassword = (TextView) findViewById(R.id.viewPassword);
        viewUsername = (TextView) findViewById(R.id.viewUsername);
        textError = (TextView) findViewById(R.id.textError);


        View.OnTouchListener errorListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textError.setText("");
                return false;
            }
        };

        textUsername.setOnTouchListener(errorListener);
        textPassword.setOnTouchListener(errorListener);


        View.OnKeyListener enterListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    textUsername.clearFocus();
                    textPassword.requestFocus();
                    return true;
                }
                return false;
            }
        };
        textUsername.setOnKeyListener(enterListener);
    }



    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.login:
                String tUsername = textUsername.getText().toString();
                String tPassword = textPassword.getText().toString();

                if(tUsername.equals("")) {
                    textError.setText("Enter username");
                } else if(tPassword.equals("")) {
                    textError.setText("Enter password");
                } else {

                    SharedPreferences sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    if (sPref.contains(APP_PREFERENCES_USERNAME)) {
                        String username = sPref.getString(APP_PREFERENCES_USERNAME, "");
                        String password = sPref.getString(APP_PREFERENCES_PASSWORD, "");

                        if (username.equals(tUsername) && password.equals(tPassword)) {
                            textUsername.setText("");
                            textPassword.setText("");
                            textError.setText("");

                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            textError.setText("The account with such username and password is not found");
                        }
                    }
                }
                break;
            case R.id.register:
                Intent intentReg = new Intent(this, RegisterActivity.class);
                startActivity(intentReg);
                break;
            default:
                break;
        }
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
