package br.upe.garanhus.esw.pweb.model.service;

import java.util.ArrayList;
import java.util.List;

import br.upe.garanhus.esw.pweb.model.Cat;
import br.upe.garanhus.esw.pweb.model.DTO.CatDTO;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class CatService {

    private Cat cat;

    public CatService() {
        cat = new Cat();
    }

    public JsonArray getAllCats() {
        JsonArray jsonArray = null;
        try {
            List<CatDTO> catList = cat.parseData(cat.fetchData());

            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            for (CatDTO catDTO : catList) {
                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("id", catDTO.getId())
                        .add("url", catDTO.getUrl())
                        .add("width", catDTO.getWidth())
                        .add("height", catDTO.getHeight()));
            }

            jsonArray = jsonArrayBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public Cat getCatById(int Id) {
        return cat;
    }
}
