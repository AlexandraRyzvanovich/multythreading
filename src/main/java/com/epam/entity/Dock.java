package com.epam.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dock {
    private static final Logger LOGGER = LogManager.getLogger();
    private int id;
    private boolean loaded;
    private int capacity;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int setCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void unloadShip(Ship ship){
            this.loaded = true;
            ship.setLoaded(false);
            LOGGER.info(ship.getShipName() + " was unloaded");

    }

    public void loadShip(Ship ship){
            this.loaded = false;
            ship.setLoaded(true);
            LOGGER.info(ship.getShipName() + " was loaded");

    }
}
