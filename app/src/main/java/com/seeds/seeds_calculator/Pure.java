package com.seeds.seeds_calculator;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class Pure extends InputType {
    @Override
    String getOutput(View v, int previousButton, Context context) {

        switch(v.getId()){
            case R.id.but5:
            case R.id.but32:
                input="$$|$$";
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
                int cursorPosition=findCursorPosition(input);
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
                return "\\times{10}^{()}";
            case R.id.but38:
                if(previousButton==R.id.but18)
                    return"\\sinh{()}";
                return "1";
            case R.id.but39:
                if(previousButton==R.id.but18)
                    return "\\cosh{()}";
                return "2";
            case R.id.but40:
                if(previousButton==R.id.but18)
                    return"\\tanh{()}";
                return "3";
            case R.id.but41:
                return"+";
            case R.id.but42:
                return "-";
            case R.id.but33:
                if(previousButton==R.id.but18)
                    return "arcsinh{()}";
                return "4";
            case R.id.but34:
                if(previousButton==R.id.but18)
                    return "arccosh{()}";
                return "5";
            case R.id.but35:
                if(previousButton==R.id.but18)
                    return "arctanh";
                return "6";
            case R.id.but36:
                return "\\times";
            case R.id.but37:
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
                return "\\sin{()}";
            case R.id.but20:
                return "\\cos{()}";
            case R.id.but21:
                return "\\tan{()}";
            case R.id.but10:
                return "\\frac{()}{()}";
            case R.id.but11:
                return "\\sqrt{()}";
            case R.id.but12:
                return"{()}^2";
            case R.id.but13:
                return "{()}^{()}";
            case R.id.but14:
                return "\\log{()}";
            case R.id.but15:
                return "\\ln{()}";
            case R.id.but7:
                return "\\int_{()}^{()}{()}";
            case R.id.but8:
                return "{()}^{-1}";
            case R.id.but9:
                return "log_{()}{()}";

        }
        return "";
    }





}
