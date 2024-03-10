**Реализация  консольного приложения** 
##В коллекции необходимо хранить объекты класса Ticket, описание которого приведено ниже.##

Разработанная программа должна удовлетворять следующим требованиям:

1.Класс, коллекцией экземпляров которого управляет программа, должен реализовывать сортировку по умолчанию.

2.Все требования к полям класса (указанные в виде комментариев) должны быть выполнены.

3.Для хранения необходимо использовать коллекцию типа java.util.ArrayDequeue

4.При запуске приложения коллекция должна автоматически заполняться значениями из файла.

5.Имя файла должно передаваться программе с помощью: переменная окружения.

6.Данные должны храниться в файле в формате json

7.Чтение данных из файла необходимо реализовать с помощью класса java.io.FileReader

8.Запись данных в файл необходимо реализовать с помощью класса java.io.BufferedOutputStream

9.Все классы в программе должны быть задокументированы в формате javadoc.

10.Программа должна корректно работать с неправильными данными (ошибки пользовательского ввода, отсутсвие прав доступа к файлу и т.п.).

11.В интерактивном режиме программа должна поддерживать выполнение следующих команд:

- help : вывести справку по доступным командам

- info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)

- show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении

- add {element} : добавить новый элемент в коллекцию

- update id {element} : обновить значение элемента коллекции, id которого равен заданному

- remove_by_id id : удалить элемент из коллекции по его id

- clear : очистить коллекцию

- save : сохранить коллекцию в файл

- execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.

- exit : завершить программу (без сохранения в файл)

- remove_first : удалить первый элемент из коллекции

- head : вывести первый элемент коллекции

- add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции

- filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки

- print_field_descending_person : вывести значения поля person всех элементов в порядке убывания

- print_field_descending_price : вывести значения поля price всех элементов в порядке убывания

``` java
public class Ticket {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле может быть null
}
```


```java
public class Coordinates {
    private Float x; //Поле не может быть null
    private float y;
}
```

```java
public class Person {
    private Long height; //Поле может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
}
```

```java
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
}
```

```java
public enum Color {
    GREEN,
    BLACK,
    WHITE;
}
```

```java
public enum Color {
    RED,
    BLACK,
    YELLOW,
    ORANGE,
    WHITE;
}
```