package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class SprintEntry extends Entry {

    private int repetition;
    private int recovery;

    public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int rep, int rec) {
        super(n, d, m, y, h, min, s, dist);
        repetition = rep;
        recovery = rec;
        name = n;
        Calendar inst = Calendar.getInstance();
        inst.set(y,m-1,d,h,min,s);
        dateAndTime = inst;
        distance = dist;
    }

    public int getRecovery() {
        return recovery;
    }

    public int getRepetitions() {
        return repetition;
    }

    @Override
    public String getEntry () {

        String result = getName() + " sprinted " + getRepetitions() + "x" + (int) getDistance() + "m in " + getHour() + ":" + getMin() + ":" + getSec() + " with " + getRecovery() + " minutes recovery on " + getDay() + "/" + getMonth() + "/" + getYear() + "\n";


        return result;
    } //getEntry
}

