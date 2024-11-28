package br.com.ucsal.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RotaScanner {
    private static final Map<String, Method> rotas = new HashMap<>();

    public static void carregarRotas(Class<?>... controllers) {
        for (Class<?> controller : controllers) {
            for (Method method : controller.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Rota.class)) {
                    Rota rota = method.getAnnotation(Rota.class);
                    rotas.put(rota.value(), method);
                }
            }
        }
    }

    public static Method obterRota(String caminho) {
        return rotas.get(caminho);
    }
}
