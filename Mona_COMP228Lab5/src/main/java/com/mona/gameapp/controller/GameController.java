package com.mona.gameapp.controller;

import com.mona.gameapp.dao.GameDao;
import com.mona.gameapp.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GameController {

    @FXML private TextField gameTitleField;
    @FXML private TableView<Game> gameTable;
    @FXML private TableColumn<Game, Long> gameIdColumn;
    @FXML private TableColumn<Game, String> gameTitleColumn;

    private GameDao gameDao;
    private final ObservableList<Game> gameList = FXCollections.observableArrayList();
    // Add setter
    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
        refreshGames(); // if you have a refresh method
    }

    @FXML
    public void initialize() {
        gameIdColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getGameId()).asObject());
        gameTitleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGameTitle()));
        gameTable.setItems(gameList);
    }

    @FXML
    private void insertGame() {
        try {
            Game g = new Game();
            g.setGameTitle(gameTitleField.getText());
            gameDao.insertGame(g);
            refreshGames();
        } catch (Exception e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }

    @FXML
    private void refreshGames() {
        try {
            gameList.setAll(gameDao.getAllGames());
        } catch (Exception e) {
            System.err.println("Refresh failed: " + e.getMessage());
        }
    }
}