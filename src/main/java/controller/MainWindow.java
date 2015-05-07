package controller;

import controller.author.AddAuthor;
import controller.binding.AddBinding;
import controller.binding.DeleteBinding;
import controller.client.AddClient;
import controller.language.AddLanguage;
import controller.language.DeleteLanguage;
import controller.publisher.AddPublisher;
import dao.HibernateUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import dao.Factory;

import model.*;
import org.hibernate.Session;
import util.Const;

import javax.persistence.Table;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 21.04.2015.
 */
public class MainWindow implements Initializable {

    // TODO check status of client depending on book rent date ends

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
        languageNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    }

    // AuthorEntity
    @FXML TableView<AuthorEntity> authorsTable;
    @FXML TableColumn authorsNameColumn;
    @FXML TableColumn authorSurnameColumn;
    @FXML TableColumn authorsBirthColumn;
    @FXML TableColumn authorsDeathColumn;
    @FXML TableColumn authorsDeleteColumn;

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
        authorsNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        authorSurnameColumn.setCellValueFactory(new PropertyValueFactory("surname"));
        authorsBirthColumn.setCellValueFactory(new PropertyValueFactory("birth"));
        authorsDeathColumn.setCellValueFactory(new PropertyValueFactory("death"));
        authorsDeleteColumn.setCellFactory(param -> new ButtonCell("Author"));
    }

    // PublisherEntity
    @FXML TableView<PublisherEntity> publishersTable;
    @FXML TableColumn publisherNameColumn;
    @FXML TableColumn publisherDeleteColumn;

    public void addPublisher(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_PUBLISHER_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 205, 85);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddPublisher.tableView = publishersTable;
                stage.showAndWait();
            }
        });

    }
    private void setUpPublishersTable(){
        publisherNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        publisherDeleteColumn.setCellFactory(param -> new ButtonCell("Publisher"));
    }

    // ClientEntity
    @FXML TableView<ClientEntity> clientsTable;
    @FXML TableColumn clientNameColumn;
    @FXML TableColumn clientBirthColumn;
    @FXML TableColumn clientBanColumn;
    @FXML TableColumn clientDeleteColumn;

    public void addClient(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_CLIENT_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 210, 120);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddClient.tableView = clientsTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpClientsTable(){
        clientNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        clientBirthColumn.setCellValueFactory(new PropertyValueFactory("birth"));
        clientBanColumn.setCellValueFactory(new PropertyValueFactory("banned"));
        clientDeleteColumn.setCellFactory(param -> new ButtonCell("Client"));
    }

    // BookEntity
    @FXML TableView<BookEntity> booksTable;
    @FXML TableColumn bookIsbnColumn;
    @FXML TableColumn bookNameColumn;
    @FXML TableColumn bookPublisherColumn;
    @FXML TableColumn bookAuthorColumn;
    @FXML TableColumn bookPriceColumn;
    @FXML TableColumn bookBindingColumn;
    @FXML TableColumn bookYearColumn;
    @FXML TableColumn bookPagesColumn;
    @FXML TableColumn bookLanguageColumn;
    @FXML TableColumn bookQuantityColumn;
    @FXML TableColumn bookDeleteColumn;

    public void addBook(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_BOOK_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddClient.tableView = clientsTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpBooksTable(){
        bookIsbnColumn.setCellValueFactory(new PropertyValueFactory("isbn"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        bookPublisherColumn.setCellValueFactory(new PropertyValueFactory("publisherByIdName"));
        /*bookAuthorColumn.setCellValueFactory(new PropertyValueFactory("?"));*/
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        /*bookBindingColumn.setCellValueFactory(new PropertyValueFactory("?"));*/
        bookYearColumn.setCellValueFactory(new PropertyValueFactory("year"));
        bookPagesColumn.setCellValueFactory(new PropertyValueFactory("pages"));
        /*bookLanguageColumn.setCellValueFactory(new PropertyValueFactory("?"));*/
        bookQuantityColumn.setCellValueFactory(new PropertyValueFactory("numberOfBooks"));
        bookDeleteColumn.setCellFactory(param -> new ButtonCell("Book"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = HibernateUtil.getInstance().openSession();
        Arrays.fill(visitedTabs, false);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                // TODO make all cases for tabs
                case "Bindings": {
                    List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
                    if (visitedTabs[6] == false) {
                        setUpBindingTable();
                        visitedTabs[6] = true;
                    }
                    bindingsTable.getItems().clear();
                    bindingsTable.getItems().addAll(bindingsList);
                    break;
                }
                case "Languages": {
                    List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
                    if (visitedTabs[5] == false) {
                        setUpLanguageTable();
                        visitedTabs[5] = true;
                    }
                    languagesTable.getItems().clear();
                    languagesTable.getItems().addAll(languagesList);
                }
                case "Authors": {
                    List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                    if (visitedTabs[4] == false) {
                        setUpAuthorsTable();
                        visitedTabs[4] = true;
                    }
                    authorsTable.getItems().clear();
                    authorsTable.getItems().addAll(authorsList);
                }
                case "Publishers": {
                    List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
                    if (visitedTabs[3] == false) {
                        setUpPublishersTable();
                        visitedTabs[3] = true;
                    }
                    publishersTable.getItems().clear();
                    publishersTable.getItems().addAll(publishersList);
                }
                case "Clients": {
                    List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);
                    if (visitedTabs[2] == false) {
                        setUpClientsTable();
                        visitedTabs[2] = true;
                    }
                    clientsTable.getItems().clear();
                    clientsTable.getItems().addAll(clientsList);
                }
                case "Books": {
                    List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
                    if (visitedTabs[1] == false) {
                        setUpBooksTable();
                        visitedTabs[1] = true;
                    }
                    booksTable.getItems().clear();

                    // Get names of fields by their id
                    List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
                    int index = 0;

                    for (int i = 0; i < booksList.size(); i++){
                        // Find publisher by publisher_id
                        for (int j = 0; j < publishersList.size(); j++){
                            if (booksList.get(i).getPublisherId() == publishersList.get(j).getId()){
                                index = j;
                                break;
                            }
                        }
                        // Put this publisher to publishersList publisherById field
                        booksList.get(i).setPublisherByIdName(publishersList.get(index).getName());
                    }
                    booksTable.getItems().addAll(booksList);
                }
            }
        });


    }
    // TODO make ButtonCell class one for all needed tables
    private class ButtonCell extends TableCell<AuthorEntity, AuthorEntity> {
        private Button cellButton;

        ButtonCell(String tableName){
            cellButton = new Button();
            cellButton.setOnAction(t -> {
                int index = getTableRow().getIndex();

                switch (tableName){
                    case "Author": {
                        authorsTable.getSelectionModel().select(index);
                        AuthorEntity authorEntity = new AuthorEntity();
                        authorEntity.setId(authorsTable.getSelectionModel().getSelectedItem().getId());
                        Factory.getInstance().<AuthorEntity>getDao().delete(authorEntity);

                        // Clear data and set new data from db
                        authorsTable.getItems().clear();
                        List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                        authorsTable.getItems().addAll(authorsList);
                        break;
                    }
                    case "Publisher": {
                        publishersTable.getSelectionModel().select(index);
                        PublisherEntity publisherEntity = new PublisherEntity();
                        publisherEntity.setId(publishersTable.getSelectionModel().getSelectedItem().getId());
                        Factory.getInstance().<PublisherEntity>getDao().delete(publisherEntity);

                        publishersTable.getItems().clear();
                        List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
                        publishersTable.getItems().addAll(publishersList);
                        break;
                    }
                    case "Client": {
                        clientsTable.getSelectionModel().select(index);
                        ClientEntity clientEntity = new ClientEntity();
                        clientEntity.setId(clientsTable.getSelectionModel().getSelectedItem().getId());
                        Factory.getInstance().<ClientEntity>getDao().delete(clientEntity);

                        clientsTable.getItems().clear();
                        List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);
                        clientsTable.getItems().addAll(clientsList);
                        break;
                    }
                    case "Book": {
                        booksTable.getSelectionModel().select(index);
                        BookEntity bookEntity = new BookEntity();
                        bookEntity.setIsbn(booksTable.getSelectionModel().getSelectedItem().getIsbn());
                        Factory.getInstance().<BookEntity>getDao().delete(bookEntity);

                        booksTable.getItems().clear();
                        List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
                        booksTable.getItems().addAll(booksList);
                        break;
                    }
                    case "Circulation":{
                        break;
                    }
                }
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
