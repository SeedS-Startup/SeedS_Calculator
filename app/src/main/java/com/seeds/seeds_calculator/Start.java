package com.seeds.seeds_calculator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import io.github.kexanie.library.MathView;



public class Start extends AppCompatActivity implements View.OnClickListener {
    Mode mode;
    static View id;
    String previousActivity=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = getIntent();
         previousActivity= mIntent.getStringExtra("FROM_Class");
         if(previousActivity!=null && previousActivity.equals("Mode")){
             onClick((View)findViewById(R.id.but4));
         }
        int[] buttonIds={R.id.but6,R.id.but7,R.id.but8,R.id.but9,R.id.but10,R.id.but11,R.id.but12,
                R.id.but13,R.id.but14,R.id.but15,R.id.but16,R.id.but17,R.id.but18,R.id.but19,R.id.but20,
                R.id.but21,R.id.but22,R.id.but23,R.id.but24,R.id.but25,R.id.but26,R.id.but27,R.id.but28,
                R.id.but29,R.id.but30,R.id.but31,R.id.but32,R.id.but33,R.id.but34,R.id.but35,R.id.but36,
                R.id.but37,R.id.but38,R.id.but39,R.id.but40,R.id.but41,R.id.but42,R.id.but43,R.id.but44,
                R.id.but45,R.id.but46,R.id.but47,R.id.c_left,R.id.c_right,R.id.c_top, R.id.replay};
        int[] imageButtonId={R.id.but1,R.id.but2,R.id.c_buttom,R.id.but4,R.id.but5};
        for(int i=0;i<buttonIds.length;i++){
            Button button=(Button)findViewById(buttonIds[i]);
            button.setOnClickListener(this);
        }
        for(int i=0;i<imageButtonId.length;i++){
            ImageButton imageButton=(ImageButton)findViewById(imageButtonId[i]);
            imageButton.setOnClickListener(this);
        }
       // mode=new Comp(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.but4):
                List<String> stringList=new ArrayList<>();
                stringList.add("COMP");
                stringList.add("CMPLX");
                stringList.add("STAT");
                stringList.add("BASE-N");
                stringList.add("EQN");
                stringList.add("MATRIX");
                stringList.add("TABLE");
                stringList.add("VECTOR");
                showDialogbox(stringList);
                break;

        }
    }

    public void showDialogbox(List<String> stringList) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        dialog.show();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int x = 0; x < childCount; x++) {
                    RadioButton btn = (RadioButton) group.getChildAt(x);
                    if (btn.getId() == checkedId) {
                        switch (btn.getText().toString()){
                            case("COMP"):
                                mode=new Comp(Start.this);
                                dialog.dismiss();
                                break;
                            case("CMPLX"):
                                mode=new Cmplx(Start.this);
                                dialog.dismiss();
                                break;
                            case("STAT"):
                            case("BASE-N"):
                                mode=new BaseN(Start.this);
                                dialog.dismiss();
                                break;
                            case("EQN"):
                                mode=new Eqn(Start.this);
                                dialog.dismiss();
                                break;
                            case("MATRIX"):
                                mode=new Matrix(Start.this);
                                dialog.dismiss();
                                break;
                            case("TABLE"):
                                mode=new Table(Start.this);
                                dialog.dismiss();
                                break;
                            case("VECTOR"):
                                mode=new Vector(Start.this);
                                dialog.dismiss();
                                break;
                        }

                    }
                }
            }
        });
    }


    public void showSnackbar() {
        Toast.makeText(this,"Coming soon",Toast.LENGTH_SHORT).show();
    }
}
