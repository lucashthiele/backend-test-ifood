package com.lucashthiele.backendtestifood.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super("Produto não encontrado");
    }
}
