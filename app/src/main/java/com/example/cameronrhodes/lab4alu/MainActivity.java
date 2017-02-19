package com.example.cameronrhodes.lab4alu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_BITS = 8;

    //can be made local. Keeping in current scope for no particular reason
    private Button calcButton;
    private Button zeroButton;
    private Button oneButton;

    private CheckBox[] A = new CheckBox[NUM_BITS];
    private CheckBox[] B = new CheckBox[NUM_BITS];
    private CheckBox[] S = new CheckBox[NUM_BITS];
    private CheckBox C;
    private CheckBox OP;

    private boolean[] AIn = new boolean[NUM_BITS];
    private boolean[] BIn = new boolean[NUM_BITS];

    private ALU logicUnit = new ALU(NUM_BITS);


    private void setCheckBoxes(CheckBox[] boxes, boolean[] values) {
        for(int i = 0; i < boxes.length; i++) {
            boxes[i].setChecked(values[i]);
        }
    }

    private void setCheckBoxes(CheckBox[] boxes, boolean val) {
        for (CheckBox box : boxes) {
            box.setChecked(val);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C = (CheckBox) findViewById(R.id.chkCarry);
        OP = (CheckBox) findViewById(R.id.chkOp0);

        calcButton = (Button) findViewById(R.id.btnCalculate);
        calcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for(int i = 0; i < NUM_BITS; i++) {
                    AIn[i] = A[i].isChecked();
                    BIn[i] = B[i].isChecked();
                }

                logicUnit.setInputs(AIn,BIn,OP.isChecked());
                logicUnit.execute();
                setCheckBoxes(S,logicUnit.getSums());
                C.setChecked(logicUnit.getOverflow());
            }
        });

        oneButton = (Button) findViewById(R.id.btnOne);
        oneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setCheckBoxes(A, true);
                setCheckBoxes(B, true);
            }
        });

        zeroButton = (Button) findViewById(R.id.btnZero);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckBoxes(A, false);
                setCheckBoxes(B, false);
            }
        });


        A[0] = (CheckBox) findViewById(R.id.chkA0);
        A[1] = (CheckBox) findViewById(R.id.chkA1);
        A[2] = (CheckBox) findViewById(R.id.chkA2);
        A[3] = (CheckBox) findViewById(R.id.chkA3);
        A[4] = (CheckBox) findViewById(R.id.chkA4);
        A[5] = (CheckBox) findViewById(R.id.chkA5);
        A[6] = (CheckBox) findViewById(R.id.chkA6);
        A[7] = (CheckBox) findViewById(R.id.chkA7);



        B[0] = (CheckBox) findViewById(R.id.chkB0);
        B[1] = (CheckBox) findViewById(R.id.chkB1);
        B[2] = (CheckBox) findViewById(R.id.chkB2);
        B[3] = (CheckBox) findViewById(R.id.chkB3);
        B[4] = (CheckBox) findViewById(R.id.chkB4);
        B[5] = (CheckBox) findViewById(R.id.chkB5);
        B[6] = (CheckBox) findViewById(R.id.chkB6);
        B[7] = (CheckBox) findViewById(R.id.chkB7);



        S[0] = (CheckBox) findViewById(R.id.chkS0);
        S[1] = (CheckBox) findViewById(R.id.chkS1);
        S[2] = (CheckBox) findViewById(R.id.chkS2);
        S[3] = (CheckBox) findViewById(R.id.chkS3);
        S[4] = (CheckBox) findViewById(R.id.chkS4);
        S[5] = (CheckBox) findViewById(R.id.chkS5);
        S[6] = (CheckBox) findViewById(R.id.chkS6);
        S[7] = (CheckBox) findViewById(R.id.chkS7);



    }


}
