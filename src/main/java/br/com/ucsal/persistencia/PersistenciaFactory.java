package br.com.ucsal.persistencia;

import br.com.ucsal.model.Produto;
import br.com.ucsal.singleton.SingletonManager;

public class PersistenciaFactory {

    public static final int MEMORIA = 0;
    public static final int HSQL = 1;

    public static ProdutoRepository<Produto, Integer> getProdutoRepository(int type) {
        switch (type) {
            case MEMORIA:
                return (ProdutoRepository<Produto, Integer>) SingletonManager.getSingletonInstance(MemoriaProdutoRepository.class);
            case HSQL:
                return new HSQLProdutoRepository();
            default:
                throw new IllegalArgumentException("Tipo de repositório inválido: " + type);
        }
    }
}
