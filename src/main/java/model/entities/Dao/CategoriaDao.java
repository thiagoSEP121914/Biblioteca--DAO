package model.entities.Dao;

import model.entities.entities.Categoria;
import model.entities.entities.Livro;

import java.util.List;

public interface CategoriaDao {

    List<Livro> findBYcategoria (Integer idCategoria);
    List<Categoria> findAll ();
    void addCategoria (Categoria obj);
    void updateCategoria (Categoria obj);
    void removeCategoria (Integer id);
}
