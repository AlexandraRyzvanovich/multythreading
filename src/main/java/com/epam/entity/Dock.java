package com.epam.entity;

public class Dock {
    private boolean loaded;

    public void processShip(Ship ship){
        if(ship.isLoaded()){
            this.loaded = true;
            ship.setLoaded(false);
            System.out.println(ship.shipName + "was unloaded");
        }else {
        }
    }
}
