package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnKeyListener{

    EditText textName, textUsername, textPassword, textPasswordConfirm;
    Button buttonSignUp, buttonCancel;
    TextView textError;

    public static final String APP_PREFERENCES = "accounts";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_USERNAME = "Username";
    public static final String APP_PREFERENCES_PASSWORD = "Password";



    SharedPreferences account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); //create and init

        buttonSignUp = (Button) findViewById(R.id.signUp);
        buttonSignUp.setOnClickListener(this);

        buttonCancel = (Button) findViewById(R.id.signUpCancel);
        buttonCancel.setOnClickListener(this);

        textUsername = (EditText) findViewById(R.id.username);

        textPassword = (EditText) findViewById(R.id.password);

        textName = (EditText) findViewById(R.id.name);

        textPasswordConfirm = (EditText) findViewById(R.id.passwordConfirm);
        textError = (TextView) findViewById(R.id.textErrorReg);


        View.OnKeyListener enterListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN &&event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    if(textName.hasFocus()){
                        textName.clearFocus();
                        textUsername.requestFocus();
                        return true;
                    }
                    if(textUsername.hasFocus()){
                        textUsername.clearFocus();
                        textPassword.requestFocus();
                        return true;
                    }
                }
                return false;
            }
        };

        textName.setOnKeyListener(enterListener);
        textUsername.setOnKeyListener(enterListener);
        textPassword.setOnKeyListener(enterListener);


        View.OnTouchListener errorListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textError.setText("");
                return false;
            }
        };

        textError.setOnTouchListener(errorListener);

    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.signUp:
                EditText editName = findViewById(R.id.name);
                String strName = editName.getText().toString();

                EditText editUserName = findViewById(R.id.username);
                String strUserName = editUserName.getText().toString();

                EditText editPassword = findViewById(R.id.password);
                String strPassword = editPassword.getText().toString();

                EditText editPasswordConfirm = findViewById(R.id.passwordConfirm);
                String strPasswordConfirm = editPasswordConfirm.getText().toString();

                if(strName.equals("") || strUserName.equals("") || strPassword.equals("") || strPasswordConfirm.equals("")){
                    textError.setText("Fill in all the fields.");
                } else{
                    String valid = isValidPassword(strPassword);
                    if(!valid.equals("")){
                        textError.setText(valid);
                    } else{
                        if(strPassword.equals(strPasswordConfirm)) {
                            if(strUserName.equals(account.getString(APP_PREFERENCES_USERNAME, ""))){
                                textError.setText("This username is already taken.");
                            } else{
                                SharedPreferences.Editor editor = account.edit();
                                editor.putString(APP_PREFERENCES_NAME, strName);
                                editor.putString(APP_PREFERENCES_USERNAME, strUserName);
                                editor.putString(APP_PREFERENCES_PASSWORD, strPassword);
                                editor.apply();
                                finish();
                            }
                        } else{
                            textError.setText("Passwords do not match.");
                        }
                    }
                }
                break;
            case R.id.signUpCancel:
                finish();
                break;
            default:
                break;
        }
    }

    private boolean isValidPasswordNumbers(String password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN_NUMBERS = "[0-9]";
        pattern = Pattern.compile(PASSWORD_PATTERN_NUMBERS);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean isValidPasswordSymbols(String password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN_SYMBOLS = "[@!^#?*&%$=+()]";
        pattern = Pattern.compile(PASSWORD_PATTERN_SYMBOLS);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean isValidPasswordLettersSmall(String password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN_LETTERS_SMALL = "[a-z]";
        pattern = Pattern.compile(PASSWORD_PATTERN_LETTERS_SMALL);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean isValidPasswordLetters(String password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN_LETTERS_SMALL = "[A-Z]+";
        pattern = Pattern.compile(PASSWORD_PATTERN_LETTERS_SMALL);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public String isValidPassword(String password) {
        String result = "";
        if(password.length() < 4 || password.length() > 10){
            result = "Invalid password length";
        }
        if(!isValidPasswordNumbers(password)) {
            result = "The password must contain numbers";
        }
        if(!isValidPasswordLetters(password)) {
            result = "The password must contain capital letters";
        }
        if(!isValidPasswordLettersSmall(password)) {
            result = "The password must contain small letters";
        }
        if(isValidPasswordSymbols(password)) {
            result = "The password must not contain characters @!^#?*&%$=-+()]";
        }
        return result;
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
