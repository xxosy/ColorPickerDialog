package com.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.library.ColorPickerDialog;



public class MainActivity extends AppCompatActivity {
    FrameLayout button;
    ColorPickerDialog colorPickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (FrameLayout)findViewById(R.id.button);
        colorPickerDialog = new ColorPickerDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPickerDialog.show();
            }
        });
    }
}
