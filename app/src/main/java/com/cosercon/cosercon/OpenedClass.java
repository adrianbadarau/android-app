package com.cosercon.cosercon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by adrian.badarau on 11/13/2014.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread,setData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name","Admin");
        String values = getData.getString("list","Hello");
        if (values.contentEquals("Item 1")){
            question.setText(et);
        }
       /* Bundle gotBasket = getIntent().getExtras();
        gotBread = gotBasket.getString("data");
        question.setText(gotBread);*/

    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvData);
        test = (TextView) findViewById(R.id.tvDataResults);
        returnData = (Button) findViewById(R.id.bReturn);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("answer",setData);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbData1:
                setData = "Good";
                break;
            case R.id.rbData2:
                setData = "Eh";
                break;
            case R.id.rbData3:
                setData = "Bad";
                break;
        }
        test.setText(setData);
    }
}
