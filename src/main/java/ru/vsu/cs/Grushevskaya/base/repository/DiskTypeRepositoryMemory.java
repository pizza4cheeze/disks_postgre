package ru.vsu.cs.Grushevskaya.base.repository;

import ru.vsu.cs.Grushevskaya.base.models.Category;
import ru.vsu.cs.Grushevskaya.base.models.DiskType;
import ru.vsu.cs.Grushevskaya.conns.PostgreSQLConn;
import ru.vsu.cs.Grushevskaya.conns.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiskTypeRepositoryMemory implements DiskTypeRepository {
    protected final SQLConnector conn;

    protected final String tableName = "disktype";

    private DiskTypeRepositoryMemory() {
        try {
            conn = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DiskTypeRepositoryMemory instance;

    public static DiskTypeRepositoryMemory getInstance() {
        if (instance == null) {
            instance = new DiskTypeRepositoryMemory();
        }
        return instance;
    }

    @Override
    public void add(DiskType item) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(item.getID() != null) {
            query += "id, ";
        }
        query += "name) VALUES ( ";

        if(item.getID() != null) {
            query += item.getID();
            query += ", ";
        }

        query += String.format("'%s');", item.getName());

        try {
            int id = conn.insert(query, "id");
            item.setID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DiskType getById(int id) {
        String query = String.format("SELECT id, name FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            DiskType d = getFromResultSet(rs);
            rs.close();
            return d;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected DiskType getFromResultSet(ResultSet rs) throws SQLException {
        DiskType p = new DiskType(
                rs.getString("name"));
        p.setID(rs.getInt("id"));
        return p;
    }

    @Override
    public List<DiskType> getAll() {
        List<DiskType> result = new ArrayList<>();

        String query = String.format("SELECT id, name FROM %s",
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
    public void update(int id, DiskType newItem) throws IllegalArgumentException {
        String query = String.format("UPDATE %s SET name = '%s' WHERE id = %d",
                tableName, newItem.getName(), id);

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
