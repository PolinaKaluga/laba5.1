package org.example.collection;


import org.example.exception.InvalidValue;
import org.example.workWithFile.FileManager;
import org.example.workWithFile.JsonParser;

import java.util.ArrayDeque;
import java.util.Comparator;

public class CollectionManager {

    private ArrayDeque<Ticket> collection;

    public CollectionManager() {

        this.collection = new ArrayDeque<>();
    }

    public void info() {
        System.out.println("Collection size: " + collection.size());
    }

    public void show() {
        for (Ticket element : collection) {
            System.out.println(element);
        }
    }

    public void add(Ticket element) {
        collection.add(element);
        System.out.println("Added: " + element);
    }

    public void updateById(int id, Ticket updatedTicket) throws InvalidValue {
        for (Ticket ticket : collection) {
            if (ticket.getId() == id) {
                ticket.setName(updatedTicket.getName());
                ticket.setCoordinates(updatedTicket.getCoordinates());
                ticket.setCreationDate(updatedTicket.getCreationDate());
                ticket.setPrice(updatedTicket.getPrice());
                ticket.setType(updatedTicket.getType());
                ticket.setPerson(updatedTicket.getPerson());
                System.out.println("Updated ticket with ID " + id);
                return;
            }
        }
        System.out.println("Ticket with ID " + id + " not found.");
    }

    public void removeById(int id) {
        collection.removeIf(ticket -> ticket.getId() == id);
        System.out.println("Removed tickets with ID " + id);
    }

    public void clear() {
        collection.clear();
        System.out.println("Collection cleared.");
    }

    public void save(String inputFile) {
        JsonParser jsonParser = new JsonParser();
        FileManager fileManager = new FileManager();

        // Создаем массив типа Ticket
        Ticket[] ticketArray = new Ticket[collection.size()];

        // Копируем элементы из коллекции в массив
        String values = jsonParser.parseToFile(collection.toArray(ticketArray));
        fileManager.writeToFile(values,inputFile);
    }

    public void removeFirst() {
        if (!collection.isEmpty()) {
            Ticket removedElement = collection.pollFirst();
            System.out.println("Removed first element from the collection: " + removedElement);
        } else {
            System.out.println("Collection is empty. No elements to remove.");
        }
    }

    public void head() {
        if (!collection.isEmpty()) {
            System.out.println("First element in the collection: " + collection.getFirst());
        } else {
            System.out.println("Collection is empty.");
        }
    }

    public void add_if_min(Ticket newTicket) {
        if (collection.isEmpty() || newTicket.getPrice() < collection.stream().mapToInt(Ticket::getPrice).min().orElse(Integer.MAX_VALUE)) {
            add(newTicket);
            System.out.println("Added a new ticket as its price is the lowest in the collection.");
        } else {
            System.out.println("The price of the new ticket is not the lowest in the collection.");
        }
    }

    public void filter_starts_with_name(String name) {
        collection.stream()
                .filter(ticket -> ticket.getName().startsWith(name))
                .forEach(System.out::println);
    }

//    public void print_field_descending_person(){

//    } тут не очень знаю как


    public void print_field_descending_price() {
        collection.stream()
                .sorted(Comparator.comparingInt(Ticket::getPrice).reversed())
                .forEach(System.out::println);
    }
}