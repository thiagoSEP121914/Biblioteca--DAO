package model.entities.Dao;

import model.entities.entities.Emprestimo;
import model.entities.entities.Livro;

import java.util.List;

public interface EmprestimoDao {

    Emprestimo findEmprestimoById (Integer id);
    List<Emprestimo> findaAll ();
    void addEmprestimo (Emprestimo obj);
    void updateEmprestimo (Emprestimo obj);
    void removEmprestimo (Integer id);
}
