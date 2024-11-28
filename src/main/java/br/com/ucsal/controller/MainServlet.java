package br.com.ucsal.controller;

import br.com.ucsal.injecao.DependencyInjector;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        ProdutoService produtoService = new ProdutoService();
        DependencyInjector.injetarDependencias(produtoService);

        // Registrar comandos com dependências injetadas
        commands.put("/produto/adicionar", new ProdutoAdicionarCommand());
        commands.put("/produto/editar", new ProdutoEditarCommand());
        commands.put("/produto/excluir", new ProdutoExcluirCommand());
        commands.put("/produto/listar", new ProdutoListarCommand());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        Command command = commands.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Rota não encontrada: " + path);
        }
    }
}
