package ru.vsu.cs.Grushevskaya.base.repository;

import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.conns.PostgreSQLConn;
import ru.vsu.cs.Grushevskaya.conns.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DCERepositoryMemory implements DCERepository {
    protected final SQLConnector conn;

    protected final String tableName = "diskcategoryentity";

    private DCERepositoryMemory() {
        try {
            conn = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DCERepositoryMemory instance;

    public static DCERepositoryMemory getInstance() {
        if (instance == null) {
            instance = new DCERepositoryMemory();
        }
        return instance;
    }


    @Override
    public void add(DiskCategoryEntity item) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(item.getID() != null) {
            query += "id, ";
        }
        query += "diskid, categoryid ) VALUES ( ";

        if(item.getID() != null) {
            query += item.getID();
            query += ", ";
        }

        query += String.format("'%s', '%s' );", item.getDiskId(), item.getCategoryId());

        try {
            int id = conn.insert(query, "id");
            item.setID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected DiskCategoryEntity getFromResultSet(ResultSet rs) throws SQLException {
        DiskCategoryEntity p = new DiskCategoryEntity(
                rs.getInt("diskid"),
                rs.getInt("categoryid"));
        p.setID(rs.getInt("id"));
        return p;
    }

    @Override
    public DiskCategoryEntity getById(int id) {
        String query = String.format("SELECT id, diskid, categoryid FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            DiskCategoryEntity p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DiskCategoryEntity> getAll() {
        List<DiskCategoryEntity> result = new ArrayList<>();

        String query = String.format("SELECT id, diskid, categoryid FROM %s",
                tableName);
        try {
            ResultSet rs = conn.select(query);
            while (rs.next()) {
                result.add(getFromResultSet(rs));
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void update(int id, DiskCategoryEntity newItem) throws IllegalArgumentException {
        String query = String.format("UPDATE %s SET diskid = '%s', categoryid = '%s' WHERE id = %d",
                tableName, newItem.getDiskId(), newItem.getCategoryId(), id);

        try {
            if (conn.update(query) != 1) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        String query = String.format("DELETE FROM %s WHERE id = %d",
                tableName, id);
        try {
            if (conn.delete(query) != 1) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
