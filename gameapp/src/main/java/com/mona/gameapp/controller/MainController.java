package com.mona.gameapp.controller;

import com.mona.gameapp.db.DatabaseManager;
import com.mona.gameapp.dao.GameDao;
import com.mona.gameapp.dao.PlayerAndGameDao;
import com.mona.gameapp.dao.PlayerDao;
import com.mona.gameapp.model.Game;
import com.mona.gameapp.model.Player;
import com.mona.gameapp.model.PlayerAndGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MainController {

    // Player tab controls
    @FXML private TextField playerIdField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField provinceField;
    @FXML private TextField phoneNumberField;
    @FXML private Button insertPlayerButton;
    @FXML private Button updatePlayerButton;
    @FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, Long> playerIdColumn;
    @FXML private TableColumn<Player, String> playerFirstNameColumn;
    @FXML private TableColumn<Player, String> playerLastNameColumn;

    // Game tab controls
    @FXML private TextField gameIdField;
    @FXML private TextField gameTitleField;
    @FXML private Button insertGameButton;
    @FXML private TableView<Game> gameTable;
    @FXML private TableColumn<Game, Long> gameIdColumn;
    @FXML private TableColumn<Game, String> gameTitleColumn;

    // Report tab controls
    @FXML private TableView<PlayedReportRow> reportTable;
    @FXML private TableColumn<PlayedReportRow, Long> rptPlayerGameIdColumn;
    @FXML private TableColumn<PlayedReportRow, String> rptPlayerNameColumn;
    @FXML private TableColumn<PlayedReportRow, String> rptGameTitleColumn;
    @FXML private TableColumn<PlayedReportRow, String> rptPlayedOnColumn;
    @FXML private TableColumn<PlayedReportRow, Double> rptScoreColumn;

    private DatabaseManager dbManager;
    private PlayerDao playerDao;
    private GameDao gameDao;
    private PlayerAndGameDao playerAndGameDao;

    @FXML
    public void initialize() {
        // initialize DB manager with your credentials (use your provided credentials)
        this.dbManager = new DatabaseManager(
                "aws-1-ca-central-1.pooler.supabase.com",
                5432,
                "postgres",
                "postgres.qvczqichssyzqovtolnn",
                "Monmon@2025"
        );

        this.playerDao = new PlayerDao(dbManager);
        this.gameDao = new GameDao(dbManager);
        this.playerAndGameDao = new PlayerAndGameDao(dbManager);

        setupPlayerTable();
        setupGameTable();
        setupReportTable();

        refreshPlayerTable();
        refreshGameTable();
        refreshReportTable();

        // select row click to load into form
        playerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) loadPlayerToForm(newSel);
        });

        gameTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) loadGameToForm(newSel);
        });
    }

    private void setupPlayerTable() {
        playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        playerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        playerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    private void setupGameTable() {
        gameIdColumn.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
    }

    private void setupReportTable() {
        rptPlayerGameIdColumn.setCellValueFactory(new PropertyValueFactory<>("playerGameId"));
        rptPlayerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        rptGameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        rptPlayedOnColumn.setCellValueFactory(new PropertyValueFactory<>("playedOn"));
        rptScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
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

            long id = playerDao.insertPlayer(p);
            p.setPlayerId(id);
            refreshPlayerTable();
            clearPlayerForm();
            showInfo("Player inserted with ID: " + id);
        } catch (SQLException e) {
            showError("Insert player failed: " + e.getMessage());
        }
    }

    @FXML
    private void updatePlayer() {
        try {
            String idText = playerIdField.getText();
            if (idText == null || idText.isBlank()) {
                showError("Select a player from the table first (click row).");
                return;
            }
            Player p = new Player();
            p.setPlayerId(Long.parseLong(idText));
            p.setFirstName(firstNameField.getText());
            p.setLastName(lastNameField.getText());
            p.setAddress(addressField.getText());
            p.setPostalCode(postalCodeField.getText());
            p.setProvince(provinceField.getText());
            p.setPhoneNumber(phoneNumberField.getText());
            playerDao.updatePlayer(p);
            refreshPlayerTable();
            showInfo("Player updated.");
        } catch (SQLException ex) {
            showError("Update player failed: " + ex.getMessage());
        }
    }

    @FXML
    private void insertGame() {
        try {
            Game g = new Game();
            g.setGameTitle(gameTitleField.getText());
            long id = gameDao.insertGame(g);
            g.setGameId(id);
            refreshGameTable();
            clearGameForm();
            showInfo("Game inserted with ID: " + id);
        } catch (SQLException ex) {
            showError("Insert game failed: " + ex.getMessage());
        }
    }

    private void refreshPlayerTable() {
        try {
            List<Player> list = playerDao.getAllPlayers();
            ObservableList<Player> obs = FXCollections.observableArrayList(list);
            playerTable.setItems(obs);
        } catch (SQLException ex) {
            showError("Cannot load players: " + ex.getMessage());
        }
    }

    private void refreshGameTable() {
        try {
            List<Game> list = gameDao.getAllGames();
            ObservableList<Game> obs = FXCollections.observableArrayList(list);
            gameTable.setItems(obs);
        } catch (SQLException ex) {
            showError("Cannot load games: " + ex.getMessage());
        }
    }

    private void refreshReportTable() {
        try {
            List<PlayerAndGame> list = playerAndGameDao.getAllPlayerAndGames();

            ObservableList<PlayedReportRow> rows = FXCollections.observableArrayList();
            // For each PlayerAndGame row, we need to join player & game titles
            for (PlayerAndGame pag : list) {
                // load player name and game title (simple queries)
                Player p = playerDao.getAllPlayers().stream()
                        .filter(x -> x.getPlayerId() == pag.getPlayerId())
                        .findFirst().orElse(null);

                Game g = gameDao.getAllGames().stream()
                        .filter(x -> x.getGameId() == pag.getGameId())
                        .findFirst().orElse(null);

                PlayedReportRow r = new PlayedReportRow();
                r.setPlayerGameId(pag.getPlayerGameId());
                r.setPlayerName(p != null ? p.getFirstName() + " " + p.getLastName() : "Unknown");
                r.setGameTitle(g != null ? g.getGameTitle() : "Unknown");
                r.setPlayedOn(pag.getPlayingDate() != null ? pag.getPlayingDate().toString() : "");
                r.setScore(pag.getScore());
                rows.add(r);
            }
            reportTable.setItems(rows);

        } catch (SQLException ex) {
            showError("Cannot load report: " + ex.getMessage());
        }
    }

    private void loadPlayerToForm(Player p) {
        playerIdField.setText(String.valueOf(p.getPlayerId()));
        firstNameField.setText(p.getFirstName());
        lastNameField.setText(p.getLastName());
        addressField.setText(p.getAddress());
        postalCodeField.setText(p.getPostalCode());
        provinceField.setText(p.getProvince());
        phoneNumberField.setText(p.getPhoneNumber());
    }

    private void loadGameToForm(Game g) {
        gameIdField.setText(String.valueOf(g.getGameId()));
        gameTitleField.setText(g.getGameTitle());
    }

    private void clearPlayerForm() {
        playerIdField.clear();
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        postalCodeField.clear();
        provinceField.clear();
        phoneNumberField.clear();
    }

    private void clearGameForm() {
        gameIdField.clear();
        gameTitleField.clear();
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

    // helper class for report table rows
    public static class PlayedReportRow {
        private long playerGameId;
        private String playerName;
        private String gameTitle;
        private String playedOn;
        private double score;

        public long getPlayerGameId() { return playerGameId; }
        public void setPlayerGameId(long playerGameId) { this.playerGameId = playerGameId; }
        public String getPlayerName() { return playerName; }
        public void setPlayerName(String playerName) { this.playerName = playerName; }
        public String getGameTitle() { return gameTitle; }
        public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
        public String getPlayedOn() { return playedOn; }
        public void setPlayedOn(String playedOn) { this.playedOn = playedOn; }
        public double getScore() { return score; }
        public void setScore(double score) { this.score = score; }
    }
}
