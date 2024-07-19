package com.nataliasti.banco.conta.strategy;

import com.nataliasti.banco.conta.Conta;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transferencia implements Operacao{
    private Conta contaDestino;

    public void executar(Conta conta, Double valor) {
        if(conta.getSaldo() >= valor){
            conta.setSaldo(conta.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);
            System.out.println("Transferência de " + valor + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }

    }
}
