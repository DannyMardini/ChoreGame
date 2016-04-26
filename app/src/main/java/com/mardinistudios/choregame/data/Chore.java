package com.mardinistudios.choregame.data;

/**
 * Created by mardinid on 4/16/16.
 */
public class Chore {
    public final static String DEFAULT_CHORE_NAME = "New Chore";
    private String name;
    private int pointValue;

    public Chore() {
        this.name = DEFAULT_CHORE_NAME;
        this.pointValue = 0;
    }

    public Chore(String name, int pointValue) {
        this.name = name != null ? name : DEFAULT_CHORE_NAME;
        this.pointValue = pointValue;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }
}
