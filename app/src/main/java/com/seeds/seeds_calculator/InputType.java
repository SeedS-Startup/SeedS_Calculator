package com.seeds.seeds_calculator;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public abstract class InputType {
    public static String input="$$|$$";
    public static Set<String> leftRightKey= new HashSet<>();
    public static Set<String> deletekey= new HashSet<>();
    public static Set<String> deleteSkipKey= new HashSet<>();

    public String getInput(){
        return input;
    }
    public void setInput(String in){
        input=in;
    }
    public int findCursorPosition(String input){
        int position=0;
        for(int i=0;i<input.length();i++){
            if(input.toCharArray()[i]=='|'){
                position=i+1;
            }
        }
        return position;
    }
    public void showAlertDialog(Context context,String text) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("hyp")
                .setMessage(text)
                .setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
    abstract String getOutput(View v,int previousButton,Context context);
}
