package com.syafiq.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etWeight,etHeight;
    Button btnCalculate, btnReset;
    TextView output;
    String calculation, BMIrisk, BMIcategory, BMIrange;

    float bmiconvert1, bmiconvert2; //declaration for 'putFloat'(saving the data process)
    SharedPreferences sharedPref1, sharedPref2;

    DecimalFormat deciForm = new DecimalFormat("###,###.##");
    //create a decimal format object (& import java.text.DecimalFormat)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = (EditText) findViewById(R.id.inWeight);
        etHeight = (EditText) findViewById(R.id.inHeight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        output = (TextView) findViewById(R.id.output);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() { //to make an action after user click Clear Button
                                        @Override
                                        public void onClick(View v) {
                                            v.getId();
                                            etWeight.getText().clear();
                                            etHeight.getText().clear();
                                        }
        });


        sharedPref1 = this.getSharedPreferences("saveinput1", Context.MODE_PRIVATE);
        sharedPref2 = this.getSharedPreferences("saveinput2", Context.MODE_PRIVATE);
        //loading the data (display data)
        bmiconvert1 = sharedPref1.getFloat("saveinput1",0);
        bmiconvert2 = sharedPref2.getFloat("saveinput2",0);

        etWeight.setText(""+bmiconvert1);
        etHeight.setText(""+bmiconvert2);

        btnCalculate.setOnClickListener(this);
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); // ('Res'ource.('menu' folder dir).(filename='menu.xml'),(method variable)

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent); //user click 'about' and go to the about page
                break;

            case R.id.instruction:
                Intent intent1 = new Intent(this, HelpActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override //this is an action process after user click "calculate"
    public void onClick(View v) {

        try {


            double f1 = Double.parseDouble(etWeight.getText().toString());
            double f2 = Double.parseDouble(etHeight.getText().toString());

            double bmi = f1 / Math.pow(f2, 2);

            if (bmi > 0 && bmi <= 18.4) {
                BMIrisk = "Malnutrition Risk";
                BMIcategory = "Underweight";
                BMIrange = "18.4kg and Below";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                BMIrisk = "Low Risk";
                BMIcategory = "Normal Weight";
                BMIrange = "18.5kg - 24.9kg";
            } else if (bmi >= 25 && bmi <= 29.9) {
                BMIrisk = "Enchanced Risk";
                BMIcategory = "Overweight";
                BMIrange = "25.0kg - 29.9kg";
            } else if (bmi >= 30.0 && bmi <= 34.9) {
                BMIrisk = "Medium Risk";
                BMIcategory = "Moderately Obese";
                BMIrange = "30.0kg - 34.9kg";
            } else if (bmi >= 35.0 && bmi <= 39.9) {
                BMIrisk = "High Risk";
                BMIcategory = "Severely Obese";
                BMIrange = "35.0kg - 39.9kg";
            } else if (bmi >= 40) {
                BMIrisk = "Very High Risk";
                BMIcategory = "Very Severely Obese";
                BMIrange = "40kg and above";
            }

            calculation = "Result:\n\nYour BMI: " + deciForm.format(bmi) + "kg" + "\nBMI Category: " + BMIcategory + "\nBMI Range: " + BMIrange + "\nHealth Risk: " + BMIrisk;
            output.setText(calculation);

            //Data Saving Process (Write the data)
            //Variable Declaration for 'putFloat' because there's no 'putDouble' to save the input
            bmiconvert1 = Float.parseFloat(etWeight.getText().toString());
            bmiconvert2 = Float.parseFloat(etHeight.getText().toString());

            SharedPreferences.Editor editor1 = sharedPref1.edit();
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            editor1.putFloat("saveinput1", bmiconvert1);
            editor2.putFloat("saveinput2", bmiconvert2);
            editor1.apply();
            editor2.apply();

        }
        catch(java.lang.NumberFormatException nfe)
        {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
        catch (Exception exp)
        {
            Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Exception", exp.getMessage());
        }
    }
}