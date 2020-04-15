package com.example.lab3_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, View.OnKeyListener{

    EditText textName, textUsername, textPassword, textPasswordConfirm;
    Button buttonSignUp, buttonCancel;
    TextView textError;

    public static final String APP_PREFERENCES = "accounts";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_USERNAME = "Username";
    public static final String APP_PREFERENCES_PASSWORD = "Password";



    SharedPreferences account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        account = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); //create and init

        buttonSignUp = (Button) view.findViewById(R.id.signUp);
        buttonSignUp.setOnClickListener(this);

        buttonCancel = (Button) view.findViewById(R.id.signUpCancel);
        buttonCancel.setOnClickListener(this);

        textUsername = (EditText) view.findViewById(R.id.username);

        textPassword = (EditText) view.findViewById(R.id.password);

        textName = (EditText) view.findViewById(R.id.name);

        textPasswordConfirm = (EditText) view.findViewById(R.id.passwordConfirm);
        textError = (TextView) view.findViewById(R.id.textErrorReg);


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

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                EditText editName = getView().findViewById(R.id.name);
                String strName = editName.getText().toString();

                EditText editUserName = getView().findViewById(R.id.username);
                String strUserName = editUserName.getText().toString();

                EditText editPassword = getView().findViewById(R.id.password);
                String strPassword = editPassword.getText().toString();

                EditText editPasswordConfirm = getView().findViewById(R.id.passwordConfirm);
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

                                Fragment logFragment = new LoginFragment();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.MainFragment, logFragment);
                                ft.commit();
                            }
                        } else{
                            textError.setText("Passwords do not match.");
                        }
                    }
                }
                break;
            case R.id.signUpCancel:
                Fragment logFragment = new LoginFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, logFragment);
                ft.commit();
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
