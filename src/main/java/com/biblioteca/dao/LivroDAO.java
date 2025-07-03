package com.biblioteca.dao;

import com.biblioteca.db.Conexao;
import com.biblioteca.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean salvarLivro(Livro livro) {

        String sql = "INSERT INTO livros (titulo, autor, isbn, ano_publicacao, disponivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = Conexao.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getIsbn());
            statement.setInt(4, livro.getAnoPublicacao());
            statement.setBoolean(5, livro.isDisponivel());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        livro.setId(rs.getInt(1));
                        System.out.println("Livro salvo com sucesso! ID: " + livro.getId());
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Livro> listarTodosLivros() throws SQLException {

        String sql = "SELECT id, titulo, autor, isbn, ano_publicacao, disponivel FROM livros";
        try (Connection conexao = Conexao.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            List<Livro> livros = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                int anoPublicacao = rs.getInt("ano_publicacao");
                boolean disponivel = rs.getBoolean("disponivel");
                Livro livro = new Livro(id, titulo, autor, isbn, anoPublicacao, disponivel);

                livros.add(livro);
            }
            return livros;

        }
    }
}
