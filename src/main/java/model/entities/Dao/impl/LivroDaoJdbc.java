package model.entities.Dao.impl;

import db.DB;
import model.entities.Dao.LivroDao;
import model.entities.entities.Categoria;
import model.entities.entities.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDaoJdbc implements LivroDao {

    private static final String ALERT  = "NÃO FOI POSSIVEL ESTABELECER CONEXÃO COM O BANCO DE DADOS";
    private Connection conn;

    public LivroDaoJdbc (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Livro findLivroById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT l.*, c.nome_categoria FROM livros AS l "
                    + "INNER JOIN categorias c ON l.id_categoria = c.id_categoria "
                    + "WHERE l.id_livro = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Livro livro = new Livro();
            while (rs.next()) {
                livro.setIdLivro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                livro.setCategoria(categoria);
                livro.setDisponibilidade(true);
            }
            return livro;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Livro> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT l.*, c.nome_categoria FROM livros AS l "
                     +"INNER JOIN categorias AS c ON l.id_categoria = c.id_categoria "
                     +"ORDER BY l.id_livro";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Livro> lista = new ArrayList<>();
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                Categoria categoria = new Categoria(rs.getInt("id_categoria"), rs.getString("nome_categoria"));
                livro.setCategoria(categoria);
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
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
    public void addLivro(Livro obj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, id_categoria, disponibilidade) "
                     + "VALUES (?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getTitulo());
            ps.setString(2, obj.getAutor());
            ps.setInt(3, obj.getAnoPublicacao());
            ps.setInt(4,obj.getCategoria().getIdCategoria());
            ps.setBoolean(5, obj.getDisponibilidade());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected >  0) {
                System.out.println("Livro cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void updateLivro(Livro obj) {
        PreparedStatement ps = null;
        String sql = "UPDATE livros "
                     +"SET titulo = ?, autor = ?, ano_publicacao = ?, id_categoria = ?, disponibilidade = ? "
                     + "WHERE id_livro = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getTitulo());
            ps.setString(2, obj.getAutor());
            ps.setInt(3, obj.getAnoPublicacao());
            ps.setInt(4, obj.getCategoria().getIdCategoria());
            ps.setBoolean(5, obj.getDisponibilidade());
            ps.setInt(6, obj.getIdLivro());

            int rowsAffected  = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("DADOS ATUALIZADOS COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void removeLivro(Integer id) {
        PreparedStatement ps = null;

        String sql = "DELETE FROM livros "
                     +"WHERE id_livro = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAfected = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }
}
