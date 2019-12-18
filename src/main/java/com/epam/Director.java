package com.epam;

import com.epam.creator.DockCreator;
import com.epam.creator.ShipsCreator;
import com.epam.entity.Dock;
import com.epam.entity.Port;
import com.epam.entity.Ship;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Director {
    private static final String SHIPS_JSON_FILE_PATH = "src/main/resources/ships";

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ShipsCreator creator = new ShipsCreator();
        DockCreator dockCreator = new DockCreator();
        List<Ship> shipsQueue =  creator.create(SHIPS_JSON_FILE_PATH);
        AtomicReference<Port> port = Port.getInstance();
        List<Dock> list = dockCreator.create("src/main/resources/docks");
        port.get().addDocks(list);

        int shipsQuantity = shipsQueue.size();
        ExecutorService executorService = Executors.newFixedThreadPool(shipsQuantity);
        for(int i = 0; i < shipsQuantity; i++) {
            executorService.submit(shipsQueue.get(i));
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
