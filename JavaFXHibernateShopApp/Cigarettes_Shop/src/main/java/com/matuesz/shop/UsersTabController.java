package com.matuesz.shop;

import com.matuesz.shop.JDBC.JDBCUserSupplier;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class UsersTabController {

    private String sortingBy = "ID";
    private String sortingType = "ASC";
    boolean onlyWithExtended = false;

    private UserSupplier userSupplier = new JDBCUserSupplier(); //CURRENTLY HARDCODED

    @FXML
    private TextArea outputConsole;

    @FXML
    public void clearConsole() {
        outputConsole.clear();
    }

    @FXML
    public void setSortingById() {
        this.sortingBy = "ID";
    }

    @FXML
    public void setSortingByNick() {
        this.sortingBy = "NICK";
    }

    @FXML
    public void setSortingByTime() {
        this.sortingBy = "TIME";
    }

    @FXML
    public void setSortingTypeAscending() {
        this.sortingType = "ASC";
    }

    @FXML
    public void setSortingTypeDescending() {
        this.sortingType = "DESC";
    }

    @FXML
    public void revertOnlyWithExtended() {
        if (onlyWithExtended) {
            onlyWithExtended = false;
        } else {
            onlyWithExtended = true;
        }
    }


    @FXML
    public void getAllUsers() {
        List<User> userList = null;
        if (onlyWithExtended) {
            userList = userSupplier.getAllExtendedInfo(sortingBy, sortingType);
        } else {
            userList = userSupplier.getAllBasicInfo(sortingBy, sortingType);
        }
        outputConsole.appendText("#####SHOWING USERS SORTED BY " + sortingBy + "#####\n\n");
        userList.forEach(user -> outputConsole.appendText(user.toString() + "\n\n"));
        outputConsole.appendText("###############END###############\n\n");
    }
}
