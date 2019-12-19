package com.epam.creator;

import com.epam.entity.Dock;
import com.epam.exception.CreatorException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DockCreator {
    public List<Dock> docks = new ArrayList<>();

    public List<Dock> create(String filePath) throws CreatorException {
        try {
            Gson gson = new Gson();
            Type shipType = new TypeToken<Collection<Dock>>() {}.getType();
            Collection<Dock> docksArray = gson.fromJson(new FileReader(filePath), shipType);
            docks.addAll(docksArray);
        } catch (FileNotFoundException e) {
            throw new CreatorException("File path not found", e.getCause());
        }
        return docks;
    }
}
