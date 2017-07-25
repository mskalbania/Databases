package com.matuesz.shop;

import com.matuesz.shop.JDBC.JDBCItemsSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemsTabController {

    @FXML
    private ListView<Item> itemListView;
    @FXML
    private ComboBox sortingWay;
    @FXML
    private ComboBox sortingType;

    private ItemsSupplier itemsSupplier = new JDBCItemsSupplier(); //currently hardcoded
    private ObservableList<Item> itemList;

    private final Comparator<Item> id = Comparator.comparingInt(i -> Integer.parseInt(i.getId()));
    private final Comparator<Item> name = Comparator.comparing(Item::getName);
    private final Comparator<Item> price = Comparator.comparing(Item::getPrice);
    private final Comparator<Item> quantity = Comparator.comparing(Item::getQuantityAtStock);
    private final Comparator<Item> type = Comparator.comparing(i -> i.getType().getTypeName());


    @FXML
    public void initialize() {
        itemList = FXCollections.observableList(itemsSupplier.getAllItems()).sorted(id);
        itemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        itemListView.setItems(itemList);
    }

    @FXML
    public void onUpdateButtonClick() {
        itemList = FXCollections.observableList(itemsSupplier.getAllItems()).sorted(id);
        itemListView.setItems(itemList);
    }

    @FXML
    public void onSortingSettingChange() {
        String sortingTypeSetting = ((String) sortingType.getSelectionModel().getSelectedItem());
        String sortingWaySetting = ((String) sortingWay.getSelectionModel().getSelectedItem());

        Comparator<Item> selectedComparator = null;

        switch (sortingWaySetting) {
            case "id":
                if (sortingTypeSetting.equals("Ascending")) {
                    selectedComparator = id;
                } else {
                    selectedComparator = id.reversed();
                }
                break;
            case "name":
                if (sortingTypeSetting.equals("Ascending")) {
                    selectedComparator = name;
                } else {
                    selectedComparator = name.reversed();
                }
                break;
            case "price":
                if (sortingTypeSetting.equals("Ascending")) {
                    selectedComparator = price;
                } else {
                    selectedComparator = price.reversed();
                }
                break;
            case "quantity":
                if (sortingTypeSetting.equals("Ascending")) {
                    selectedComparator = quantity;
                } else {
                    selectedComparator = quantity.reversed();
                }
                break;
            case "type":
                if (sortingTypeSetting.equals("Ascending")) {
                    selectedComparator = type;
                } else {
                    selectedComparator = type.reversed();
                }
                break;
        }
        itemListView.setItems(itemList.sorted(selectedComparator));
    }
}
