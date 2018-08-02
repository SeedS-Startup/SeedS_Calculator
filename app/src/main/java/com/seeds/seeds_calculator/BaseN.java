package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.mariuszgromada.math.mxparser.Expression;

public class BaseN extends Mode {
    String fLine="";
    String sLine="";
    ButtonInput bi=new BaseNButton(activity);
    static int previousButton=0;

    public BaseN(Activity _activity) {
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
                String base=bi.getBase();
                String output="";
                switch (base){
                    case("Dec"):
                        output=output((int)expression.calculate(),10);
                        break;
                    case("Hex"):
                        output=output((int)expression.calculate(),16);
                        break;
                    case("Bin"):
                        output=output((int)expression.calculate(),2);
                        break;
                    case("Oct"):
                        output=output((int)expression.calculate(),8);
                        break;
                }
                sMathView.setText(base+": "+output);
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
    public String output(int input,int base){
        String output="";
        if(input<base){
            output=Integer.toString(input);
            return output;
        }
        while(input>=base){
            int remain=input%base;
            output=Integer.toString(remain)+output;
            input=input/base;
        }
        output=Integer.toString(input)+output;
        return output;
    }
}
