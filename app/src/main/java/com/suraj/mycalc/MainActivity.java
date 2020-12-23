package com.suraj.mycalc;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ast.Scope;

import com.faendir.rhino_android.RhinoAndroidHelper;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bPlus, bMinus, bDiv, bMulti, bPercent, bEquals, b0, b00, clearButton;
    ImageButton bBackSpace;
    TextView textViewEquation, textViewAns;
    String equation = "", num = "";
    double n1 = 0, ans = 0;

    //ArrayList operands = new ArrayList<Integer>();
    Vector<Double> opr = new Vector<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.buttonNo1);
        b2 = findViewById(R.id.buttonNo2);
        b3 = findViewById(R.id.buttonNo3);
        b4 = findViewById(R.id.buttonNo4);
        b5 = findViewById(R.id.buttonNo5);
        b6 = findViewById(R.id.buttonNo6);
        b7 = findViewById(R.id.buttonNo7);
        b8 = findViewById(R.id.buttonNo8);
        b9 = findViewById(R.id.buttonNo9);
        b0 = findViewById(R.id.buttonNo0);
        b00 = findViewById(R.id.buttonNo00);
        clearButton = findViewById(R.id.buttonClear);

        bPlus = findViewById(R.id.buttonPlus);
        bMinus = findViewById(R.id.buttonMinus);
        bMulti = findViewById(R.id.buttonMultiply);
        bDiv = findViewById(R.id.buttonDivide);
        bPercent = findViewById(R.id.buttonPercentage);

        bBackSpace = findViewById(R.id.buttonBackspace);
        bEquals = findViewById(R.id.buttonEqual);
        textViewEquation = findViewById(R.id.textViewEquation);
        textViewAns = findViewById(R.id.textViewAns);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                equation = equation + "0";
                settingText();
                solve();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "1";
                settingText();

                solve();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "2";
                settingText();
                solve();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "3";
                settingText();
                solve();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "4";
                settingText();
                solve();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "5";
                settingText();
                solve();
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "6";
                settingText();
                solve();
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "7";
                settingText();
                solve();
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "8";
                settingText();
                solve();
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = equation + "9";
                settingText();
                solve();
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //n1 = Integer.parseInt(equation);

                //opr.addElement(n1);
                if (equation.charAt(equation.length() - 1) != '+' && equation.charAt(equation.length() - 1) != '-') {
                    equation = equation + "+";
                }
                settingText();
                //solve();
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //n1 = Integer.parseInt(equation);

                //opr.addElement(n1);
                if (equation.charAt(equation.length() - 1) != '-' && equation.charAt(equation.length() - 1) != '+') {
                    equation = equation + "-";
                }
                settingText();
                //solve();
            }
        });

        bMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (equation.charAt(equation.length() - 1) != '×' && equation.charAt(equation.length() - 1) != '÷') {
                    equation = equation + "×";
                }
                settingText();
                //solve();
            }
        });

        bEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = "";
                num = "";
                n1 = 0;
                ans = 0;

                textViewAns.setText("0");
                textViewEquation.setText("0");
            }
        });

        textViewAns.setText("0");
        textViewEquation.setText("0");

    }

    public void settingText() {
        textViewEquation.setText(equation);
    }

    public void solve() {

        String input = equation;

        input = input.replaceAll("×", "*");
        input = input.replaceAll("%", "/100");

        //process = process.replaceAll("×","*");
        //process = process.replaceAll("%","/100");
        input = input.replaceAll("÷", "/");

        if (equation.charAt(equation.length() - 1) == '+') {
            input = input.substring(0, input.length() - 1);
        }

        Context rhino = Context.enter();

        rhino.setOptimizationLevel(-1);

        String finalAns = "";

        try {
            Scriptable scriptable = rhino.initStandardObjects();
            finalAns = rhino.evaluateString(scriptable, input, "javascript", 1, null).toString();

        } catch (Exception e) {
            finalAns = "Error";
        }

        for (int i = 0; i < finalAns.length(); i++) {
            if (finalAns.charAt(i) == '.') {
                if ((finalAns.length() - 1) - i == 1) {
                    if (finalAns.charAt(i + 1) == '0') {
                        finalAns = finalAns.substring(0, i);
                    }
                }
                break;
            }
        }

        textViewAns.setText(finalAns);


        /*double n = 0;
        opr.clear();
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) != '+') {
                n = (n * 10) + (equation.charAt(i) - 48);
            } else {
                opr.addElement(n);
                n = 0;
            }
        }

        if (n > 0) {
            opr.addElement(n);
        }
        n = 0;
        ans = 0;
        for (int i = 0; i < opr.size(); i++) {
            ans = ans + opr.get(i);
        }

        String a = Double.toString(ans);
        ans = 0;

        for(int i=0; i<a.length(); i++){
            if(a.charAt(i)=='.'){
                if((a.length()-1) - i==1){
                    if(a.charAt(i+1)=='0'){
                        a = a.substring(0, i);
                    }
                }
                break;
            }
        }
        textViewAns.setText(a);*/
    }

}