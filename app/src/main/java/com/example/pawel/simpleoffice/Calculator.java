package com.example.pawel.simpleoffice;

import android.media.MediaCodec;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Calculator extends AppCompatActivity {

    private TextView _screen;
    private String display = "";
    private String currentOperatior = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _screen = (TextView)findViewById(R.id.TextCalculator);
        _screen.setText(display);

    }

    private  void updateScreen(){
        _screen.setText(display);
    }

    protected void onClickNumber (View v){
        if(result != ""){
            clear();
            updateScreen();
        }
        Button button = (Button) v;
        display += button.getText();
        updateScreen();
    }

    //********
    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '/': return true;
            default: return false;

            }
        }

    public void onClickOperator (View v) {
        if(display == "") return;

        Button button = (Button) v;

        if(result != ""){
            String _display = result;
            clear();
            display = _display;
        }

        if(currentOperatior != ""){
            Log.d("CalcX", ""  + display.charAt(display.length()-1));
            if(isOperator(display.charAt(display.length()-1))) {
                display = display.replace(display.charAt(display.length() - 1), button.getText().charAt(0));
                updateScreen();
                return;
            }
            else {
                getResult();
                display = result;
                result = "";
            }
            currentOperatior = button.getText().toString();
        }
        display += button.getText();
        currentOperatior = button.getText().toString();
        updateScreen();
    }

    private void clear(){
        display = "";
        currentOperatior = "";
        result = "";
    }

    protected void onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "/": try {
                return Double.valueOf(a) / Double.valueOf(b);
            }
            catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
                default: return -1;
        }
    }

    private boolean getResult(){
        if(currentOperatior == "") return false;
        String[] operation = display.split(Pattern.quote(currentOperatior));
        if(operation.length < 2) return false;
        result =  String.valueOf(operate(operation[0], operation[1], currentOperatior));
        return true;
    }

    protected void onClickEqual (View v){
        if(display == "") return;
        if(!getResult()) return;
        _screen.setText(display + "\n" + String.valueOf(result));

    }
}
