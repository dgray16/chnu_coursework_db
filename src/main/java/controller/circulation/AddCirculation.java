package controller.circulation;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.BookEntity;
import model.CirculationEntity;
import model.ClientEntity;
import util.Const;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 13.05.2015.
 */
public class AddCirculation implements Initializable {
    public static TableView<CirculationEntity> tableView;

    @FXML ComboBox booksBox;
    @FXML ComboBox clientsBox;
    @FXML DatePicker givingPicker;
    @FXML TextField rentField;

    public void add(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        CirculationEntity circulationEntity = new CirculationEntity();
        if (    booksBox.getValue() != null &&
                clientsBox.getValue() != null &&
                givingPicker.getValue() != null &&
                !rentField.getText().equals("") && rentField.getText().matches(Const.INTEGER_NUMBER_PATTERN)){

            // Find book isbn by book name
            List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
            for (int i = 0; i < booksList.size(); i++){
                if (booksList.get(i).getName().equals(booksBox.getSelectionModel().getSelectedItem().toString())) {
                    circulationEntity.setBookId(booksList.get(i).getIsbn());
                    break;
                }

            }

            // Find client id by client name
            List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);
            for (int i = 0; i < clientsList.size(); i++){
                if (clientsList.get(i).getName().equals(clientsBox.getSelectionModel().getSelectedItem().toString())) {
                    circulationEntity.setClientId(clientsList.get(i).getId());
                    break;
                }

            }

            circulationEntity.setGivingTime(givingPicker.getValue().toString());
            circulationEntity.setRentTime(Byte.parseByte(rentField.getText()));

            Factory.getInstance().<CirculationEntity>getDao().add(circulationEntity);

            List<CirculationEntity>  circulationList = Factory.getInstance().<CirculationEntity>getDao().getAll(CirculationEntity.class);

            tableView.getItems().clear();

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

            tableView.getItems().addAll(circulationList);

            Stage stage = (Stage) rentField.getScene().getWindow();
            stage.close();

        }else {
            if(booksBox.getValue() == null) alert.setHeaderText("Book is empty");
            else if (clientsBox.getValue() == null) alert.setHeaderText("Client is empty");
            else if (givingPicker.getValue() == null) alert.setHeaderText("Date is empty");
            else if (rentField.getText().equals("")) alert.setHeaderText("Rent time - empty");
            else if (rentField.getText().matches(Const.INTEGER_NUMBER_PATTERN)) alert.setHeaderText("Rent must be an integer number");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
        for (int i = 0; i < booksList.size(); i++){
            booksBox.getItems().add(booksList.get(i).getName());
        }
        List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);
        for (int i = 0; i < clientsList.size(); i++){
            clientsBox.getItems().add(clientsList.get(i).getName());
        }
    }
}
