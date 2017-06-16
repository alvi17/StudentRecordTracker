package alvi17.studentrecordtracker;

/**
 * Created by User on 5/5/2017.
 */

public class Batch {

    public String name;
    public Long year;

    public Batch()
    {

    }

    public Batch(String name,Long year)
    {
        this.name=name;
        this.year=year;
    }

    public String getName() {
        return name;
    }

    public Long getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
