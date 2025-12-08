package com.mona.gameapp.controller;

import com.mona.gameapp.dao.PlayerDao;
import com.mona.gameapp.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlayerController {

    @FXML private TextField playerIdField, firstNameField, lastNameField, addressField, postalCodeField, provinceField, phoneNumberField;
    @FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, Long> playerIdColumn;
    @FXML private TableColumn<Player, String> playerFirstNameColumn, playerLastNameColumn, playerAddressColumn, playerPostalCodeColumn, playerProvinceColumn, playerPhoneColumn;

    private PlayerDao playerDao;
    private final ObservableList<Player> playerList = FXCollections.observableArrayList();

    // Add this setter instead:
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
        refreshPlayers(); // populate table once DAO is set
    }


    @FXML
    public void initialize() {
        playerIdColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getPlayerId()).asObject());
        playerFirstNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));
        playerLastNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));
        playerAddressColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));
        playerPostalCodeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPostalCode()));
        playerProvinceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getProvince()));
        playerPhoneColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhoneNumber()));

        playerTable.setItems(playerList);

        // 🔑 Selection listener
        playerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                playerIdField.setText(String.valueOf(newSel.getPlayerId()));
                firstNameField.setText(newSel.getFirstName());
                lastNameField.setText(newSel.getLastName());
                addressField.setText(newSel.getAddress());
                postalCodeField.setText(newSel.getPostalCode());
                provinceField.setText(newSel.getProvince());
                phoneNumberField.setText(newSel.getPhoneNumber());
            }
        });


    }

    @FXML
    private void insertPlayer() {
        try {
            Player p = new Player();
            p.setFirstName(firstNameField.getText());
            p.setLastName(lastNameField.getText());
            p.setAddress(addressField.getText());
            p.setPostalCode(postalCodeField.getText());
            p.setProvince(provinceField.getText());
            p.setPhoneNumber(phoneNumberField.getText());
            playerDao.insertPlayer(p);
            refreshPlayers();
        } catch (Exception e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }

    @FXML
    private void updatePlayer() {
        try {
            Player p = new Player();
            p.setPlayerId(Long.parseLong(playerIdField.getText()));
            p.setFirstName(firstNameField.getText());
            p.setLastName(lastNameField.getText());
            p.setAddress(addressField.getText());
            p.setPostalCode(postalCodeField.getText());
            p.setProvince(provinceField.getText());
            p.setPhoneNumber(phoneNumberField.getText());
            playerDao.updatePlayer(p);
            refreshPlayers();
        } catch (Exception e) {
            System.err.println("Update failed: " + e.getMessage());
        }
    }

    @FXML
    private void refreshPlayers() {
        try {
            playerList.setAll(playerDao.getAllPlayers());
        } catch (Exception e) {
            System.err.println("Refresh failed: " + e.getMessage());
        }
    }
}