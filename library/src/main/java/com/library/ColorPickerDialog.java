package com.library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by SSL-D on 2016-10-05.
 */

public class ColorPickerDialog extends Dialog implements SeekBar.OnSeekBarChangeListener{
    private static String TAG = ColorPickerDialog.class.getSimpleName();
    private Context context;

    private FrameLayout frameColorPicker;
    private FrameLayout frameColorBox;
    private FrameLayout frameBorderColor;
    private FrameLayout frameBackgrounTvColor;
    private TextView tvColor;

    private SeekBar seekBarColorRed;
    private SeekBar seekBarColorGreen;
    private SeekBar seekBarColorBlue;

    private EditText etColorRed;
    private EditText etColorGreen;
    private EditText etColorBlue;

    private FrameLayout btnGetColor;

    private String mColorR = "00";
    private String mColorG = "00";
    private String mColorB = "00";

    private String mColor = "#000000";

    private String mComplementaryColorR = "00";
    private String mComplementaryColorG = "00";
    private String mComplementaryColorB = "00";

    private OnColorPickerButtonClickListener mOnColorPickerButtonClickListener;

    public ColorPickerDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ColorPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected ColorPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public void setOnColorPickerButtonClickListener(OnColorPickerButtonClickListener onColorPickerButtonClickListener) {
        mOnColorPickerButtonClickListener = onColorPickerButtonClickListener;
        btnGetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnColorPickerButtonClickListener.onClick(view);
                hide();
            }
        });
    }

    public String getColor(){
        return mColor;
    }

    public String getColorR(){
        return mColorR;
    }

    public String getColorG(){
        return mColorG;
    }

    public String getColorB(){
        return mColorB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_color_picker);
        frameColorPicker =(FrameLayout)findViewById(R.id.frame_color_picker);
        frameColorBox = (FrameLayout)findViewById(R.id.frame_color_box);
        frameBorderColor = (FrameLayout)findViewById(R.id.frame_border_color);
        frameBackgrounTvColor = (FrameLayout)findViewById(R.id.frame_background_tv_color);
        tvColor = (TextView)findViewById(R.id.tv_color);

        seekBarColorRed = (SeekBar) findViewById(R.id.seekbar_color_red);
        seekBarColorGreen = (SeekBar) findViewById(R.id.seekbar_color_green);
        seekBarColorBlue = (SeekBar) findViewById(R.id.seekbar_color_blue);

        seekBarColorRed.setOnSeekBarChangeListener(this);
        seekBarColorGreen.setOnSeekBarChangeListener(this);
        seekBarColorBlue.setOnSeekBarChangeListener(this);

        etColorRed = (EditText)findViewById(R.id.et_color_red);
        etColorGreen = (EditText)findViewById(R.id.et_color_green);
        etColorBlue = (EditText)findViewById(R.id.et_color_blue);

        btnGetColor = (FrameLayout)findViewById(R.id.btn_get_color);

        long iColorR = Long.parseLong(mColorR,16);
        long iColorG = Long.parseLong(mColorG,16);
        long iColorB = Long.parseLong(mColorB,16);

        seekBarColorRed.setProgress((int)iColorR);
        seekBarColorGreen.setProgress((int)iColorG);
        seekBarColorBlue.setProgress((int)iColorB);

        etColorRed.setText(String.valueOf(iColorR));
        etColorGreen.setText(String.valueOf(iColorG));
        etColorBlue.setText(String.valueOf(iColorB));

        etColorRed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    seekBarColorRed.setProgress(Integer.valueOf(s.toString()));
                }
            }
        });

        etColorGreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    seekBarColorGreen.setProgress(Integer.valueOf(s.toString()));
                }
            }
        });

        etColorBlue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    seekBarColorBlue.setProgress(Integer.valueOf(s.toString()));
                }
            }
        });

        initialize();
    }

    private void initialize(){
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        lpWindow.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lpWindow.width = (int)(width*0.7);
        getWindow().setAttributes(lpWindow);

        frameColorBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int)(width*0.5)));
    }

    private void makeColor(){
        mColor = "#" + mColorR + mColorG + mColorB;
        String complementaryColor = "#" + mComplementaryColorR + mComplementaryColorG + mComplementaryColorB;
        refreshColorPicker(mColor,complementaryColor);
    }

    private void refreshColorPicker(String color, String complementaryColore){
        tvColor.setText(color);
        tvColor.setTextColor(Color.parseColor(complementaryColore));
        frameBackgrounTvColor.setBackgroundColor(Color.parseColor(color));
        frameColorBox.setBackgroundColor(Color.parseColor(color));
        frameBorderColor.setBackgroundColor(Color.parseColor(complementaryColore));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int i = seekBar.getId();
        if (i == R.id.seekbar_color_red) {
            etColorRed.setText(String.valueOf(progress));
            etColorRed.setSelection(etColorRed.length());
            mColorR = Integer.toHexString(progress);
            mComplementaryColorR = Integer.toHexString(seekBar.getMax() - progress);
            if (progress < 16) {
                mColorR = "0" + mColorR;
            }
            if (seekBar.getMax() - progress < 16) {
                mComplementaryColorR = "0" + mComplementaryColorR;
            }

        } else if (i == R.id.seekbar_color_green) {
            etColorGreen.setText(String.valueOf(progress));
            etColorGreen.setSelection(etColorGreen.length());
            mColorG = Integer.toHexString(progress);
            mComplementaryColorG = Integer.toHexString(seekBar.getMax() - progress);
            if (progress < 16) {
                mColorG = "0" + mColorG;
            }
            if (seekBar.getMax() - progress < 16) {
                mComplementaryColorG = "0" + mComplementaryColorG;
            }

        } else if (i == R.id.seekbar_color_blue) {
            mColorB = Integer.toHexString(progress);
            etColorBlue.setText(String.valueOf(progress));
            etColorBlue.setSelection(etColorBlue.length());
            mComplementaryColorB = Integer.toHexString(seekBar.getMax() - progress);
            if (progress < 16) {
                mColorB = "0" + mColorB;

            }
            if (seekBar.getMax() - progress < 16) {
                mComplementaryColorB = "0" + mComplementaryColorB;
            }

        } else {
        }
        makeColor();
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
