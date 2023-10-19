/*
* File: App.java
* Author: Vitovszki Tamás
* Copyright: 2023, Vitovszki Tamás
* Group: Szoft II/2/N
* Date: 2023-10-05
* Github: https://github.com/Tomasman05/
* Licenc: GNU GPL
*/
import controllers.TodoController;

public class App {
    public static void main(String[] args) throws Exception {
        new TodoController().create();
    }
}
