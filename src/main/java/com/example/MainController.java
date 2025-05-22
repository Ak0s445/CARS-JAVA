package com.example;

import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private TableColumn<Car, String> brandCol;

    @FXML
    private TextField brandField;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, Integer> idCol;

    @FXML
    private TextField idField;


    @FXML
    private TableColumn<Car, String> plateCol;

    @FXML
    private TextField plateField;

    @FXML
    private TableColumn<Car, Integer> priceCol;

    @FXML
    private TextField priceField;

    @FXML
    private TableColumn<Car, Integer> yearCol;

    @FXML
    private TextField yearField;

    @FXML
    void onClickAddButton(ActionEvent event) {
        startAdd();
    }


    @FXML
    void initialize() {
        System.out.println("initialize");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        plateCol.setCellValueFactory(new PropertyValueFactory<>("plate"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //  List<Car> carList = new ArrayList<>();
        //  carList.add(new Car(1, "ABC-123", 2020, "BMW", 1000000)); //kocsi hozzáadása
        //  carList.add(new Car(2, "BGE-456", 2020, "Skoda", 150000));
        //   carList.add(new Car(3, "CPG-789", 2020, "Chevrolet", 10000000));

        carTable.getItems().addAll(Storage.readFile());
    }


    private void startAdd() {
        Car car = new Car();
        car.setId(generateId());
        car.setPlate(plateField.getText());
        car.setYear(Integer.parseInt(yearField.getText()));
        car.setBrand(brandField.getText());
        car.setPrice(Integer.parseInt(priceField.getText()));
        carTable.getItems().add(car);
        clearFields();
    }
    private int generateId() {
       int size =  carTable.getItems().size();
       Car car = carTable.getItems().get(size-1);
       return car.getId() + 1;

    }

    private void clearFields(){
        idField.setText("");
        plateField.setText("");
        yearField.setText("");
        brandField.setText("");
        priceField.setText("");

    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
       startDelete();
    }

      private void startDelete(){
           

        if (deleteQuestion()){
            int index = carTable.getSelectionModel().getSelectedIndex();
            carTable.getItems().remove(index);
        }  
    } 

    private boolean deleteQuestion(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(App._Stage);
        alert.setTitle("Figyelmeztetés");
        alert.setHeaderText("Biztosan törölni akarja?");
        alert.setContentText("Az adatok törlésre kerülnek!");

        ButtonType yesButton = new ButtonType("Igen");
        ButtonType noButton = new ButtonType("Nem");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            return true;
        }  

        return false;
    }

    @FXML
    void onClickModifyButton(ActionEvent event) {
        startModify();
    }

    private void startModify(){
        Car car = new Car();
        car.setId(Integer.parseInt(idField.getText()));
        car.setPlate(plateField.getText());
        car.setYear(Integer.parseInt(yearField.getText()));
        car.setBrand(brandField.getText());
        car.setPrice(Integer.parseInt(priceField.getText()));

        int index = carTable.getSelectionModel().getSelectedIndex();
        carTable.getItems().set(index, car);
        clearFields();
        carTable.setDisable(false);
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        startSave();
    }
    private void startSave(){
        ArrayList<Car> carList = new ArrayList<>(carTable.getItems());
        Storage.writeFile(carList);


    }

    @FXML
    void onMouseClickedCarTable(MouseEvent event) {
       startDoubleClick(event);
    }

    private void startDoubleClick(MouseEvent event){
         if (event.getClickCount() == 2){
            //System.out.println("Működik a katt, jöhet most a yacht");

            Car car = carTable.getSelectionModel().getSelectedItem();
            idField.setText(String.valueOf(car.getId()));
            plateField.setText(car.getPlate());
            yearField.setText(String.valueOf(car.getYear()));
            brandField.setText(car.getBrand());
            priceField.setText(String.valueOf(car.getPrice()));

            carTable.setDisable(true);
        }
    }

}
