package com.seeds.seeds_calculator;

public abstract class Print {
    String input;

    public Print(String input) {
        this.input = input;
    }
    public abstract String output();
}
