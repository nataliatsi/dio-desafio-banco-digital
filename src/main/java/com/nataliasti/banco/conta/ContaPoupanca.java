package com.nataliasti.banco.conta;

public class ContaPoupanca extends Conta{

    @Override
    public void exibirInfo(){
        System.out.println("----- Conta Poupança -----");
        super.exibirInfo();
    }

}
