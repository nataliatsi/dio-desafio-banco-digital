package com.nataliasti.banco.conta.strategy;

import com.nataliasti.banco.conta.Conta;

public interface Operacao {
    void executar(Conta conta, Double valor);
}
