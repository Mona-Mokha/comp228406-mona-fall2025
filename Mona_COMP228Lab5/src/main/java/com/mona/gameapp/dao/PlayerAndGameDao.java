package com.mona.gameapp.dao;

import com.mona.gameapp.db.DatabaseManager;
import com.mona.gameapp.model.PlayerAndGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerAndGameDao {

    private final DatabaseManager db;

    public PlayerAndGameDao(DatabaseManager db) {
        this.db = db;
    }

    public long insertPlayerAndGame(PlayerAndGame pag) throws SQLException {
        String sql = "INSERT INTO public.playerandgame (player_id, game_id, playing_date, score) " +
                "VALUES (?, ?, ?, ?) RETURNING player_game_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, pag.getPlayerId());
            ps.setLong(2, pag.getGameId());
            ps.setTimestamp(3, Timestamp.valueOf(pag.getPlayingDate()));
            ps.setDouble(4, pag.getScore());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong(1);
            return -1;
        }
    }

    public List<PlayerAndGame> getAllPlayerAndGames() throws SQLException {
        List<PlayerAndGame> list = new ArrayList<>();
        String sql = "SELECT player_game_id, player_id, game_id, playing_date, score " +
                "FROM public.playerandgame ORDER BY player_game_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PlayerAndGame pag = new PlayerAndGame();
                pag.setPlayerGameId(rs.getLong("player_game_id"));
                pag.setPlayerId(rs.getLong("player_id"));
                pag.setGameId(rs.getLong("game_id"));
                Timestamp ts = rs.getTimestamp("playing_date");
                if (ts != null) pag.setPlayingDate(ts.toLocalDateTime());
                pag.setScore(rs.getDouble("score"));
                list.add(pag);
            }
        }
        return list;
    }
}