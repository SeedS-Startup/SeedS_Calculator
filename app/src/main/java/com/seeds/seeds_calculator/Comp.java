package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.mariuszgromada.math.mxparser.Expression;

public class Comp extends Mode{
    String fLine="";
    String sLine="";
    ButtonInput bi=new Pure(activity);
    static int previousButton=0;

    public Comp(Activity _activity) {
        super(_activity);
        fLine="$$|$$";
        fMathView.setText(fLine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.but4):
                Intent intent = new Intent(super.activity.getBaseContext(),Start.class);
                intent.putExtra("FROM_Class", "Mode");
                super.activity.getBaseContext().startActivity(intent);
                break;
            case(R.id.but47):
                MathOperations operate=new MathOperations();
                sLine=operate.handleInputString(fMathView.getText());
                Expression expression=new Expression(sLine);
                sMathView.setText(Double.toString(expression.calculate()));
                break;
            case R.id.but5:
            case R.id.but32:
                fLine="$$|$$";
                sLine="";
                fMathView.setText(fLine);
                sMathView.setText(sLine);
                break;
            default:
                fLine=bi.getOutput(v,previousButton,activity,fMathView.getText());
                fMathView.setText(fLine);
                previousButton=v.getId();


        }

    }
}
