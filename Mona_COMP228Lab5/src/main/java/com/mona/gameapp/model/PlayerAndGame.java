package com.mona.gameapp.model;

import java.time.LocalDateTime;

public class PlayerAndGame {
    private long playerGameId;
    private long playerId;
    private long gameId;
    private LocalDateTime playingDate;
    private double score;

    public long getPlayerGameId() { return playerGameId; }
    public void setPlayerGameId(long playerGameId) { this.playerGameId = playerGameId; }

    public long getPlayerId() { return playerId; }
    public void setPlayerId(long playerId) { this.playerId = playerId; }

    public long getGameId() { return gameId; }
    public void setGameId(long gameId) { this.gameId = gameId; }

    public LocalDateTime getPlayingDate() { return playingDate; }
    public void setPlayingDate(LocalDateTime playingDate) { this.playingDate = playingDate; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}
