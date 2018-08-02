package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;

public class Vector extends Mode {
    String fLine="";
    String sLine="";
    ButtonInput bi=new ComplexButton(activity);
    static int previousButton=0;
    int before=bi.getDeletekey().size();
    int after=bi.getDeletekey().size();
    int type=0;
    boolean isFirst=true;
    boolean view=false;
    boolean vectorIsClicked=false;
    double a[];
    double b[];
    int x=0;

    public Vector(Activity _activity) {
        super(_activity);
        fLine="$$|$$";
        fMathView.setText(fLine);
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("VecA");
        stringList1.add("VecB");
        stringList1.add("VecC");
        bi.showDialogbox(stringList1, activity,fMathView);
        sLine="$$|$$";
        sMathView.setText(sLine);
    }

    @Override
    public void onClick(View v) {
        after=bi.getDeletekey().size();
        if(before==after && vectorIsClicked) {
            vectorIsClicked=false;
            view = false;
        }
        if(before<after && vectorIsClicked){
            vectorIsClicked=false;
            view=true;
        }
        System.out.println(fMathView.getText());
        if(isFirst){
            isFirst=false;
            if(fMathView.getText().substring(3).equals("[0|,0,0]$$")){
                x=3;
            }
            else if(fMathView.getText().substring(3).equals("[0|,0]$$")){
                x=2;
            }


            fLine=fMathView.getText();
        }
        System.out.println(fMathView.getText());
        fLine=fMathView.getText();
        switch (v.getId()){
            case(R.id.but4):
                Intent intent = new Intent(super.activity.getBaseContext(),Start.class);
                intent.putExtra("FROM_Class", "Mode");
                super.activity.getBaseContext().startActivity(intent);
                break;
            case(R.id.but47):
                if(!sLine.equals("$$|$$")) {
                    int i;
                    for (i = 0; i < sLine.length(); i++) {
                        if (sLine.toCharArray()[i] == '|')
                            break;
                    }
                    String index = sLine.substring(2, i) + sLine.substring(i + 1, sLine.length() - 2);
                    MathOperations mo = new MathOperations();
                    index = mo.handleInputString(index);
                    Expression e = new Expression(index);
                    double answer = e.calculate();
                    fLine = fMathView.getText();
                    int cursorPosition = bi.findCursorPosition(fLine) - 1;
                    for (i = cursorPosition - 1; i >= 0; i--) {
                        if (fLine.toCharArray()[i] == ',' || fLine.toCharArray()[i] == '[')
                            break;
                    }
                    fLine = fLine.substring(0, i + 1) + Double.toString(answer) + fLine.substring(cursorPosition);
                    fMathView.setText(fLine);
                    System.out.println(fLine);
                    sLine = "$$|$$";
                    sMathView.setText(sLine);
                }
                else {
                    showSnackbar();
                }
                break;
            case R.id.but5:
                fLine="$$|$$";
                sLine="";
                fMathView.setText(fLine);
                sMathView.setText(sLine);
                break;
            case R.id.but32:
                fLine = fMathView.getText();
                if (fLine.toCharArray()[2] == 'A') {
                    a = new double[x];
                    fillmatrix(a, fLine);
                } else if (fLine.toCharArray()[2] == 'B') {
                    b = new double[x];
                    fillmatrix(b, fLine);
                }
                view = true;
                break;
            case (R.id.but34):
                if (previousButton == R.id.but1) {
                    List<String> stringList = new ArrayList<>();
                    before = bi.getDeletekey().size();
                    vectorIsClicked=true;
                    stringList.add("DimVec");
                    stringList.add("DataVec");
                    stringList.add("VA");
                    stringList.add("VB");
                    stringList.add("VC");
                    stringList.add("Vs");
                    stringList.add("Dot");
                    bi.showDialogbox(stringList, activity, fMathView);
                }
                break;

            default:
                boolean check=true;
                if(!view) {
                    switch (v.getId()) {
                        case (R.id.c_right):
                            if (sLine.equals("$$|$$")) {
                                check = false;
                                fLine = fMathView.getText();
                                int position = bi.findCursorPosition(fLine) - 1;
                                for (int i = position - 1; i < fLine.length() - 5; i++) {
                                    fLine = fLine.substring(0, position) + fLine.substring(position + 1, position + 2) + "|" + fLine.substring(position + 2);
                                    position = bi.findCursorPosition(fLine) - 1;
                                    if (fLine.toCharArray()[position + 1] == ']' || fLine.toCharArray()[position + 1] == ',') {
                                        fMathView.setText(fLine);
                                        break;
                                    }
                                }

                            }

                            break;
                        case (R.id.c_left):
                            if (sLine.equals("$$|$$")) {
                                check = false;
                                fLine = fMathView.getText();
                                int position = bi.findCursorPosition(fLine) - 1;
                                for (int i = position - 1; i > 2; i--) {
                                    fLine = fLine.substring(0, position - 1) + "|" + fLine.substring(position - 1, position) + fLine.substring(position + 1);
                                    position = bi.findCursorPosition(fLine) - 1;
                                    if (fLine.toCharArray()[position + 1] == ',' && fLine.toCharArray()[position + 1] != '[') {
                                        fMathView.setText(fLine);
                                        break;
                                    }
                                }

                            }
                            break;
                        case (R.id.c_top):
                            if (sLine.equals("$$|$$") && type != 0) {
                                check = false;
                            }
                            break;

                        case (R.id.c_buttom):
                            if (sLine.equals("$$|$$") && type != 0) {
                                check = false;
                            }
                            break;


                    }
                    if (check) {
                        sLine = bi.getOutput(v, previousButton, activity, sMathView.getText());
                        sMathView.setText(sLine);
                        previousButton = v.getId();
                    }
                }
                else{
                    fLine = bi.getOutput(v, previousButton, activity, fMathView.getText());
                    fMathView.setText(fLine);
                    previousButton = v.getId();

                }


        }

    }
    private void fillmatrix(double[] a, String text) {
        int cursor=bi.findCursorPosition(text)-1;
        int w;
        for( w=0;w<text.length()-1;w++){
            if(text.toCharArray()[w+1]==','){
                break;
            }
        }
        if(cursor<=w){
            text=text.substring(0,cursor)+text.substring(cursor+1,w+1)+"|"+text.substring(w+1);
        }
        else{
            text=text.substring(0,w+1)+"|"+text.substring(w+1,cursor)+text.substring(cursor+1);
        }
        for(int i=0;i<x;i++){

            int cursorPosition=bi.findCursorPosition(text)-1;
            int k=0;
            for(k=cursorPosition;k>0;k--){
                if(text.toCharArray()[k]==','||text.toCharArray()[k]=='['){
                    break;
                }
            }
            a[i]=Double.parseDouble(text.substring(k+1,cursorPosition));
            int position = bi.findCursorPosition(text) - 1;
            for (int l = position - 1; l < text.length() - 5; l++) {
                text = text.substring(0, position) + text.substring(position + 1, position + 2) + "|" + text.substring(position + 2);
                position = bi.findCursorPosition(text) - 1;
                if (text.toCharArray()[position + 1] == ',') {
                    break;
                }
            }

        }
        fMathView.setText("$$|$$");
        sMathView.setText("$$|$$");
    }
}
