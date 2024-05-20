package com.example.minimalrotation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlConnection {
    public static void main(String[] args) throws SQLException {
        String sql1 = "CREATE TABLE Positions ( X INT, Y INT, Name VARCHAR(255) );";
        String sql2 = "INSERT INTO Positions (X, Y, Name) VALUES (0, 0, 'M3');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (0, 25, 'M2');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (0, 50, 'M1');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (0, 65, 'Security_Hut1');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (30, 0, 'Security_Hut2');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (30, 20, 'Military');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (30, 45, 'Student_Life_Center');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (30, 65, 'Stop1');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (55, 0, 'Hangar2');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (55, 35, 'T3');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (55, 55, 'T4');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (55, 65, 'BinBin');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (75, 0, 'Security_Hut4');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (75, 35, 'Pool');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (75, 55, 'T2');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (75, 65, 'Stop2');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (90, 0, 'T1');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (90, 30, 'Dining_Hall');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (90, 50, 'Hangar1');\n" +
                "INSERT INTO Positions (X, Y, Name) VALUES (90, 65, 'Security_Hut3');";
        String url = "jdbc:postgresql://localhost:5432/Map";
        String username = "postgres";
        String password = "Password1";

        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        st.executeQuery(sql2);
    }
}
