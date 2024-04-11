package org.example.commands;

import org.example.collection.CollectionManager;

/**
 * Команда, выводящая все что хранится в коллекции
 */

public class ShowCommand implements Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. Показывает подробное содержание содержимого коллекции.
     *
     * @return
     */
    @Override
    public boolean execute() {
        if (!collectionManager.getCollection().isEmpty()) {
            collectionManager.show();
        } else {
            System.out.println("Коллекция пустая.");
        }
        return false;
    }
    /**
     * @return Метод, возвращающий описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "команда показывает подробное содержимое всех элементов коллекции";
    }
}
