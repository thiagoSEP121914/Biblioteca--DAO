package org.example;

import db.DB;
import model.entities.Dao.CategoriaDao;
import model.entities.Dao.DaoFactory;
import model.entities.Dao.LivroDao;
import model.entities.Dao.UsuarioDao;
import model.entities.Dao.impl.CategoriaDaojdbc;
import model.entities.Dao.impl.LivroDaoJdbc;
import model.entities.entities.Categoria;
import model.entities.entities.Livro;
import model.entities.entities.Usuario;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        System.out.println("=== SEARCH USER id ===");
        Usuario usuario = usuarioDao.findById(1);
        System.out.println(usuario);

        System.out.println("\n=== SEARCH ALL USER ===");
        List<Usuario> list = usuarioDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n=== ADD USER ===");
        usuarioDao.addUsuario(new Usuario("Matias vinna", "amtiasratus@gmail.com", "1121324376"));


    }
}