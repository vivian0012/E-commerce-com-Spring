package com.ProdutosECompras.Project.services.exceptions;

import java.io.Serial;

// Recebemos o Objeto na exceção que aplicamos, no caso, nos arquivos de service.
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Verifique o ID e tente novamente: " + id);
    }
}
