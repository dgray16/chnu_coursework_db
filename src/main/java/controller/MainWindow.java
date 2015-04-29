package controller;

import controller.author.AddAuthor;
import controller.binding.AddBinding;
import controller.binding.DeleteBinding;
import controller.language.AddLanguage;
import controller.language.DeleteLanguage;
import dao.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dao.Factory;
import javafx.util.Callback;
import model.AuthorEntity;
import model.BindingEntity;
import model.LanguageEntity;
import org.hibernate.Session;
import util.Const;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 21.04.2015.
 */
public class MainWindow implements Initializable {
    @FXML TabPane tabPane;
    private boolean visitedTabs[] = new boolean[7];

    // BindingEntity
    @FXML TableView<BindingEntity> bindingsTable;
    @FXML TableColumn bindingTypeColumn;

    public void addBinding(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_BINDING_FORM_PATH));
                } catch (IOException e) {
                }

                Scene scene = new Scene(root, 315, 109);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddBinding.tableView = bindingsTable;
                stage.showAndWait();
            }
        });
    }
    public void deleteBinding(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.DELETE_BINDING_FORM_PATH));
                } catch (IOException e) {
                }

                Scene scene = new Scene(root, 315, 109);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                DeleteBinding.tableView = bindingsTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpBindingTable(){
        bindingsTable.setEditable(false);
        bindingTypeColumn.setCellValueFactory(new PropertyValueFactory("type"));
    }

    // LanguageEntity
    @FXML TableView<LanguageEntity> languagesTable;
    @FXML TableColumn languageNameColumn;

    public void addLanguage(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_LANGUAGE_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 315, 109);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddLanguage.tableView = languagesTable;
                stage.showAndWait();
            }
        });
    }
    public void deleteLanguage(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.DELETE_LANGUAGE_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 315, 109);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                // this works fine
                stage.initOwner(bindingsTable.getScene().getWindow());
                DeleteLanguage.tableView = languagesTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpLanguageTable(){
        languagesTable.setEditable(false);
        languageNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    }

    // AuthorEntity
    @FXML TableView<AuthorEntity> authorsTable;
    @FXML TableColumn authorsNameColumn;
    @FXML TableColumn authorSurnameColumn;
    @FXML TableColumn authorsBirthColumn;
    @FXML TableColumn authorsDeathColumn;
    @FXML TableColumn deleteColumn;

    public void addAuthor(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_AUTHOR_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 320, 205);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddAuthor.tableView = authorsTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpAuthorsTable(){
        authorsTable.setEditable(false);
        authorsNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        authorSurnameColumn.setCellValueFactory(new PropertyValueFactory("surname"));
        authorsBirthColumn.setCellValueFactory(new PropertyValueFactory("birth"));
        authorsDeathColumn.setCellValueFactory(new PropertyValueFactory("death"));
        deleteColumn.setCellFactory(param -> new ButtonCell());
    }

    //PublisherEntity


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = HibernateUtil.getInstance().openSession();
        Arrays.fill(visitedTabs, false);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getText().equals("Bindings")) {
                List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
                if (visitedTabs[6] == false) {
                    setUpBindingTable();
                    visitedTabs[6] = true;
                }
                bindingsTable.getItems().clear();
                bindingsTable.getItems().addAll(bindingsList);
            }

            if (newValue.getText().equals("Languages")) {
                List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
                if (visitedTabs[5] == false) {
                    setUpLanguageTable();
                    visitedTabs[5] = true;
                }
                languagesTable.getItems().clear();
                languagesTable.getItems().addAll(languagesList);
            }

            if (newValue.getText().equals("Authors")) {
                List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                if (visitedTabs[4] == false) {
                    setUpAuthorsTable();
                    visitedTabs[4] = true;
                }
                authorsTable.getItems().clear();
                authorsTable.getItems().addAll(authorsList);
            }
        });


    }
    private class ButtonCell extends TableCell<AuthorEntity, AuthorEntity> {
        private Button cellButton;
        ButtonCell(){
            cellButton = new Button();
            cellButton.setOnAction(t -> {
                int index = getTableRow().getIndex();
                authorsTable.getSelectionModel().select(index);
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setId(authorsTable.getSelectionModel().getSelectedItem().getId());
                Factory.getInstance().<AuthorEntity>getDao().delete(authorEntity);

                // Clear data and set new data from db
                authorsTable.getItems().clear();
                List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                authorsTable.getItems().addAll(authorsList);
            });

        }
        //Display button if the row is not empty
        @Override
        protected void updateItem(AuthorEntity record, boolean empty) {
            super.updateItem(record, empty);
            if(!empty){
                cellButton.setText("Delete");
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
