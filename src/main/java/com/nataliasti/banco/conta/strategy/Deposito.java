package com.nataliasti.banco.conta.strategy;

import com.nataliasti.banco.conta.Conta;
import lombok.var;

public class Deposito implements Operacao{
    public void executar(Conta conta, Double valor) {
        var saldo = conta.getSaldo();
        conta.setSaldo(saldo + valor);
        System.out.printf("Depósito de R$%.2f realizado com sucesso.\n", valor);

    }
}
