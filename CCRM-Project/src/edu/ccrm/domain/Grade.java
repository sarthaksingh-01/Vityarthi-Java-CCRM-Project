package edu.ccrm.domain;

public enum Grade {
    A(10), B(9), C(8), D(7), F(0);

    private final int points;
    Grade(int p) { points=p; }
    public int getPoints() { return points; }
}