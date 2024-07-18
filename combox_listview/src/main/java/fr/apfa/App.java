package fr.apfa;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    private static Logger logger = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {

        stage.setTitle("Country List");

        // section1
        Label contriesLabel = new Label("Aviables contries");
        ObservableList<Country> comboBoxList = FXCollections.observableArrayList();
       
        List<Country> countries = Country.getContriesList();

        comboBoxList.addAll(countries);git 
        ComboBox<Country> comboBoxCountries = new ComboBox<>(comboBoxList);
        // comboBoxList.addListener(new ListChangeListener<Country>() { // ajout du
        // listenner
        // @Override
        // public void onChanged(Change<? extends Country> country) {

        // logger.info("Il s'est passé un truc, là.");
        // }
        // });

        // section 2 haute
        Button addButton = new Button(">");
        // if (comboBoxCountries == null) {
        // addButton.setDisable(true);
        // }else
        // addButton.setDisable(true);

        // addButton.setDisable(true);

        Button addAllButton = new Button(">>");
        // addAllButton.setDisable(true);

        // section 2 basse
        Button deleteButton = new Button("<");
        Button deleteAllButton = new Button("<<");

        // section 3
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");
        ListView listViewCountries = new ListView<Country>();

        Button quitButton = new Button("Quit");

        // addButton.textProperty().addListener((observable, oldValue, newValue) -> {// lors de clique checkbox,la
        //     // section Backround s'affiche
        //     addButton.setDisable(false);
        // });

        addButton.setOnAction(e -> {
            listViewCountries.getItems().add(comboBoxCountries.getValue());
            comboBoxCountries.getItems().remove(comboBoxCountries.getValue());
        });

        addAllButton.setOnAction(e2 -> {
            listViewCountries.getItems().addAll(comboBoxCountries.getItems());
            comboBoxCountries.getItems().clear();

            // if (selectedItem != null && !selectedItem.isEmpty()) {
            // // addButton.setDisable(false);
            // list.getItems().add(selectedItem);
            // logger.info("country added");
            // comboBox.getItems().remove(selectedItem); // ne fonctionne pas

        });

        HBox hboxForButton = new HBox(upButton, downButton);
        VBox section1VBox = new VBox(contriesLabel, comboBoxCountries);
        VBox section2HVBox = new VBox(addButton, addAllButton);
        VBox section2BVBox = new VBox(deleteButton, deleteAllButton);
        VBox section3VBox = new VBox(hboxForButton, listViewCountries, quitButton);

        GridPane gridPane = new GridPane();
        gridPane.add(section1VBox, 0, 0);
        gridPane.add(section2HVBox, 1, 0);
        gridPane.add(section2BVBox, 1, 3);
        gridPane.add(section3VBox, 2, 0);
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        var scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

}