package ru.vsu.cs.Grushevskaya.base.repository;

import ru.vsu.cs.Grushevskaya.base.models.Disk;
import ru.vsu.cs.Grushevskaya.base.models.DiskCategoryEntity;
import ru.vsu.cs.Grushevskaya.conns.PostgreSQLConn;
import ru.vsu.cs.Grushevskaya.conns.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiskRepositoryMemory implements DiskRepository {
    protected final SQLConnector conn;

    protected final String tableName = "disk";

    private DiskRepositoryMemory() {
        try {
            conn = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DiskRepositoryMemory instance;

    public static DiskRepositoryMemory getInstance() {
        if (instance == null) {
            instance = new DiskRepositoryMemory();
        }
        return instance;
    }


    @Override
    public void add(Disk item) {
        String query = String.format("INSERT INTO %s (", tableName);
        if(item.getID() != null) {
            query += "id, ";
        }
        query += "name, disktypeid, yearofrelease, description ) VALUES ( ";

        if(item.getID() != null) {
            query += item.getID();
            query += ", ";
        }

        query += String.format("'%s', '%s', '%s', '%s' );", item.getName(), item.getDiskTypeId(), item.getYearOfRelease(), item.getDescription());

        try {
            int id = conn.insert(query, "id");
            item.setID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Disk getFromResultSet(ResultSet rs) throws SQLException {
        Disk p = new Disk(
                rs.getString("name"),
                rs.getInt("disktypeid"),
                rs.getInt("yearofrelease"),
                rs.getString("description"));
        p.setID(rs.getInt("id"));
        return p;
    }

    @Override
    public Disk getById(int id) {
        String query = String.format("SELECT id, name, disktypeid, yearofrelease, description FROM %s WHERE id = %d",
                tableName, id);
        try {
            ResultSet rs = conn.select(query);
            rs.next();
            Disk p = getFromResultSet(rs);
            rs.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Disk> getAll() {
        List<Disk> result = new ArrayList<>();

        String query = String.format("SELECT id, name, disktypeid, yearofrelease, description FROM %s",
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
    public void update(int id, Disk newItem) throws IllegalArgumentException {
        String query = String.format("UPDATE %s SET name = '%s', disktypeid = '%s', yearofrelease = '%s', description = '%s' WHERE id = %d",
                tableName, newItem.getName(), newItem.getDiskTypeId(), newItem.getYearOfRelease(), newItem.getDescription(), id);

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
