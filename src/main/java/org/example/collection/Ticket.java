package org.example.collection;

import org.example.exception.InvalidValue;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Ticket {

    private static final AtomicLong idGenerator = new AtomicLong(1);
    private long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price;
    private TicketType type;
    private Person person;
    private int ownerId; // New attribute for ownerId

    public Ticket(String name, Coordinates coordinates, int price, TicketType type, Person person) {
        this.id = idGenerator.getAndIncrement();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.price = price;
        this.type = type;
        this.person = person;

    }

    public Ticket(int id, String name, Coordinates coordinates, LocalDate creationDate, int price, TicketType type, Person person) {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValue {
        if (name == null || name.isEmpty()) {
            throw new InvalidValue("Имя не может быть пустым");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws InvalidValue {
        if (coordinates == null) {
            throw new InvalidValue("Координата не может быть пустой");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws InvalidValue {
        if (price <= 0) {
            throw new InvalidValue("Цена должна быть положительной");
        }
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) throws InvalidValue {
        if (type == null) {
            throw new InvalidValue("Тип билета не может быть пустым");
        }
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) throws InvalidValue {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", type=" + type +
                ", person=" + person +
                '}';
    }
}