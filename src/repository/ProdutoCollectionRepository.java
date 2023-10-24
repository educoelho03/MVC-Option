package repository;

import model.Categoria;
import model.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ProdutoCollectionRepository {
    private static List<Produto> produtos;


    static {
        produtos = new Vector<>();

        Produto kindle = new Produto();
        kindle.setCategoria(CategoriaCollectionRepository.findById(1L));
        kindle.setNome("Kindle");
        kindle.setDescricao("teste");
        kindle.setDataCadastro(LocalDateTime.now());
        kindle.setPreco(BigDecimal.valueOf(899.99));

        Produto iphone = new Produto();
        iphone.setCategoria(CategoriaCollectionRepository.findById(2L));
        iphone.setNome("Iphone 14 PRO MAX");
        iphone.setDescricao("Aparelho celular da ultima geração da Apple");
        iphone.setDataCadastro(LocalDateTime.now());
        iphone.setPreco(BigDecimal.valueOf(12999.99));

        Arrays.asList(kindle, iphone).forEach(ProdutoCollectionRepository::save);
    }


    public static Produto save(Produto produto) {
        if (produtos.contains(produto)) {
            produto.setId((long) produtos.size() + 1);
            produtos.add(produto);
            return produto;
        } else {
            return null;
        }
    }

    public static List<Produto> findAll(){
        return produtos;
    }

    public static Produto findById(Long id){
        return produtos.stream().filter(produto -> produto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Produto> findByCategoria(Categoria categoria){
        return produtos.stream().filter(produto -> produto.getCategoria().equals(categoria))
                .toList();
    }
}
