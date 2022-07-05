package com.jdbc.koshechkin.example;

import com.jdbc.koshechkin.example.getConnection.ConnectionToDateBase;
import com.jdbc.koshechkin.example.throwException.sqlRuntimeException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        var select = """
                SELECT c.id, c.name, c.data FROM company_storage.company c;
                """;

        try (var con = ConnectionToDateBase.open();
             var preparedStatement = con.prepareStatement(select)) {
            var result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("id") + ":"
                        + result.getString("name")
                        + ":" + result.getDate("data"));
            }
        } catch (SQLException e) {
            throw new sqlRuntimeException(e);
        }
    }
}
