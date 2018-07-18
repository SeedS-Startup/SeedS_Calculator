package com.seeds.seeds_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import io.github.kexanie.library.MathView;

public class Start extends AppCompatActivity implements View.OnClickListener {
    static int previousButton=0;
    static InputType inputType=new Pure();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("close","yes");
        Toast.makeText(this, "shift", Toast.LENGTH_SHORT).show();
        MathView mathView=(MathView)findViewById(R.id.f_line);
        mathView.setText("$$|$$");
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
    }

    @Override
    public void onClick(View v) {
        if(previousButton==R.id.but1) {
            Toast.makeText(this, "shift", Toast.LENGTH_SHORT).show();
            inputType = new Shift();
        }
        else if(previousButton==R.id.but2)
            inputType=new Alpha();
        else
            inputType=new Pure();
        MathView mathView=(MathView)findViewById(R.id.f_line);
        mathView.setHorizontalScrollBarEnabled(true);
        mathView.setVerticalScrollBarEnabled(true);
        String mathString=inputType.getOutput(v,previousButton,this);
        mathView.setText(mathString);
        previousButton=v.getId();
    }
}
