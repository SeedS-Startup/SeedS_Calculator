package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import org.mariuszgromada.math.mxparser.Expression;
import java.util.ArrayList;
import java.util.List;

public class Eqn extends Mode {
    String fLine="";
    String sLine="";
    ButtonInput bi=new ComplexButton(activity);
    static int previousButton=0;
    int type=0;
    boolean isFirst=true;

    public Eqn(Activity _activity) {
        super(_activity);
        fLine="$$|$$";
        fMathView.setText(fLine);
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("anX+bnY=cn");
        stringList1.add("anX+bnY+cnZ=dn");
        stringList1.add("aX2+bX+c=0");
        stringList1.add("aX3+bX2+cX+d=0");
        bi.showDialogbox(stringList1, activity,fMathView);
        sLine="$$|$$";
        sMathView.setText(sLine);
    }

    @Override
    public void onClick(View v) {
        System.out.println(fMathView.getText());
        if(isFirst){
            isFirst=false;
            if(fMathView.getText().equals("$$0abc$$$$1,0|,0,0,$$$$2,0,0,0,$$")){
                type=1;
            }
            else if(fMathView.getText().equals("$$0abcd$$$$1,0|,0,0,0,$$$$2,0,0,0,0,$$$$3,0,0,0,0,$$")){
                type=2;
            }

            fLine=fMathView.getText();
        }
        switch (v.getId()){
            case(R.id.but4):
                Intent intent = new Intent(super.activity.getBaseContext(),Start.class);
                intent.putExtra("FROM_Class", "Mode");
                super.activity.getBaseContext().startActivity(intent);
                break;
            case(R.id.but47):
                if(!sLine.equals("$$|$$")){
                    int i;
                    for(i=0;i<sLine.length();i++){
                        if(sLine.toCharArray()[i]=='|')
                            break;
                    }
                    String index=sLine.substring(2,i)+sLine.substring(i+1,sLine.length()-2);
                    MathOperations mo=new MathOperations();
                    index=mo.handleInputString(index);
                    Expression e=new Expression(index);
                    double answer=e.calculate();
                    fLine=fMathView.getText();
                    int cursorPosition=bi.findCursorPosition(fLine)-1;
                    for(i=cursorPosition-1;i>=0;i--){
                         if(fLine.toCharArray()[i]==',')
                             break;
                      }
                     fLine=fLine.substring(0,i+1)+Double.toString(answer)+fLine.substring(cursorPosition);
                    fMathView.setText(fLine);
                    System.out.println(fLine);
                    sLine="$$|$$";
                    sMathView.setText(sLine);
                }
                else {
                    showSnackbar();
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
                boolean check=true;
                switch(v.getId()) {
                    case (R.id.c_right):
                        if(sLine.equals("$$|$$")) {
                            check=false;
                            fLine = fMathView.getText();
                            int position = bi.findCursorPosition(fLine) - 1;
                            for (int i = position - 1; i < fLine.length() - 5; i++) {
                                fLine = fLine.substring(0, position) + fLine.substring(position + 1, position + 2) + "|" + fLine.substring(position + 2);
                                position = bi.findCursorPosition(fLine) - 1;
                                if (fLine.toCharArray()[position + 1] == ',' && fLine.toCharArray()[position - 2] != '$') {
                                    fMathView.setText(fLine);
                                    break;
                                }
                            }

                        }
                        break;
                    case (R.id.c_left):
                        if(sLine.equals("$$|$$")) {
                            check=false;
                            fLine = fMathView.getText();
                            int position = bi.findCursorPosition(fLine) - 1;
                            for (int i = position - 1; !(fLine.toCharArray()[i]==','&&fLine.toCharArray()[i-1]=='1'&&fLine.toCharArray()[i-2]=='$'); i--) {
                                fLine = fLine.substring(0, position-1)+"|" + fLine.substring(position - 1, position )  + fLine.substring(position + 1);
                                position = bi.findCursorPosition(fLine) - 1;
                                if (fLine.toCharArray()[position + 1] == ',' && fLine.toCharArray()[position - 2] != '$') {
                                    fMathView.setText(fLine);
                                    break;
                                }
                            }

                        }
                        break;
                    case (R.id.c_top):
                        if(sLine.equals("$$|$$") && type!=0){
                            check=false;
                            int count=0;
                            fLine=fMathView.getText();
                            int position = bi.findCursorPosition(fLine) - 1;
                            for(int i=position;i>2;i--){
                                if(fLine.toCharArray()[i-1]==','){
                                    count++;
                                }
                                if(count==3+type){
                                    fLine = fLine.substring(0, position-1)+"|" + fLine.substring(position - 1, position )  + fLine.substring(position + 1);
                                    fMathView.setText(fLine);
                                    break;
                                }
                                fLine = fLine.substring(0, position-1)+"|" + fLine.substring(position - 1, position )  + fLine.substring(position + 1);
                                position = bi.findCursorPosition(fLine) - 1;


                            }

                        }
                        break;

                    case (R.id.c_buttom):
                        if(sLine.equals("$$|$$") && type!=0){
                            check=false;
                            int count=0;
                            fLine=fMathView.getText();
                            int position = bi.findCursorPosition(fLine) - 1;
                            for(int i=position;i<fLine.length()-2;i++){
                                if(fLine.toCharArray()[i+1]==','){
                                    count++;
                                }
                                if(count==4+type){
                                    fMathView.setText(fLine);
                                    break;
                                }
                                fLine = fLine.substring(0, position) + fLine.substring(position + 1, position + 2) + "|" + fLine.substring(position + 2);
                                position = bi.findCursorPosition(fLine) - 1;

                            }

                        }
                        break;


                }
                if(check) {
                    sLine = bi.getOutput(v, previousButton, activity, sMathView.getText());
                    sMathView.setText(sLine);
                    previousButton = v.getId();
                }


        }

    }
}
