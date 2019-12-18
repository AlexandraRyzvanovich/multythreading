package com.epam.entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final int POOL_SIZE = 10;
    private Semaphore semaphore = new Semaphore(POOL_SIZE);
    private Queue<Dock> listDocks = new LinkedList<>();;
    private static final Lock instanceLock = new ReentrantLock();
    private static final Lock collectionLock = new ReentrantLock();

    private static AtomicReference<Port> port;

    private Port() {
    }

    public static AtomicReference<Port> getInstance() {
        if (port == null) {
            instanceLock.tryLock();
            AtomicReference<Port> temp;
            try{
                if(port == null){
                    temp = new AtomicReference<Port>(new Port());
                    port = temp;
                }
            }finally {
                instanceLock.unlock();
            }
        }
        return port;
    }

    public Dock getDock() {
        Dock dock = null;
        try{
            semaphore.acquire();
            collectionLock.lock();
            try {
                dock = listDocks.poll();
            }finally {
                collectionLock.unlock();
            }
        }catch (InterruptedException e) {
            semaphore.release();
           // throw new CallInactiveThreadException("Thread operation error, cause ",e);
        }
        return dock;
    }

    public void returnDock(Dock dock) {
        collectionLock.lock();
        try{
            this.listDocks.add(dock);
        }finally {
            collectionLock.unlock();
            semaphore.release();
        }
    }
}
