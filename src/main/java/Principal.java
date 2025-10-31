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

        Biblioteca bib = new Biblioteca();

        int op;

        do {
            System.out.println("\nSistema de empréstimos de livros\n");
            op = Menu();
            switch (op) {
                case 0:
                    break;
                case 1:
                    listarLivrosDisponiveis(bib);
                    break;
                case 2:
                    realizarEmprestimo(bib);
                    break;
                case 3:
                    cadastrarUmLivro();
                    break;
                case 4:
                    cadastrarUmAutor();
                    break;
                case 5:
                    cadastrarUmCliente();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (op != 0);
        System.out.println("Obrigado por usar o sistema de empréstimo, até a próxima!\n");
    }

    private static void cadastrarUmCliente() {
    }

    private static void cadastrarUmAutor() {
    }

    private static void cadastrarUmLivro() {
    }

    private static void realizarEmprestimo(Biblioteca bib) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ID do livro: ");
        int id = Integer.parseInt(sc.nextLine());

        if(bib.livroDisponivel(id)){
            Emprestimo emp = new Emprestimo();
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
            bib.addEmprestimo(emp);
            System.out.println("Emprestimo realizado com sucesso");
        }else{
            System.out.println("Não é possivel realizar o emprestimo. Livro já emprestado!");
        }
    }

    private static void listarLivrosDisponiveis(Biblioteca biblioteca) {
        for(Livro l: biblioteca.livroDisponiveis()){
            System.out.println(l.getId() + " - " + l.getTitulo());
        }
    }

    private static int Menu(){
        System.out.println("Menu Principal");
        System.out.println("1 - Listagem de Livros disponiveis");
        System.out.println("2 - Realizar emprestimo");
        System.out.println("3 - Cadastrar um livro");
        System.out.println("4 - Cadastrar um autor");
        System.out.println("5 - Cadastrar um cliente");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }
}