package com.epam.entity;

import com.epam.exception.ShipThreadException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final int POOL_SIZE = 5;
    private final Queue<Dock> listDocks = new LinkedList<>();
    private final Semaphore semaphore = new Semaphore(POOL_SIZE);
    private static final Lock instanceLock = new ReentrantLock();
    private static final Lock collectionLock = new ReentrantLock();

    private static AtomicReference<Port> port;

    private Port() {
    }

    public static AtomicReference<Port> getInstance() {
        if (port == null) {
            instanceLock.tryLock();
            AtomicReference<Port> temp;
            try {
                if (port == null) {
                    temp = new AtomicReference<>(new Port());
                    port = temp;
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return port;
    }

    public Dock getDock() throws ShipThreadException {
        Dock dock;
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new ShipThreadException("While getting a dock exception occurred", e.getCause());
        }
        collectionLock.lock();
        try {
            dock = listDocks.poll();
        } finally {
            collectionLock.unlock();
            semaphore.release();
        }
        return dock;
    }

    public void returnDock(Dock dock) {
        collectionLock.lock();
        try {
            this.listDocks.add(dock);
        } finally {
            collectionLock.unlock();
        }
    }

    public void addDocks(List<Dock> docks) {
        listDocks.addAll(docks);
    }
}
