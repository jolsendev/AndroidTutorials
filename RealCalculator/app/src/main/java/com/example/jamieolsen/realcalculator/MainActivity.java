package com.example.jamieolsen.realcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ce;
    private Button clear;
    private Button back;
    private Button divide;
    private Button nine;
    private Button eight;
    private Button seven;
    private Button six;
    private Button five;
    private Button four;
    private Button three;
    private Button two;
    private Button one;
    private Button zero;
    private Button neg;
    private Button decimal;
    private Button addition;
    private Button subtraction;
    private Button multiplication;
    private Button division;
    private Button equals;
    private TextView textResult;
    private TextView textEquation;
    private StringBuilder sb;
    private StringBuilder sbEquation;
    private String subString;
    private boolean decimalNotSet = true;
    private boolean pressedEqual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initElements();
        initButtonListeners();
        initStringBuilder();        
    }

    private void initStringBuilder() {
        sb = new StringBuilder();
        sbEquation= new StringBuilder();
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnCE:
                clearEquationStringBuilder();
                clearStringBuilder();
                textEquation.setText("");
                textResult.setText("0.0");
                break;
            case R.id.btnC:
                textResult.setText("0.0");
                clearStringBuilder();
                break;
            case R.id.btnBack:
                String text = sb.toString();
                if(text.length()>0){
                    String sub = text.substring(0,text.length()-1);
                    String checkDec = text.substring(text.length()-1,text.length());
                    if(checkDec.equals(".")){decimalNotSet = true;}
                    if(sub.length()>0){
                        textResult.setText(sub);
                        sb.replace(0, sb.length(), sub);
                    }else{
                        sb.replace(0,sb.length(),"");
                        textResult.setText("0.0");
                    }
                }
                break;
            case R.id.btn9:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(9);
                textResult.setText(sb.toString());
                break;
            case R.id.btn8:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(8);
                textResult.setText(sb.toString());
                break;
            case R.id.btn7:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(7);
                textResult.setText(sb.toString());
                break;
            case R.id.btn6:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(6);
                textResult.setText(sb.toString());
                break;
            case R.id.btn5:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(5);
                textResult.setText(sb.toString());
                break;
            case R.id.btn4:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(4);
                textResult.setText(sb.toString());
                break;
            case R.id.btn3:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(3);
                textResult.setText(sb.toString());
                break;
            case R.id.btn2:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(2);
                textResult.setText(sb.toString());
                break;
            case R.id.btn1:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(1);
                textResult.setText(sb.toString());
                break;
            case R.id.btn0:
                if(pressedEqual){
                    clearResult();
                    pressedEqual = false;
                }
                sb.append(0);
                textResult.setText(sb.toString());
                break;
            case R.id.btnNeg:
                if(!textResult.getText().equals("0.0")){
                    if(neg.getText().toString().equals("neg")){
                        sb.insert(0,"-");
                        textResult.setText(sb.toString());
                        neg.setText("pos");
                    }else if (neg.getText() == "pos"){
                        sb.replace(0,1,"");
                        textResult.setText(sb.toString());
                        neg.setText("neg");
                    }
                }
                break;
            case R.id.btnDecimalPoint:
                if(!(sb.toString().indexOf(".")>-1)){
                    sb.append(".");
                    textResult.setText(sb.toString());
                }
                break;
            case R.id.btnAddition:
                setEquationText(" + ");
                break;
            case R.id.btnSubtract:
                setEquationText(" - ");
                break;
            case R.id.btnMultiplication:
                setEquationText(" * ");
                break;
            case R.id.btnDivision:
                setEquationText(" / ");
                break;
            case R.id.btnEquals:
                sbEquation.append(sb.toString());
                double e = eval(sbEquation.toString());
                clearStringBuilder();
                sb.append(Double.toString(e));
                textResult.setText(Double.toString(e));
                clearEquationStringBuilder();
                clearEquation();
                pressedEqual = true;
                break;
            default:
                break;




        }
    }
    private void initButtonListeners() {
        ce.setOnClickListener(this);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        zero.setOnClickListener(this);
        neg.setOnClickListener(this);
        decimal.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        equals.setOnClickListener(this);
    }

    private void clearResult() {
        clearStringBuilder();
        textResult.setText("");
    }
    private void clearEquation(){
        textEquation.setText("");
    }
    private void clearEquationStringBuilder() {
        sbEquation.replace(0, sbEquation.length(), "");
    }

    private void setEquationText(String operator) {
        if(textResult.getText().equals("0.0")){
            sbEquation.append("0.0");
        }else{
            sbEquation.append(sb.toString());
        }

        sbEquation.append(operator);
        textEquation.setText(sbEquation.toString());
        textResult.setText("0.0");
        clearStringBuilder();
    }


    private void initElements() {        ce = (Button) findViewById(R.id.btnCE);
        clear = (Button) findViewById(R.id.btnC);
        back = (Button) findViewById(R.id.btnBack);
        nine = (Button) findViewById(R.id.btn9);
        eight = (Button) findViewById(R.id.btn8);
        seven = (Button) findViewById(R.id.btn7);
        six = (Button) findViewById(R.id.btn6);
        five = (Button) findViewById(R.id.btn5);
        four = (Button) findViewById(R.id.btn4);
        three = (Button) findViewById(R.id.btn3);
        two = (Button) findViewById(R.id.btn2);
        one = (Button) findViewById(R.id.btn1);
        zero = (Button) findViewById(R.id.btn0);
        neg = (Button) findViewById(R.id.btnNeg);
        decimal = (Button) findViewById(R.id.btnDecimalPoint);
        addition = (Button) findViewById(R.id.btnAddition);
        subtraction = (Button) findViewById(R.id.btnSubtract);
        multiplication = (Button) findViewById(R.id.btnMultiplication);
        division = (Button) findViewById(R.id.btnDivision);
        equals = (Button) findViewById(R.id.btnEquals);
        textResult = (TextView) findViewById(R.id.textResult);
        textEquation = (TextView) findViewById(R.id.textEquation);

    }

    private void clearStringBuilder() {
         sb.replace(0, sb.length(), "");
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

    //Taken from google: http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
