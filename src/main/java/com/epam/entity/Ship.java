package com.epam.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

public class Ship implements Runnable{
    private static final Logger LOGGER = LogManager.getLogger();
    private String shipName;
    private boolean isLoaded;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    @Override
    public void run() {
        AtomicReference<Port> port = Port.getInstance();
        Dock dock = port.get().getDock();
        try {
            System.out.println("Trying to process ship");
            if(this.isLoaded() && !dock.isLoaded()) {
                dock.unloadShip(this);
                notify();
            }else if(this.isLoaded() && dock.isLoaded()) {
                this.wait();
            }else if(!this.isLoaded() && dock.isLoaded()){
                dock.loadShip(this);
                notify();
            }else {
                this.wait();
            }
            System.out.println("ship is processed");
        } catch (InterruptedException e) {
            LOGGER.error("Ship thread run failed", e.getCause());

        }
    }
}
