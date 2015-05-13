package controller.circulation;

import dao.Factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.BookEntity;
import model.CirculationEntity;
import model.ClientEntity;

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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        List<BookEntity> booksList = Factory.getInstance().<BookEntity>getDao().getAll(BookEntity.class);
        // TODO put data into comboBox
        List<ClientEntity> clientsList = Factory.getInstance().<ClientEntity>getDao().getAll(ClientEntity.class);

    }
}
