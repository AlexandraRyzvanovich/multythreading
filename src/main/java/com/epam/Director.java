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
    private static final String DOCKS_JSON_FILE_PATH = "src/main/resources/docks";

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ShipsCreator creator = new ShipsCreator();
        DockCreator dockCreator = new DockCreator();
        List<Ship> shipsQueue =  creator.create(SHIPS_JSON_FILE_PATH);
        AtomicReference<Port> port = Port.getInstance();
        List<Dock> listDocks = dockCreator.create(DOCKS_JSON_FILE_PATH);
        port.get().addDocks(listDocks);

        int dockQuantity = listDocks.size();
        ExecutorService executorService = Executors.newFixedThreadPool(dockQuantity);
        for(int i = 0; i < shipsQueue.size() ; i++) {
            executorService.submit(shipsQueue.get(i));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Zaebok");
    }
}
