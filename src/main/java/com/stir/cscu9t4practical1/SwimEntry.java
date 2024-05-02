package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class SwimEntry extends Entry {

    private String location;

    public SwimEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String where) {
        super(n, d, m, y, h, min, s, dist);
        location = where;
        name = n;
        Calendar inst = Calendar.getInstance();
        inst.set(y,m-1,d,h,min,s);
        dateAndTime = inst;
        distance = dist;
    }

    public String getWhere() {
        return location;
    }

    @Override
    public String getEntry () {
        String result = getName()+" swam " + getDistance() + " km " + getWhere() + " in " +getHour()+":"+getMin()+":"+ getSec() + " on "
                +getDay()+"/"+getMonth()+"/"+getYear() + "\n";
        return result;
    } //getEntry
}

