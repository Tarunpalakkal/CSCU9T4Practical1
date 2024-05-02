// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e)
   {
       String message = "Record added\n";
       String name = e.getName();
       int day = e.getDay();
       int month = e.getMonth();
       int year = e.getYear();
       boolean record = false;

       ListIterator<Entry> iter = tr.listIterator();
       while (iter.hasNext())
       {
           Entry current = iter.next();
           if(current.getName().equalsIgnoreCase(name) && current.getDay()==day && current.getMonth()==month && current.getYear()==year)
           {
               message = "Record already exists.\n";
               record=true;
               break;
           }

       }

       if (!record) {
           tr.add(e);
       }

       return message;

   } // addClass

    public String deleteEntry(String name, int day, int month, int year)
    {
        String message = "Record doesn't exist\n";

        ListIterator<Entry> iter = tr.listIterator();
        while (iter.hasNext())
        {
            Entry current = iter.next();
            if(current.getName().equalsIgnoreCase(name) && current.getDay()==day && current.getMonth()==month && current.getYear()==year)
            {
                message = "Record deleted.\n";
                tr.remove(current);
                break;
            }

        }


        return message;
    }
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry

    public String lookupEntries(int d, int m, int y)
    {

        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result += current.getEntry();
        }
        if(result.isBlank())
        {
            result="Sorry couldn't find anything for this date";
        }
        return result;

    }
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord
