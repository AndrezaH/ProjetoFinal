package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Andreza Honório
 */
public class TipoDAO extends DataBaseDAO{

    public TipoDAO() throws Exception {
    }
    
    
 
       public ArrayList<Tipo> getLista() throws Exception{
        ArrayList<Tipo> lista = new ArrayList<Tipo>();
        String sql = "SELECT * FROM tipo";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Tipo p = new Tipo();
            p.setIdtipo(rs.getInt("idtipo"));
            p.setTipo(rs.getString("tipo"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }
}
