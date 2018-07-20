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
                deletekey.add("Rnd{()}");
                leftRightKey.add(")}");
                leftRightKey.add("Rnd{(");
                return "Rnd{()}";
            case R.id.but44:
                deletekey.add("Rand{()}");
                leftRightKey.add(")}");
                leftRightKey.add("Rand{(");
                return "Rand{()}";
            case R.id.but45:
                deletekey.add("\\pi");
                leftRightKey.add("\\pi");
                return "\\pi";
            case R.id.but41:
                deletekey.add("Pol{()}");
                leftRightKey.add(")}");
                leftRightKey.add("Pol{(");
                return "Pol{()}";
            case R.id.but42:
                deletekey.add("Rec{()}");
                leftRightKey.add(")}");
                leftRightKey.add("Rec{(");
                return "Rec{()}";
            case R.id.but36:
                deletekey.add("P{()}");
                leftRightKey.add(")}");
                leftRightKey.add("P{(");
                return "P{()}";
            case R.id.but37:
                deletekey.add("C{()}");
                leftRightKey.add(")}");
                leftRightKey.add("C{(");
                return "C{()}";
            case R.id.but24:
                return "%";
            case R.id.but25:
                return ",";
            case R.id.but18:
                deletekey.add("\\mid{()}\\mid");
                leftRightKey.add("\\mid{(");
                leftRightKey.add(")}\\mid");
                return "\\mid{()}\\mid";
            case R.id.but19:
                deletekey.add("\\arcsin{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\arcsin{(");
                return "\\arcsin{()}";
            case R.id.but20:
                deletekey.add("\\arccos{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\arccos{(");
                return "\\arccos{()}";
            case R.id.but21:
                deletekey.add("\\arctan{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\arctan{(");
                return "\\arctan{()}";
            case R.id.but10:
                deletekey.add("{()}\\frac{()}{()}");
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add("{()}\\frac{(");
                return "{()}\\frac{()}{()}";
            case R.id.but11:
                deletekey.add("\\sqrt[3]{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\sqrt[3]{(");
                return "\\sqrt[3]{()}";
            case R.id.but12:
                deletekey.add("{()}^3");
                leftRightKey.add(")}^3");
                leftRightKey.add("{(");
                return "{()}^3";
            case R.id.but13:
                deletekey.add("\\sqrt[()]{()}");
                leftRightKey.add("\\sqrt[(");
                leftRightKey.add(")]{(");
                leftRightKey.add(")}");
                return "\\sqrt[()]{()}";
            case R.id.but14:
                deletekey.add("{10}^{()}");
                leftRightKey.add(")}");
                leftRightKey.add("{10}^{(");
                return "{10}^{()}";
            case R.id.but15:
                deletekey.add("{e}^{()}");
                leftRightKey.add(")}");
                leftRightKey.add("{e}^{(");
                return "{e}^{()}";
            case R.id.but8:
                deletekey.add("{()}!");
                leftRightKey.add("{(");
                leftRightKey.add(")}!");
                return "{()}!";
            case R.id.but9:
                deletekey.add("\\sum_{()}^{()}{()}");
                leftRightKey.add(")}");
                leftRightKey.add(")}{(");
                leftRightKey.add(")}^{(");
                leftRightKey.add("\\sum_{(");
                return "\\sum_{()}^{()}{()}";

        }
        return "";
    }
}
