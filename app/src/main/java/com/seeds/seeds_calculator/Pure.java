package com.seeds.seeds_calculator;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class Pure extends InputType {
    @Override
    String getOutput(View v, int previousButton, Context context) {
        int cursorPosition;
        switch(v.getId()){
            case R.id.but5:
            case R.id.but32:
                input="$$|$$";
                break;
            case R.id.c_top:
            case R.id.c_left:
                int leftSkipNumber=findLeftSkipNumber(input,leftRightKey);
                cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1-leftSkipNumber)+"|"+input.substring(cursorPosition-1-leftSkipNumber,cursorPosition-1)+input.substring(cursorPosition);
                break;
            case R.id.c_buttom:
            case R.id.c_right:
                int rightSkipNumber=findRightSkipNumber(input,leftRightKey);
                cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1)+input.substring(cursorPosition,cursorPosition+rightSkipNumber)+"|"+input.substring(cursorPosition+rightSkipNumber);
                break;
            case R.id.but4:
                String modeText="1.COMP\t2.CMPLX\n3.STAT\t4.BASE-N\n5.EQN-1\t6.MATRIX\n7.TABLE\t8.VECTOR";
                showAlertDialog(context,modeText);
                break;
            case R.id.but18:
                String hypText="1.sinh\t2.cosh\n3.tanh\t4.sinh-1\n5.cosh-1\t6.tanh-1";
                showAlertDialog(context,hypText);
                break;
            default:
                String output="";
                output=findString(v,previousButton);
                cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1)+output+input.substring(cursorPosition-1);
        }
        return input;
    }



    private String findString(View v, int previousButton) {
        switch(v.getId()){
            case R.id.but43:
                return "0";
            case R.id.but44:
                return ".";
            case R.id.but45:
                leftRightKey.add(")}");
                leftRightKey.add("\\times{10}^{(");
                return "\\times{10}^{()}";
            case R.id.but38:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("\\sinh{(");
                    return "\\sinh{()}";
                }
                return "1";
            case R.id.but39:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("\\cosh{(");
                    return "\\cosh{()}";
                }
                return "2";
            case R.id.but40:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("\\tanh{(");
                    return "\\tanh{()}";
                }
                return "3";
            case R.id.but41:
                return"+";
            case R.id.but42:
                return "-";
            case R.id.but33:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("arcsinh{(");
                    return "arcsinh{()}";
                }
                return "4";
            case R.id.but34:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("arccosh{(");
                    return "arccosh{()}";
                }
                return "5";
            case R.id.but35:
                if(previousButton==R.id.but18) {
                    leftRightKey.add(")}");
                    leftRightKey.add("arctanh{(");
                    return "arctanh{()}";
                }
                return "6";
            case R.id.but36:
                leftRightKey.add("\\times");
                return "\\times";
            case R.id.but37:
                leftRightKey.add("\\div");
                return "\\div";
            case R.id.but28:
                return "7";
            case R.id.but29:
                return "8";
            case R.id.but30:
                return "9";
            case R.id.but24:
                return "(";
            case R.id.but25:
                return ")";
            case R.id.but19:
                leftRightKey.add(")}");
                leftRightKey.add("\\sin{(");
                return "\\sin{()}";
            case R.id.but20:
                leftRightKey.add(")}");
                leftRightKey.add("\\cos{(");
                return "\\cos{()}";
            case R.id.but21:
                leftRightKey.add(")}");
                leftRightKey.add("\\tan{(");
                return "\\tan{()}";
            case R.id.but10:
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add("\\frac{(");
                return "\\frac{()}{()}";
            case R.id.but11:
                leftRightKey.add(")}");
                leftRightKey.add("\\sqrt{(");
                return "\\sqrt{()}";
            case R.id.but12:
                leftRightKey.add("{(");
                leftRightKey.add(")}^2");
                return"{()}^2";
            case R.id.but13:
                leftRightKey.add(")}");
                leftRightKey.add("{(");
                leftRightKey.add(")}^{(");
                return "{()}^{()}";
            case R.id.but14:
                leftRightKey.add(")}");
                leftRightKey.add("\\log{(");
                return "\\log{()}";
            case R.id.but15:
                leftRightKey.add(")}");
                leftRightKey.add("\\ln{(");
                return "\\ln{()}";
            case R.id.but7:
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add(")}^{(");
                leftRightKey.add("\\int_{(");
                return "\\int_{()}^{()}{()}";
            case R.id.but8:
                leftRightKey.add("{(");
                leftRightKey.add(")}^{-1}");
                return "{()}^{-1}";
            case R.id.but9:
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add("\\log_{(");
                return "\\log_{()}{()}";

        }
        return "";
    }

    private int findLeftSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition=findCursorPosition(input);
        if(cursorPosition==3)
            return 0;
        int skipNumber=1;
        for(String str: leftRightKey){
            if(input.substring(cursorPosition-1-str.length(),cursorPosition-1).equals(str)){
                if(str.length()>skipNumber){
                    skipNumber=str.length();
                }
            }
        }
        return skipNumber;
    }
    private int findRightSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition=findCursorPosition(input);
        if(cursorPosition==input.length()-2)
            return 0;
        int skipNumber=1;
        for(String str: leftRightKey){
            if(input.substring(cursorPosition,cursorPosition+str.length()).equals(str)){
                if(str.length()>skipNumber){
                    skipNumber=str.length();
                }
            }
        }
        return skipNumber;
    }



}
