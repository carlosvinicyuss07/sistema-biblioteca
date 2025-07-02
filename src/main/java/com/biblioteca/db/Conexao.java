package com.biblioteca.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_biblioteca?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "1910";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void main(String[] args) {
        try (Connection conexao = Conexao.getConnection()) {
            System.out.println("Conexão com o Banco de Dados estabelecida com sucesso!");
            System.out.println("Conectando ao banco: " + conexao.getCatalog());
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
