package com.biblioteca.dao;

import com.biblioteca.db.Conexao;
import com.biblioteca.model.Membro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembroDAO {

    public boolean salvarMembro(Membro membro) throws SQLException {

        String sql = "INSERT INTO membros (nome, matricula, telefone) VALUES (?,?,?);";
        try (Connection conexao = Conexao.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, membro.getNome());
            statement.setString(2, membro.getMatricula());
            statement.setString(3, membro.getTelefone());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        membro.setId(rs.getInt(1));
                        System.out.println("Membro salvo com sucesso! ID: " + membro.getId());
                    }
                }
                return true;
            }
            return false;
        }
    }

    public List<Membro> listarTodosMembros() throws SQLException {

        String sql = "SELECT id, nome, matricula, telefone FROM membros";
        try (Connection conexao = Conexao.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            List<Membro> membros = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                String telefone = rs.getString("telefone");

                Membro membro = new Membro(id, nome, matricula, telefone);
                membros.add(membro);
            }
            return membros;
        }
    }
}
