import models.Autor;
import models.Biblioteca;
import models.Emprestimo;
import models.Livro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Principal {
    public static void main(String[] args) {
        String resp = "";
        Scanner sc = new Scanner(System.in);

        Biblioteca bib = new Biblioteca();
        Emprestimo emp = new Emprestimo();

        List<Emprestimo> emps = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = df.parse("20/10/1933");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Autor autor = new Autor(1, "Graciliano Ramos", dt);
        bib.addAutor(autor);

        Date dt2 = null;
        try {
            dt2 = df.parse("20/11/1893");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Autor autor2 = new Autor(1, "Fernando Pessoa", dt2);
        bib.addAutor(autor2);

        Livro livro = new Livro(1, "Livro de Graciliano", autor, true, new Date());
        bib.addLivro(livro);

        Livro livro2 = new Livro(2, "Livro de Fernando Pessoa", autor2, true, new Date());
        bib.addLivro(livro2);

        System.out.println("Empréstimos de livros\n");

        System.out.print("Deseja ver a lista de livros disponíveis? ");
        resp = sc.nextLine();
        while(!resp.equals("NAO")){
            for(Livro l: bib.livroDisponiveis()){
                System.out.println(l.getId());
                System.out.println(l.getTitulo());
            }
            System.out.print("Deseja emprestar algum livro? ");
            resp = sc.nextLine();
            if(resp.equals("SIM")){
                System.out.print("Digite o ID do livro: ");
                int id = Integer.parseInt(sc.nextLine());

                if(bib.livroDisponivel(id)){
                    System.out.println("Digite o nome do cliente: ");
                    String nomeCliente = sc.nextLine();
                    emp.setId(1);
                    emp.setNomeCliente(nomeCliente);
                    for(Livro l: bib.livroDisponiveis()){
                        if(l.getId() == id){
                            emp.setLivro(l);
                            l.setDisponivel(false);
                        }
                    }
                    emp.setDataEmprestimo(new Date());
                    emp.setDataDevolucao(null);
                    emps.add(emp);
                    bib.setEmprestimos(emps);
                    System.out.println("Emprestimo realizado com sucesso");
                }else{
                    System.out.println("Não é possivel realizar o emprestimo. Livro já emprestado!");
                }
            }
            System.out.print("Deseja ver a lista de livros disponíveis? ");
            resp = sc.nextLine();
        }

        System.out.println("Relatorio de Emprestimo");
        for(Emprestimo em: emps){
            System.out.println(em.getId());
            System.out.println(em.getNomeCliente());
            System.out.println(em.getLivro().getTitulo());
            System.out.println(em.getDataEmprestimo());
        }

        System.out.println("Todos os livros");
        for(Livro liv: bib.getLivros()){
            System.out.println(liv.getId());
            System.out.println(liv.getTitulo());
            System.out.println(liv.getAutor().getNome());
            System.out.println(liv.isDisponivel() ? "Disponível" : "Emprestado");
        }
        System.out.println("Obrigado por usar o sistema de empréstimo, até a próxima!\n");
    }
}