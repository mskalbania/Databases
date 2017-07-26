package com.matuesz.shop.item;

import com.matuesz.shop.Hibernate.HibernateItemSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.Comparator;

public class ItemsTabController {

    @FXML
    private ListView<Item> itemListView;
    @FXML
    private ComboBox sortingWay;
    @FXML
    private ComboBox sortingType;

    private ItemsSupplier itemsSupplier = new HibernateItemSupplier(); //currently hardcoded
    private ObservableList<Item> itemList;

    private final Comparator<Item> id = Comparator.comparingInt(Item::getId);
    private final Comparator<Item> name = Comparator.comparing(Item::getName);
    private final Comparator<Item> price = Comparator.comparing(Item::getPrice);
    private final Comparator<Item> quantity = Comparator.comparing(Item::getQuantityAtStock);
    private final Comparator<Item> type = Comparator.comparing(i -> i.getItemType().getTypeName());


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
