package org.example.collection;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.exception.InvalidValue;
import org.example.workWithFile.FileManager;
import org.example.workWithFile.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * * Клас, который управляет коллекцией.
 */


public class CollectionManager {

    /**
     * * Коллекци, над которой будет осуществляться работа.
     */

    private ArrayDeque<Ticket> collection;

    /**
     * * Переменная даты инициализации.
     */

    ZonedDateTime initializationDate;

    /**
     * * Конструктор - создание нового объекта менеджера коллекции.
     */
    public CollectionManager() {

        this.collection = new ArrayDeque<>();
    }

    /**
     * * Метод, используемый в команде удаления первого элемента.
     * @return объект ArrayDeque<Ticket>, который хранит коллекцию билетов.
     */
    public ArrayDeque<Ticket> getCollection() {
        return collection;
    }

    /**
     * * Метод, который возвращает информацию о коллекции.
     */
    public void info() {
        System.out.println("Collection type: " + collection.getClass().getName());
        System.out.println("Collection size: " + collection.size());
    }

    /**
     * * Метод, выводящий все коллекцию.
     */

    public void show() {
        for (Ticket element : collection) {
            System.out.println(element);
        }
    }


    /**
     * * Метод для добавления нового элемента в коллекцию.
     */
    public void add(Ticket ticket) throws InvalidValue {
        if (ticket != null) {
            if (ticket.getName() != null && !ticket.getName().isEmpty()) {
                if (isTicketValid(ticket)) {
                    collection.add(ticket);
                    System.out.println("Новый элемент успешно добавлен в коллекцию.");
                } else {
                    System.out.println("Error: Unable to add the ticket to the collection. Please check the ticket conditions.");
                }
            }
        } else {
            System.out.println("Error: Empty ticket passed.");
        }
    }




    /**
     * * Метод, обновляющий элемент коллекции по id.
     */

    public boolean updateById(int id, Ticket updatedTicket) throws InvalidValue {
        for (Ticket ticket : collection) {
            if (ticket.getId() == id) {
                if (updatedTicket != null) {
                    ticket.setName(updatedTicket.getName());
                    ticket.setCoordinates(updatedTicket.getCoordinates());
                    ticket.setPrice(updatedTicket.getPrice());
                    ticket.setType(updatedTicket.getType());
                    ticket.setPerson(updatedTicket.getPerson());
                    System.out.println("Updated ticket with ID " + id);
                    return false;
                } else {
                    throw new InvalidValue("Updated ticket is null.");
                }
            }
        }
        System.out.println("Ticket with ID " + id + " not found.");
        return false;
    }


    /**
     * * Метод, удаляющий элемент из коллекции.
     */
    public boolean removeById(int id) {
        collection.removeIf(ticket -> ticket.getId() == id);
        System.out.println("Removed tickets with ID " + id);
        return false;
    }


    /**
     * * Метод, очищающий коллекцию.
     */

    public void clear() {
        collection.clear();
        System.out.println("Collection cleared.");
    }


    /**
     * * Метод, который сохраняет коллекцию.
     */

//    public void save(String inputFile) {
//        JsonParser jsonParser = new JsonParser();
//        FileManager fileManager = new FileManager();
//
//        // Создаем массив типа Ticket
//        Ticket[] ticketArray = new Ticket[collection.size()];
//
//        // Копируем элементы из коллекции в массив
//        String values = jsonParser.parseToFile(collection.toArray(ticketArray));
//        fileManager.writeToFile(values,inputFile);
//    }


    public void save(String inputFile) {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collection);
            try (FileWriter fileWriter = new FileWriter(inputFile)) {
                fileWriter.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }




    public void saveCollection() {
        JsonParser jsonParser = new JsonParser();
        FileManager fileManager = new FileManager();

        // Создаем массив типа Ticket
        Ticket[] ticketArray = new Ticket[collection.size()];

        // Копируем элементы из коллекции в массив
        String values = jsonParser.parseToFile(collection.toArray(ticketArray));

        // Сохраняем коллекцию в файл
        fileManager.writeToFile(values, "collection.json");
    }


    /**
     * * Метод, который удаляет первый элемент.
     */

    public void removeFirst() {
        if (!collection.isEmpty()) {
            Ticket removedElement = collection.pollFirst();
            System.out.println("Removed first element from the collection: " + removedElement);
        } else {
            System.out.println("Collection is empty. No elements to remove.");
        }
    }

    /**
     * * Метод, который выводит первый элемент.
     */

    public void head() {
        if (!collection.isEmpty()) {
            System.out.println("First element in the collection: " + collection.getFirst());
        } else {
            System.out.println("Collection is empty.");
        }
    }



//    public void filter_starts_with_name(String name) {
//        collection.stream()
//                .filter(ticket -> ticket.getName().startsWith(name))
//                .forEach(System.out::println);
//    }




    public boolean isTicketValid(Ticket ticket) {
        return ticket != null &&
                ticket.getId() > 0 &&
                isIdUnique((int) ticket.getId()) &&
                ticket.getName() != null &&
                !ticket.getName().isEmpty() &&
                ticket.getCoordinates() != null &&
                ticket.getCreationDate() != null &&
                ticket.getPerson() != null &&
                ticket.getPrice() > 0;
    }

    private boolean isIdUnique(int id) {
        return collection.stream().noneMatch(t -> t.getId() == id);
    }


    /**
     * * Метод, который выводит элемент, у которого название задано подстрокой.
     */


    public ArrayDeque<Ticket> filterStartsWithName(String nameSubstring) {
        List<Ticket> filteredList = collection.stream()
                .filter(ticket -> ticket.getName().startsWith(nameSubstring))
                .collect(Collectors.toList());
        ArrayDeque<Ticket> filteredDeque = new ArrayDeque<>(filteredList);
        return filteredDeque;
    }

    /**
     * * Метод, который фильтрует по росту человека.
     */


    public void print_field_descending_person() {
        if (!collection.isEmpty()) {
            List<Ticket> sortedTickets = collection.stream()
                    .sorted(Comparator.comparing(ticket -> ticket.getPerson().getHeight(), Comparator.reverseOrder()))
                    .collect(Collectors.toList());

            System.out.println("Список билетов в порядке убывания роста пассажира:");
            for (Ticket ticket : sortedTickets) {
                System.out.println("Ticket ID: " + ticket.getId());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }


    /**
     * * Метод, который фильтрует по цене билета.
     */


    public void print_field_descending_price() {
        if (!collection.isEmpty()) {
            List<Ticket> sortedTickets = collection.stream()
                    .sorted(Comparator.comparingInt(Ticket::getPrice).reversed())
                    .collect(Collectors.toList());

            System.out.println("Список билетов в порядке убывания цены:");
            for (Ticket ticket : sortedTickets) {
                System.out.println("Ticket ID: " + ticket.getId() + "Ticket price: " + ticket.getPrice());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public void execute_script(String commandName, String[] commandArgs) throws InvalidValue {
        switch (commandName) {
//            case "add":
//                Coordinates coordinates = new Coordinates(Float.parseDouble(commandArgs[1]), Double.parseDouble(commandArgs[2]));
//                Ticket ticket = new Ticket(commandArgs[0], coordinates, commandArgs[3], commandArgs[4]);
//                add(ticket);
//                break;
            case "info":
                info();
                break;
            case "show":
                show();
                break;
//            case "update":
//                teBupdayId(Integer.parseInt(commandArgs[0]), commandArgs[1], Double.parseDouble(commandArgs[2]), Double.parseDouble(commandArgs[3]), commandArgs[4], commandArgs[5]);
//                break;
            case "remove_by_id":
                removeById(Integer.parseInt(commandArgs[0]));
                break;
            case "clear":
                clear();
                break;
//            case "save":
//                save();
//                break;
            case "execute_script":
                // You can add the implementation of the "execute_script" command here if needed
                break;
            case "exit":
                // Do nothing, the command is already handled by the CommandInvoker
                break;
            case "remove_first":
                removeFirst();
                break;
            case "head":
                head();
                break;
//            case "add_if_min":
//                add_if_min(commandArgs[0], Double.parseDouble(commandArgs[1]), Double.parseDouble(commandArgs[2]), commandArgs[3], commandArgs[4]);
//                break;
            case "filter_starts_with_name":
                filterStartsWithName(commandArgs[0]);
                break;
            case "print_field_descending_person":
                print_field_descending_person();
                break;
            case "print_field_descending_price":
                print_field_descending_price();
                break;
            default:
                throw new InvalidValue("Unknown command: " + commandName);
        }
    }



    public void addIfMin(Ticket newTicket) throws InvalidValue {
        Ticket minPriceTicket = getMinPriceTicket();
        if (minPriceTicket == null || newTicket.getPrice() < minPriceTicket.getPrice()) {
            add(newTicket);
            System.out.println("New ticket added successfully.");
        } else {
            System.out.println("The price of the new ticket is not less than the price of the minimum price ticket in the collection.");
        }
    }

    public Ticket getMinPriceTicket() {
        if (collection.isEmpty()) {
            return null;
        }
        Ticket minPriceTicket = collection.getFirst();
        for (Ticket ticket : collection) {
            if (ticket.getPrice() < minPriceTicket.getPrice()) {
                minPriceTicket = ticket;
            }
        }
        return minPriceTicket;
    }

}