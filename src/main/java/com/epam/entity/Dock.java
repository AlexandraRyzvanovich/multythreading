package com.epam.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dock {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int id;
    private boolean loaded;
    private int capacity;

    public Dock(int id, boolean loaded, int capacity) {
        this.id = id;
        this.loaded = loaded;
        this.capacity = capacity;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void unloadShip(Ship ship){
            this.setLoaded(true);
            ship.setLoaded(false);
            LOGGER.info(ship.getShipName() + " was unloaded");
    }

    public void loadShip(Ship ship){
            this.loaded = false;
            ship.setLoaded(true);
            LOGGER.info(ship.getShipName() + " was loaded");
    }
}
