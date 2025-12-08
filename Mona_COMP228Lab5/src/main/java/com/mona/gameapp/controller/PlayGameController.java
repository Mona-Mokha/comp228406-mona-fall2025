package com.mona.gameapp.controller;

import com.mona.gameapp.dao.GameDao;
import com.mona.gameapp.dao.PlayerAndGameDao;
import com.mona.gameapp.dao.PlayerDao;
import com.mona.gameapp.model.Game;
import com.mona.gameapp.model.Player;
import com.mona.gameapp.model.PlayerAndGame;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlayGameController {

    @FXML private ComboBox<Player> pgPlayerCombo;
    @FXML private ComboBox<Game> pgGameCombo;
    @FXML private DatePicker pgDatePicker;
    @FXML private TextField pgScoreField;

    //@FXML private Button addPlayedGameButton;

    private PlayerDao playerDao;
    private GameDao gameDao;
    private PlayerAndGameDao playerAndGameDao;
    private ReportController reportController; // optional, to refresh report

    // ------------------ SETTERS ------------------
    public void setPlayerDao(PlayerDao dao) { this.playerDao = dao; }
    public void setGameDao(GameDao dao) { this.gameDao = dao; }
    public void setPlayerAndGameDao(PlayerAndGameDao dao) { this.playerAndGameDao = dao; }
    public void setReportController(ReportController rc) { this.reportController = rc; }

    // ------------------ INITIALIZE ------------------
    @FXML
    public void initialize() {
        //loadCombos();
    }
    public void setDaos(PlayerDao playerDao, GameDao gameDao, PlayerAndGameDao pagDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.playerAndGameDao = pagDao;

        // Now it's safe to load combo boxes
        try {
            loadCombos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ------------------ COMBOS ------------------
    public void loadCombos() {
        try {
            pgPlayerCombo.setItems(FXCollections.observableArrayList(playerDao.getAllPlayers()));
            pgGameCombo.setItems(FXCollections.observableArrayList(gameDao.getAllGames()));
        } catch (SQLException ex) {
            showError("Failed to load players or games: " + ex.getMessage());
        }
    }

    // ------------------ INSERT PLAYED GAME ------------------
    @FXML
    private void insertPlayedGame() {
        Player p = pgPlayerCombo.getValue();
        Game g = pgGameCombo.getValue();
        LocalDate date = pgDatePicker.getValue();

        if (p == null || g == null || date == null) {
            showError("Please select a player, a game, and a date.");
            return;
        }

        int score;
        try {
            score = Integer.parseInt(pgScoreField.getText());
        } catch (NumberFormatException e) {
            showError("Score must be a number.");
            return;
        }

        PlayerAndGame pag = new PlayerAndGame();
        pag.setPlayerId(p.getPlayerId());
        pag.setGameId(g.getGameId());
        pag.setPlayingDate(date.atStartOfDay()); // LocalDate -> LocalDateTime
        pag.setScore(score);

        try {
            playerAndGameDao.insertPlayerAndGame(pag);
            showInfo("Played game recorded.");
            clearForm();
            loadCombos(); // refresh combo lists if needed

            if (reportController != null) {
                reportController.refreshReports(); // public method to refresh
            }
        } catch (SQLException ex) {
            showError("Failed to insert played game: " + ex.getMessage());
        }
    }

    // ------------------ HELPERS ------------------
    private void clearForm() {
        pgPlayerCombo.getSelectionModel().clearSelection();
        pgGameCombo.getSelectionModel().clearSelection();
        pgDatePicker.setValue(null);
        pgScoreField.clear();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
