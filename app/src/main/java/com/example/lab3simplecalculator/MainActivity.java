package com.example.lab3simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // ---- state added by us ----
    private enum Operator { none, add, minus, multiply, divide, eq }
    private double data1 = 0, data2 = 0;
    private Operator optr = Operator.none;
    private boolean requiresCleaning = false;
    private boolean hasDot = false;

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
    }

    public void onClickNumericalButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);

        // If the previous press was =, start a fresh expression
        if (optr == Operator.eq) {
            optr = Operator.none;
            curText.setText("");
        }

        if (requiresCleaning) {
            requiresCleaning = false;
            curText.setText("");
        }


        if (pressID == R.id.button00) curText.setText(curText.getText() + "0");
        if (pressID == R.id.button01) curText.setText(curText.getText() + "1");
        if (pressID == R.id.button02) curText.setText(curText.getText() + "2");
        if (pressID == R.id.button03) curText.setText(curText.getText() + "3");
        if (pressID == R.id.button04) curText.setText(curText.getText() + "4");
        if (pressID == R.id.button05) curText.setText(curText.getText() + "5");
        if (pressID == R.id.button06) curText.setText(curText.getText() + "6");
        if (pressID == R.id.button07) curText.setText(curText.getText() + "7");
        if (pressID == R.id.button08) curText.setText(curText.getText() + "8");
        if (pressID == R.id.button09) curText.setText(curText.getText() + "9");
        if (pressID == R.id.buttonDot) {
            if (!hasDot) { curText.setText(curText.getText() + "."); hasDot = true; }
            else { curText.setText("ERROR"); } // not sure why this case is included if you
            // want to add together two floating point numbers... can it not compute? --> there are a couple
            // of things that need to be fixed to make that work, but I haven't been asked to do that lol
        }

    }

    public void onClickFunctionButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);

        // CE always clears everything
        if (pressID == R.id.buttonCE) {
            optr = Operator.none; curText.setText("");
            data1 = 0; data2 = 0; requiresCleaning = false;
            return;
        }

        String dataText = curText.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;

        if (optr == Operator.none) {
            data1 = numberVal; requiresCleaning = true;
            if (pressID == R.id.buttonAdd) optr = Operator.add;
            if (pressID == R.id.buttonSub) optr = Operator.minus;
            if (pressID == R.id.buttonMult) optr = Operator.multiply;
            if (pressID == R.id.buttonDiv) optr = Operator.divide;
        } else {
            data2 = numberVal;
            double result = 0;
            switch (optr) {
                case add: result = data1 + data2; break;
                case minus: result = data1 - data2; break;
                case multiply: result = data1 * data2; break;
                case divide: result = data1 / data2; break;
            }
            data1 = result; optr = Operator.none;
            curText.setText(String.valueOf(result));
        }

    }

//    // the onClick for the 7 button
//    public void onClickB7(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "7");
//    }
//
//    // the onClick for the 8 button
//    public void onClickB8(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "8");
//    }
//
//    // the onClick for the 9 button
//    public void onClickB9(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "9");
//    }
//
//    // the onClick for the + button
//    public void onClickBAdd(View view) {
//        optr = Operator.add;
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        data1 = Double.parseDouble(eText.getText().toString());
//        eText.setText("");
//    }
//
//    // the onClick for the 4 button
//    public void onClickB4(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "4");
//    }
//
//    // the onClick for the 5 button
//    public void onClickB5(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "5");
//    }
//
//    // the onClick for the 6 button
//    public void onClickB6(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "6");
//    }
//
//    // the onClick for the - button
//    public void onClickBSub(View view) {
//        optr = Operator.minus;
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        data1 = Double.parseDouble(eText.getText().toString());
//        eText.setText("");
//    }
//
//    // the onClick for the 1 button
//    public void onClickB1(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "1");
//    }
//
//    // the onClick for the 2 button
//    public void onClickB2(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "2");
//    }
//
//    // the onClick for the 3 button
//    public void onClickB3(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "3");
//    }
//
//    // the onClick for the * button
//    public void onClickBMult(View view) {
//        optr = Operator.multiply;
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        data1 = Double.parseDouble(eText.getText().toString());
//        eText.setText("");
//    }
//
//    // the onClick for the 0 button
//    public void onClickB0(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + "0");
//    }
//
//    // the onClick for the . button
//    public void onClickBDot(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText(eText.getText() + ".");
//    }
//
//    // the onClick for the CE button
//    public void onClickBCE(View view) {
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        eText.setText("");
//    }
//
//    // the onClick for the / button
//    public void onClickBDiv(View view) {
//        optr = Operator.divide;
//        TextView eText = (TextView) findViewById(R.id.resultEdit);
//        data1 = Double.parseDouble(eText.getText().toString());
//        eText.setText("");
//    }
//
//    // the onClick for the = button
//    public void onClickBEq(View view) {
//        if (optr != Operator.none) {
//            TextView eText = (TextView) findViewById(R.id.resultEdit);
//            data2 = Double.parseDouble(eText.getText().toString());
//            double result = 0;
//
//            if (optr == Operator.add)   result = data1 + data2;
//            else if (optr == Operator.minus) result = data1 - data2;
//            else if (optr == Operator.multiply) result = data1 * data2;
//            else if (optr == Operator.divide)   result = data1 / data2;
//
//            optr = Operator.none;
//            data1 = result;
//
//            if ((result - (int) result) != 0) {
//                eText.setText(String.valueOf(result));
//            } else {
//                eText.setText(String.valueOf((int) result));
//            }
//        }
//    }
}
