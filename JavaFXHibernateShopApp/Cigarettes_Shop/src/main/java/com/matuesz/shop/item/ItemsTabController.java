package com.matuesz.shop.item;

import com.matuesz.shop.Hibernate.HibernateItemSupplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ItemsTabController {

    @FXML
    private ListView<Item> itemListView;
    @FXML
    private TextArea statsArea;
    @FXML
    private ComboBox sortingWay;
    @FXML
    private ComboBox sortingType;
    @FXML
    private ComboBox itemType;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private Button clearFieldsButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;


    private ItemsSupplier itemsSupplier = new HibernateItemSupplier(); //currently hardcoded
    private ObservableList<Item> itemList;

    private final Comparator<Item> id = Comparator.comparingInt(Item::getId);
    private final Comparator<Item> name = Comparator.comparing(Item::getName);
    private final Comparator<Item> price = Comparator.comparing(Item::getPrice);
    private final Comparator<Item> quantity = Comparator.comparing(Item::getQuantityAtStock);
    private final Comparator<Item> type = Comparator.comparing(i -> i.getItemType().getTypeName());

    @FXML
    public void initialize() {
        itemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        update();

    }

    @FXML
    public void onRemoveButtonClick() {
        Item selected = itemListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            itemsSupplier.removeItem(selected);
        }
        update();
        removeButton.setDisable(true);
    }

    @FXML
    public void onAddButtonClick() {

        Item item = new Item();
        item.setName(nameField.getText());
        item.setPrice(Double.parseDouble(priceField.getText()));
        item.setQuantityAtStock(Integer.parseInt(quantityField.getText()));
        item.setItemType(((ItemType) itemType.getSelectionModel().getSelectedItem()));
        itemsSupplier.addItem(item);
        update();
        addButton.setDisable(true);
    }

    @FXML
    public void onFieldsInputChange() {
        String name = nameField.getText().trim();
        String quantity = quantityField.getText().trim();
        String price = priceField.getText().trim();
        ItemType selectedType = (ItemType) itemType.getSelectionModel().getSelectedItem();

        if (name != "" && quantity != "" && price != "" && selectedType != null) {
            addButton.setDisable(false);
        }
    }

    @FXML
    public void update() {
        itemList = FXCollections.observableList(itemsSupplier.getAllItems()).sorted(id);
        itemListView.setItems(itemList);
        itemType.setItems(FXCollections.observableList(itemsSupplier.getItemTypes()));
        setCurrentStatistics();
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

    @FXML
    public void onItemSelected() {
        Item item = itemListView.getSelectionModel().getSelectedItem();
        if (item != null) {
            nameField.setText(item.getName());
            priceField.setText(Double.toString(item.getPrice()));
            quantityField.setText(Integer.toString(item.getQuantityAtStock()));
            itemType.getSelectionModel().select(item.getItemType());
        }
        removeButton.setDisable(false);
    }

    @FXML
    public void onClearFieldsButtonClick() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
        itemType.valueProperty().setValue(null);
        addButton.setDisable(true);
        removeButton.setDisable(true);
        updateButton.setDisable(true);
    }

    private void setCurrentStatistics() {
        statsArea.clear();
        statsArea.appendText("IN STOCK " + calculateItemsAmount() + " ITEMS\n");
    }

    private int calculateItemsAmount() {
        return itemList
                .stream()
                .reduce(0, (sum, item) -> sum += item.getQuantityAtStock(),
                        (sum1, sum2) -> sum1 + sum2);
    }
}
