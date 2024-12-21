package model.entities.Dao;

import db.DB;
import model.entities.Dao.impl.CategoriaDaojdbc;
import model.entities.Dao.impl.EmprestimoDaojdbc;
import model.entities.Dao.impl.LivroDaoJdbc;
import model.entities.Dao.impl.UsuarioDaoJbdc;
import model.entities.entities.Categoria;

public class DaoFactory {

    public static UsuarioDao createUsuarioDao () {
        return new UsuarioDaoJbdc(DB.getConn());
    }

    public static LivroDao createLivroDao () {
        return new LivroDaoJdbc(DB.getConn());
    }

    public static CategoriaDao createCategoriaDao () {
        return  new CategoriaDaojdbc(DB.getConn());
    }
    public static EmprestimoDao createEmprestimoDao () {
        return  new EmprestimoDaojdbc(DB.getConn());
    }
}
