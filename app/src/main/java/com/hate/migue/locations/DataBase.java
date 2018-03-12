package com.hate.migue.locations;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.AndroidSupport;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import java.io.IOException;

public class DataBase{

    ObjectContainer db;
    DataBase(String dir){
        openDB(dir);
    }


    void store(Position p){
        ObjectContainer dbses = getSession();
        dbses.store(p);
        dbses.commit();
        dbses.close();
    }

    ObjectSet<Position> query(){
        ObjectContainer dbses = getSession();
        Query consulta = dbses.query();
        consulta.constrain(Position.class);
        ObjectSet<Position> output = consulta.execute();
        dbses.close();
        return output ;
    }


    public EmbeddedConfiguration getDb4oConfig() throws IOException {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.common().add(new AndroidSupport());
        configuration.common().objectClass(Position.class).
                objectField("fecha").indexed(true);
        return configuration;
    }

    void openDB(String dir){
        try {
            db = Db4oEmbedded.openFile(getDb4oConfig(), dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObjectContainer getSession(){
        return db.ext().openSession();
    }

    void close(){
        db.close();
    }


}
