package com.mona.gameapp.controller;

import com.mona.gameapp.dao.PlayerAndGameDao;
import com.mona.gameapp.dao.PlayerDao;
import com.mona.gameapp.dao.GameDao;
import com.mona.gameapp.model.Player;
import com.mona.gameapp.model.PlayerAndGame;
import com.mona.gameapp.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportController {

    @FXML private ComboBox<String> reportTypeCombo;
    @FXML private ComboBox<Player> playerComboBox;
    @FXML private TableView<PlayerAndGame> reportTable;
    @FXML private TableColumn<PlayerAndGame, String> rptPlayerColumn;
    @FXML private TableColumn<PlayerAndGame, String> rptGameTitleColumn;
    @FXML private TableColumn<PlayerAndGame, String> rptPlayedOnColumn;
    @FXML private TableColumn<PlayerAndGame, Double> rptScoreColumn;

    private PlayerAndGameDao playerAndGameDao;
    private PlayerDao playerDao;
    private GameDao gameDao;

    private final ObservableList<PlayerAndGame> reportList = FXCollections.observableArrayList();

    public void setPlayerAndGameDao(PlayerAndGameDao dao) {
        this.playerAndGameDao = dao;
        refreshReports(); // or any method that populates the table
    }
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }


    @FXML
    public void initialize() {


        // Table columns
        rptPlayerColumn.setCellValueFactory(data -> {
            try {
                Player p = playerDao.getAllPlayers().stream()
                        .filter(pl -> pl.getPlayerId() == data.getValue().getPlayerId())
                        .findFirst().orElse(null);
                return new javafx.beans.property.SimpleStringProperty(p != null ? p.getFirstName() + " " + p.getLastName() : "");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        rptGameTitleColumn.setCellValueFactory(data -> {
            try {
                Game g = gameDao.getAllGames().stream()
                        .filter(game -> game.getGameId() == data.getValue().getGameId())
                        .findFirst().orElse(null);
                return new javafx.beans.property.SimpleStringProperty(g != null ? g.getGameTitle() : "");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        rptPlayedOnColumn.setCellValueFactory(data -> {
            if (data.getValue().getPlayingDate() != null) {
                String formatted = data.getValue().getPlayingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                return new javafx.beans.property.SimpleStringProperty(formatted);
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        rptScoreColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getScore()).asObject());

        reportTable.setItems(reportList);


    }




    public void loadPlayers() {
        try {
            List<Player> players = playerDao.getAllPlayers();
            playerComboBox.setItems(FXCollections.observableArrayList(players));
        } catch (Exception e) {
            System.err.println("Failed to load players: " + e.getMessage());
        }
    }

    public void loadReportTypes() {
        reportTypeCombo.setItems(FXCollections.observableArrayList(
                "All Reports",
                "By Player"
        ));
        reportTypeCombo.getSelectionModel().selectFirst();
    }

    @FXML
    public void showReport() {
        refreshReports();
    }

    public void refreshReports() {
        if (playerAndGameDao == null) return;
        try {
            List<PlayerAndGame> all = playerAndGameDao.getAllPlayerAndGames();

            // Filter by player if needed
            Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
            if ("By Player".equals(reportTypeCombo.getValue()) && selectedPlayer != null) {
                all.removeIf(pag -> pag.getPlayerId() != selectedPlayer.getPlayerId());

        } else if ("All Reports".equals(reportTypeCombo)) {
            // Clear selection so the combo box is empty
            playerComboBox.getSelectionModel().clearSelection();
        }

            reportList.setAll(all);
        } catch (Exception e) {
            System.err.println("Refresh report failed: " + e.getMessage());
        }
    }
}
