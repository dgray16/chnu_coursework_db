package controller.binding;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BindingEntity;
import util.Const;

import java.util.List;

/**
 * Created by Administrator on 28.04.2015.
 */
public class AddBinding {
    @FXML TextField typeField;

    public static TableView<BindingEntity> tableView;

    public void toUpperCase(){
        char string[] = typeField.getText().toCharArray();
        if (string.length >= 1) {
            String symbol = String.valueOf(string[0]).toUpperCase();
            if (!String.valueOf(string[0]).equals(symbol)) {
                typeField.setText(typeField.getText().substring(0, 0) + symbol);
                typeField.selectAll();
                typeField.deselect();
            }
        }
    }
    public void add(){
        if (!typeField.getText().equals("") && typeField.getText().trim().matches(Const.WORD_PATTERN)) {
            BindingEntity bindingEntity = new BindingEntity();
            bindingEntity.setType(typeField.getText());
            Factory.getInstance().<BindingEntity>getDao().add(bindingEntity);

            tableView.getItems().clear();
            List<BindingEntity> bindingList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
            tableView.getItems().addAll(bindingList);

            Stage stage = (Stage) typeField.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (typeField.getText().equals("")) alert.setHeaderText("Your name is empty!");
            else if (typeField.getText().contains(" ")) alert.setHeaderText("Name can`t contain spacebar");
            else if (!typeField.getText().matches(Const.WORD_PATTERN)) alert.setHeaderText("Your name is not word!");
            alert.showAndWait();
        }
    }
}
