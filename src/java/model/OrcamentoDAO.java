package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class OrcamentoDAO extends DataBaseDAO{
    
    public OrcamentoDAO()throws Exception{}
    
    
    public ArrayList<Orcamento> getLista() throws Exception{
        ArrayList<Orcamento> lista = new ArrayList<Orcamento>();
        String sql = "SELECT * FROM orcamento";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Orcamento o = new Orcamento();
            o.setIdorcamento(rs.getInt("idorcamento"));
            o.setValor(rs.getDouble("valor"));
            o.setData_orcamento(rs.getDate("data_orcamento"));
            o.setData_assinatura(rs.getDate("data_assinatura"));
            o.setData_entrada(rs.getDate("data_entrada"));
            o.setData_vencimento(rs.getDate("data_vencimento"));
            o.setDescricao(rs.getString("descricao"));
            Cliente c = new Cliente();
            o.setCliente(c.getCarregaPorID(rs.getInt("idcliente")));
            lista.add(o);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Orcamento o){
        try{
            this.conectar();
            String sql;
            if(o.getIdorcamento()==0)
                sql ="INSERT INTO orcamento (valor, data_orcamento, data_assinatura, data_entrada, "
                        + "data_vencimento, descricao, idcliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            else
                sql = "UPDATE orcamento SET valor=?, data_orcamento=?, data_assinatura=?, data_entrada=?, "
                        + "data_vencimento=?, descricao=?, idcliente=? WHERE idorcamento=?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, o.getValor());
            pstm.setDate(2, new Date (o.getData_orcamento().getTime()));
            pstm.setDate(3, new Date (o.getData_assinatura().getTime()));
            pstm.setDate(4, new Date (o.getData_entrada().getTime()));
            pstm.setDate(5, new Date (o.getData_vencimento().getTime()));
            pstm.setString(6, o.getDescricao());
            pstm.setInt(7, o.getCliente().getIdcliente());
            if(o.getIdorcamento()>0)
                pstm.setInt(8, o.getIdorcamento());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
    
    public Orcamento getCarregaPorID(int idorcamento) throws Exception{
        Orcamento o = new Orcamento();
        String sql = "SELECT o.*, c.idcliente FROM orcamento o"
                + " INNER JOIN cliente c ON "
                + " c.idcliente = o.idcliente WHERE o.idorcamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idorcamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            o.setIdorcamento(rs.getInt("idorcamento"));
            o.setValor(rs.getDouble("valor"));
            o.setData_orcamento(rs.getDate("data_orcamento"));
            o.setData_assinatura(rs.getDate("data_assinatura"));
            o.setData_entrada(rs.getDate("data_entrada"));
            o.setData_vencimento(rs.getDate("data_vencimento"));
            o.setDescricao(rs.getString("descricao"));
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            o.setCliente(c);
            o.setServico(servicosVinculadosAoOrcamento(idorcamento));
            o.setNaoServico(servicosNaoVinculadosAoOrcamento(idorcamento));
        }
        this.desconectar();
        return o;
        
    }
    
    public boolean excluir(Orcamento o){
        try{
            this.conectar();
            String sql ="DELETE FROM orcamento WHERE idorcamento=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, o.getIdorcamento());
            pstm.execute();
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }

    public ArrayList<Servico> servicosVinculadosAoOrcamento(int idorcamento) throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>();
        
        String sql = "SELECT s.* FROM servico_orcamento as so, "
                + " servico as s WHERE so.idservico = s.idservico AND "
                + "so.idorcamento=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idorcamento);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Servico s = new Servico();
            s.setIdservico(rs.getInt("idservico"));
            s.setDescricao(rs.getString("descricao"));
            Tipo_Servico ts = new Tipo_Servico();
            ts.setIdtipo_servico((rs.getInt("idtipo_servico")));
            s.setTipo_servico(ts);
            lista.add(s);  
    }
         this.desconectar();
        return lista;
    }
    
    public ArrayList<Servico> servicosNaoVinculadosAoOrcamento(int idorcamento) throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>();
        
        String sql = " SELECT * FROM servico WHERE idservico "
                + " NOT IN (SELECT idservico FROM servico_orcamento "
                         + " WHERE idorcamento =?)";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idorcamento);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Servico s = new Servico();
            s.setIdservico(rs.getInt("idservico"));
            s.setDescricao(rs.getString("descricao"));
            Tipo_Servico ts = new Tipo_Servico();
            ts.setIdtipo_servico((rs.getInt("idtipo_servico")));
            s.setTipo_servico(ts);
            lista.add(s);  
    }
         this.desconectar();
        return lista;
        }
    
    public boolean vincular (int idservico, int idorcamento){
        
        try{
            String sql = "INSERT INTO servico_orcamento (idservico, idorcamento) "
                    + "VALUES (?,?)";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,idservico);
            pstm.setInt(2,idorcamento);
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
        
    public boolean desvincular(int idservico, int idorcamento){
        
        try{
            String sql = "DELETE FROM servico_orcamento WHERE "
                    + " idservico=? AND idorcamento=? ";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,idservico);
            pstm.setInt(2,idorcamento);
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}
    