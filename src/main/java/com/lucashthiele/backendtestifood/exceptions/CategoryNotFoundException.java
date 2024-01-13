package com.lucashthiele.backendtestifood.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(){
        super("Categoria não encontrada");
    }
}
