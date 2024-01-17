package com.example.notebook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> ozuChoice;
    @FXML
    private ChoiceBox<Integer> hddChoice;
    @FXML
    private ChoiceBox<String> osChoice;
    @FXML
    private ChoiceBox<String> colorChoice;

    @FXML
    private Button find;
    @FXML
    private TableView<NoteBook> noteBookTable;

    private Main main;

    @FXML
    void initialize() {
        //Создаем колонки
        TableColumn<NoteBook, Integer> ozuColumn = new TableColumn<NoteBook, Integer>("ОЗУ");
        ozuColumn.setCellValueFactory(new PropertyValueFactory<NoteBook, Integer>("ozu"));

        TableColumn<NoteBook, Integer> hddColumn = new TableColumn<NoteBook, Integer>("HDD");
        hddColumn.setCellValueFactory(new PropertyValueFactory<NoteBook, Integer>("hdd"));

        TableColumn<NoteBook, String> osColumn = new TableColumn<NoteBook, String>("ОС");
        osColumn.setCellValueFactory(new PropertyValueFactory<NoteBook, String>("os"));

        TableColumn<NoteBook, String> colorColumn = new TableColumn<NoteBook, String>("Цвет");
        colorColumn.setCellValueFactory(new PropertyValueFactory<NoteBook, String>("color"));

        //Привязываем колонки таблицы к данным
        noteBookTable.getColumns().addAll(ozuColumn, hddColumn, osColumn, colorColumn);

        //Заполняем данными строки выбора фильтров
        ozuChoice.getItems().add(1);
        ozuChoice.getItems().add(2);
        ozuChoice.getItems().add(4);
        ozuChoice.getItems().add(6);
        ozuChoice.getItems().add(8);

        hddChoice.getItems().add(500);
        hddChoice.getItems().add(1000);
        hddChoice.getItems().add(1500);

        osChoice.getItems().add("Windows");
        osChoice.getItems().add("MacOS");
        osChoice.getItems().add("Linux");

        colorChoice.getItems().add("Black");
        colorChoice.getItems().add("White");
        colorChoice.getItems().add("Silver");
        colorChoice.getItems().add("Green");
    }
        //Заполняем таблицу данными
        public void setMain (Main main){
            this.main = main;
            noteBookTable.setItems(main.getNoteBooks());
        }

        //Действие на кнопку
    @FXML
    void find(ActionEvent event) {

        //Оборачиваем таблицу в оболочку для фильтрации
        FilteredList<NoteBook> filteredData = new FilteredList<>(main.getNoteBooks());

        //Предикат для фильтрации
        filteredData.setPredicate(noteBook ->
        Integer.toString(noteBook.getOZU()).contains(Integer.toString(ozuChoice.getValue()))&&
        Integer.toString(noteBook.getHdd()).contains(Integer.toString(hddChoice.getValue()))&&
        noteBook.getOS().contains(osChoice.getValue())&&
        noteBook.getColor().contains(colorChoice.getValue())
        );


        //Заполняем таблицу новыми данными
        noteBookTable.setItems(filteredData);
    }
}
