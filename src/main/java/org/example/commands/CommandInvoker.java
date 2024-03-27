package org.example.commands;


import org.example.collection.CollectionManager;
import org.example.commands.*;
import org.example.io.UserIO;
import org.example.workWithFile.TicketFieldsReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, через который осуществляется исполнение команд. Хранит коллекции всех существующих команд.
 */
public class CommandInvoker {
    /**
     * Коллекция команд без дополнительных аргументов, которые записываются с новой строки.
     */
    private HashMap<String, Command> commandsWithoutArguments;
    /**
     * Коллекция команд с дополнительными аргументами, которые записываются с новой строки.
     */
    private HashMap<String, CommandWithArguments> commandsWithArguments;
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Поле, хранящее ссылку на объект класса UserIO.
     */
    private UserIO userIO;
    /**
     * Поле, хранящее строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     */
    private String inputFile;
    /**
     * Поле, хранящее ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    private TicketFieldsReader ticketFieldsReader;
    /**
     * Поле, хранящее объект класса ExecuteScript.Script.
     */
//    ExecuteScriptCommand.Script script;
    /**
     * Поле, хранящее список команд
     */
    ArrayList<String> commandsHistoryList = new ArrayList<>();

    /**
     * Использование файла
     * Конструктор класса. Внутри вызывается метод putCommands, добавляющий команды в коллекции команд, создается новый объект класса ExecuteScript.Script.
     * @param collectionManager  Хранит ссылку созданный в объекте Application объект CollectionManager.
     * @param userIO             Хранит ссылку на объект класса UserIO.
     * @param inputFile          Хранит строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     * @param ticketFieldsReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, String inputFile, TicketFieldsReader ticketFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.inputFile = inputFile;
        this.ticketFieldsReader = ticketFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
//        this.script = new ExecuteScriptCommand.Script();
        this.putCommands();
    }

    /**
     * Использование скрипта
     * Конструктор класса. Внутри вызывается метод putCommands, инициализируется поле, в которое присваивается существующий объект класса ExecuteScript.Script.
     * @param collectionManager  Хранит ссылку на созданный в объекте Application объект CollectionManager.
     * @param userIO             Хранит ссылку на объект класса UserIO.
     * @param ticketFieldsReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, TicketFieldsReader ticketFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.ticketFieldsReader = ticketFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
//        this.script = script;
        this.putCommands();
    }

    /**
     * Метод, добавляющий команды в соответствующие им коллекции.
     */
    private void putCommands() {
        commandsWithoutArguments.put("info", new InfoCommand(collectionManager));
        commandsWithoutArguments.put("show", new ShowCommand(collectionManager));
        commandsWithoutArguments.put("clear", new ClearCommand(collectionManager));
        commandsWithoutArguments.put("save", new SaveCommand(collectionManager, inputFile));
        commandsWithoutArguments.put("exit", new ExitCommand());
        commandsWithoutArguments.put("help", new HelpCommand(commandsWithoutArguments, commandsWithArguments));
//        commandsWithoutArguments.put("history", new HistoryCommand(commandsHistoryList));
//        commandsWithoutArguments.put("print_field_descending_author", new PrintFieldDescendingAuthor(collectionManager));
//        commandsWithoutArguments.put("print_field_ascending_difficulty", new PrintFieldAscendingDifficultyCommand(collectionManager));commandsWithoutArguments.put("help", new HelpCommand(commandsWithoutArguments, commandsWithArguments));
//        commandsWithoutArguments.put("group_counting_by_difficulty", new GroupCountingByDifficult(collectionManager));
//        commandsWithoutArguments.put("insert",new InsertElementWithoutIDCommand(collectionManager,userIO,labWorkFieldsReader));

//        commandsWithArguments.put("insert_with_id", new InsertElementCommand(collectionManager, userIO, labWorkFieldsReader));
//        commandsWithArguments.put("update", new UpdateElementCommand(collectionManager, userIO));
//        commandsWithArguments.put("remove_key", new RemoveKeyElementCommand(collectionManager));
//        commandsWithArguments.put("execute_script", new ExecuteScriptCommand(collectionManager, labWorkFieldsReader, script));
//        commandsWithArguments.put("remove_lower", new RemoveLowerElement(collectionManager));
//        commandsWithArguments.put("remove_greater_key", new RemoveGreaterKeyCommand(collectionManager));
    }

    /**
     * Метод, который определяет из полученной строки команду, исполняет ее и передает ей необходимые аргументы.
     * Если команда не распознана, то в стандартный поток вывода выводится соответствующее сообщение.
     * @param firstCommandLine Первая строка команды, где хранится само ее название и переданные на этой же строке аргументы.
     */
    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s+");//убираем пробелы в начале и конце. Сплитуем по пробелам

        String[] args = Arrays.copyOfRange(words, 1, words.length); //Выдергиваем аргументы
        if (commandsWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) { //Преобразуем все в нижний регистр.
            CommandWithArguments command = commandsWithArguments.get(words[0].toLowerCase(Locale.ROOT));//получаем команду из списка
            command.getCommandArguments(args);//Даем ей нужные аргументы
            command.execute();//Выполнение команды
        } else if (commandsWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command = commandsWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();//Выполнение команды
        } else {
            System.err.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
        }
    }
}