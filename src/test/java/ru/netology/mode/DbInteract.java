package ru.netology.mode;


import com.github.javafaker.Faker;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInteract {
    @BeforeEach
    void setUp() throws SQLException {
        val faker = new Faker();
        val dataSQL = "INSERT INTO users(login, password) VALUES (?, ?);";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass" );
                val dataStmt = conn.prepareStatement(dataSQL); ){
            dataStmt.setString(1, faker.name().username()); dataStmt.setString(2, "password"); dataStmt.executeUpdate();
            dataStmt.setString(1, faker.name().username()); dataStmt.setString(2, "password"); dataStmt.executeUpdate();
        } }
}
