import Persistance.Database;
import UI.MainMenu;

public class Main {

    public static void main(String[] args) {
        final String USER = "bankuser";
        final String PASSWORD = "Bank123";
        final String URL = "jdbc:mysql://localhost:3306/bank?serverTimezone=CET&useSSL=false";
        Database database = new Database(USER, PASSWORD, URL);
        MainMenu mainMenu = new MainMenu(database);
        mainMenu.mainMenuLoop();
    }
}
