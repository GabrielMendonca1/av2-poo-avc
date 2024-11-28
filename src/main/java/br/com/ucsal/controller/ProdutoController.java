package br.com.ucsal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view/*") 
public class ProdutoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private Map<String, Command> commands = new HashMap<>();

	
    @Override
    public void init() {
        commands.put("/editarProduto", new ProdutoEditarCommand());
        commands.put("/adicionarProduto", new ProdutoAdicionarCommand());
        commands.put("/excluirProduto", new ProdutoExcluirCommand());
        commands.put("/listarProdutos", new ProdutoListarCommand());
        commands.put("/", new ProdutoListarCommand()); 
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        Command command = commands.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
        }
    }

	


}
