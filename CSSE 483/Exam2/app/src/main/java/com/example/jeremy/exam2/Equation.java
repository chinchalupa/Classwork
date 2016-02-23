package com.example.jeremy.exam2;

/**
 * Created by Jeremy on 1/9/2016.
 */
public class Equation {

    private int base;
    private int multiplier;
    private int result;
    private boolean displayingAnswer;

    public Equation(int base, int multiplier, int result) {
        this.base = base;
        this.multiplier = multiplier;
        this.result = result;
        this.displayingAnswer = false;
    }

    public boolean isDisplayingAnswer() {
        return displayingAnswer;
    }

    public int getBase() {
        return base;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getResult() {
        return result;
    }

    public String getProblem() {
        return String.valueOf(this.base) + " ^ " + String.valueOf(this.multiplier);
    }

    public void setDisplayingAnswer(boolean displayingAnswer) {
        this.displayingAnswer = displayingAnswer;
    }

    @Override
    public String toString() {
        if(displayingAnswer)
            return String.valueOf(this.base) + " ^ " + String.valueOf(this.multiplier) + " = " + String.valueOf(this.result);
        return String.valueOf(this.base) + " ^ " + String.valueOf(this.multiplier);
    }
}
