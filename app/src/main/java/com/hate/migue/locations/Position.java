package com.hate.migue.locations;

import android.location.Location;

import java.util.Date;
import java.util.GregorianCalendar;

public class Position {
    private Location position;
    private Date fecha;

    public Position() {
        this(new Location("provider"));
    }

    public Position(Location position) {
        this(position, new GregorianCalendar().getTime());
    }

    public Position(Location position, Date fecha) {
        this.position = position;
        this.fecha = fecha;
    }
    

    public Location getposition() {
        return position;
    }

    public void setposition(Location position) {
        this.position = position;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "position{" +
                "position=" + position.toString() +
                ", fecha=" + fecha.toString() +
                '}';
    }
}
