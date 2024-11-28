package br.com.ucsal.service;

import br.com.ucsal.injecao.Inject;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.ProdutoRepository;
import java.util.List;

public class ProdutoService {

    @Inject
    private ProdutoRepository<Produto, Integer> produtoRepository;

    public void adicionarProduto(String nome, double preco) {
        produtoRepository.adicionar(new Produto(null, nome, preco));
    }

    public void atualizarProduto(Produto produto) {
        produtoRepository.atualizar(produto);
    }

    public void removerProduto(Integer id) {
        produtoRepository.remover(id);
    }

    public Produto obterProdutoPorId(Integer id) {
        return produtoRepository.obterPorID(id);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listar();
    }
}
