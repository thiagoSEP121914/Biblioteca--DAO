package model.entities.Dao;

import model.entities.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    Usuario findById (Integer id);
    List<Usuario> findAll ();
    void addUsuario (Usuario obj);
    void updateUsuario (Usuario ob);
    void removeUsuario (Integer id);
}
