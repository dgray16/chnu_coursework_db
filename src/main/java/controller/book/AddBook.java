package controller.book;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import util.Const;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 07.05.2015.
 */
public class AddBook implements Initializable{
    Alert alert = new Alert(Alert.AlertType.ERROR);
    public static TableView tableView;

    @FXML TextField isbnField;
    @FXML TextField nameField;
    @FXML ComboBox publishersBox;
    @FXML ComboBox authorsBox;
    @FXML TextField priceField;
    @FXML ComboBox bindingBox;
    @FXML DatePicker yearPicker;
    @FXML TextField pagesField;
    @FXML ComboBox languagesBox;
    @FXML TextField bookQuantityField;
    @FXML DatePicker incomeDatePicker;

    public void isbnTextModify(){
        switch (isbnField.getSelection().getEnd()){
            case 1: {
                isbnField.setText(isbnField.getText() + "-");
                isbnField.selectAll();
                isbnField.deselect();
                break;
            }
            case 6: {
                isbnField.setText(isbnField.getText() + "-");
                isbnField.selectAll();
                isbnField.deselect();
                break;
            }
            case 11: {
                isbnField.setText(isbnField.getText() + "-");
                isbnField.selectAll();
                isbnField.deselect();
                break;
            }
        }
    }
    public void toUpperCase(){
        char string[] = nameField.getText().toCharArray();
        if (string.length >= 1) {
            String symbol = String.valueOf(string[0]).toUpperCase();
            if (!String.valueOf(string[0]).equals(symbol)) {
                nameField.setText(nameField.getText().substring(0, 0) + symbol);
                nameField.selectAll();
                nameField.deselect();
            }
        }
    }
    public void add(){
    if (    !isbnField.getText().equals("") && isbnField.getText().matches(Const.ISBN_PATTERN) &&
            !nameField.getText().equals("") && nameField.getText().matches(Const.WORD_PATTERN) &&
            publishersBox.getValue() != null &&
            authorsBox.getValue() != null &&
            !priceField.getText().equals("") && priceField.getText().matches(Const.DECIMAL_NUMBER_PATTERN) &&
            bindingBox.getValue() != null &&
            yearPicker.getValue() != null &&
            !pagesField.getText().equals("") && pagesField.getText().matches(Const.INTEGER_NUMBER_PATTERN) &&
            languagesBox.getValue() != null &&
            !bookQuantityField.getText().equals("") && bookQuantityField.getText().matches(Const.INTEGER_NUMBER_PATTERN) &&
            incomeDatePicker.getValue() != null){

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(isbnField.getText());
        bookEntity.setName(nameField.getText());

        // Find publisher id by publisher name from comboBox
        List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
        for (int i = 0; i < publishersList.size(); i++){
            if (publishersBox.getSelectionModel().getSelectedItem().toString().equals(publishersList.get(i).getName())){
                int index = publishersList.get(i).getId();
                bookEntity.setPublisherId(index);
                break;
            }
        }

        // Find author id by author name from comboBox
        List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
        String[] words = authorsBox.getSelectionModel().getSelectedItem().toString().split("\\s+");
        for (int j = 0; j < words.length; j++){
            words[j] = words[j].replaceAll("[^\\w]", "");
        }
        for (int i = 0; i < authorsList.size(); i++){
            if (words[0].equals(authorsList.get(i).getName()) && words[1].equals(authorsList.get(i).getSurname())){
                int index = authorsList.get(i).getId();
                bookEntity.setAuthorId(index);
                break;
            }
        }

        bookEntity.setPrice(Float.parseFloat(priceField.getText()));

        // Find binding id by binding name from comboBox
        List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
        for (int i = 0; i < bindingsList.size(); i++){
            if (bindingBox.getSelectionModel().getSelectedItem().toString().equals(bindingsList.get(i).getType())){
                int index = bindingsList.get(i).getId();
                bookEntity.setBindingId(index);
                break;
            }
        }

        bookEntity.setYear(yearPicker.getValue().getYear());
        bookEntity.setPages(Integer.parseInt(pagesField.getText()));

        // Find language id by language name from comboBox
        List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
        for (int i = 0; i < languagesList.size(); i++){
            if (languagesBox.getSelectionModel().getSelectedItem().toString().equals(languagesList.get(i).getName())){
                int index = languagesList.get(i).getId();
                bookEntity.setLanguageId(index);
                break;
            }
        }

        bookEntity.setNumberOfBooks(Byte.parseByte(bookQuantityField.getText()));
        bookEntity.setIncomeDate(incomeDatePicker.getValue().toString());

        Factory.getInstance().<BookEntity>getDao().add(bookEntity);

        tableView.getItems().clear();
        List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);

        // Get names of fields by their id
        int index = 0;
        for (int i = 0; i < booksList.size(); i++){

            // Find publisher by publisher_id
            publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
            for (int j = 0; j < publishersList.size(); j++){
                if (booksList.get(i).getPublisherId() == publishersList.get(j).getId()){
                    index = j;
                    break;
                }
            }
            // Put this publisher to publishersList publisherById field
            booksList.get(i).setPublisherByIdName(publishersList.get(index).getName());

            // Find author by author_id
            authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
            for (int j = 0; j < authorsList.size(); j++){
                if (booksList.get(i).getAuthorId() == authorsList.get(j).getId()){
                    index = j;
                    break;
                }
            }
            // Put this author to authorByIdFullName
            booksList.get(i).setAuthorByIdFullName(authorsList.get(index).getName() + " " + authorsList.get(index).getSurname());

            // Find binding by binding_id
            bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
            for (int j = 0; j < bindingsList.size(); j++){
                if (booksList.get(i).getBindingId() == bindingsList.get(j).getId()){
                    index = j;
                    break;
                }
            }
            // Put this binding to bindingByIdType
            booksList.get(i).setBindingByIdType(bindingsList.get(index).getType());

            // Find language by language_id
            languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
            for (int j = 0; j < languagesList.size(); j++){
                if (booksList.get(i).getLanguageId() == languagesList.get(j).getId()){
                    index = j;
                    break;
                }
            }
            // Put this language to languageByIdName
            booksList.get(i).setLanguageByIdName(languagesList.get(index).getName());
        }

        tableView.getItems().addAll(booksList);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    } else{
        if (isbnField.getText().equals("")) alert.setHeaderText("ISBN - empty");
        else if (!isbnField.getText().matches(Const.ISBN_PATTERN)) alert.setHeaderText("Not correct ISBN");

        else if (nameField.getText().equals("")) alert.setHeaderText("Name of book - empty");
        else if (!nameField.getText().matches(Const.WORD_PATTERN)) alert.setHeaderText("Name must be with words only and no spaces");

        else if (publishersBox.getValue() == null) alert.setHeaderText("Select publisher");
        else if (authorsBox.getValue() == null) alert.setHeaderText("Select author");

        else if (priceField.getText().equals("")) alert.setHeaderText("Price - empty");
        else if (!priceField.getText().matches(Const.DECIMAL_NUMBER_PATTERN)) alert.setHeaderText("Not correct price");

        else if (bindingBox.getValue() == null) alert.setHeaderText("Select binding");
        else if (yearPicker.getValue() == null) alert.setHeaderText("Select year");

        else if (pagesField.getText().equals("")) alert.setHeaderText("Pages - empty");
        else if (!pagesField.getText().matches(Const.INTEGER_NUMBER_PATTERN)) alert.setHeaderText("Not correct pages number");

        else if (languagesBox.getValue() == null) alert.setHeaderText("Select language");

        else if (bookQuantityField.getText().equals("")) alert.setHeaderText("Number of books - empty");
        else if (!bookQuantityField.getText().matches(Const.INTEGER_NUMBER_PATTERN)) alert.setHeaderText("Not correct number of books");

        else if (incomeDatePicker.getValue() == null) alert.setHeaderText("Select income date");

        alert.showAndWait();
    }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
        for (int i = 0; i < publishersList.size(); i++){
            publishersBox.getItems().add(publishersList.get(i).getName());
        }

        List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
        for (int i = 0; i < authorsList.size(); i++){
            authorsBox.getItems().add(authorsList.get(i).getName() + " " + authorsList.get(i).getSurname());
        }

        List<BindingEntity> bindingsList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
        for (int i = 0; i < bindingsList.size(); i++){
            bindingBox.getItems().add(bindingsList.get(i).getType());
        }

        List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
        for (int i = 0; i < languagesList.size(); i++){
            languagesBox.getItems().add(languagesList.get(i).getName());
        }
    }
}
