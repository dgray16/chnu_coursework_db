package controller.publisher;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PublisherEntity;
import util.Const;

import java.util.List;

/**
 * Created by Administrator on 06.05.2015.
 */
public class AddPublisher {
    public static TableView<PublisherEntity> tableView;

    @FXML TextField nameField;

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
        if (!nameField.getText().equals("") && nameField.getText().matches(Const.WORD_PATTERN)){
            PublisherEntity publisherEntity = new PublisherEntity();
            publisherEntity.setName(nameField.getText());
            Factory.getInstance().<PublisherEntity>getDao().add(publisherEntity);

            tableView.getItems().clear();
            List<PublisherEntity> publishersList = Factory.getInstance().<PublisherEntity>getDao().getAll(PublisherEntity.class);
            tableView.getItems().addAll(publishersList);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (nameField.getText().equals("")) alert.setHeaderText("Your name is empty!");
            else if (nameField.getText().contains(" ")) alert.setHeaderText("Name can`t contain spacebar");
            else if (!nameField.getText().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your name is not word!");
            alert.showAndWait();
        }
    }
}
