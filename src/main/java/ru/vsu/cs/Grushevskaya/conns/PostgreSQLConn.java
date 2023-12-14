package ru.vsu.cs.Grushevskaya.conns;

import java.sql.*;

public class PostgreSQLConn implements SQLConnector {
    protected final String url;
    protected final Connection connection;


    private PostgreSQLConn() throws SQLException {
        String db = "disks";
        String user = "postgres";
        String pass = "1234";
        url = "jdbc:postgresql://localhost:5432/" + db + "?user=" + user + "&password=" + pass;

        connection = DriverManager.getConnection(url);
    }

    private static PostgreSQLConn INSTANCE;

    public static PostgreSQLConn getInstance() throws SQLException {
        if(INSTANCE == null){
            INSTANCE = new PostgreSQLConn();
        }
        return INSTANCE;
    }

    @Override
    public ResultSet select(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int insert(String query, String colRet) throws SQLException {
        System.out.println(query);
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();

        if(!resultSet.next()) {
            throw new RuntimeException();
        }
        int id = resultSet.getInt(colRet);
        if(resultSet.next()){
            throw new RuntimeException();
        }
        return id;
    }

    @Override
    public int update(String query) throws SQLException {
        System.out.println(query);
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement.getUpdateCount();
    }

    @Override
    public int delete(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement.getUpdateCount();
    }
}
