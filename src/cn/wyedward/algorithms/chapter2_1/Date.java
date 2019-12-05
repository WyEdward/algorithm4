package cn.wyedward.algorithms.chapter2_1;

public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }
    public int day(){
        return day;
    }
    public int month(){
        return month;
    }
    public int year(){
        return year;
    }

    @Override
    public int compareTo(Date that) {
        if(this.year > that.year) return +1;
        if(this.year < that.year) return -1;
        if(this.month > that.month) return +1;
        if(this.month < that.month) return -1;
        if(this.day > that.day) return +1;
        if(this.day < that.day) return -1;
        return 0;
    }
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}
