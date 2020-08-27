package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ParcelaDAO extends DataBaseDAO{

    public ParcelaDAO() throws Exception {
    }
 
    
    
    public ArrayList<Parcela> getLista(int idparcelamento, int idorcamento) throws Exception{
        
        ArrayList<Parcela> lista = new ArrayList<Parcela>();
        String sql = "SELECT * FROM parcela WHERE idparcelamento=? AND idorcamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idparcelamento);
        pstm.setInt(2, idorcamento);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Parcela p = new Parcela();
            p.setIdparcela(rs.getInt("idparcela"));
            p.setValor(rs.getDouble("valor"));
            p.setStatus(rs.getString("status"));
            Parcelamento pa = new Parcelamento();
            p.setParcelamento(pa.getCarregaPorID(rs.getInt("idparcelamento")));
            Orcamento o = new Orcamento();
            p.setOrcamento(o.getCarregaPorID(rs.getInt("idorcamento")));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }
    
    public Parcela getCarregaPorID(int idparcela, int idparcelamento, int idorcamento) throws Exception{
        Parcela p = new Parcela();
        String sql = "SELECT * FROM parcela WHERE idparcela=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idparcela);
        pstm.setInt(2, idparcelamento);
        pstm.setInt(3, idorcamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            p.setIdparcela(rs.getInt("idparcela"));
            p.setValor(rs.getDouble("valor"));
            p.setStatus(rs.getString("status"));
            Parcelamento pa = new Parcelamento();
            p.setParcelamento(pa.getCarregaPorID(rs.getInt("idparcelamento")));
            Orcamento o = new Orcamento();
            p.setOrcamento(o.getCarregaPorID(rs.getInt("idorcamento")));
        }
        this.desconectar();
        return p;
        
    }
    public boolean alterar(Parcela p) {
        try {
            this.conectar();
            String sql1 ="UPDATE parcela SET status = 'Pago' WHERE idparcela=? AND idparcelamento=? AND idorcamento=? ";
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
            pstm1.setInt(1, p.getIdparcela());
            pstm1.setInt(2, p.getParcelamento().getIdparcelamento());
            pstm1.setInt(3, p.getOrcamento().getIdorcamento());
            pstm1.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
    }
}

