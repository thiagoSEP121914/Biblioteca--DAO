package model.entities.Dao.impl;

import db.DB;
import model.entities.Dao.UsuarioDao;
import model.entities.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJbdc implements UsuarioDao {


    public static final String ALERT = "ERRO AO CONECTAR AO BANCO DE DADOS";
    public Connection conn;
    public UsuarioDaoJbdc (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios "
                     +"WHERE id_usuario = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Usuario usuario = new Usuario();

            while (rs.next()) {

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Usuario> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuarios";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                list.add(usuario);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void addUsuario(Usuario obj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO usuarios (nome, email, telefone) "
                     +"VALUES "
                     +"(?, ?, ?) ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getNome());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getTelefone());
            int rowsAffect = ps.executeUpdate();

            if (rowsAffect > 0 ) {
                System.out.println("DADOS CADASTRADOS COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        }
    }

    @Override
    public void updateUsuario(Usuario ob) {
        PreparedStatement ps  = null;
        String sql = "UPDATE usuarios "
                     +"SET nome = ?, email = ?, telefone = ? "
                     + "WHERE id_usuario = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,ob.getNome());
            ps.setString(2, ob.getEmail());
            ps.setString(3, ob.getTelefone());
            ps.setInt(4, ob.getIdUsuario());
            int rowsAffect = ps.executeUpdate();

            if (rowsAffect > 0) {
                System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
            }
        }catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void removeUsuario(Integer id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM usuarios "
                     +"WHERE id_usuario = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DADOS DELETADOS COM SUCESSO!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
}
