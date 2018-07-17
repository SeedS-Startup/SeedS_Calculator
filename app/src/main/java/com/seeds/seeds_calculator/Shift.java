package com.seeds.seeds_calculator;

import android.content.Context;
import android.view.View;

public class Shift extends InputType {
    @Override
    String getOutput(View v, int previousButton, Context context) {
        switch(v.getId()){
            case R.id.but46:
                String drg="1.digree\t2.radian\n3.gradian";
                showAlertDialog(context,drg);
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
                return "Rnd{()}";
            case R.id.but44:
                return "Rand{()}";
            case R.id.but45:
                return "\\pi";
            case R.id.but41:
                return "Pol{()}";
            case R.id.but42:
                return "Rec{()}";
            case R.id.but36:
                return "P{()}";
            case R.id.but37:
                return "C{()}";
            case R.id.but24:
                return "%";
            case R.id.but25:
                return ",";
            case R.id.but18:
                return "\\mid{()}\\mid";
            case R.id.but19:
                return "\\arcsin{()}";
            case R.id.but20:
                return "\\arccos{()}";
            case R.id.but21:
                return "\\arctan{()}";
            case R.id.but10:
                return "{()}\\frac{()}{()}";
            case R.id.but11:
                return "\\sqrt[3]{()}";
            case R.id.but12:
                return "{()}^3";
            case R.id.but13:
                return "\\sqrt[()]{()}";
            case R.id.but14:
                return "{10}^{()}";
            case R.id.but15:
                return "{e}^{()}";
            case R.id.but8:
                return "{()}!";
            case R.id.but9:
                return "\\sum_{()}^{()}{()}";

        }
        return "";
    }
}
