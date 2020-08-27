package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class GerarOrcamentoDAO extends  DataBaseDAO{

    public GerarOrcamentoDAO() throws Exception {
    }
    
    public GerarOrcamento getCarregaPorID(int idcliente, int idservico, int idparcelamento, int idparcela, int idtipo_servico, int idorcamento) throws Exception{
    
        GerarOrcamento go = new GerarOrcamento();
        
        String sql = "SELECT * FROM cliente WHERE idcliente=?"
                + "INNER JOIN servico WHERE idservico=?"
                + "INNER JOIN parcelamento WHERE idparcelamento=?"
                + "INNER JOIN parcela WHERE idparcela=?"
                + "INNER JOIN tipo_servico WHERE idtipo_servico=?"
                + "INNER JOIN orcamento WHERE idorcamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idcliente);
        pstm.setInt(2, idservico);
        pstm.setInt(3, idparcelamento);
        pstm.setInt(4, idparcela);
        pstm.setInt(5, idtipo_servico);
        pstm.setInt(6, idorcamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome_ceo(rs.getString("nome_ceo"));
            go.setCliente(c);
        }
        this.desconectar();
        return go;
        
    }
    public ArrayList<GerarOrcamento> getLista(int idcliente, int idservico, int idparcelamento, int idparcela, int idtipo_servico, int idorcamento) throws Exception{
    
        ArrayList<GerarOrcamento> lista = new ArrayList<GerarOrcamento>();
        
        String sql = "SELECT * FROM cliente"
                + "INNER JOIN servico"
                + "INNER JOIN parcelamento"
                + "INNER JOIN parcela"
                + "INNER JOIN tipo_servico"
                + "INNER JOIN orcamento";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idcliente);
        pstm.setInt(2, idservico);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            GerarOrcamento go = new GerarOrcamento();
            Cliente c = new Cliente();
            go.setCliente(c.getCarregaPorID(rs.getInt("idcliente")));
            lista.add(go);
        }
        this.desconectar();
        return lista;
    }
 }
