package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ParcelamentoDAO extends DataBaseDAO{
    
    public ParcelamentoDAO() throws Exception{}
    
    public ArrayList<Parcelamento> getLista() throws Exception {
        ArrayList<Parcelamento> lista = new ArrayList<Parcelamento>();
        String sql = "SELECT * FROM parcelamento ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Parcelamento p = new Parcelamento();
            p.setIdparcelamento(rs.getInt("idparcelamento"));
            Tipo t = new Tipo();
            t.setIdtipo(rs.getInt("idtipo"));
            p.setTipo(t);
            p.setQuantidade(rs.getDouble("quantidade"));
            p.setTotal(rs.getDouble("total"));
            Orcamento o = new Orcamento();
            p.setOrcamento(o.getCarregaPorID(rs.getInt("idorcamento")));
            lista.add(p);            
        }
        this.desconectar();
        return lista;
    }
    public ArrayList<Parcelamento> getListaParcelamentosPorOcamento(int id) throws Exception {
        ArrayList<Parcelamento> lista = new ArrayList<Parcelamento>();
        String sql = "SELECT p.*, t.* FROM parcelamento p, tipo t WHERE p.idorcamento=? AND p.idtipo= t.idtipo";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Parcelamento p = new Parcelamento();
            p.setIdparcelamento(rs.getInt("p.idparcelamento"));
            Tipo t = new Tipo();
            t.setIdtipo(rs.getInt("p.idtipo"));
            t.setTipo(rs.getString("t.tipo"));
            p.setTipo(t);
            p.setQuantidade(rs.getDouble("p.quantidade"));
            p.setTotal(rs.getDouble("p.total"));
            Orcamento o = new Orcamento();
            p.setOrcamento(o.getCarregaPorID(rs.getInt("p.idorcamento")));
            lista.add(p);            
        }
        this.desconectar();
        return lista;
    }

public boolean gravar(Parcelamento p) {
        try {
            this.conectar();
            String sql;
            if (p.getIdparcelamento()== 0) {
                sql = "INSERT INTO parcelamento (idtipo,quantidade,total,idorcamento) "
                        + " VALUES (?,?,?,?)";
            } else {
                sql = "UPDATE parcelamento SET idtipo=?,quantidade=?,total=?, idorcamento=? "
                        + "WHERE idarcelamento=?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getTipo().getIdtipo());
            pstm.setDouble(2, p.getQuantidade());
            pstm.setDouble(3, p.getTotal());
            pstm.setInt(4, p.getOrcamento().getIdorcamento());
           
            if(p.getIdparcelamento()>0)
                pstm.setInt(5, p.getIdparcelamento());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
public void regParcelamento(Parcelamento p) throws Exception{ //é booleando
        this.conectar();
       try{
        String sql = "INSERT INTO parcelamento (idtipo,quantidade,total,idorcamento) VALUES(?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        Tipo t = new Tipo();
        pstm.setInt(1, p.getTipo().getIdtipo());
        pstm.setDouble(2, p.getQuantidade());
        pstm.setDouble(3, p.getTotal());
        pstm.setInt(4, p.getOrcamento().getIdorcamento());
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        if(rs.next()){
            p.setIdparcelamento(rs.getInt(1));
        }
        for(int i=0; i<p.getQuantidade(); i++){
            String sql_par = "INSERT INTO parcela (idparcela,valor,status, idparcelamento,idorcamento) VALUES(?,?,?,?,?)";
            PreparedStatement pstm_par = conn.prepareStatement(sql_par);
            pstm_par.setInt(1, (i+1));
            pstm_par.setDouble(2, (p.getTotal()/p.getQuantidade()));
            pstm_par.setString(3, "Não pago"); 
            pstm_par.setInt(4, p.getIdparcelamento());
            pstm_par.setInt(5, p.getOrcamento().getIdorcamento());
            pstm_par.execute();
        }
        this.desconectar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Parcelamento getCarregaPorID(int idparcelamento) throws Exception {
        Parcelamento p = new Parcelamento();
        String sql = "SELECT p.*, t.tipo, o.idorcamento FROM parcelamento p, tipo t, orcamento o "
                + "WHERE o.idorcamento = p.idorcamento and t.tipo = p.idtipo and p.idparcelamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idparcelamento);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            p.setIdparcelamento(rs.getInt("idparcelamento"));
            Tipo t = new Tipo();
            t.setIdtipo(rs.getInt("idtipo"));
            p.setTipo(t);
            p.setQuantidade(rs.getDouble("quantidade"));
            p.setTotal(rs.getDouble("total"));
            Orcamento o = new Orcamento();
            o.setIdorcamento(rs.getInt("p.idorcamento"));
            p.setOrcamento(o);
        }
        this.desconectar();
        return p;

    }

    public boolean excluir(Parcelamento p) {
        try {
            this.conectar();
               String sql = "DELETE FROM parcela WHERE idparcelamento=? AND idorcamento=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdparcelamento());
            pstm.setInt(2, p.getOrcamento().getIdorcamento());
            pstm.execute();
            String sql2 = "DELETE FROM parcelamento WHERE idparcelamento=? AND idorcamento=? ";
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            pstm2.setInt(1, p.getIdparcelamento());
            pstm2.setInt(2, p.getOrcamento().getIdorcamento());
            pstm2.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
