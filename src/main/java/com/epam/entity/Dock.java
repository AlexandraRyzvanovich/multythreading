package com.epam.entity;

public class Dock {
    private int id;
    private boolean loaded;
    private boolean capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isCapacity() {
        return capacity;
    }

    public void setCapacity(boolean capacity) {
        this.capacity = capacity;
    }

    public void processShip(Ship ship){
        if(ship.isLoaded()){
            this.loaded = true;
            ship.setLoaded(false);
            System.out.println(ship.getShipName() + "was unloaded");
        }else {
        }
    }
}
