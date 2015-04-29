package controller.binding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import dao.Factory;
import javafx.stage.Stage;
import model.BindingEntity;
import util.Const;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 21.04.2015.
 */
public class DeleteBinding implements Initializable {
    @FXML ComboBox bindingBox;

    public static TableView<BindingEntity> tableView;
    private List<BindingEntity> bindingList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);

    public void delete(){
        BindingEntity bindingEntity = new BindingEntity();

        // Find in list where type in checkBox = type in list
        int index = 0;
        for (int i = 0; i < bindingList.size(); i++){
            if (bindingList.get(i).getType().equals(bindingBox.getSelectionModel().getSelectedItem().toString())){
                index = bindingList.get(i).getId();
                break;
            }
        }

        // Delete data
        if (index != 0) bindingEntity.setId(index);
        Factory.getInstance().<BindingEntity>getDao().delete(bindingEntity);

        // Clear data and set new data from db
        tableView.getItems().clear();
        List<BindingEntity> bindingList = Factory.getInstance().<BindingEntity>getDao().getAll(BindingEntity.class);
        tableView.getItems().addAll(bindingList);

        // Close window
        Stage stage = (Stage) bindingBox.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Put data into comboBox
        ObservableList<String> bindingsObsList = FXCollections.observableArrayList();
        for (int i = 0; i < bindingList.size(); i++){
            bindingsObsList.add(bindingList.get(i).getType());
        }
        bindingBox.setItems(bindingsObsList);
    }
}
