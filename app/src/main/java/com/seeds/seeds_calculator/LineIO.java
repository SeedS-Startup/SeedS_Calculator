package com.seeds.seeds_calculator;

import java.util.ArrayList;

public class LineIO extends Print {
    public LineIO(String input) {
        super(input);
    }

    @Override
    public String output() {
        for(int i=0;i<input.length();i++){
            ArrayList<Integer> indexes=new ArrayList<>();
            ArrayList<Character> items=new ArrayList<>();
            String add="";
            if((indexes=exist("{()}\\frac{()}{()}",input,i))!=null){
                    items.add('\\');
                    items.add('f');
                    items.add('r');
                    items.add('a');
                    items.add('c');
                    add = "-)";
                    input=deleteAdd(input,items,indexes,add);
            }
            else if((indexes=exist("\\int_{()}^{()}{()}",input,i))!=null){
                items.add('_');
                items.add('^');
                add=",";
                input=deleteAdd(input,items,indexes,add);

            }
            else if((indexes=exist("\\frac{()}{()}",input,i))!=null){
                items.add('\\');
                items.add('f');
                items.add('r');
                items.add('a');
                items.add('c');
                add = "-)";
                input=deleteAdd(input,items,indexes,add);

            }
            else if((indexes=exist("\\log_{()}{()}",input,i))!=null){
                items.add('_');
                add=",";
                input=deleteAdd(input,items,indexes,add);

            }
            else if((indexes=exist("\\sum_{()}^{()}{()}",input,i))!=null){
                items.add('_');
                items.add('^');
                add=",";
                input=deleteAdd(input,items,indexes,add);
            }
            else if((indexes=exist("\\frac{d}{dx}{()}\\mid _{x=()}",input,i))!=null){
                items.add('\\');
                items.add('f');
                items.add('r');
                items.add('a');
                items.add('c');
                items.add('m');
                items.add('i');
                items.add('d');
                items.add('d');
                add=",";
                input=deleteAdd(input,items,indexes,add);
                for(int j=0;j<input.length()-1;j++){
                    if(input.toCharArray()[j]=='x'&&input.toCharArray()[j+1]=='('){
                        input=input.substring(0,j)+input.substring(j+1);
                    }
                }

            }

            else if((indexes=exist("{()}^",input,i))!=null){
                for(int j=0;j<input.length()-1;j++){
                    if(input.toCharArray()[j]=='^'){
                        input=input.substring(0,j)+"pow"+input.substring(j+1);
                    }
                }

            }



            else if((indexes=exist("{}^{()}",input,i))!=null){
                for(int j=0;j<input.length()-1;j++){
                    if(input.toCharArray()[j]=='^'){
                        input=input.substring(0,j)+"pow"+input.substring(j+1);
                    }
                }

            }

        }
        return input;
    }
    public String deleteAdd(String input,ArrayList<Character> items,ArrayList<Integer> indexes,String add){
        int deleteNumber=0;
        for(Integer index:indexes){
            for(char ch:items) {
                if (input.toCharArray()[index - deleteNumber] == ch) {
                    input = input.substring(0, index - deleteNumber) + input.substring(index + 1 - deleteNumber);
                    deleteNumber++;
                    break;
                }
            }
        }
        for(int j=0;j<input.length()-1;j++){
            if(input.toCharArray()[j]=='}'&&input.toCharArray()[j+1]=='{'){
                input=input.substring(0,j+1)+add+input.substring(j+1);
            }
        }
        return input;
    }

    private ArrayList<Integer> exist(String s, String input, int i) {
        ArrayList<Integer> indexes=new ArrayList<>();
        if(input.toCharArray()[i]!=s.toCharArray()[0])
            return null;
        indexes.add(i);
        i++;
        int pCheck=0;
        for (int count=1;count<s.length()&&i<input.length();i++){
            if(input.toCharArray()[i]==s.toCharArray()[count] &&pCheck==0){
                count++;
                indexes.add(i);
            }
            else if(input.toCharArray()[i]=='{'||input.toCharArray()[i]=='('){
                pCheck++;
            }
            else if(input.toCharArray()[i]=='}'||input.toCharArray()[i]==')'){
                if(pCheck>0){
                    pCheck--;
                }
            }
        }
        if(indexes.size()==s.length())
            return indexes;
        return null;
    }
}
