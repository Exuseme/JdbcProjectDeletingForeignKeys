package com.jdbc.koshechkin.example;

import com.jdbc.koshechkin.example.getConnection.ConnectionToDateBase;
import com.jdbc.koshechkin.example.throwException.sqlRuntimeException;
import com.sun.source.tree.Tree;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Integer id = 8;
        String deleteToCompany = """
                DELETE FROM company_storage.company WHERE id = ?;
                """;
        String deleteToEmployee = """
                DELETE FROM company_storage.employee WHERE company_id = ?;
                """;

        Connection connection = null;
        PreparedStatement preparedStatementCompany = null;
        PreparedStatement preparedStatementEmployee = null;
        try {
            connection = ConnectionToDateBase.open();
            preparedStatementEmployee = connection.prepareStatement(deleteToEmployee);
            preparedStatementCompany = connection.prepareStatement(deleteToCompany);

            connection.setAutoCommit(false);

            preparedStatementEmployee.setInt(1, id);
            preparedStatementCompany.setInt(1, id);


            preparedStatementEmployee.executeUpdate();

            preparedStatementCompany.executeUpdate();


            connection.commit();


        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null)
                connection.close();
            if (preparedStatementCompany != null) {
                preparedStatementCompany.close();
            }
            if (preparedStatementEmployee != null) {
                preparedStatementEmployee.close();
            }
        }
    }
}
