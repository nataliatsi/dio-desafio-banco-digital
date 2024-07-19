package com.nataliasti.banco.menu;

import com.nataliasti.banco.conta.Conta;
import com.nataliasti.banco.conta.ContaCorrente;
import com.nataliasti.banco.conta.ContaPoupanca;
import com.nataliasti.banco.conta.strategy.Deposito;
import com.nataliasti.banco.conta.strategy.Saque;
import com.nataliasti.banco.conta.strategy.Transferencia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Map<Long, Conta> contas;
    private Conta contaAtiva;

    public Menu() {
        scanner = new Scanner(System.in);
        contas = new HashMap<>();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Criar conta");
            System.out.println("2. Selecionar conta");
            System.out.println("3. Exibir informações da conta ativa");
            System.out.println("4. Depositar na conta ativa");
            System.out.println("5. Transferir da conta ativa");
            System.out.println("6. Sacar da conta ativa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    selecionarConta();
                    break;
                case 3:
                    exibirInformacoes();
                    break;
                case 4:
                    realizarDeposito();
                    break;
                case 5:
                    realizarTransferencia();
                    break;
                case 6:
                    realizarSaque();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("---------------------------------------------");

        } while (opcao != 0);
    }

    public void criarConta() {
        System.out.println("Escolha o tipo da conta: ");
        System.out.println("1 - Conta Poupança");
        System.out.println("2 - Conta Corrente");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Conta conta = null;
        if (tipoConta == 1) {
            conta = new ContaPoupanca();
            System.out.println("Conta Poupança criada com sucesso.");
        } else if (tipoConta == 2) {
            conta = new ContaCorrente();
            System.out.println("Conta Corrente criada com sucesso.");
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }

        long numeroConta = contas.size() + 1;
        contas.put(numeroConta, conta);
        System.out.println("Número da conta: " + numeroConta);
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            System.out.println("Contas cadastradas:");
            for (Map.Entry<Long, Conta> entry : contas.entrySet()) {
                System.out.println("Número da conta: " + entry.getKey() + ", Tipo: " +
                        (entry.getValue() instanceof ContaPoupanca ? "Poupança" : "Corrente"));
            }
        }
    }

    public void selecionarConta() {
        listarContas();
        System.out.print("Digite o número da conta: ");
        long numeroConta = scanner.nextLong();
        contaAtiva = contas.get(numeroConta);
        if (contaAtiva != null) {
            System.out.println("Conta " + numeroConta + " selecionada.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void exibirInformacoes() {
        if (contaAtiva != null) {
            contaAtiva.exibirInfo();
        } else {
            System.out.println("Nenhuma conta ativa selecionada.");
        }
    }

    public void realizarDeposito() {
        if (contaAtiva != null) {
            System.out.print("Digite o valor para depósito: ");
            double valor = scanner.nextDouble();
            contaAtiva.setOperacao(new Deposito());
            contaAtiva.realizarOperacao(valor);
        } else {
            System.out.println("Nenhuma conta ativa selecionada.");
        }
    }

    public void realizarTransferencia() {
        if (contaAtiva != null) {
            System.out.print("Digite o número da conta de destino: ");
            long numeroContaDestino = scanner.nextLong();
            Conta contaDestino = contas.get(numeroContaDestino);
            if (contaDestino != null) {
                System.out.print("Digite o valor para transferência: ");
                double valor = scanner.nextDouble();
                contaAtiva.setOperacao(new Transferencia(contaDestino));
                contaAtiva.realizarOperacao(valor);
            } else {
                System.out.println("Conta de destino não encontrada.");
            }
        } else {
            System.out.println("Nenhuma conta ativa selecionada.");
        }
    }

    public void realizarSaque() {
        if (contaAtiva != null) {
            System.out.print("Digite o valor para saque: ");
            double valor = scanner.nextDouble();
            contaAtiva.setOperacao(new Saque());
            contaAtiva.realizarOperacao(valor);
        } else {
            System.out.println("Nenhuma conta ativa selecionada.");
        }
    }
}
