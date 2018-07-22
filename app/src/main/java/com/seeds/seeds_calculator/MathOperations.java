package com.seeds.seeds_calculator;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MathOperations {
    String input;
    ArrayList<OperatorInfos> operatorInfos=new ArrayList<>();
    public MathOperations(String input) {
        operatorInfos=writeInfos();
        this.input = input;
        this.input=correctingInputString(this.input);
        this.input=findPostfix(this.input);
        System.out.println(this.input);
    }

    private ArrayList<OperatorInfos> writeInfos() {
        ArrayList<OperatorInfos> operatorInfos=new ArrayList<>();
        operatorInfos.add(new OperatorInfos("-","1","2"));
        operatorInfos.add(new OperatorInfos("+","1","2"));
        operatorInfos.add(new OperatorInfos("times","2","2"));
        operatorInfos.add(new OperatorInfos("div","2","2"));
        operatorInfos.add(new OperatorInfos("^","3","2"));
        operatorInfos.add(new OperatorInfos("sin","0","1"));
        operatorInfos.add(new OperatorInfos("cos","0","1"));
        operatorInfos.add(new OperatorInfos("tan","0","1"));
        operatorInfos.add(new OperatorInfos("log","0","1"));
        operatorInfos.add(new OperatorInfos("ln","0","1"));
        operatorInfos.add(new OperatorInfos("!","0","1"));
        return operatorInfos;
    }


    public String correctingInputString(String input){
        for(int i=0;i<input.length();i++){
            if(input.toCharArray()[i]=='\\'||input.toCharArray()[i]=='{' ||input.toCharArray()[i]=='}'
                    ||input.toCharArray()[i]=='$'||input.toCharArray()[i]=='|'){
                input=input.substring(0,i)+" "+input.substring(i+1);
            }
            if((input.toCharArray()[i]=='s'&& input.toCharArray()[i+1]!='i')||input.toCharArray()[i]=='^'||input.toCharArray()[i]=='v')
                input=input.substring(0,i+1)+" "+input.substring(i+1);
        }
        for(int i=0;i<input.length();i++){
            if(input.toCharArray()[i]=='('||input.toCharArray()[i]==')' ||input.toCharArray()[i]=='-'
                    ||input.toCharArray()[i]=='+'||input.toCharArray()[i]=='%'||input.toCharArray()[i]==','
                    ||input.toCharArray()[i]=='['||input.toCharArray()[i]==']'){
                input=input.substring(0,i+1)+" "+input.substring(i+1);
                input=input.substring(0,i)+" "+input.substring(i);
                i++;
            }
        }
        char previousChar=input.toCharArray()[0];
        for(int i=1;i<input.length()-1;i++){
            if(previousChar==' ' && input.toCharArray()[i]==' '){
                input=input.substring(0,i)+input.substring(i+1);
            }
            previousChar=input.toCharArray()[i];
        }
        return input;
    }
    private String findPostfix(String input) {
        String output="";
        Stack<String> operators=new Stack<>();
        String[] inputItems=input.split(" ");
        for(int i=0;i<inputItems.length;i++){
            if(!inputItems[i].equals("")) {
                if (isNumeric(inputItems[i])) {
                    output=output+inputItems[i]+" ";
                } else if (inputItems[i].equals("(")) {
                    operators.add(inputItems[i]);
                } else if (inputItems[i].equals(")")) {
                    String operator = operators.pop();
                    while(!operator.equals("(")){
                        output=output+operator+" ";
                        operator=operators.pop();
                    }
                    operator=operators.pop();
                    for(OperatorInfos op:operatorInfos){
                        if(op.getName().equals(operator)){
                            if(Integer.parseInt(op.getRate())==0)
                                output=output+operator+" ";
                            else
                                operators.add(operator);
                            break;
                        }
                    }
                } else {
                    while (!operators.empty()) {
                        String previousOperation = operators.pop();
                        int rate=0;
                        for(OperatorInfos op:operatorInfos) {
                            if (op.getName().equals(inputItems[i]))
                                rate = Integer.parseInt(op.getRate());
                        }
                        if (operators.empty() && rate == 0) {
                            operators.add(previousOperation);
                            break;
                        }

                        else if (greaterOrder(previousOperation, inputItems[i])) {
                            output=output+previousOperation+" ";
                        } else {
                            operators.add(previousOperation);
                            break;
                        }

                    }
                        operators.add(inputItems[i]);

                }
            }

        }
        while(!operators.empty()){
            output=output+operators.pop()+"";
        }

        return output;
    }


    private boolean isNumeric(String inputItem) {

        for(int i=0;i<inputItem.length();i++){
            int check=0;
            if((inputItem.toCharArray()[i]<='9' && inputItem.toCharArray()[i]>='0'))
                check=1;
            else if(inputItem.toCharArray()[i]=='.')
                check=1;
            else if(inputItem.toCharArray()[i]<='D' && inputItem.toCharArray()[i]>='A')
                check=1;
            else if(inputItem.toCharArray()[i]=='M')
                check=1;
            else if(inputItem.toCharArray()[i]=='X')
                check=1;
            else if(inputItem.toCharArray()[i]=='e')
                check=1;
            if(check==0)
                return false;

        }

        return true;
    }
    private boolean greaterOrder(String previousOperation, String inputItem) {
        int firstOperator=0;
        int secondOperator=0;

        for(OperatorInfos op:operatorInfos){
            if(op.getName().equals(previousOperation))
                firstOperator=Integer.parseInt(op.getRate());
            if(op.getName().equals(inputItem))
                secondOperator=Integer.parseInt(op.getRate());
        }
        if(firstOperator>secondOperator)
            return true;
        return false;

    }

}
