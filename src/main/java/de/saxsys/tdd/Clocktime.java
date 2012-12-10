package de.saxsys.tdd;


public class Clocktime {

    private static final int ONE_DAY = 24 * 60 * 60;

    private int seconds;

    public Clocktime() {
        this(0, 0, 0);
    }

    public Clocktime(int hours) {
        this(hours, 0, 0);
    }

    public Clocktime(int hours, int minutes) {
        this(hours, minutes, 0);
    }

    public Clocktime(int hours, int minutes, int seconds) {
        this.seconds = ((hours * 60) + minutes) * 60 + seconds;

        this.seconds %= ONE_DAY;

        if (this.seconds < 0) {
            this.seconds += ONE_DAY;
        }
    }

    private int totalSeconds() {
        return seconds;
    }

    public Clocktime(Clocktime time) {
        this.seconds = time.totalSeconds();
    }

    public int seconds() {
        return seconds % 60;
    }

    public int minutes() {
        return (seconds / 60) % 60;
    }

    public int hours() {
        return seconds / 60 / 60;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours(), minutes(), seconds());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return (seconds() + minutes() + hours());
    }

    public void add(int i) {
        this.seconds += i;

        this.seconds = (this.seconds + ONE_DAY) % ONE_DAY;
    }

    public int diff(Clocktime ct) {
        int other = ct.totalSeconds();

        int difference = other - seconds;

        return difference < 0 ? difference + ONE_DAY : difference;
    }


}
