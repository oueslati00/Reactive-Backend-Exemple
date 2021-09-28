package prg.sid.eventservice;

import java.time.Instant;

class Event{
    private Instant instant;
    private double value;
    private String societeID;

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSocieteID() {
        return societeID;
    }

    public void setSocieteID(String societeID) {
        this.societeID = societeID;
    }
}
