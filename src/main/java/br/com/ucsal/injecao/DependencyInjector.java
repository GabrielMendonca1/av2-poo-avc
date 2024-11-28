package br.com.ucsal.injecao;

import br.com.ucsal.persistencia.PersistenciaFactory;
import java.lang.reflect.Field;

public class DependencyInjector {

    /**
     * Processa os campos anotados com @Inject e injeta as dependências necessárias.
     *
     * @param objeto O objeto no qual as dependências devem ser injetadas.
     */
    public static void injetarDependencias(Object objeto) {
        Field[] fields = objeto.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);

                try {
                    Object dependencia = PersistenciaFactory.getProdutoRepository(PersistenciaFactory.MEMORIA); // ou HSQL
                    field.set(objeto, dependencia);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao injetar dependência no campo: " + field.getName(), e);
                }
            }
        }
    }
}
