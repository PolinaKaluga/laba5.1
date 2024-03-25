package org.example.collection;
import org.example.exception.InvalidValue;

import java.util.ArrayDeque;
import java.util.Comparator;

public class CollectionManager {
    private ArrayDeque<Ticket> collection = new ArrayDeque<>();

    public void help() {
        System.out.println(":");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("remove_first : удалить первый элемент из коллекции");
        System.out.println("head : вывести первый элемент коллекции");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
        System.out.println("print_field_descending_person : вывести значения поля person всех элементов в порядке убывания");
        System.out.println("print_field_descending_price : вывести значения поля price всех элементов в порядке убывания");
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

    public void removeFirst(){
        if (!collection.isEmpty()) {
            Ticket removedElement = collection.pollFirst();
            System.out.println("Removed first element from the collection: " + removedElement);
        } else {
            System.out.println("Collection is empty. No elements to remove.");
        }
    }

    public void head(){
        if (!collection.isEmpty()) {
            System.out.println("First element in the collection: " + collection.getFirst());
        } else {
            System.out.println("Collection is empty.");
        }
    }

    public void add_if_min(Ticket newTicket){
        if (collection.isEmpty() || newTicket.getPrice() < collection.stream().mapToInt(Ticket::getPrice).min().orElse(Integer.MAX_VALUE)) {
            add(newTicket);
            System.out.println("Added a new ticket as its price is the lowest in the collection.");
        } else {
            System.out.println("The price of the new ticket is not the lowest in the collection.");
        }
    }

    public void filter_starts_with_name(String name){
        collection.stream()
                .filter(ticket -> ticket.getName().startsWith(name))
                .forEach(System.out::println);
    }

//    public void print_field_descending_person(){

//    } тут не очень знаю как


    public void print_field_descending_price(){
        collection.stream()
                .sorted(Comparator.comparingInt(Ticket::getPrice).reversed())
                .forEach(System.out::println);
    }
}

