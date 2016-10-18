package com.testapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.library.ColorPickerDialog;
import com.library.OnColorPickerButtonClickListener;


public class MainActivity extends AppCompatActivity {
  FrameLayout button;
  FrameLayout background;
  ColorPickerDialog colorPickerDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    button = (FrameLayout) findViewById(R.id.button);
    background = (FrameLayout) findViewById(R.id.activity_main);
    colorPickerDialog = new ColorPickerDialog(this);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        colorPickerDialog.show();
        colorPickerDialog.setOnColorPickerButtonClickListener(new OnColorPickerButtonClickListener() {
          @Override
          public void onClick(View view) {
            background.setBackgroundColor(Color.parseColor(colorPickerDialog.getColor()));
          }
        });
      }
    });
  }
}
