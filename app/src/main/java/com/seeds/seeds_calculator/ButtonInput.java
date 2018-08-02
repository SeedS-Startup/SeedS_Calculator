package com.seeds.seeds_calculator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.kexanie.library.MathView;

public abstract class ButtonInput {
    public static Set<String> leftRightKey = new HashSet<>();
    public static Set<String> deletekey = new HashSet<>();
    public static Set<String> deleteSkipKey = new HashSet<>();
    Activity active;

    public Set<String> getDeletekey() {
        return deletekey;
    }

    public ButtonInput(Activity active) {
        this.active = active;
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

    public void showDialogbox(final List<String> stringList, final Activity context, final MathView mathView) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(context); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }

        dialog.show();
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int childCount = group.getChildCount();
                    for (int x = 0; x < childCount; x++) {
                        List<String> stringList1=new ArrayList<>();
                        RadioButton btn = (RadioButton) group.getChildAt(x);
                        if (btn.getId() == checkedId) {
                            int cursorPosition=findCursorPosition(mathView.getText());
                            switch (btn.getText().toString()) {
                                case ("sinh"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"\\sinh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("\\sinh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("\\sinh{()}");
                                    break;
                                case ("cosh"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"\\cosh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("\\cosh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("\\cosh{()}");
                                    break;
                                case ("tanh"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"\\tanh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("\\tanh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("\\tanh{()}");
                                    break;
                                case ("sinh-1"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"arcsinh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("arcsinh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("arcsinh{()}");
                                    break;
                                case ("cosh-1"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"arccosh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("arccosh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("arccosh{()}");
                                    break;
                                case ("tanh-1"):
                                    mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"arctanh{()}"+mathView.getText().substring(cursorPosition-1));
                                    leftRightKey.add("arctanh{(");
                                    leftRightKey.add(")}");
                                    deletekey.add("arctanh{()}");
                                    break;
                                case ("anX+bnY=cn"):
                                    mathView.setText("$$0abc$$$$1,0|,0,0,$$$$2,0,0,0,$$");
                                    break;
                                case ("anX+bnY+cnZ=dn"):
                                    mathView.setText("$$0abcd$$$$1,0|,0,0,0,$$$$2,0,0,0,0,$$$$3,0,0,0,0,$$");
                                    break;
                                case ("aX2+bX+c=0"):
                                    mathView.setText("$$0abc$$$$1,0|,0,0,$$");
                                    break;
                                case ("aX3+bX2+cX+d=0"):
                                    mathView.setText("$$0abcd$$$$1,0|,0,0,0,$$");
                                    break;
                                case ("VecA"):
                                    dialog.dismiss();
                                    mathView.setText("$$A|$$");
                                    stringList1.clear();
                                    stringList1.add("3");
                                    stringList1.add("2");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case ("VecB"):
                                    dialog.dismiss();
                                    mathView.setText("$$B|$$");
                                    stringList1.clear();
                                    stringList1.add("3");
                                    stringList1.add("2");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case ("VecC"):
                                    dialog.dismiss();
                                    mathView.setText("$$C|$$");
                                    stringList1.clear();
                                    stringList1.add("3");
                                    stringList1.add("2");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case ("3"):
                                    mathView.setText(mathView.getText().substring(0,3)+"[0|,0,0]$$");
                                    break;
                                case ("2"):
                                    mathView.setText(mathView.getText().substring(0,3)+"[0|,0]$$");
                                    break;
                                case ("MatA"):
                                    dialog.dismiss();
                                    mathView.setText("$$A$$");
                                    stringList1.clear();
                                    stringList1.add("3*3");
                                    stringList1.add("3*2");
                                    stringList1.add("3*1");
                                    stringList1.add("2*3");
                                    stringList1.add("2*2");
                                    stringList1.add("2*1");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case ("MatB"):
                                    dialog.dismiss();
                                    mathView.setText("$$B$$");
                                    stringList1.clear();
                                    stringList1.add("3*3");
                                    stringList1.add("3*2");
                                    stringList1.add("3*1");
                                    stringList1.add("2*3");
                                    stringList1.add("2*2");
                                    stringList1.add("2*1");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("MatC"):
                                    dialog.dismiss();
                                    mathView.setText("$$C$$");
                                    stringList1.clear();
                                    stringList1.add("3*3");
                                    stringList1.add("3*2");
                                    stringList1.add("3*1");
                                    stringList1.add("2*3");
                                    stringList1.add("2*2");
                                    stringList1.add("2*1");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("3*3"):
                                    mathView.setText(mathView.getText()+"$$0|,0,0,$$$$0,0,0,$$$$0,0,0,$$");
                                    break;
                                case("3*2"):
                                    mathView.setText(mathView.getText()+"$$0|,0,$$$$0,0,$$$$0,0,$$");

                                    break;
                                case("3*1"):
                                    mathView.setText(mathView.getText()+"$$0|,$$$$0,$$$$0,$$");
                                    break;
                                case("2*3"):
                                    mathView.setText(mathView.getText()+"$$0|,0,0,$$$$0,0,0,$$");
                                    break;
                                case("2*2"):
                                    mathView.setText(mathView.getText()+"$$0|,0,$$$$0,0,$$");
                                    break;
                                case("2*1"):
                                    mathView.setText(mathView.getText()+"$$0|,$$$$0,$$");
                                    break;
                                case("Dim"):
                                    dialog.dismiss();
                                    stringList1.clear();
                                    stringList1.add("MatA");
                                    stringList1.add("MatB");
                                    stringList1.add("MatC");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("DimVec"):
                                    dialog.dismiss();
                                    stringList1.clear();
                                    stringList1.add("VecA");
                                    stringList1.add("VecB");
                                    stringList1.add("VecC");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("Data"):
                                    dialog.dismiss();
                                    stringList1.clear();
                                    stringList1.add("3*3");
                                    stringList1.add("3*2");
                                    stringList1.add("3*1");
                                    stringList1.add("2*3");
                                    stringList1.add("2*2");
                                    stringList1.add("2*1");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("DataVec"):
                                    dialog.dismiss();
                                    stringList1.clear();
                                    stringList1.add("3");
                                    stringList1.add("2");
                                    showDialogbox(stringList1,context,mathView);
                                    break;
                                case("MA"):
                                    addToMathView(mathView,"MatA");
                                    break;
                                case("MB"):
                                    addToMathView(mathView,"MatB");
                                    break;
                                case("MC"):
                                    addToMathView(mathView,"MatC");
                                    break;
                                case("MAns"):
                                    addToMathView(mathView,"MatAnc");
                                    break;
                                case("det"):
                                    addToMathView(mathView,"det");
                                    break;
                                case("Trn"):
                                    addToMathView(mathView,"Trn");
                                    break;
                                case("VA"):
                                    addToMathView(mathView,"VA");
                                    break;
                                case ("VB"):
                                    addToMathView(mathView,"VB");
                                    break;
                                case("VC"):
                                    addToMathView(mathView,"VC");
                                    break;
                                case("VAns"):
                                    addToMathView(mathView,"VAns");
                                    break;
                                case("Dot"):
                                    addToMathView(mathView,"Dot");
                                    break;

                            }
                            dialog.dismiss();
                        }
                    }
                }
            });

    }

    protected  void addToMathView(MathView mathView, String add){
        int cursorPosition=findCursorPosition(mathView.getText());

        if(add.equals("Trn")||add.equals("det")){
            mathView.setText(mathView.getText().substring(0,cursorPosition-1)+add+"()"+mathView.getText().substring(cursorPosition-1));
            leftRightKey.add(add+"(");
            leftRightKey.add(")");
            deletekey.add(add+"()");
            return;
        }
        else if(add.equals("Dot")){
            mathView.setText(mathView.getText().substring(0,cursorPosition-1)+"."+mathView.getText().substring(cursorPosition-1));
            return;

        }
        mathView.setText(mathView.getText().substring(0,cursorPosition-1)+add+mathView.getText().substring(cursorPosition-1));
        leftRightKey.add(add);
        deletekey.add(add);
    }


    public abstract String getOutput(View v, int previousButton, Activity context, String input);
     public String getBase(){
         return null;
     }
    public void showSnackbar() {
        Toast.makeText(active,"Coming soon",Toast.LENGTH_SHORT).show();
    }

}
