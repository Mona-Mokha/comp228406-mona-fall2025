package com.mona.gameapp.dao;

import com.mona.gameapp.db.DatabaseManager;
import com.mona.gameapp.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    private final DatabaseManager db;

    public PlayerDao(DatabaseManager db) {
        this.db = db;
    }

    public long insertPlayer(Player p) throws SQLException {
        String sql = "INSERT INTO public.Player (first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?) RETURNING player_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getAddress());
            ps.setString(4, p.getPostalCode());
            ps.setString(5, p.getProvince());
            ps.setString(6, p.getPhoneNumber());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return -1;
        }
    }

    public void updatePlayer(Player p) throws SQLException {
        String sql = "UPDATE public.Player SET first_name=?, last_name=?, address=?, postal_code=?, province=?, phone_number=? WHERE player_id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getAddress());
            ps.setString(4, p.getPostalCode());
            ps.setString(5, p.getProvince());
            ps.setString(6, p.getPhoneNumber());
            ps.setLong(7, p.getPlayerId());
            ps.executeUpdate();
        }
    }

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT player_id, first_name, last_name, address, postal_code, province, phone_number FROM public.Player ORDER BY player_id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getLong("player_id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setAddress(rs.getString("address"));
                p.setPostalCode(rs.getString("postal_code"));
                p.setProvince(rs.getString("province"));
                p.setPhoneNumber(rs.getString("phone_number"));
                list.add(p);
            }
        }
        return list;
    }
}
