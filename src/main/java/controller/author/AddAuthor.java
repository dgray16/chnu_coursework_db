package controller.author;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AuthorEntity;
import model.AuthorEntity;
import util.Const;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 28.04.2015.
 */
public class AddAuthor implements Initializable {
    @FXML TextField nameField;
    @FXML TextField surnameField;
    @FXML DatePicker birthPicker;
    @FXML CheckBox deathCheckBox;
    @FXML DatePicker deathPicker;

    public static TableView<AuthorEntity> tableView;

    public String toUpperCase(TextField field){
        char string[] = field.getText().toCharArray();
        if (string.length >= 1) {
            String symbol = String.valueOf(string[0]).toUpperCase();
            if (!String.valueOf(string[0]).equals(symbol)) {
                field.setText(field.getText().substring(0, 0) + symbol);
            }
        }
        return field.getText();
    }

    public void add(){
        if ( !nameField.getText().equals("") &&
                nameField.getText().trim().matches(Const.WORD_PATTERN) &&
                !surnameField.getText().equals("") &&
                surnameField.getText().trim().matches(Const.WORD_PATTERN) &&
                birthPicker.getValue() != null) {

                    AuthorEntity authorEntity = new AuthorEntity();
                    if (!deathCheckBox.isSelected() && deathPicker.getValue() != null)
                    authorEntity.setDeath(deathPicker.getValue().getYear());

                authorEntity.setName(nameField.getText());
                authorEntity.setSurname(surnameField.getText());
                authorEntity.setBirth(birthPicker.getValue().getYear());

                Factory.getInstance().<AuthorEntity>getDao().add(authorEntity);

                tableView.getItems().clear();
                List<AuthorEntity> authorsList = Factory.getInstance().<AuthorEntity>getDao().getAll(AuthorEntity.class);
                tableView.getItems().addAll(authorsList);

                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (nameField.getText().equals("")) alert.setHeaderText("Your name is empty!");
            else if (nameField.getText().contains(" ")) alert.setHeaderText("Name can`t contain spacebar");
            else if (!nameField.getText().trim().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your name is not word!");
            else if (surnameField.getText().equals("")) alert.setHeaderText("Your surname is empty!");
            else if (surnameField.getText().contains(" ")) alert.setHeaderText("Surname can`t contain spacebar");
            else if (!surnameField.getText().trim().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your surname is not word!");
            else if (birthPicker.getValue() == null) alert.setHeaderText("Birthday is empty");
            if (deathPicker.isVisible() && deathPicker.getValue() == null) alert.setHeaderText("Death date is empty");
            alert.showAndWait();
        }
    }
    public void setDeathPickerVisibility(){
        if (deathPicker.isVisible() == false) deathPicker.setVisible(true);
        else deathPicker.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameField.setOnKeyReleased(event -> {
            nameField.setText(toUpperCase(nameField));
            nameField.selectAll();
            nameField.deselect();
        });
        surnameField.setOnKeyReleased(event -> {
            surnameField.setText(toUpperCase(surnameField));
            surnameField.selectAll();
            surnameField.deselect();
        });
    }
}
