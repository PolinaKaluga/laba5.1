package org.example.commands;


import org.example.collection.CollectionManager;

/**
 * Команда, сохраняющая коллекцию
 */
public class SaveCommand implements Command{
    /**
     * Поле, хранящее адрес файла, куда следует сохранить коллекцию.
     */
    private String inputFile;
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     * @param inputFile         Хранит адрес файла, куда следует сохранить элементы коллекции.
     */
    public SaveCommand(CollectionManager collectionManager, String inputFile){
        this.collectionManager=collectionManager;
        this.inputFile=inputFile;
    }
    /**
     * Метод, сохраняющий коллекцию в указанном файле в формате XML. В случае некорректной работы высветится ошибка.
     */

    @Override
    public void execute() {
        collectionManager.save(inputFile);
        System.out.println("Коллекция была сохранена.");
    }
    /**
     * @return Метод, возвращающий описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "сохраняет коллекцию в указанный файл";
    }
}
