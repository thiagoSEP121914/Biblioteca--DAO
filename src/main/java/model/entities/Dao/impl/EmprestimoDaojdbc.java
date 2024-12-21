package model.entities.Dao.impl;

import model.entities.Dao.EmprestimoDao;
import model.entities.entities.Emprestimo;
import model.entities.entities.Livro;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public class EmprestimoDaojdbc implements EmprestimoDao {

    private Connection conn;

    public EmprestimoDaojdbc (Connection conn) {
        this.conn = conn;
    }

    public EmprestimoDaojdbc () {

    }

    @Override
    public Emprestimo findEmprestimoById(Integer id) {
        return null;
    }

    @Override
    public List<Emprestimo> findaAll() {
        return null;
    }

    @Override
    public void addEmprestimo(Livro obj) {

    }

    @Override
    public void updateEmprestimo(Livro obj) {

    }

    @Override
    public void removEmprestimo(Integer id) {

    }
}
