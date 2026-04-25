package com.example.lab1;

import java.io.Serializable;

public class Student implements Serializable {
    private final String name;
    private final float score1, score2, score3;
    private final float average;
    private final String grade;

    public Student(String name, float score1, float score2, float score3) {
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.average = (score1 + score2 + score3) / 3f;
        this.grade = calcGrade(this.average);
    }

    private String calcGrade(float avg) {
        if (avg >= 8.0f) return "Excellent";
        if (avg >= 6.5f) return "Good";
        if (avg >= 5.0f) return "Average";
        return "Weak";
    }

    public String getName() { return name; }
    public float getScore1() { return score1; }
    public float getScore2() { return score2; }
    public float getScore3() { return score3; }
    public float getAverage() { return average; }
    public String getGrade() { return grade; }
}
