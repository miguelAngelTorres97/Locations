package com.hate.migue.locations;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.GregorianCalendar;

public class Position {
    private LatLng position;
    private Date fecha;

    public Position() {
        this(new Location("provider"));
    }

    public Position(Location position) {
        this(position, new GregorianCalendar().getTime());
    }

    public Position(Location position, Date fecha) {
        this.position = new LatLng(position.getLatitude(), position.getLongitude());
        this.fecha = fecha;
    }
    

    public LatLng getposition() {
        return position;
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
