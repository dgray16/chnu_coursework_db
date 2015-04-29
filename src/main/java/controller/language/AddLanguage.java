package controller.language;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LanguageEntity;
import util.Const;

import java.util.List;

/**
 * Created by Administrator on 28.04.2015.
 */
public class AddLanguage {
    @FXML TextField nameField;

    public static TableView<LanguageEntity> tableView;

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
        if (!nameField.getText().equals("") && nameField.getText().trim().matches(Const.WORD_PATTERN)) {
            LanguageEntity languageEntity = new LanguageEntity();
            languageEntity.setName(nameField.getText());
            Factory.getInstance().<LanguageEntity>getDao().add(languageEntity);

            tableView.getItems().clear();
            List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
            tableView.getItems().addAll(languagesList);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (nameField.getText().equals("")) alert.setHeaderText("Your name is empty!");
            else if(nameField.getText().contains(" ")) alert.setHeaderText("Name can`t contain spacebar");
            else if (!nameField.getText().trim().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your name is not word!");
            alert.showAndWait();
        }
    }
}
