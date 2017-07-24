package com.matuesz.shop;

import com.matuesz.shop.JDBC.JDBCUserSupplier;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

@SuppressWarnings({"ConstantConditions", "unchecked"})
public class UsersTabController {

    @FXML
    private ListView usersList;
    @FXML
    private TextField idField;
    @FXML
    private TextField nickField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField joinedField;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;

    private String sortingBy = "ID";
    private String sortingType = "ASC";
    boolean onlyWithExtended = false;

    private UserSupplier userSupplier = new JDBCUserSupplier(); //CURRENTLY HARDCODED

    @FXML
    public void initialize(){
        updateList();
    }

    @FXML
    public void updateList() {
        List<User> list = null;
        if (onlyWithExtended) {
            list = userSupplier.getAllExtendedInfo(sortingBy, sortingType);
        } else {
            list = userSupplier.getAllBasicInfo(sortingBy, sortingType);
        }
        usersList.getItems().setAll(list);
        usersList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void onSelectedItem() {
        User selectedUser = (User) usersList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            idField.setText(selectedUser.getId());
            nickField.setText(selectedUser.getNick());
            emailField.setText(selectedUser.getEmail());
            joinedField.setText(selectedUser.getTime_joined());
            updateButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    @FXML
    public void onRemoveButtonClick() {
        String selectedUserId = ((User) usersList.getSelectionModel().getSelectedItem()).getId();
        userSupplier.deleteUser(selectedUserId);
        clearFields();
        removeButton.setDisable(true);
        updateButton.setDisable(true);
        updateList();
    }

    @FXML
    public void onAddButtonClicked() {
        User temp = User.build()
                .withNick(nickField.getText())
                .withEmail(emailField.getText())
                .get();

        userSupplier.addUser(temp);
        clearFields();
        updateList();
    }

    @FXML
    public void onFieldEdit() {
        String nick = nickField.getText();
        String email = emailField.getText();
        if (nick.isEmpty() || nick.trim().isEmpty() || email.isEmpty() || email.trim().isEmpty()) {
            addButton.setDisable(true);
        } else {
            addButton.setDisable(false);
        }
    }

    @FXML
    public void setSortingById() {
        this.sortingBy = "ID";
        updateList();
    }

    @FXML
    public void setSortingByNick() {
        this.sortingBy = "NICK";
        updateList();
    }

    @FXML
    public void setSortingByTime() {
        this.sortingBy = "TIME";
        updateList();
    }

    @FXML
    public void setSortingTypeAscending() {
        this.sortingType = "ASC";
        updateList();
    }

    @FXML
    public void setSortingTypeDescending() {
        this.sortingType = "DESC";
        updateList();
    }

    @FXML
    public void revertOnlyWithExtended() {
        if (onlyWithExtended) {
            onlyWithExtended = false;
            updateList();
        } else {
            onlyWithExtended = true;
            updateList();
        }
    }

    public void clearFields() {
        nickField.clear();
        emailField.clear();
        idField.clear();
        joinedField.clear();
    }

}
