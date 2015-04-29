package controller.language;

import dao.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.LanguageEntity;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 21.04.2015.
 */
public class DeleteLanguage implements Initializable {
    @FXML ComboBox languagesBox;

    public static TableView<LanguageEntity> tableView;
    private List<LanguageEntity> languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);

    public void delete(){
        LanguageEntity LanguageEntity = new LanguageEntity();

        // Find in list where type in checkBox = type in list
        int index = 0;
        for (int i = 0; i < languagesList.size(); i++){
            if (languagesList.get(i).getName().equals(languagesBox.getSelectionModel().getSelectedItem().toString())){
                index = languagesList.get(i).getId();
                break;
            }
        }

        // Delete data
        if (index != 0) LanguageEntity.setId(index);
        Factory.getInstance().<LanguageEntity>getDao().delete(LanguageEntity);

        // Clear data and set new data from db
        tableView.getItems().clear();
        languagesList = Factory.getInstance().<LanguageEntity>getDao().getAll(LanguageEntity.class);
        tableView.getItems().addAll(languagesList);

        // Close window
        Stage stage = (Stage) languagesBox.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Put data into comboBox
        ObservableList<String> languagesObsList = FXCollections.observableArrayList();
        for (int i = 0; i < languagesList.size(); i++){
            languagesObsList.add(languagesList.get(i).getName());
        }
        languagesBox.setItems(languagesObsList);
    }
}
