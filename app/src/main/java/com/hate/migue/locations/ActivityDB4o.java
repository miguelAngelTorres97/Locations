package com.hate.migue.locations;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.AndroidSupport;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import java.io.IOException;
import java.util.GregorianCalendar;

public class ActivityDB4o extends AppCompatActivity {

    private static final String TAG = ActivityDB4o.class.getSimpleName();

    ObjectContainer objectContainer;

    public EmbeddedConfiguration getDb4oConfig() throws IOException {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.common().add(new AndroidSupport());
        configuration.common().objectClass(Position.class).
                objectField("fecha").indexed(true);
        return configuration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db4o);

        objectContainer = openDataBase();

        Position loc = new Position();
        objectContainer.store(loc);
        objectContainer.commit();

        loc = new Position(new Location("provider"));
        objectContainer.store(loc);
        objectContainer.commit();

        loc = new Position(new Location("proveedor"), new GregorianCalendar(2018,1,22).getTime());
        objectContainer.store(loc);
        objectContainer.commit();

        Query consulta = objectContainer.query();
        consulta.constrain(Position.class);
        ObjectSet<Position> localizaciones = consulta.execute();
        for(Position localizacion: localizaciones){
            Log.v(TAG, "1: " + localizacion.toString());
        }

        ObjectSet<Position> locs = objectContainer.query(
                new Predicate<Position>() {
                    @Override
                    public boolean match(Position loc) {
                        return loc.getFecha().equals(new GregorianCalendar(2018,1,22).getTime());
                    }
                });
        for(Position localizacion: locs){
            Log.v(TAG, "2: " + localizacion.toString());
        }
        objectContainer.close();
    }

    private ObjectContainer openDataBase() {
        ObjectContainer objectContainer = null;
        try {
            String name = getExternalFilesDir(null) + "/ejemplo.db4o";
            objectContainer = Db4oEmbedded.openFile(getDb4oConfig(), name);
        } catch (IOException e) {
            Log.v(TAG, e.toString());
        }
        return objectContainer;
    }
}
