package com.example.jamieolsen.realcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initElements();
        initListeners();
        initStringBuilder();        
    }

    private void initStringBuilder() {
        sb = new StringBuilder();
        sbEquation= new StringBuilder();
    }

    private void initListeners() {

        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEquationStringBuilder();
                clearStringBuilder();
                textEquation.setText("");
                textResult.setText("0.0");
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textResult.setText("0.0");
                clearStringBuilder();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = sb.toString();
                if(text.length()>0){
                    String sub = text.substring(0,text.length()-1);
                    if(sub.length()>0){
                        textResult.setText(sub);
                        sb.replace(0, sb.length(), sub);
                    }else{
                        sb.replace(0,sb.length(),"");
                        textResult.setText("0.0");
                    }
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sb.append(9);
                    textResult.setText(sb.toString());
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(8);
                textResult.setText(sb.toString());
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(7);
                textResult.setText(sb.toString());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(6);
                textResult.setText(sb.toString());
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(5);
                textResult.setText(sb.toString());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(4);
                textResult.setText(sb.toString());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(3);
                textResult.setText(sb.toString());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(2);
                textResult.setText(sb.toString());
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(1);
                textResult.setText(sb.toString());
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(0);
                textResult.setText(sb.toString());
            }
        });
        neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.append(".");
                textResult.setText(sb.toString());
            }
        });
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEquationText(" + ");
            }
        });
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEquationText(" - ");
            }
        });
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEquationText(" * ");
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEquationText(" \\ ");
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbEquation.append(sb.toString());
                double e = eval(sbEquation.toString());
                textResult.setText(Double.toString(e));
                clearStringBuilder();
                clearEquationStringBuilder();
                clearEquation();
            }
        });
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
        sbEquation.append(sb.toString());
        sbEquation.append(operator);
        textEquation.setText(sbEquation.toString());
        textResult.setText("0.0");
        clearStringBuilder();
    }


    private void initElements() {        ce = (Button) findViewById(R.id.btnCE);
        clear = (Button) findViewById(R.id.btnC);
        back = (Button) findViewById(R.id.btnBack);
        divide = (Button) findViewById(R.id.btnDivision);
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
    public static double eval(final String str) {
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
