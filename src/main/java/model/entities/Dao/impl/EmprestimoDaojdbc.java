package model.entities.Dao.impl;

import db.DB;
import model.entities.Dao.EmprestimoDao;
import model.entities.entities.Emprestimo;
import model.entities.entities.Livro;
import model.entities.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PrimitiveIterator;

public class EmprestimoDaojdbc implements EmprestimoDao {

    private static final String ALERT = "OCORREU UM ERRO NO BANCO DE DADOS" ;
    private Connection conn;

    public EmprestimoDaojdbc () {
    }

    public EmprestimoDaojdbc (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Emprestimo findEmprestimoById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * from emprestimos "
                     + "WHERE id_emprestimo = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Emprestimo emprestimo = new Emprestimo();
            while (rs.next()) {
                emprestimo.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setIdusuario(rs.getInt("id_usuario"));
                emprestimo.setIdLivro(rs.getInt("id_livro"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }
            return emprestimo;
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Emprestimo> findaAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM emprestimos";


        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Emprestimo> lista = new ArrayList<>();
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setIdusuario(rs.getInt("id_usuario"));
                emprestimo.setIdLivro(rs.getInt("id_livro"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
                lista.add(emprestimo);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(sql);
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void addEmprestimo(Emprestimo obj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO emprestimos (id_usuario, id_livro, data_emprestimo, data_devolucao) "
                     +"VALUES"
                      +"(?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getIdusuario());
            ps.setInt(2, obj.getIdLivro());
            ps.setDate(3, java.sql.Date.valueOf(obj.getDataEmprestimo()));
            ps.setDate(4, java.sql.Date.valueOf(obj.getDataDevolucao()));
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dados cadastrados com sucesso!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void updateEmprestimo(Emprestimo obj) {
        PreparedStatement ps =  null;
        String sql = "UPDATE emprestimos "
                     +"SET id_usuario = ?, id_livro = ?, data_emprestimo = ?, data_devolucao = ? "
                     + "WHERE id_emprestimo = ?";

        try {

            ps = conn.prepareStatement(sql);
            ps.setInt(1,obj.getIdusuario());
            ps.setInt(2,obj.getIdLivro());
            ps.setDate(3, java.sql.Date.valueOf(obj.getDataEmprestimo()));
            ps.setDate(4, java.sql.Date.valueOf(obj.getDataDevolucao()));
            ps.setInt(5,obj.getIdEmprestimo());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dados atualizados com sucesso!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void removEmprestimo(Integer id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM emprestimos "
                     + "WHERE id_emprestimos = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("EMPRESTIMO REMOVIDO COM SUCESSO!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(ALERT + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
}
