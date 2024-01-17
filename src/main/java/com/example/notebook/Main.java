package com.example.notebook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //Загружаем визальную оболочку
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();

        //Создаем ноутбуки
        noteBooks.add(new NoteBook(1,500,"Windows", "Black"));
        noteBooks.add(new NoteBook(2,1000, "MacOS", "White"));
        noteBooks.add(new NoteBook(4,1000, "Linux", "White"));
        noteBooks.add(new NoteBook(4,500, "MacOS", "Silver"));
        noteBooks.add(new NoteBook(2,1500, "Linux", "Black"));
        noteBooks.add(new NoteBook(6,500, "MacOS", "Green"));
        noteBooks.add(new NoteBook(8,1500, "Windows", "Black"));
        noteBooks.add(new NoteBook(8,1000, "MacOS", "White"));

        //Передаем экземпляр класса в контроллер
        Controller controller = fxmlLoader.getController();
        controller.setMain(this);
    }

    //Создаем список ноутбуков
    private ObservableList<NoteBook> noteBooks = FXCollections.observableArrayList();

    //Метод получения списка ноутбуков для контроллера
    public ObservableList<NoteBook> getNoteBooks(){
        return noteBooks;
    }

    public static void main(String[] args) {
        launch();
    }
}