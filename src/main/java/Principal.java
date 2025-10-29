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

        Autor autor = new Autor();
        autor.setId(1);
        autor.setNome("Graciliano Ramos");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = df.parse("20/10/1933");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        autor.setDataNascimento(dt);

        bib.addAutor(autor);


        Livro livro = new Livro();
        livro.setId(1);
        livro.setAutor(autor);
        livro.setTitulo("Livro de Graciliano Ramos");
        livro.setDisponivel(true);
        livro.setDataCadastro(new Date());
        bib.addLivro(livro);

        System.out.println("Empréstimos de livros\n");

        System.out.print("Deseja ver a lista de livros disponíveis? ");
        resp = sc.nextLine();
        while(!resp.equals("NAO")){
            List<Livro> disponiveis = bib.getLivros();
            for(Livro l: disponiveis){
                if(l.isDisponivel()) {
                    System.out.println(l.getId());
                    System.out.println(l.getTitulo());
                }
            }
            System.out.print("Deseja emprestar algum livro? ");
            resp = sc.nextLine();
            if(resp.equals("SIM")){
                System.out.print("Digite o ID do livro: ");
                int id = Integer.parseInt(sc.nextLine());

                System.out.println("Digite o nome do cliente: ");
                String nomeCliente = sc.nextLine();

                emp.setId(1);
                emp.setNomeCliente(nomeCliente);

                for(Livro l: disponiveis) {
                    if (l.getId() == id) {
                        emp.setLivro(l);
                        emp.setDataEmprestimo(new Date());
                        emp.setDataDevolucao(null);
                        l.setDisponivel(false);
                    }
                }
                emps.add(emp);
            }
            bib.setEmprestimos(emps);
            System.out.println("Emprestimo realizado com sucesso");
            System.out.print("Deseja ver a lista de livros disponíveis? ");
            resp = sc.nextLine();
        }
        for(Emprestimo em: emps){
            System.out.println(em.getId());
            System.out.println(em.getNomeCliente());
            System.out.println(em.getLivro().getTitulo());
            System.out.println(em.getDataEmprestimo().toString());
        }
        System.out.println("Obrigado por usar o sistema de empréstimo, até a próxima!\n");
    }
}