package com.epam.entity;

import com.epam.exception.ShipThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

public class Ship implements Runnable{
    private static final Logger LOGGER = LogManager.getLogger();
    private final String shipName;
    private boolean isLoaded;
    private boolean isProcessed;

    public Ship(String shipName, boolean isLoaded) {
        this.shipName = shipName;
        this.isLoaded = isLoaded;
    }

    public String getShipName() {
        return shipName;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    @Override
    public void run() {
        AtomicReference<Port> port = Port.getInstance();
        Dock dock = null;
        while (!isProcessed) {
            try {
                dock = port.get().getDock();
                LOGGER.debug("Trying to process " + this.shipName);
                if (this.isLoaded && !dock.isLoaded()) {
                    dock.unloadShip(this);
                    isProcessed = true;
                    LOGGER.debug(this.shipName + " is processed");
                } else if (!this.isLoaded && dock.isLoaded()) {
                    dock.loadShip(this);
                    isProcessed = true;
                    LOGGER.debug(this.shipName + " is processed");
                } else {
                    LOGGER.debug(this.shipName + " is waiting for dock");
                }
            } catch (ShipThreadException e) {
                LOGGER.error("While processing Ship thread exception occurred", e.getCause());
            } finally {
                port.get().returnDock(dock);
            }
        }
    }
}
