package padrao;

import model.Categoria;
import model.Produto;
import repository.CategoriaCollectionRepository;
import repository.ProdutoCollectionRepository;
import view.CategoriaView;
import view.Opcao;
import view.OpcaoView;
import view.ProdutoView;

import javax.swing.*;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Opcao opcao = null;

        do {
            opcao = OpcaoView.select();
            switch (opcao){
                case CADASTRAR_CATEGORIA -> cadastrarCategoria();
                case CADASTRAR_PRODUTO -> cadastrarProduto();
                case ALTERAR_PRODUTO -> alterarProduto();
                case CONSULTAR_PRODUTO_POR_ID -> consultarProdutoPorId();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> consultarProdutoPorCategoria();
            }
        } while (opcao != Opcao.ENCERRAR_SISTEMA);
    }


    public static void cadastrarCategoria(){
        CategoriaView view = new CategoriaView();
        Categoria categoria = CategoriaView.form();
        CategoriaCollectionRepository.save(categoria);
        view.sucesso(categoria);
    }

    public static void cadastrarProduto(){
        Produto produto = ProdutoView.form();
        ProdutoCollectionRepository.save(produto);
        ProdutoView.sucesso(produto);
    }

    public static void alterarProduto(){
        Produto produto = ProdutoView.select();
        ProdutoView.update(produto);
    }

    public static void consultarProdutoPorId(){
        Long id = 0L;
        do {
            try {
                id = Long.parseLong(JOptionPane.showInputDialog("Informe o id do produto"));
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Id Inválido");
            }
        } while (id <= 0);

        Produto p = ProdutoCollectionRepository.findById(id);
        if(p != null){
            ProdutoView.show(p);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }

    public static void consultarProdutoPorCategoria(){
        Categoria categoria = CategoriaView.select(null);
        List<Produto> produtos = ProdutoCollectionRepository.findByCategoria(categoria);
        if(produtos.size() == 0)
            JOptionPane.showMessageDialog(null, "Não encontramos produtos cadastros para esta categoria");
        produtos.forEach(System.out::println);
        produtos.forEach(ProdutoView::show);

    }
}
