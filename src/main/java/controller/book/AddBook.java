package controller.book;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Administrator on 07.05.2015.
 */
public class AddBook {
    @FXML TextField isbnField;
    @FXML TextField nameField;
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
    public void add(){
        if (!isbnField.getText().equals("") &&
            // TODO check ISBN regex and check it on form

                )
    }
}
