package com.saurabh.tip_split;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;


public class MainActivity extends AppCompatActivity {

    RadioGroup  radioGroup;
    RadioButton r1,r2,a1,a2,a3,a4;
    EditText n1,n2;
    TextView t1,t2,t3,t4;
    double num,total;
    Button b1,b2;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.radioGroup);
        n1=findViewById(R.id.n1);
        t1=findViewById(R.id.tip);
        t2=findViewById(R.id.total);
        n2=findViewById(R.id.n2);
        t3=findViewById(R.id.totalpp);
        t4=findViewById(R.id.over);
        a1=findViewById(R.id.r1);
        a2=findViewById(R.id.r2);
        a3=findViewById(R.id.r3);
        a4=findViewById(R.id.r4);


        r1=radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                r2=radioGroup.findViewById(i);
                boolean isChecked=r2.isChecked();
                total=0.0;
                double k;
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                if(isChecked){

                    if(TextUtils.isEmpty(n1.getText().toString()))
                    {
                        n1.setError("Please Enter Value");
                        a1.setChecked(false);
                        a2.setChecked(false);
                        a3.setChecked(false);
                        a4.setChecked(false);

                    }
                    else {
                        try {


                            num = Double.parseDouble(n1.getText().toString());

                        }
                        catch (NumberFormatException e){

                            return;

                        }
                        k = num;
                        switch (i) {
                            case R.id.r1:
                                num = num * 0.12;
                                break;
                            case R.id.r2:
                                num = num * 0.15;
                                break;
                            case R.id.r3:
                                num = num * 0.18;
                                break;
                            case R.id.r4:
                                num = num * 0.2;
                                break;
                        }
                        String num1=String.format("%.2f", num);

                        num = Double.parseDouble(num1);
                        total = k + num;
                        String total1=String.format("%.2f", total);


                        t1.setText("$" + num1);
                        t2.setText("$" + total1);

                    }

            }
            }
        });
        b1=findViewById(R.id.go);
        b2=findViewById(R.id.clear);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(n2.getText().toString()))
                {
                    n2.setError("Please Enter Valid Input");

                }
                else {
                    hideKey(view);

                    n = Integer.parseInt(n2.getText().toString());
                    if(n<1){
                        n2.setError("Please Enter minimum 1");
                        return;
                    }

                    double l = 0.0;
                    l = (double)Math.ceil(total*100 / n)/100;
                    String l1=String.format("%.2f", l);

                    t3.setText("$" + l1);
                    double e = Math.abs(total - (l * n));
                    String e1=String.format("%.2f", e);

                    t4.setText("$" + e1);
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                n1.setText("");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                n2.setText("");
                t4.setText("");
                total=0.0;
                num=0.0;
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);


            }
        });




    }
    private void hideKey(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}