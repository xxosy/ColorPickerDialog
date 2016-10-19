package com.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.library.ColorPickerDialog;
import com.library.OnPositiveButtonClickListener;


public class MainActivity extends AppCompatActivity {
  FrameLayout button;
  FrameLayout background;
  ColorPickerDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    button = (FrameLayout) findViewById(R.id.button);
    background = (FrameLayout) findViewById(R.id.activity_main);

    dialog = new ColorPickerDialog.Builder(this)
        .positiveButton("GET")
        .negativeButton("CANCEL")
        .positiveButtonClickListener(new OnPositiveButtonClickListener() {
          @Override
          public void onClick(View view) {

          }
        })
        .build();
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog.show();
      }
    });

  }
}
