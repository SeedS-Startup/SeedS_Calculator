package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;

public class Table extends Mode {
    String fLine="";
    String sLine="";
    ButtonInput bi=new ComplexButton(activity);
    static int previousButton=0;
    static String function="";
    static double start=0;
    static double end=0;
    static double step=0;
    static int equalPressed=0;
    ArrayList<String> answer=new ArrayList<>();

    public Table(Activity _activity) {
        super(_activity);
        fLine="$$f(x)=|$$";
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
                if(equalPressed==0){
                    function="";
                    int st=0;
                    int en=0;
                    int cur=0;
                    String text=fMathView.getText();
                    for(int i=1;i<text.length();i++){
                        if(text.toCharArray()[i]=='='){
                            st=i+1;
                        }
                        if(text.toCharArray()[i]=='$'){
                            en=i-1;
                        }
                        if(text.toCharArray()[i]=='|'){
                            cur=i;
                        }

                    }
                    if(cur<en && cur>=st){
                        function=text.substring(st,cur)+text.substring(cur+1,en);

                    }
                    else{
                        function=text.substring(st,en);
                    }
                    fMathView.setText("$$start?|$$");
                    sMathView.setText(Double.toString(start));
                    fLine=fMathView.getText();
                    equalPressed++;

                }
                else if(equalPressed==1){
                    start=0;
                    int st=0;
                    int en=0;
                    int cur=0;
                    String text=fMathView.getText();
                    for(int i=1;i<text.length();i++){
                        if(text.toCharArray()[i]=='?'){
                            st=i+1;
                        }
                        if(text.toCharArray()[i]=='$'){
                            en=i-1;
                        }
                        if(text.toCharArray()[i]=='|'){
                            cur=i;
                        }

                    }
                    if(cur<en && cur>=st){
                        start=Double.parseDouble(text.substring(st,cur)+text.substring(cur+1,en));

                    }
                    else{
                        start=Double.parseDouble(text.substring(st,en));
                    }
                    fMathView.setText("$$end?|$$");
                    sMathView.setText(Double.toString(end));
                    fLine=fMathView.getText();
                    equalPressed++;
                }
                else if(equalPressed==2){
                    end=0;
                    int st=0;
                    int en=0;
                    int cur=0;
                    String text=fMathView.getText();
                    for(int i=1;i<text.length();i++){
                        if(text.toCharArray()[i]=='?'){
                            st=i+1;
                        }
                        if(text.toCharArray()[i]=='$'){
                            en=i-1;
                        }
                        if(text.toCharArray()[i]=='|'){
                            cur=i;
                        }

                    }
                    if(cur<en && cur>=st){
                        end=Double.parseDouble(text.substring(st,cur)+text.substring(cur+1,en));

                    }
                    else{
                        end=Double.parseDouble(text.substring(st,en));
                    }
                    fMathView.setText("$$step?|$$");
                    sMathView.setText(Double.toString(step));
                    fLine=fMathView.getText();
                    equalPressed++;
                }
                else if(equalPressed==3){
                    step=0;
                    int st=0;
                    int en=0;
                    int cur=0;
                    String text=fMathView.getText();
                    for(int i=1;i<text.length();i++){
                        if(text.toCharArray()[i]=='?'){
                            st=i+1;
                        }
                        if(text.toCharArray()[i]=='$'){
                            en=i-1;
                        }
                        if(text.toCharArray()[i]=='|'){
                            cur=i;
                        }

                    }
                    if(cur<en && cur>=st){
                        step=Double.parseDouble(text.substring(st,cur)+text.substring(cur+1,en));

                    }
                    else{
                        step=Double.parseDouble(text.substring(st,en));
                    }
                    equalPressed++;
                }
                if(equalPressed==4){
                    MathOperations mo=new MathOperations();
                    function=mo.handleInputString(function);
                    System.out.println(function);
                    Function f=new Function("f",function,"x");
                    String tempAnswer="$$0 |   X   |   Y   |";
                    answer.add(tempAnswer);
                    for(int i=0;start+step*i<=end;i++){
                        tempAnswer="$$"+Integer.toString(i+1)+" |"+Double.toString(start+step*i)+"|"+Double.toString(f.calculate(start+step*i))+"|$$";
                        answer.add(tempAnswer);
                    }
                    String finalAnswer="";
                    for(String st:answer){
                        System.out.println(st);
                        finalAnswer+=st+" ";
                    }
                    // input.setText(finalAnswer);
                }
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
