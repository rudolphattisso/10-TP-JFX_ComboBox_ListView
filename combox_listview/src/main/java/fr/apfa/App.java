package fr.apfa;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
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
        // stage.getIcons().add(new Image("https://github.com/rudolphattisso/10-TP-jfx_ComboBox_ListView/blob/main/combox_listview/src/main/resources/icons.png"));

        // section1
        Label contriesLabel = new Label("Aviables contries");
        ObservableList<Country> comboBoxList = FXCollections.observableArrayList();

        List<Country> countries = Country.getContriesList();

        comboBoxList.addAll(countries);
        ComboBox<Country> comboBoxCountries = new ComboBox<>(comboBoxList);

        Button addButton = new Button(">");
        // addButton.setDisable(true);

        Button addAllButton = new Button(">>");
        // addAllButton.setDisable(true);

        // section 2 basse
        Button getButton = new Button("<");
        getButton.setDisable(true);
        Button getAllButton = new Button("<<");
        getAllButton.setDisable(true);

        // section 3
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");
        ListView<Country> listViewCountries = new ListView<>();

        Button quitButton = new Button("Quit");

        listViewCountries.getItems().addListener(new ListChangeListener<Country>() {
            @Override
            public void onChanged(Change<? extends Country> country) {

                if (comboBoxList.isEmpty()) {
                    addButton.setDisable(true);
                    addAllButton.setDisable(true);
                } else {
                    addButton.setDisable(false);
                    addAllButton.setDisable(false);
                }
            }

        });

        comboBoxList.addListener(new ListChangeListener<Country>() {
            @Override
            public void onChanged(Change<? extends Country> country) {
                if (listViewCountries.getItems().isEmpty()) {
                    getButton.setDisable(true);
                    getAllButton.setDisable(true);
                    // logger.info("Il s'est passé un truc, là.");
                } else {
                    getButton.setDisable(false);
                    getAllButton.setDisable(false);
                }
            }

        });

        // action
        addButton.setOnAction(e -> {
            // Stocker la valeur dans une variable
            Country countryToMove = comboBoxCountries.getValue();
            // suppression dans la comboBox
            comboBoxCountries.getItems().remove(countryToMove);// exprission de base
                                                               // comboBoxCountries.getItems().remove(comboBoxCountries.getValue());
            // copie dans list view.
            listViewCountries.getItems().add(countryToMove);
        });

        addAllButton.setOnAction(e2 -> {
            listViewCountries.getItems().addAll(comboBoxCountries.getItems());
            comboBoxCountries.getItems().clear();

        });

        getButton.setOnAction(e3 -> {
            Country countryToMoveGet = listViewCountries.getSelectionModel().getSelectedItem();
            listViewCountries.getItems().remove(countryToMoveGet);
            comboBoxCountries.getItems().add(countryToMoveGet);
        });

        getAllButton.setOnAction(e -> {
            comboBoxCountries.getItems().addAll(listViewCountries.getItems());
            listViewCountries.getItems().clear();
        });
        
        upButton.setOnAction(e -> {
            // recuperer l'élement selectionné et l'inserer à l'index -1
            ObservableList<Country> list = listViewCountries.getItems();// prendre l'élément de la liste
            // recup element selectionné
            Country country = listViewCountries.getSelectionModel().getSelectedItem();
            // chercher son index
            int indexCountry = list.indexOf(country);
            if(indexCountry != 0){
                // supprimer ancien élement à l'index
                list.remove(indexCountry);
                // modif index recupéré:
                indexCountry--;
                // inser obj dans list avec son index.
                list.add(indexCountry, country);
                listViewCountries.getSelectionModel().select(indexCountry);
            }
        });
        
        downButton.setOnAction(e -> {
            // recuperer l'élement selectionné et l'inserer à l'index -1
            ObservableList<Country> list = listViewCountries.getItems();// prendre l'élément de la liste
            // recup element selectionné
            Country country = listViewCountries.getSelectionModel().getSelectedItem();
            // chercher son index
            int indexCountry = list.indexOf(country);
            if(indexCountry<list.size()-1){
                
                
                // supprimer ancien élement à l'index
                list.remove(indexCountry);
                // modif index recupéré:
                indexCountry++;
                // inser obj dans list avec son index.
                list.add(indexCountry, country);
                listViewCountries.getSelectionModel().select(indexCountry);
            };
        });
        
        quitButton.setOnAction(value -> {
            Platform.exit();
        });

        HBox hboxForButton = new HBox(upButton, downButton);
        VBox section1VBox = new VBox(contriesLabel, comboBoxCountries);
        VBox section2HVBox = new VBox(addButton, addAllButton);
        VBox section2BVBox = new VBox(getButton, getAllButton);
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