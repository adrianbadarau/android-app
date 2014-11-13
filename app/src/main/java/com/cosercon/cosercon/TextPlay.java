package com.cosercon.cosercon;

import  android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Random;

/**
 * Created by adrian.badarau on 11/11/2014.
 */
public class TextPlay extends Activity implements View.OnClickListener {
    ToggleButton passTog;
    EditText input;
    TextView display;
    Button chkCmd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        initializare();

        passTog.setOnClickListener(this);

        chkCmd.setOnClickListener(this);


    }

    private void initializare() {
        chkCmd = (Button) findViewById(R.id.bResults);
        passTog = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommands);
        display = (TextView) findViewById(R.id.tvResults);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bResults:
                String check = input.getText().toString();
                display.setText(check);
                if (check.contentEquals("left")){
                    display.setGravity(Gravity.LEFT);
                }else if (check.contentEquals("center")){
                    display.setGravity(Gravity.CENTER);
                }else if (check.contentEquals("right")){
                    display.setGravity(Gravity.RIGHT);
                }else if (check.contentEquals("blue")){
                    display.setTextColor(Color.BLUE);
                }else if (check.contentEquals("WTF")){
                    Random crazy = new Random();
                    display.setText("WTF !!!!");
                    display.setTextSize(crazy.nextInt(75));
                    display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));
                    switch (crazy.nextInt(3)){
                        case 0:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            display.setText("Nope");
                            break;
                        case 2:
                            display.setTextColor(Color.RED);
                            break;
                    }
                }else {
                    display.setText("I don't know that");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.BLACK);
                }
                break;
            case R.id.tbPassword:
                if (passTog.isChecked()){
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
