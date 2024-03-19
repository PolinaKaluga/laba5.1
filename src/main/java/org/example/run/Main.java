package org.example.run;


/**
 * @author Polina Kalugina
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (!args[0].isEmpty()) {
                Application application = new Application();
                application.start(args[0]);
            }
        } else {
            Application application = new Application();
            //Переменная окружения
//            String filepath = System.getenv("FILEPATH");
            //Самостоятельно прописываем путь(хардкодим)
            String filepath="test.json";
            application.start(filepath);
        }
    }
}
