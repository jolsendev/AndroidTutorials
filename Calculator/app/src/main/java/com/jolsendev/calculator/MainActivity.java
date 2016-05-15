package com.jolsendev.calculator;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText operand1;
    private EditText operand2;
    private Button addition;
    private Button subtraction;
    private Button multiplication;
    private Button division;
    private Button clear;
    private TextView textResult;
    private double oper1;
    private double oper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        operand1 = (EditText) findViewById(R.id.editOperand1);
        operand2 = (EditText) findViewById(R.id.editOperand2);
        addition = (Button) findViewById(R.id.btnAddition);
        subtraction = (Button) findViewById(R.id.btnSubtraction);
        multiplication = (Button) findViewById(R.id.btnMultiplication);
        division = (Button)findViewById(R.id.btnDivision);
        textResult = (TextView)findViewById(R.id.textResult);
        clear = (Button) findViewById(R.id.btnClear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand1.setText("");
                operand2.setText("");
                textResult.setText("0.0");
                operand1.requestFocus();
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operand1.getText().length() > 0 && operand2.getText().length() > 0){
                    oper1 = Double.parseDouble(operand1.getText().toString());
                    oper2 = Double.parseDouble(operand2.getText().toString());

                    double theResult = oper1 + oper2;
                    textResult.setText(Double.toString(theResult));
                }else{
                    Toast.makeText(MainActivity.this, "Please enter numbers to compute", Toast.LENGTH_LONG).show();
                }

            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(operand1.getText().length() > 0 && operand2.getText().length() > 0){
                    oper1 = Double.parseDouble(operand1.getText().toString());
                    oper2 = Double.parseDouble(operand2.getText().toString());
                    double theResult = oper1 - oper2;
                    textResult.setText(Double.toString(theResult));
                }else{
                    Toast.makeText(MainActivity.this, "Please enter numbers to compute", Toast.LENGTH_LONG).show();
                }

            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operand1.getText().length() > 0 && operand2.getText().length() > 0){
                    oper1 = Double.parseDouble(operand1.getText().toString());
                    oper2 = Double.parseDouble(operand2.getText().toString());

                    double theResult = oper1 / oper2;
                    textResult.setText(Double.toString(theResult));
                }else{
                    Toast.makeText(MainActivity.this, "Please enter numbers to compute", Toast.LENGTH_LONG).show();
                }

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operand1.getText().length() > 0 && operand2.getText().length() > 0){
                    oper1 = Double.parseDouble(operand1.getText().toString());
                    oper2 = Double.parseDouble(operand2.getText().toString());

                    double theResult = oper1 * oper2;
                    textResult.setText(Double.toString(theResult));
                }else{
                    Toast.makeText(MainActivity.this, "Please enter numbers to compute", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
