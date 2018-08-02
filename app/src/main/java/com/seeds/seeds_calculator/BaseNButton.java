package com.seeds.seeds_calculator;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Set;

public class BaseNButton extends ButtonInput {
    public static String base = "Dec";

    public BaseNButton(Activity active) {
        super(active);
    }

    @Override
    public String getOutput(View v, int previousButton, Activity context, String input) {
        int cursorPosition;
        switch(v.getId()){
            case R.id.c_top:
            case R.id.c_left:
                int leftSkipNumber=findLeftSkipNumber(input,leftRightKey);
                input=handleLeftSkip(leftSkipNumber,input);
                break;
            case R.id.c_buttom:
            case R.id.c_right:
                int rightSkipNumber=findRightSkipNumber(input,leftRightKey);
                cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1)+input.substring(cursorPosition,cursorPosition+rightSkipNumber)+"|"+input.substring(cursorPosition+rightSkipNumber);
                break;
            case R.id.but31:
                input=handleDelete(input,deletekey,leftRightKey,context);
                break;
            default:
                String output="";
                output=findString(v,previousButton,context);
                cursorPosition=findCursorPosition(input);
                input=input.substring(0,cursorPosition-1)+output+input.substring(cursorPosition-1);
        }
        return input;
    }
    private String findString(View v, int previousButton, Activity context) {
        switch (v.getId()) {
            case R.id.but43:
                return "0";
            case R.id.but45:
                deletekey.add("\\times{10}^{()}");
                leftRightKey.add(")}");
                leftRightKey.add("\\times{10}^{(");
                return "\\times{10}^{()}";
            case R.id.but38:
                return "1";
            case R.id.but39:
                return "2";
            case R.id.but40:
                return "3";
            case R.id.but41:
                return "+";
            case R.id.but17:
                return "B";
            case R.id.but16:
                return "A";
            case R.id.but42:
                return "-";
            case R.id.but33:
                return "4";
            case R.id.but34:
                return "5";
            case R.id.but35:
                return "6";
            case R.id.but36:
                deletekey.add("\\times");
                leftRightKey.add("\\times");
                return "\\times";
            case R.id.but37:
                deletekey.add("\\div");
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
                return "D";
            case R.id.but20:
                return "E";
            case R.id.but21:
                return "F";
            case R.id.but12:
                base = "Dec";
                break;
            case R.id.but13:
                base = "Hex";
                break;
            case R.id.but14:
                base = "Bin";
                break;
            case R.id.but15:
                base = "Oct";
                break;

        }
        return "";
    }

    private int findLeftSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition = findCursorPosition(input);
        if (cursorPosition == 3)
            return 0;
        int skipNumber = 1;
        for (String str : leftRightKey) {
            if (cursorPosition - 1 - str.length() >= 0) {
                if (input.substring(cursorPosition - 1 - str.length(), cursorPosition - 1).equals(str)) {
                    if (str.length() > skipNumber) {
                        skipNumber = str.length();
                    }
                }
            }
        }
        return skipNumber;
    }

    private int findRightSkipNumber(String input, Set<String> leftRightKey) {
        int cursorPosition = findCursorPosition(input);
        if (cursorPosition == input.length() - 2)
            return 0;
        int skipNumber = 1;
        for (String str : leftRightKey) {
            if (cursorPosition + str.length() < input.length() - 1) {
                if (input.substring(cursorPosition, cursorPosition + str.length()).equals(str)) {
                    if (str.length() >= skipNumber) {
                        skipNumber = str.length();
                    }
                }
            }
        }
        return skipNumber;
    }

    private String handleDelete(String input, Set<String> deletekey, Set<String> leftRightKey, Context context) {
        int cursorPosition = findCursorPosition(input);
        for (String str : deletekey) {
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes = exist(str, input);
            if (indexes != null) {
                for (Integer i : indexes) {
                    input = input.substring(0, i) + input.substring(i + 1);

                }

                return input;
            }
        }
        int leftSkipNumber = findLeftSkipNumber(input, leftRightKey);
        if (leftSkipNumber > 1) {
            input = handleLeftSkip(leftSkipNumber,input);
            return input;
        }
        if (cursorPosition > 3)
            input = input.substring(0, cursorPosition - 2) + input.substring(cursorPosition - 1);
        return input;
    }

    private ArrayList<Integer> exist(String str, String input) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int cursorPosition = findCursorPosition(input);
        int strLength = str.length() - 1;
        int pCheck = 0;
        if (input.toCharArray()[cursorPosition - 2] == str.toCharArray()[strLength]) {
            indexes.add(cursorPosition - 2);
            indexes.add(cursorPosition - 3);
            strLength -= 2;
            for (int i = cursorPosition - 4; i >= 0 && strLength >= 0; i--) {
                if (input.toCharArray()[i] == str.toCharArray()[strLength]) {
                    if (input.toCharArray()[i] == '{' || input.toCharArray()[i] == '(') {
                        if (pCheck == 0) {
                            indexes.add(i);
                            strLength--;
                        } else
                            pCheck--;
                    } else {
                        indexes.add(i);
                        strLength--;
                    }
                } else if (input.toCharArray()[i] == '}' || input.toCharArray()[i] == ')') {
                    pCheck++;
                } else if (input.toCharArray()[i] == '{' || input.toCharArray()[i] == '(') {
                    pCheck--;
                }


            }
        }
        if (indexes.size() == str.length())
            return indexes;
        return null;
    }

    private String handleLeftSkip(int leftSkipNumber,String input) {
        int cursorPosition = findCursorPosition(input);
        input = input.substring(0, cursorPosition - 1 - leftSkipNumber) + "|" + input.substring(cursorPosition - 1 - leftSkipNumber, cursorPosition - 1) + input.substring(cursorPosition);
        return input;
    }

    public int findCursorPosition(String input) {
        int position = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.toCharArray()[i] == '|') {
                position = i + 1;
            }
        }
        return position;
    }
    public String getBase(){
        return base;
    }

}
