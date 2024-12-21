package model.entities.Dao;

import model.entities.entities.Livro;

import java.util.List;

public interface LivroDao {

    Livro findLivroById (Integer id);
    List<Livro> findAll ();
    void addLivro (Livro obj);
    void updateLivro (Livro obj);
    void removeLivro (Integer obj);
}
