package org.example.workWithFile;


import org.example.collection.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public List<Ticket> parseToCollection(String data) {

        // Парсим JSON и добавляем билеты в ArrayDeque
        JSONArray jsonArray = new JSONArray(data);
        ArrayList<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            // Парсинг координат
            JSONObject coordinatesObject = jsonObject.getJSONObject("coordinates");
            float x = coordinatesObject.getFloat("x");
            float y = coordinatesObject.getFloat("y");
            // Парсинг даты создания билета
            String creationDateString = jsonObject.getString("creationDate");
            LocalDate creationDate = LocalDate.parse(creationDateString);
            // Парсинг цены
            int price = jsonObject.getInt("price");
            // Парсинг типа билета (если он есть)
            TicketType type = null;
            if (!jsonObject.isNull("type")) {
                String typeString = jsonObject.getString("type");
                type = TicketType.valueOf(typeString);
            }
            // Парсинг информации о человеке (если она есть)
            Person person = null;
            if (!jsonObject.isNull("person")) {
                JSONObject personObject = jsonObject.getJSONObject("person");
                Long height = null;
                if (!personObject.isNull("height")) {
                    height = personObject.getLong("height");
                }
                Float weight = null;
                if (!personObject.isNull("weight")) {
                    weight = (float) personObject.getDouble("weight");
                }
                EyeColor eyeColor = EyeColor.valueOf(personObject.getString("eyeColor"));
                HairColor hairColor = HairColor.valueOf(personObject.getString("hairColor"));
                person = new Person(height, weight, eyeColor, hairColor);
            }

            // Создаем объект Ticket и добавляем его в ArrayList
            Ticket ticket = new Ticket(id, name, new Coordinates(x, y), creationDate, price, type, person);
            tickets.add(ticket);
        }
        return tickets;
    }
}
