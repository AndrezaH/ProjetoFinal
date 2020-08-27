package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Tipo_ServicoDAO extends DataBaseDAO {

    

    public Tipo_ServicoDAO() throws Exception {
    }

    public ArrayList<Tipo_Servico> getLista() throws Exception {
        ArrayList<Tipo_Servico> lista = new ArrayList<Tipo_Servico>();
        String sql = "SELECT * FROM tipo_servico ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Tipo_Servico ts = new Tipo_Servico();
            ts.setIdtipo_servico(rs.getInt("idtipo_servico"));
            ts.setTipo_servico(rs.getString("tipo_servico"));
            Funcionario f = new Funcionario();
            ts.setFuncionario(f.getCarregaPorID(rs.getInt("idfuncionario")));
            lista.add(ts);
        }
        this.desconectar();
        return lista;
    }

public boolean gravar(Tipo_Servico ts) {
        try {
            this.conectar();
            String sql;
            if (ts.getIdtipo_servico() == 0) {
                sql = "INSERT INTO tipo_servico (tipo_servico, idfuncionario) "
                        + " VALUES (?,?)";
            } else {
                sql = "UPDATE tipo_servico SET tipo_servico=?, idfuncionario=? "
                        + "WHERE idtipo_servico=?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ts.getTipo_servico());
            pstm.setInt(2, ts.getFuncionario().getIdfuncionario());
            if(ts.getIdtipo_servico()>0)
                pstm.setInt(3, ts.getIdtipo_servico());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Tipo_Servico getCarregaPorID(int idtipo_servico) throws Exception {
        Tipo_Servico ts = new Tipo_Servico();
        String sql = "SELECT ts.*, f.idfuncionario FROM tipo_servico ts"
                + " INNER JOIN funcionario f ON f.idfuncionario = ts.idfuncionario WHERE ts.idtipo_servico=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idtipo_servico);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            ts.setIdtipo_servico(rs.getInt("idtipo_servico"));
            ts.setTipo_servico(rs.getString("tipo_servico"));
            Funcionario f = new Funcionario();
            f.setIdfuncionario(rs.getInt("ts.idfuncionario"));
            ts.setFuncionario(f);
        }
        this.desconectar();
        return ts;

    }

    public boolean excluir(Tipo_Servico ts) {
        try {
            this.conectar();
               String sql = "DELETE FROM tipo_servico WHERE idtipo_servico=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, ts.getIdtipo_servico());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
