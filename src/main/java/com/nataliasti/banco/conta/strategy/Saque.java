package com.nataliasti.banco.conta.strategy;

import com.nataliasti.banco.conta.Conta;
import lombok.var;

public class Saque implements Operacao{
    public void executar(Conta conta, Double valor) {
        var saldo = conta.getSaldo();
        if(saldo >= valor){
            conta.setSaldo(saldo - valor);
            System.out.printf("Saque no valor de R$%.2f foi realizado com sucesso.\n", valor);
        } else {
            System.out.println("Saldo insuficiente.");
        }

    }
}
