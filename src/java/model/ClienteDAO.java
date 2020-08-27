package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO extends DataBaseDAO{
    
    public ClienteDAO() throws Exception{}
    
    
    public ArrayList<Cliente> getLista() throws Exception{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome_empresa(rs.getString("nome_empresa"));
            c.setNome_ceo(rs.getString("nome_ceo"));
            c.setTelefone(rs.getString("telefone"));
            c.setEndereco(rs.getString("endereco"));
            c.setDescricao(rs.getString("descricao"));
            c.setArea_atuacao(rs.getString("area_atuacao"));
            lista.add(c);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Cliente c){
        try{
            this.conectar();
            String sql;
            if(c.getIdcliente()==0)
                sql ="INSERT INTO cliente (nome_empresa,nome_ceo, telefone, endereco, descricao, area_atuacao) VALUES (?, ?, ?, ?, ?, ?)";
            else
                sql = "UPDATE cliente SET nome_empresa=?, nome_ceo=?, telefone=?, endereco=?, descricao=?, area_atuacao=? WHERE idcliente=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNome_empresa());
            pstm.setString(2, c.getNome_ceo());
            pstm.setString(3, c.getTelefone());
            pstm.setString(4, c.getEndereco());
            pstm.setString(5, c.getDescricao());
            pstm.setString(6, c.getArea_atuacao());
            if(c.getIdcliente()>0)
                pstm.setInt(7, c.getIdcliente());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
    
    public Cliente getCarregaPorID(int idcliente) throws Exception{
        Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE idcliente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idcliente);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome_empresa(rs.getString("nome_empresa"));
            c.setNome_ceo(rs.getString("nome_ceo"));
            c.setTelefone(rs.getString("telefone"));
            c.setEndereco(rs.getString("endereco"));
            c.setDescricao(rs.getString("descricao"));
            c.setArea_atuacao(rs.getString("area_atuacao"));
        }
        this.desconectar();
        return c;
        
    }
    
    public boolean excluir(Cliente c){
        try{
            this.conectar();
            String sql ="DELETE FROM cliente WHERE idcliente=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, c.getIdcliente());
            pstm.execute();
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
}
