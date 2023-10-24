package repository;

import model.Categoria;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CategoriaCollectionRepository {
    private static final List<Categoria> categorias;

    static {
        categorias = new Vector<>();
        String[] categorias = {"Eletronicos", "Celulares", "Livros", "Games"};
        Arrays.asList(categorias).forEach(CategoriaCollectionRepository::save);
    }

    public static Categoria save(String s){
        Categoria c = new Categoria(s);
        if(!categorias.contains(c)){
            c.setId((long) categorias.size() + 1);
            categorias.add(c);
            return c;
        } else {
            return null;
        }
    }

    public static List<Categoria> findAll(){
        return categorias;
    }

    public static Categoria findById(Long id){
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Categoria save(Categoria categoria){
        if(!categorias.contains(categoria)){
            categoria.setId((long) categorias.size() + 1);
            categorias.add(categoria);
            return categoria;
        } else {
            return null;
        }
    }
}
