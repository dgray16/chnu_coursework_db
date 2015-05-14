package controller;

import controller.author.AddAuthor;
import controller.binding.AddBinding;
import controller.binding.DeleteBinding;
import controller.book.AddBook;
import controller.circulation.AddCirculation;
import controller.client.AddClient;
import controller.language.AddLanguage;
import controller.language.DeleteLanguage;
import controller.publisher.AddPublisher;
import dao.HibernateUtil;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dao.Factory;

import model.*;
import org.hibernate.Session;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;
import util.Const;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

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
    @FXML TableColumn bookIncomeColumn;
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
                Scene scene = new Scene(root, 760, 440);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddBook.tableView = booksTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpBooksTable(){
        bookIsbnColumn.setCellValueFactory(new PropertyValueFactory("isbn"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        bookPublisherColumn.setCellValueFactory(new PropertyValueFactory("publisherByIdName"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory("authorByIdFullName"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        bookBindingColumn.setCellValueFactory(new PropertyValueFactory("bindingByIdType"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory("year"));
        bookPagesColumn.setCellValueFactory(new PropertyValueFactory("pages"));
        bookLanguageColumn.setCellValueFactory(new PropertyValueFactory("languageByIdName"));
        bookQuantityColumn.setCellValueFactory(new PropertyValueFactory("numberOfBooks"));
        bookIncomeColumn.setCellValueFactory(new PropertyValueFactory("incomeDate"));
        bookDeleteColumn.setCellFactory(param -> new ButtonCell("Book"));
    }

    // CirculationEntity
    @FXML TableView<CirculationEntity> circulationTable;
    @FXML TableColumn circulationBookColumn;
    @FXML TableColumn circulationClientColumn;
    @FXML TableColumn circulationGivingColumn;
    @FXML TableColumn circulationReceivingColumn;
    @FXML TableColumn circulationRentColumn;
    @FXML TableColumn circulationDeleteColumn;

    public void addCirculation(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource(Const.ADD_CIRCULATION_FORM_PATH));
                } catch (IOException e) {
                }
                Scene scene = new Scene(root, 260, 240);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(bindingsTable.getScene().getWindow());
                AddCirculation.tableView = circulationTable;
                stage.showAndWait();
            }
        });
    }
    private void setUpCirculationTable(){
        circulationBookColumn.setCellValueFactory(new PropertyValueFactory("bookByIdName"));
        circulationClientColumn.setCellValueFactory(new PropertyValueFactory("clientByIdName"));
        circulationGivingColumn.setCellValueFactory(new PropertyValueFactory("givingTime"));
        circulationRentColumn.setCellValueFactory(new PropertyValueFactory("rentTime"));
        circulationDeleteColumn.setCellFactory(param -> new ButtonCell("Circulation"));

        circulationReceivingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        circulationReceivingColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CirculationEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CirculationEntity, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setReceivingTime(event.getNewValue());
            }
        });
        circulationReceivingColumn.setCellValueFactory(new PropertyValueFactory("receivingTime"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = HibernateUtil.getInstance().openSession();
        Arrays.fill(visitedTabs, false);

        // First time whe program starts
        List<CirculationEntity> circulationList0 = Factory.getInstance().<CirculationEntity>getDao().getAll(CirculationEntity.class);
        if (visitedTabs[0] == false) {
            setUpCirculationTable();
            visitedTabs[0] = true;
        }

        List<BookEntity> booksList0 = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
        List<ClientEntity> clientsList0 = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);

        for (int i = 0; i < circulationList0.size(); i++){

            // Find book by book isbn
            for (int j = 0; j < booksList0.size(); j++){
                if (booksList0.get(j).getIsbn().equals(circulationList0.get(i).getBookId())){
                    // Put this bookName to circulationList
                    circulationList0.get(i).setBookByIdName(booksList0.get(j).getName());
                    break;
                }
            }
            // Find client by client id
            for (int j = 0; j < clientsList0.size(); j++){
                if (clientsList0.get(j).getId() == circulationList0.get(i).getClientId()){
                    // Put this clientName to circulationList
                    circulationList0.get(i).setClientByIdName(clientsList0.get(j).getName());
                    break;
                }
            }
        }
        circulationTable.getItems().addAll(circulationList0);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {

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
                    int index = 0;

                    List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
                    List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                    List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
                    List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);

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

                        // Find author by author_id
                        for (int j = 0; j < authorsList.size(); j++){
                            if (booksList.get(i).getAuthorId() == authorsList.get(j).getId()){
                                index = j;
                                break;
                            }
                        }
                        // Put this author to authorByIdFullName
                        booksList.get(i).setAuthorByIdFullName(authorsList.get(index).getName() + " " + authorsList.get(index).getSurname());

                        // Find binding by binding_id
                        for (int j = 0; j < bindingsList.size(); j++){
                            if (booksList.get(i).getBindingId() == bindingsList.get(j).getId()){
                                index = j;
                                break;
                            }
                        }
                        // Put this binding to bindingByIdType
                        booksList.get(i).setBindingByIdType(bindingsList.get(index).getType());

                        // Find language by language_id
                        for (int j = 0; j < languagesList.size(); j++){
                            if (booksList.get(i).getLanguageId() == languagesList.get(j).getId()){
                                index = j;
                                break;
                            }
                        }
                        // Put this language to languageByIdName
                        booksList.get(i).setLanguageByIdName(languagesList.get(index).getName());
                    }
                    booksTable.getItems().addAll(booksList);
                }
                // TODO not needed call when i`m going out of this tab :(
                case "Circulation": {
                    List<CirculationEntity> circulationList = Factory.getInstance().<CirculationEntity>getDao().getAll(CirculationEntity.class);
                    if (visitedTabs[0] == false) {
                        setUpCirculationTable();
                        visitedTabs[0] = true;
                    }
                    circulationTable.getItems().clear();

                    List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
                    List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);

                    for (int i = 0; i < circulationList.size(); i++){

                        // Find book by book isbn
                        for (int j = 0; j < booksList.size(); j++){
                            if (booksList.get(j).getIsbn().equals(circulationList.get(i).getBookId())){
                                // Put this bookName to circulationList
                                circulationList.get(i).setBookByIdName(booksList.get(j).getName());
                                break;
                            }
                        }
                        // Find client by client id
                        for (int j = 0; j < clientsList.size(); j++){
                            if (clientsList.get(j).getId() == circulationList.get(i).getClientId()){
                                // Put this clientName to circulationList
                                circulationList.get(i).setClientByIdName(clientsList.get(j).getName());
                                break;
                            }
                        }


                        try {
                            // Set status of client depending on book rent date ends
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar endDate = Calendar.getInstance();

                            endDate.setTime(format.parse(circulationList.get(i).getGivingTime()));
                            endDate.add(Calendar.DATE, circulationList.get(i).getRentTime());

                            Calendar today = Calendar.getInstance();
                            today.setTime(today.getTime());

                            // TODO NullPointerException
                            // Bye

                            if (today.after(endDate) && circulationList.get(i).getReceivingTime().equals("")) {
                                for (int j = 0; j < clientsList.size(); j++){
                                    if (circulationList.get(i).getClientId() == clientsList.get(j).getId()){
                                        ClientEntity clientEntity = new ClientEntity();
                                        clientEntity.setBanned(Byte.parseByte("1"));
                                        Factory.getInstance().<ClientEntity>getDao().update(clientEntity);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    circulationTable.getItems().addAll(circulationList);
                }
            }
        });

    }

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

                        // Get names of fields by their id
                        index = 0;
                        for (int i = 0; i < booksList.size(); i++){

                            // Find publisher by publisher_id
                            List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
                            for (int j = 0; j < publishersList.size(); j++){
                                if (booksList.get(i).getPublisherId() == publishersList.get(j).getId()){
                                    index = j;
                                    break;
                                }
                            }
                            // Put this publisher to publishersList publisherById field
                            booksList.get(i).setPublisherByIdName(publishersList.get(index).getName());

                            // Find author by author_id
                            List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                            for (int j = 0; j < authorsList.size(); j++){
                                if (booksList.get(i).getAuthorId() == authorsList.get(j).getId()){
                                    index = j;
                                    break;
                                }
                            }
                            // Put this author to authorByIdFullName
                            booksList.get(i).setAuthorByIdFullName(authorsList.get(index).getName() + " " + authorsList.get(index).getSurname());

                            // Find binding by binding_id
                            List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
                            for (int j = 0; j < bindingsList.size(); j++){
                                if (booksList.get(i).getBindingId() == bindingsList.get(j).getId()){
                                    index = j;
                                    break;
                                }
                            }
                            // Put this binding to bindingByIdType
                            booksList.get(i).setBindingByIdType(bindingsList.get(index).getType());

                            // Find language by language_id
                            List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
                            for (int j = 0; j < languagesList.size(); j++){
                                if (booksList.get(i).getLanguageId() == languagesList.get(j).getId()){
                                    index = j;
                                    break;
                                }
                            }
                            // Put this language to languageByIdName
                            booksList.get(i).setLanguageByIdName(languagesList.get(index).getName());
                        }

                        booksTable.getItems().addAll(booksList);
                        break;
                    }
                    case "Circulation":{
                        circulationTable.getSelectionModel().select(index);
                        CirculationEntity circulationEntity = new CirculationEntity();
                        circulationEntity.setId(circulationTable.getSelectionModel().getSelectedItem().getId());
                        Factory.getInstance().<CirculationEntity>getDao().delete(circulationEntity);

                        circulationTable.getItems().clear();
                        List<CirculationEntity> circulationList = Factory.getInstance().<CirculationEntity>getDao().getAll(CirculationEntity.class);

                        List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
                        List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);

                        for (int i = 0; i < circulationList.size(); i++){

                            // Find book by book isbn
                            for (int j = 0; j < booksList.size(); j++){
                                if (booksList.get(j).getIsbn().equals(circulationList.get(i).getBookId())){
                                    // Put this bookName to circulationList
                                    circulationList.get(i).setBookByIdName(booksList.get(j).getName());
                                    break;
                                }
                            }
                            // Find client by client id
                            for (int j = 0; j < clientsList.size(); j++){
                                if (clientsList.get(j).getId() == circulationList.get(i).getClientId()){
                                    // Put this clientName to circulationList
                                    circulationList.get(i).setClientByIdName(clientsList.get(j).getName());
                                    break;
                                }
                            }
                        }
                        circulationTable.getItems().addAll(circulationList);
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
