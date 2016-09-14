package com.example.jamie.myportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button popularMoviesButton;
    Button stockHawkButton;
    Button buildItBigger;
    Button materialAppButton;
    Button goUbiquitousButton;
    Button capstoneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
        initListeners();
    }

    private void initListeners() {
        popularMoviesButton.setOnClickListener(this);
        stockHawkButton.setOnClickListener(this);
        buildItBigger.setOnClickListener(this);
        materialAppButton.setOnClickListener(this);
        capstoneButton.setOnClickListener(this);
        goUbiquitousButton.setOnClickListener(this);
    }

    private void initButtons() {
        popularMoviesButton = (Button) findViewById(R.id.bPopularMoviesButton);
        stockHawkButton  = (Button) findViewById(R.id.bStockHawkButton);
        buildItBigger = (Button) findViewById(R.id.bBuildItBiggerButton);
        materialAppButton = (Button) findViewById(R.id.bMaterialAppButton);
        capstoneButton = (Button) findViewById(R.id.bCapstoneButton);
        goUbiquitousButton = (Button) findViewById(R.id.bGoUbiquitousButton);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Toast message = null;
        switch (id){
            case R.id.bPopularMoviesButton:
                message.makeText(this, "This is the popular movie project", Toast.LENGTH_LONG).show();
                break;
            case R.id.bStockHawkButton:
                message.makeText(this, "This is the stock hawk project", Toast.LENGTH_LONG).show();
                break;
            case R.id.bBuildItBiggerButton:
                message.makeText(this, "This is the build it bigger project", Toast.LENGTH_LONG).show();
                break;
            case R.id.bMaterialAppButton:
                message.makeText(this, "This is the material app project", Toast.LENGTH_LONG).show();
                break;
            case R.id.bCapstoneButton:
                message.makeText(this, "This is the capstone project", Toast.LENGTH_LONG).show();
                break;
            case R.id.bGoUbiquitousButton:
                message.makeText(this, "This is the go ubiquitous project", Toast.LENGTH_LONG).show();
                break;
        }
        
    }
}
