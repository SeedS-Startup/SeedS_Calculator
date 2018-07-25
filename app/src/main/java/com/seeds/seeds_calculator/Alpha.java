package com.seeds.seeds_calculator;

import android.content.Context;
import android.view.View;

public class Alpha extends InputType {
    @Override
    String getOutput(View v, int previousButton, Context context) {
        switch(v.getId()){
            default:
                String output="";
                output=findString(v,previousButton);
                int cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1)+output+input.substring(cursorPosition-1);
        }
        return input;
    }

    private String findString(View v, int previousButton) {
        switch (v.getId()){
            case R.id.but45:
                return"e";
            case R.id.but25:
               return "x";
            case R.id.but26:
                return "Y";
            case R.id.but27:
                return  "M";
            case R.id.but16:
                return "A";
            case R.id.but17:
                return "B";
            case R.id.but18:
                return "C";
            case R.id.but19:
                return "D";
            case R.id.but6:
                return "=";

        }
        return "";
    }
}
