package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //загр. разметки

        int[] numberIds = new int[] {
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine,
                R.id.doobleZero,
                R.id.point,
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.sin,
                R.id.cos,
                R.id.division,
                R.id.bktOpen,
                R.id.bktClose,
        };

        int[] actionsIds = new int[] {
                R.id.equals,
                R.id.backspace,
                R.id.AC,
                R.id.percent,
                R.id.MMinus,
                R.id.MPlus,
                R.id.MC,
                R.id.MR
        };

        text = findViewById(R.id.text);

        calculator = new CalculatorModel();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                text.setText(calculator.getText());
            }
        };

        for(int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for(int i = 0; i < actionsIds.length; i++ ){
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnclickListener);
        }


    }
}
