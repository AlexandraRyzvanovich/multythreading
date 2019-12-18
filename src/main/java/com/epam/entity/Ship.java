package com.epam.entity;

import java.util.concurrent.atomic.AtomicReference;

public class Ship implements Runnable {
    String shipName;
    boolean isLoaded;

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
            dock.processShip(this);
            System.out.println("ship is processed");
        }finally {
            port.get().returnDock(dock);
        }
    }
}
