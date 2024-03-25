package org.example.commands;


import org.example.collection.CollectionManager;
/**
 * Класс команды, которая удаляет элемент из коллекции по его id.
 */
public class RemoveByIdCommand implements Command {
    private CollectionManager collectionManager;
    private int id;

    public RemoveByIdCommand(CollectionManager collectionManager, int id){
        this.collectionManager = collectionManager;
        this.id = id;
    }

    @Override
    public void execute() {
        collectionManager.removeById(id);
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
}