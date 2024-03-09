package org.example.collection;
import java.util.ArrayDeque;

public class CollectionManager {
    private ArrayDeque<String> collection = new ArrayDeque<>();

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

    }

    public void show() {
        // проходить по коллекции for each?
    }

    public void add(String element) {
        collection.add(element);
        System.out.println("Added: " + element);
    }

    public void updateById(int id, String element) {
        // Реализация команды update id {element}
    }

    public void removeById(int id) {
        // Реализация команды remove_by_id id
    }

    public void clear() {
        collection.clear();
        System.out.println("Collection cleared.");
    }

    public void remove_first(){
        if (!collection.isEmpty()) {
            String removedElement = collection.pollFirst();
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

    public void  add_if_min(){}

    public void  ilter_starts_with_name(){}

    public void print_field_descending_person(){}

    public void print_field_descending_price(){}

}
