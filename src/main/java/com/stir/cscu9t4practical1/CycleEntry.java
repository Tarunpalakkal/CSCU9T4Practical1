package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class CycleEntry extends Entry {

    private String terrain;
    private String tempo;

    public CycleEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String ter, String temp) {
        super(n, d, m, y, h, min, s, dist);
        terrain = ter;
        tempo = temp;
        name = n;
        Calendar inst = Calendar.getInstance();
        inst.set(y,m-1,d,h,min,s);
        dateAndTime = inst;
        distance = dist;
    }

    public String getTempo() {
        return tempo;
    }

    public String getTerrain() {
        return terrain;
    }

    @Override
    public String getEntry () {

        String result = getName() + " cycled " + getDistance() + " km in " + getHour() + ":" + getMin() + ":" + getSec() + " on " + getDay() + "/" + getMonth() + "/" + getYear() + " on " + getTerrain() + " at " + getTempo() + " tempo" + "\n";

        return result;
    } //getEntry
}

