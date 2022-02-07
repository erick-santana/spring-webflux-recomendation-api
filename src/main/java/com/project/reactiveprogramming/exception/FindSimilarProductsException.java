package com.project.reactiveprogramming.exception;

public class FindSimilarProductsException extends BaseException {

    public FindSimilarProductsException() {
        super(500, "not_found", "Não foram encontrados produtos similares para as categorias selecionadas");
    }
}
