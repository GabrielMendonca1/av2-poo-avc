package br.com.ucsal.singleton;

import java.util.HashMap;
import java.util.Map;

public class SingletonManager {

    private static final Map<Class<?>, Object> singletons = new HashMap<>();

    /**
     * Retorna a instância Singleton de uma classe anotada com @Singleton.
     *
     * @param clazz A classe anotada.
     * @return A instância Singleton.
     */
    public static synchronized Object getSingletonInstance(Class<?> clazz) {
        if (!singletons.containsKey(clazz)) {
            if (clazz.isAnnotationPresent(Singleton.class)) {
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    singletons.put(clazz, instance);
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao criar instância Singleton para a classe: " + clazz.getName(), e);
                }
            } else {
                throw new IllegalArgumentException("A classe " + clazz.getName() + " não está anotada com @Singleton.");
            }
        }
        return singletons.get(clazz);
    }
}
