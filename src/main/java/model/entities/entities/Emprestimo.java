package model.entities.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Emprestimo implements Serializable {


    private Integer idEmprestimo;
    private Integer idusuario;
    private Integer idLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo () {
    }

    public Emprestimo(Integer idEmprestimo, Integer idusuario, Integer idLivro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idusuario = idusuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "idEmprestimo=" + idEmprestimo +
                ", idusuario=" + idusuario +
                ", idLivro=" + idLivro +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}