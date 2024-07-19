package com.nataliasti.banco.conta;

public class ContaCorrente extends Conta{

    @Override
    public void exibirInfo(){
        System.out.println("----- Conta Corrente -----");
        super.exibirInfo();
    }

}
