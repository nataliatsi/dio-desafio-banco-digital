package com.nataliasti.banco.conta;

import com.nataliasti.banco.conta.strategy.Operacao;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
@EqualsAndHashCode(of = "numero")
public abstract class Conta {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private Long numero;
    private Integer agencia;
    private Double saldo;
    private LocalDateTime dataCriacao;
    private Operacao operacao;

    public Conta() {
        this.numero = gerarNumeroDaConta();
        this.agencia = 1;
        this.saldo = 0.0;
        this.dataCriacao = LocalDateTime.now();
    }

    public void realizarOperacao(Double valor){
        if(operacao != null){
            operacao.executar(this, valor);
        } else {
            System.out.println("Nenhuma operação definida.");
        }
    }

    public Long gerarNumeroDaConta(){
        Random random = new Random();
        return 10000000L + (long) (random.nextDouble() * 90000000L);
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public void exibirInfo(){
        System.out.println("Número da conta: " + numero);
        System.out.println("Agência: " + agencia);
        System.out.println("Saldo: R$" + saldo);
        System.out.println("Data de criação: " + dataCriacao.format(FORMATTER));
    }

}
