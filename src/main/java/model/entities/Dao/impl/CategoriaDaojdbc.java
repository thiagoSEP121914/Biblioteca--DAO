package model.entities.Dao.impl;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import db.DB;
import model.entities.Dao.CategoriaDao;
import model.entities.entities.Categoria;
import model.entities.entities.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDaojdbc implements CategoriaDao {


    private static final String ALERT = "NAO FOI POSSIVEL ESTABELER CONEXÃO COM O BANCO DE DADOS!! ";
    private Connection conn;
    public CategoriaDaojdbc (Connection conn) {
        this.conn = conn;
    }



    @Override
    public List<Livro> findBYcategoria(Integer idCategoria) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql =  "SELECT l.*, c.nome_categoria " +
                        "FROM livros AS l " +
                        "INNER JOIN categorias AS c ON l.id_categoria = c.id_categoria " +
                        "WHERE l.id_categoria = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();

            List<Livro> lista = new ArrayList<>();
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                Categoria categoria = new Categoria(rs.getInt("id_categoria"), rs.getString("nome_categoria"));
                livro.setCategoria(categoria);
                lista.add(livro);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Categoria> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM categorias";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Categoria> list = new ArrayList<>();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                list.add(categoria);
            }
            return  list;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        }
    }

    @Override
    public void addCategoria(Categoria obj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO categorias (nome_categoria) "
                     +"VALUES "
                     +"(?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getNomeCategoria());
            int rowsAffected =  ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DADOS CADASTRADOS COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void updateCategoria(Categoria obj) {
        PreparedStatement ps = null;
        String sql = "UPDATE categorias "
                     +"SET nome_categoria = ? "
                     +"WHERE id_categoria = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getNomeCategoria());
            ps.setInt(2,obj.getIdCategoria());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
            System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        }
    }

    @Override
    public void removeCategoria(Integer id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM categorias "
                     +"WHERE id_categoria = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("DADO EXCLUIDO COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        }
    }
}
