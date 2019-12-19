package com.epam.creator;

import com.epam.entity.Ship;
import com.epam.exception.CreatorException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShipCreator {
    public final List<Ship> ships = new ArrayList<>();

    public List<Ship> create(String filePath) throws CreatorException {
        try {
            Gson gson = new Gson();
            Type shipType = new TypeToken<Collection<Ship>>() {}.getType();
            Collection<Ship> shipsArray = gson.fromJson(new FileReader(filePath), shipType);
            ships.addAll(shipsArray);
        } catch (FileNotFoundException e) {
            throw new CreatorException("File path not found", e.getCause());
        }
        return ships;
    }
}
