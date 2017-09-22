package be.vdab.jdbc;

import java.sql.*;

public class JDBCconnection {
    public static final String URL = "jdbc:mysql://noelvaes.eu:3306/StudentDB1";
    public static final String USERNAME = "student";
    public static final String PASSWORD = "student123";

    public static void main(String[] args) {

//        showAllBeers("kriek");

        System.out.print("All kriek beers");
        showAllBeersWithPrepStmt(""); /*sql1*/

        System.out.print("All beers");
        showAllBeersWithPrepStmt(""); /*sql2*/

//        ChangeStock cs = new ChangeStock();
//        cs.ChangeStockOfKriek();

    }

//    private static void showAllBeers(String beer) {
//        String sql = "SELECT * FROM Beers where Name like '%" + beer + "%';";
//
//        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//              System.out.println(rs.getString("Name"));
//              }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private static void showAllBeersWithPrepStmt(String beer) {
        String sql1 = "SELECT * FROM Beers where Name like ?;";
        String sql2 = "SELECT * FROM Beers where Name like ?;";



        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql1)) {
            stmt.setString(1, "%" + beer + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("Id")+", ");
                System.out.print(rs.getString("Name")+", ");
                System.out.print(rs.getString("BrewerId")+", ");
                System.out.print(rs.getString("CategoryId")+", ");
                System.out.print(rs.getString("Price")+", ");
                System.out.print(rs.getString("Stock")+", ");
                System.out.print(rs.getString("Alcohol")+"%, ");
                System.out.println(rs.getString("Version")+", ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
