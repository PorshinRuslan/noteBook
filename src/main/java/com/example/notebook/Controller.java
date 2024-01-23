package com.example.notebook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Button clear;
    @FXML
    private TableView<NoteBook> noteBookTable;

    private Main main;
    FilteredList<NoteBook> ozuFilter;
    FilteredList<NoteBook> hddFilter;
    FilteredList<NoteBook> osFilter;
    FilteredList<NoteBook> colorFilter;

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
        ozuChoice.getItems().addAll(1,2,4,6,8);
        hddChoice.getItems().addAll(500,1000,1500);
        osChoice.getItems().addAll("Windows","MacOS","Linux");
        colorChoice.getItems().addAll("Black","White","Silver","Green");
    }

    //Заполняем таблицу данными
        public void setMain (Main main){
            this.main = main;
            noteBookTable.setItems(main.getNoteBooks());

            //Создаем фильтры
            ozuFilter = new FilteredList<>(main.getNoteBooks(), t -> true);
            hddFilter = new FilteredList<>(ozuFilter, t -> true);
            osFilter = new FilteredList<>(hddFilter, t -> true);
            colorFilter = new FilteredList<>(osFilter, t -> true);
        }


        //Действие на кнопку найти
    @FXML
    void find(ActionEvent event) {

        //Описываем предикаты для каждого столбца
        ozuFilter.setPredicate(noteBook -> {
            if(ozuChoice.getValue()==null)
                return true;
            else if (Integer.toString(noteBook.getOZU()).contains(Integer.toString(ozuChoice.getValue())))
                return true;
            return false;
        });

        hddFilter.setPredicate(noteBook -> {
            if(hddChoice.getValue()==null)
                return true;
            else if (Integer.toString(noteBook.getHdd()).contains(Integer.toString(hddChoice.getValue())))
                return true;
            return false;
        });
        osFilter.setPredicate(noteBook -> {
            if(osChoice.getValue()==null)
                return true;
            else if (noteBook.getOS().contains(osChoice.getValue()))
                return true;
            return false;
        });

        colorFilter.setPredicate(noteBook -> {
            if(colorChoice.getValue()==null)
                return true;
            else if (noteBook.getColor().contains(colorChoice.getValue()))
                return true;
            return false;
        });

        //Выводим отфильтрованный список
        noteBookTable.setItems(colorFilter);

    }

    //Сброс фильтров
    @FXML
    void clear(ActionEvent event) {
        noteBookTable.setItems(main.getNoteBooks());
        ozuChoice.getSelectionModel().clearSelection();
        hddChoice.getSelectionModel().clearSelection();
        osChoice.getSelectionModel().clearSelection();
        colorChoice.getSelectionModel().clearSelection();
    }

}
