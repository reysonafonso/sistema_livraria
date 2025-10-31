package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();


    public Biblioteca() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = df.parse("20/10/1933");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Autor autor = new Autor(1, "Graciliano Ramos", dt);
        this.addAutor(autor);

        Date dt2 = null;
        try {
            dt2 = df.parse("20/11/1893");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Autor autor2 = new Autor(1, "Fernando Pessoa", dt2);
        this.addAutor(autor2);

        Livro livro = new Livro(1, "Livro de Graciliano", autor, true, new Date());
        this.addLivro(livro);

        Livro livro2 = new Livro(2, "Livro de Fernando Pessoa", autor2, true, new Date());
        this.addLivro(livro2);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void addAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<Livro> livroDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();

        for(Livro livro : livros) {
            if(livro.isDisponivel()){
                disponiveis.add(livro);
            }
        }

        return disponiveis;
    }

    public boolean livroDisponivel(int id) {

        boolean emprestado = false;

        for(Livro livro : livros) {
            if(livro.isDisponivel()){
                emprestado = true;
            }
        }

        return emprestado;
    }
}
