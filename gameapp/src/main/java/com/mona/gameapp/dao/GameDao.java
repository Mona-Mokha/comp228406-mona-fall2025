package com.mona.gameapp.dao;

import com.mona.gameapp.db.DatabaseManager;
import com.mona.gameapp.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    private final DatabaseManager db;

    public GameDao(DatabaseManager db) {
        this.db = db;
    }

    public long insertGame(Game g) throws SQLException {
        String sql = "INSERT INTO public.Game (game_title) VALUES (?) RETURNING game_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getGameTitle());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong(1);
            return -1;
        }
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> list = new ArrayList<>();
        String sql = "SELECT game_id, game_title FROM public.Game ORDER BY game_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Game g = new Game();
                g.setGameId(rs.getLong("game_id"));
                g.setGameTitle(rs.getString("game_title"));
                list.add(g);
            }
        }
        return list;
    }
}
