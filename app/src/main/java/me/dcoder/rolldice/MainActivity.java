package me.dcoder.rolldice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    TextView txtResult;
    RadioGroup rdgDice;
    CheckBox chkTrue10;
    CheckBox chkRollTwice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        rdgDice = findViewById(R.id.rdgDice);
        chkTrue10 = findViewById(R.id.chkTrue10);
        chkRollTwice = findViewById(R.id.chkRollTwice);

        rdgDice.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        chkTrue10.setVisibility(checkedId == R.id.rbDice10 ? View.VISIBLE : View.GONE);
    }

    public void onRollButtonClick(View view) {
        String result = "";

        int min = 1;
        int max = 0;
        switch (rdgDice.getCheckedRadioButtonId()) {
            case R.id.rbDice4:
                max = 4;
                break;
            case R.id.rbDice6:
                max = 6;
                break;
            case R.id.rbDice8:
                max = 8;
                break;
            case R.id.rbDice10:
                if(chkTrue10.isChecked()){
                    min = 0;
                    max = 9;
                } else {
                    max = 10;
                }
                break;
            case R.id.rbDice12:
                max = 12;
                break;
            case R.id.rbDice20:
                max = 20;
                break;
        }

        int count = chkRollTwice.isChecked() ? 2 : 1;
        for (int i = 0; i < count; i++) {
            if(result.length() != 0) {
                result += " ";
            }
            // SO says Random is more efficient and Math.ramdom() internally uses Random.double()
            result += new Random().nextInt((max + 1) - min) + min;
        }

        txtResult.setText(result);
    }
}
