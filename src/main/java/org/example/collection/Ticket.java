package org.example.collection;

import org.example.exception.InvalidValue;

import java.time.LocalDate;

public class Ticket {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле может быть null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValue {
        if(name == null){throw new InvalidValue("Имя не может быть пустым");}
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws InvalidValue{
        if(coordinates == null){throw new InvalidValue("Кордината не может быть пустой");}
        this.coordinates = coordinates;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) throws InvalidValue{
        if(creationDate == null){throw new InvalidValue("");}
        this.creationDate = creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws InvalidValue{
        if(price <= 0){throw new InvalidValue("Цена должна быть положительная");}
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) throws InvalidValue{
        if(type==null){throw new InvalidValue("");}
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person)throws InvalidValue {
        if(person==null){throw new InvalidValue("");}
        this.person = person;
    }

}
