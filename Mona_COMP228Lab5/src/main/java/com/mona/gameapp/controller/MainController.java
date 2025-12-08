package com.mona.gameapp.controller;

import com.mona.gameapp.dao.GameDao;
import com.mona.gameapp.dao.PlayerAndGameDao;
import com.mona.gameapp.dao.PlayerDao;
import com.mona.gameapp.db.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML private TabPane mainTabPane;

    // DAOs and DB connection
    private DatabaseManager dbManager;
    private PlayerDao playerDao;
    private GameDao gameDao;
    private PlayerAndGameDao playerAndGameDao;

    // Child controllers (injected from fx:include)
    @FXML private VBox playerViewController;
    @FXML private VBox gameViewController;
    @FXML private VBox reportViewController;
    @FXML private VBox playGameViewController;
    @FXML private PlayGameController playGameViewControllerController;

    // These will be injected automatically by FXMLLoader
    @FXML private PlayerController playerViewControllerController;
    @FXML private GameController gameViewControllerController;
    @FXML private ReportController reportViewControllerController;


    public void initialize() {
        // --- CREATE DATABASE CONNECTION AND DAOS ---
        dbManager = new DatabaseManager(
                "aws-1-ca-central-1.pooler.supabase.com",
                5432,
                "postgres",
                "postgres.qvczqichssyzqovtolnn",
                "Monmon@2025"
        );

        playerDao = new PlayerDao(dbManager);
        gameDao = new GameDao(dbManager);
        playerAndGameDao = new PlayerAndGameDao(dbManager);

        System.out.println("MainController initialized with DB connection.");

        // --- INJECT DAOs INTO CHILD CONTROLLERS ---
        if (playerViewControllerController != null) {
            playerViewControllerController.setPlayerDao(playerDao);
        }
        if (gameViewControllerController != null) {
            gameViewControllerController.setGameDao(gameDao);
        }

        if (playGameViewControllerController != null) {
            playGameViewControllerController.setDaos(playerDao, gameDao, playerAndGameDao);
            playGameViewControllerController.setReportController(reportViewControllerController);
        }
        if (reportViewControllerController != null) {
            reportViewControllerController.setPlayerAndGameDao(playerAndGameDao);
            reportViewControllerController.setPlayerDao(playerDao);   // <-- ADD THIS
        }
        if (reportViewControllerController != null) {
            reportViewControllerController.setPlayerAndGameDao(playerAndGameDao);
            reportViewControllerController.setPlayerDao(playerDao);
            reportViewControllerController.setGameDao(gameDao);

            // load player list + report types once
            reportViewControllerController.loadPlayers();
            reportViewControllerController.loadReportTypes();
        }



        mainTabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab.getText().equals("Play Game") && playGameViewControllerController != null) {
                playGameViewControllerController.loadCombos();
            }
        });




    }

    // Getters if needed elsewhere
    public PlayerDao getPlayerDao() { return playerDao; }
    public GameDao getGameDao() { return gameDao; }
    public PlayerAndGameDao getPlayerAndGameDao() { return playerAndGameDao; }
}
