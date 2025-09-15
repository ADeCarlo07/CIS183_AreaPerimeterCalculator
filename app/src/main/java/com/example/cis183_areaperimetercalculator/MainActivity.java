package com.example.cis183_areaperimetercalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin._Assertions;

public class MainActivity extends AppCompatActivity {

    //rectangle and square:
    //area - length and width
    //perimeter - length and width

    //circle:
    //area - radius
    //circumference - radius

    //triangle:
    //area - base and height
    //perimeter side a and side b and side c

    EditText et_j_length;
    EditText et_j_width;

    ConstraintLayout cons_j_squareRefactView;
    Spinner sp_j_shapes;
    TextView tv_j_areaPerimeter;

    public ArrayAdapter spinnderAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_j_length = findViewById(R.id.et_v_length);
        et_j_width = findViewById(R.id.et_v_width);
        cons_j_squareRefactView = findViewById(R.id.cons_v_squareRefactView);
        sp_j_shapes = findViewById(R.id.sp_v_shapes);
        tv_j_areaPerimeter = findViewById(R.id.tv_v_computedValues);

        //because we are using one spinner (drop down menu) that only contains strings
        //we can use a string array with the build in array adapter to populate
        //the spinner

        //this will populate our spinner
        String[] shapes = new String[]{"Square", "Rectangle", "Circle","Triangle"};

        spinnderAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shapes);

        sp_j_shapes.setAdapter(spinnderAdaptor);

        setupSpinnerChangeListener();
        textChangeListenerSquareRect();
    }

    public void setupSpinnerChangeListener()
    {
        sp_j_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0)
                {
                    //Square
                    showConstraintView(cons_j_squareRefactView);
                    //not showing for circle and triangle
                }
                else if (position == 1)
                {
                    //Rectangle
                    showConstraintView(cons_j_squareRefactView);
                    //not showing for circle and triangle

                }
                else if (position == 2)
                {
                    //Circle

                    //not showing for triangle or rectangle
                    hideConstraintView(cons_j_squareRefactView);
                }
                else if (position == 3)
                {
                    //Triangle

                    //not showing for circle or rectangle
                    hideConstraintView(cons_j_squareRefactView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void hideConstraintView(ConstraintLayout cl)
    {
        cl.setVisibility(View.INVISIBLE);
    }

    public void showConstraintView(ConstraintLayout cl)
    {
        cl.setVisibility(View.VISIBLE);
    }

    public void textChangeListenerSquareRect()
    {
        et_j_width.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });

        et_j_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });
    }

    public void setAreaAndPerimeterSquareRect(String lengthS, String widthS)
    {
        if (lengthS.isEmpty() || widthS.isEmpty())
        {
            return;
        }
        //int length = Integer.parseInt(lengthS);
        double length = Double.parseDouble(lengthS);
        //int width = Integer.parseInt(widthS);
        double width = Double.parseDouble(widthS);

        double area = length * width;
        double perimeter = length + length + width + width;

        tv_j_areaPerimeter.setText("Area = " + area + "\n Perimeter = " + perimeter);
    }
}