package org.example.run;

import org.example.collection.CollectionManager;
import org.example.collection.Ticket;
import org.example.commands.CommandInvoker;
import org.example.exception.InvalidValue;
import org.example.io.UserIO;
import org.example.workWithFile.FileManager;
import org.example.workWithFile.JsonParser;
import org.example.workWithFile.TicketFieldsReader;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс, через который производится запуск данного приложения.
 */
public class Application {
    /**
     * менеджер коллекций
     */
    CollectionManager collectionManager;
    /**
     * менеджер файлов
     */
    FileManager fileManager;
    /**
     * XML парсер
     */
    JsonParser jsonParser;
    /**
     * хранит ссылку на объект, производящий чтение и вывод команд
     */
    UserIO userIO;
    /**
     * хранит ссылку на объект, который производит запуск выбранных команд
     */
    CommandInvoker commandInvoker;
    /**
     * хранит ссылку на объект, производящий чтение полей класса Ticket
     */
    TicketFieldsReader ticketFieldsReader;


    /**
     * Метод, выполняющий запуск программы. Через него происходит работа всей программы.
     *
     * @param inputFile имя файла, откуда следует читать объекты коллекции, представленные в формате XML
     * @throws
     */
    public void start(String inputFile) {

        collectionManager = new CollectionManager();
        fileManager = new FileManager();
        jsonParser = new JsonParser();
        userIO = new UserIO();

        ticketFieldsReader = new TicketFieldsReader(userIO);

        this.commandInvoker = new CommandInvoker(collectionManager, userIO, inputFile, ticketFieldsReader);

        try {
            File ioFile = new File(inputFile);
            //Валидация
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            String fileData= fileManager.readFromFile(inputFile);

            List<Ticket> tickets = jsonParser.parseToCollection(fileData);
            // Declare and initialize the jsonObject variable



            for (Ticket ticket : tickets) {
                collectionManager.add(ticket);
            }
            collectionManager.show();

            userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (IOException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        } catch (InvalidValue e) {
            throw new RuntimeException(e);
        }
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        } catch (InvalidValue e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод, выполняющий циклическое чтение команд из строки ввода
     */
    public void cycle() throws InvalidValue {
        userIO.printCommandText("Программа была запущена.\n");
        while (true) {
            userIO.printCommandText("\nВведите название команды:\n");
            userIO.printPreamble();
            String line = userIO.readLine();
            commandInvoker.execute(line);
        }
    }
}
