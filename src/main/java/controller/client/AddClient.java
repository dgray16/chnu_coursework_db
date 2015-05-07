package controller.client;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClientEntity;
import util.Const;

import java.util.List;

/**
 * Created by Administrator on 07.05.2015.
 */
public class AddClient {
    public static TableView<ClientEntity> tableView;

    @FXML TextField nameField;
    @FXML DatePicker birthPicker;

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
        if (!nameField.getText().equals("") && nameField.getText().trim().matches(Const.WORD_PATTERN) && birthPicker.getValue() != null) {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setName(nameField.getText());
            clientEntity.setBirth(birthPicker.getValue().getYear());
            Factory.getInstance().<ClientEntity>getDao().add(clientEntity);

            tableView.getItems().clear();
            List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);
            tableView.getItems().addAll(clientsList);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (nameField.getText().equals("")) alert.setHeaderText("Your name is empty!");
            else if (nameField.getText().contains(" ")) alert.setHeaderText("Name can`t contain spacebar");
            else if (!nameField.getText().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your name is not word!");
            else if (birthPicker.getValue() == null) alert.setHeaderText("Birthday is empty");
            alert.showAndWait();
        }
    }
}
