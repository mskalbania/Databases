package com.matuesz.shop.user;

import com.matuesz.shop.Hibernate.HibernateUserSupplier;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "unchecked"})
public class UsersTabController {

    @FXML
    private ListView usersListView;
    @FXML
    private TextField findField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nickField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField joinedField;
    @FXML
    private TextField adminField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField genderField;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private Button extraInfoAdd;
    @FXML
    private Button extraInfoDelete;
    @FXML
    private ComboBox findBox;

    private String sortingBy = "ID";
    private String sortingType = "ASC";
    private boolean onlyWithExtended = false;
    private String currentFindMethod = "id";
    private List<User> usersList;

    private UserSupplier userSupplier = new HibernateUserSupplier(); //CURRENTLY HARDCODED

    @FXML
    public void initialize() {
        updateList();
    }

    @FXML
    public void onFindBoxInput() {
        if (findField.getText() != null && findField.getText() != "") {
            List<User> output = new ArrayList<>();
            output.addAll(usersList.stream()
                    .filter(findPredicatePick())
                    .collect(Collectors.toList())
            );
            usersListView.getItems().setAll(output);
        } else {
            usersListView.getItems().setAll(usersList);
        }
    }

    @FXML
    public void onFindTypeChange() {
        currentFindMethod = ((String) findBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void updateList() {
        List<User> list = null;
        if (onlyWithExtended) {
            list = userSupplier.getAllExtendedInfo(sortingBy, sortingType);
        } else {
            list = userSupplier.getAllBasicInfo(sortingBy, sortingType);
        }
        usersListView.getItems().setAll(list);
        usersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        usersList = list;
    }

    @FXML
    public void onSelectedItem() {
        User selectedUser = (User) usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            idField.setText(Integer.toString(selectedUser.getId()));
            nickField.setText(selectedUser.getNick());
            emailField.setText(selectedUser.getEmail());
            joinedField.setText(selectedUser.getTime_joined());

            UserExtraInfo info = selectedUser.getUserExtraInfo();
            if (info != null) {
                if (info.getIsAdmin() == 0) {
                    adminField.setText("No");
                } else {
                    adminField.setText("Yes");
                }
                addressField.setText(info.getAddress());
                phoneField.setText(info.getPhoneNumber());
                genderField.setText(info.getGender());
                extraInfoDelete.setDisable(false);
            }
            extraInfoAdd.setDisable(false);
            updateButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    @FXML
    public void onExtraInfoAddButtonClick() {
        User selectedUser = ((User) usersListView.getSelectionModel().getSelectedItem());
        UserExtraInfo info = new UserExtraInfo();

        if (adminField.getText().trim().toUpperCase().equals("YES")) {
            info.setIsAdmin(1);
        } else {
            info.setIsAdmin(0);
        }
        info.setId(selectedUser.getId());
        info.setAddress(addressField.getText());
        info.setPhoneNumber(phoneField.getText());
        ((HibernateUserSupplier) userSupplier).updateExtraInfo(info, genderField.getText());
        updateList();
    }

    @FXML
    public void onRemoveInfoButtonClick() {

        User temp = ((User) usersListView.getSelectionModel().getSelectedItem());

        if (temp.getUserExtraInfo() != null) {
            ((HibernateUserSupplier) userSupplier).removeExtraInfo(temp.getUserExtraInfo());
        }
        updateList();
    }

    @FXML
    public void onRemoveButtonClick() {
        int selectedUserId = ((User) usersListView.getSelectionModel().getSelectedItem()).getId();
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
        updateList();
        removeButton.setDisable(true);
        updateButton.setDisable(true);
        addButton.setDisable(true);
    }

    @FXML
    public void onUpdateButtonClicked() {
        User temp = User.build()
                .withEmail(emailField.getText())
                .withNick(nickField.getText())
                .withId(Integer.parseInt(idField.getText()))
                .get();
        userSupplier.updateUser(temp);
        clearFields();
        addButton.setDisable(true);
        removeButton.setDisable(true);
        updateButton.setDisable(true);
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
        this.sortingBy = "id";
        updateList();
    }

    @FXML
    public void setSortingByNick() {
        this.sortingBy = "nick";
        updateList();
    }

    @FXML
    public void setSortingByTime() {
        this.sortingBy = "time_joined";
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

    private void clearFields() {
        nickField.clear();
        emailField.clear();
        idField.clear();
        joinedField.clear();
    }

    private Predicate<User> findPredicatePick() {
        switch (currentFindMethod) {
            case "id":
                return user -> Integer.toString(user.getId()).contains(findField.getText());
            case "email":
                return user -> user.getEmail().contains(findField.getText());
            case "nick":
                return user -> user.getNick().contains(findField.getText());
            case "date":
                return user -> user.getTime_joined().contains(findField.getText());
        }
        return null;
    }
}
