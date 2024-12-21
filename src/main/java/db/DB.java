package db;


import java.sql.*;

public class DB {

    private static final String ALERT = "NAO FOI POSSIVEL ESTABELECER A CONEXAO COM O BANCO DE DADOS";
    private static Connection conn = null;

    public static Connection getConn () {
        if (conn == null) {
            try {
                String URL = "jdbc:postgresql://localhost:5432/biblioteca";
                String USER = "postgres";
                String PASSWORD = "123";
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO!!");
            } catch (SQLException e) {
                throw new RuntimeException(ALERT + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConection () {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(ALERT  + e.getMessage());
            }
        }
    }

    public static void closeStatement (Statement st) {

       if (st != null) {
           try {
               st.close();
           } catch (SQLException e) {
               throw new RuntimeException(ALERT + e.getMessage());
           }
       }
    }

    public static void closeResultSet (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(ALERT + e.getMessage());
            }
        }
    }
}
