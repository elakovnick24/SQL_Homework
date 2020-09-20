package ru.netology;

import com.github.javafaker.Faker;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.mode.User;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbInteractDbUtils {
    @BeforeEach
    void setUp() throws SQLException {
        val faker = new Faker();
        val runner = new QueryRunner();
        val dataSQL = "INSERT INTO users(login, password, id) VALUES (?, ?, ?);";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
        ) {
// обычная вставка
            runner.update(conn, dataSQL, faker.name().username(), "pass", 1234);
            runner.update(conn, dataSQL, faker.name().username(), "pass", 12345);
        }
    }

    @Test
    void stubTest() throws SQLException {
        val countSQL = "SELECT COUNT(*) FROM users;";
        val usersSQL = "SELECT * FROM users;";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
        ) {
            val count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println(count);
            val first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
            System.out.println(first);
            val all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
            System.out.println(all);
            stubTest1();
        }
    }

    public static String stubTest1() throws SQLException {
     val idSQL = "SELECT id FROM users WHERE login = 'vasya';";
     val authSQL = "SELECT code FROM auth_codes WHERE user_id = (?)";
     val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
        ) {
            val idUser = runner.query(conn, idSQL,new ScalarHandler<String>());
            val codes = runner.query(conn, authSQL, new ScalarHandler<String>(),idUser);
            System.out.println(codes);
            return codes;
        }
    }
}
