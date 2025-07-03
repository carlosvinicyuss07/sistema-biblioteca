package com.biblioteca.app;

import com.biblioteca.dao.MembroDAO;
import com.biblioteca.model.Membro;

import java.sql.SQLException;
import java.util.List;

public class SistemaBibliotecaApp {

    public static void main(String[] args) {
        MembroDAO membroDAO = new MembroDAO();

        // --- Teste de Inserção de Membros ---
        System.out.println("--- Testando Inserção de Membros ---");
        Membro membro1 = new Membro("João Silva", "MAT001", "9999-1111");
        Membro membro2 = new Membro("Maria Souza", "MAT002", "9999-2222");
        Membro membro3 = new Membro("Carlos Ferreira", "MAT003", "9999-3333");

        try {
            boolean salvo1 = membroDAO.salvarMembro(membro1);
            if (salvo1) {
                System.out.println("Membro 1 salvo: " + membro1.getNome() + " (ID: " + membro1.getId() + ")");
            } else {
                System.out.println("Falha ao salvar Membro 1: " + membro1.getNome());
            }

            boolean salvo2 = membroDAO.salvarMembro(membro2);
            if (salvo2) {
                System.out.println("Membro 2 salvo: " + membro2.getNome() + " (ID: " + membro2.getId() + ")");
            } else {
                System.out.println("Falha ao salvar Membro 2: " + membro2.getNome());
            }

            boolean salvo3 = membroDAO.salvarMembro(membro3);
            if (salvo3) {
                System.out.println("Membro 3 salvo: " + membro3.getNome() + " (ID: " + membro3.getId() + ")");
            } else {
                System.out.println("Falha ao salvar Membro 3: " + membro3.getNome());
            }

        } catch (SQLException e) {
            System.err.println("Erro durante a inserção de membros: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Testando Listagem de Membros ---");
        // --- Teste de Listagem de Membros ---
        try {
            List<Membro> membros = membroDAO.listarTodosMembros();
            if (membros.isEmpty()) {
                System.out.println("Nenhum membro encontrado.");
            } else {
                System.out.println("Lista de Membros:");
                for (Membro membro : membros) {
                    System.out.println("ID: " + membro.getId() +
                            ", Nome: " + membro.getNome() +
                            ", Matrícula: " + membro.getMatricula() +
                            ", Telefone: " + membro.getTelefone());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro durante a listagem de membros: " + e.getMessage());
            e.printStackTrace();
        }

    }
}