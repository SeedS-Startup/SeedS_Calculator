package com.seeds.seeds_calculator;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.github.kexanie.library.MathView;

public abstract class Mode implements View.OnClickListener  {
    MathView fMathView;
    MathView sMathView;

    public Activity activity;

    public Mode(Activity _activity) {
        this.activity=_activity;
         buttonListeners();

    }

    protected void buttonListeners(){
        fMathView=(MathView)this.activity.findViewById(R.id.f_line);
        sMathView=(MathView)this.activity.findViewById(R.id.s_line);
        int[] buttonIds={R.id.but6,R.id.but7,R.id.but8,R.id.but9,R.id.but10,R.id.but11,R.id.but12,
                R.id.but13,R.id.but14,R.id.but15,R.id.but16,R.id.but17,R.id.but18,R.id.but19,R.id.but20,
                R.id.but21,R.id.but22,R.id.but23,R.id.but24,R.id.but25,R.id.but26,R.id.but27,R.id.but28,
                R.id.but29,R.id.but30,R.id.but31,R.id.but32,R.id.but33,R.id.but34,R.id.but35,R.id.but36,
                R.id.but37,R.id.but38,R.id.but39,R.id.but40,R.id.but41,R.id.but42,R.id.but43,R.id.but44,
                R.id.but45,R.id.but46,R.id.but47,R.id.c_left,R.id.c_right,R.id.c_top, R.id.replay};
        int[] imageButtonId={R.id.but1,R.id.but2,R.id.c_buttom,R.id.but4,R.id.but5};
        for(int i=0;i<buttonIds.length;i++){
            Button button=(Button)this.activity.findViewById(buttonIds[i]);
            button.setOnClickListener(this);
        }
        for(int i=0;i<imageButtonId.length;i++){
            ImageButton imageButton=(ImageButton)this.activity.findViewById(imageButtonId[i]);
            imageButton.setOnClickListener(this);
        }
    }
    public void showSnackbar() {
        LinearLayout linearLayout=(LinearLayout)this.activity.findViewById(R.id.layout);
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Coming Soon !", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
