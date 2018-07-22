package com.seeds.seeds_calculator;

public class OperatorInfos {
    String name;
    String rate;
    String operandNum;

    public OperatorInfos(String name,String rate,String operatorNum) {
        this.name = name;
        this.rate=rate;
        this.operandNum=operatorNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperandNum() {
        return operandNum;
    }

    public void setOperandNum(String operandNum) {
        this.operandNum = operandNum;
    }

    public String getRate() {

        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
