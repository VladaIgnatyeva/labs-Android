package com.example.lab3_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.lab3_fragments.RegisterFragment.APP_PREFERENCES;
import static com.example.lab3_fragments.RegisterFragment.APP_PREFERENCES_PASSWORD;
import static com.example.lab3_fragments.RegisterFragment.APP_PREFERENCES_USERNAME;


public class LoginFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, View.OnKeyListener{

    Button btnLogin;
    Button btnRegister;
    EditText textUsername, textPassword;
    TextView viewUsername, viewPassword, textError;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = (Button) view.findViewById(R.id.login);
        btnLogin.setOnClickListener(this);

        btnRegister = (Button) view.findViewById(R.id.register);
        btnRegister.setOnClickListener(this);

        textUsername = (EditText) view.findViewById(R.id.username);

        textPassword = (EditText) view.findViewById(R.id.password);

        viewPassword = (TextView) view.findViewById(R.id.viewPassword);
        viewUsername = (TextView) view.findViewById(R.id.viewUsername);
        textError = (TextView) view.findViewById(R.id.textError);


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


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String tUsername = textUsername.getText().toString();
                String tPassword = textPassword.getText().toString();

                if(tUsername.equals("")) {
                    textError.setText("Enter username");
                } else if(tPassword.equals("")) {
                    textError.setText("Enter password");
                } else {
                    SharedPreferences sPref = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    if (sPref.contains(APP_PREFERENCES_USERNAME)) {
                        String username = sPref.getString(APP_PREFERENCES_USERNAME, "");
                        String password = sPref.getString(APP_PREFERENCES_PASSWORD, "");

                        if (username.equals(tUsername) && password.equals(tPassword)) {
                            textUsername.setText("");
                            textPassword.setText("");
                            textError.setText("");

                            Fragment galeryFragment = new Galery();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.MainFragment, galeryFragment);
                            ft.commit();
                        } else {
                            textError.setText("The account with such username and password is not found");
                        }
                    }
                }
                break;
            case R.id.register:
                Fragment regFragment = new RegisterFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, regFragment);
                ft.commit();
                break;
            default:
                break;
        }
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
