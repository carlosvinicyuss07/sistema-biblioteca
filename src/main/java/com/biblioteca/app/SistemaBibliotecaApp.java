package com.biblioteca.app;

import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Livro;

public class SistemaBibliotecaApp {

    public static void main(String[] args) {
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", "978-853590-083-4", 1899);
        Livro livro2 = new Livro("1984", "George Orwell", "978-853591-484-9", 1949);
        Livro livro3 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "978-857827-069-7", 1943);
        Livro livro4 = new Livro("A Revolução dos Bichos", "George Orwell", "978-853590-272-0", 1945);
        Livro livro5 = new Livro("Crime e Castigo", "Fiódor Dostoiévski", "978-857326-174-3", 1866);

        LivroDAO livroDAO1 = new LivroDAO();

        // testando o metodo salvarLivro(Livro livro) da Classe LivroDAO
        boolean salvo = livroDAO1.salvarLivro(livro1);
        if (salvo) {
            System.out.println("Livro 1 salvo com ID: " + livro1.getId());
        } else {
            System.out.println("Falha ao salvar Livro 1.");
        }

    }
}
