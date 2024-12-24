package org.example;

import db.DB;
import model.entities.Dao.*;
import model.entities.Dao.impl.CategoriaDaojdbc;
import model.entities.Dao.impl.LivroDaoJdbc;
import model.entities.entities.Categoria;
import model.entities.entities.Emprestimo;
import model.entities.entities.Livro;
import model.entities.entities.Usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        EmprestimoDao emprestimoDao = DaoFactory.createEmprestimoDao();

        LocalDate data = LocalDate.of(2021, 12, 24);
        LocalDate dataDevolucao = LocalDate.of(2025, 1, 30);
        Emprestimo emprestimo = new Emprestimo(5, 1, 4, data, dataDevolucao);
        emprestimoDao.updateEmprestimo(emprestimo);

    }
}