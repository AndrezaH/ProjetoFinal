package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PagamentoDAO extends DataBaseDAO{
    
    
    public PagamentoDAO()throws Exception{}
    
    
    public ArrayList<Pagamento> getLista() throws Exception{
        ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
        String sql = "SELECT * FROM pagamento";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Pagamento p = new Pagamento();
            p.setIdpagamento(rs.getInt("idpagamento"));
            p.setValor(rs.getDouble("valor"));
            p.setData(rs.getDate("data"));
            p.setStatus(rs.getString("status"));
            Funcionario f = new Funcionario();
            p.setFuncionario(f.getCarregaPorID(rs.getInt("idfuncionario")));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Pagamento p){
       
        try{
            this.conectar();
            String sql;
            if(p.getIdpagamento()==0)
                sql ="INSERT INTO pagamento (valor, data, status, idfuncionario) VALUES (?, ?, ?, ?)";
            else
                sql = "UPDATE pagamento SET valor=?, data=?, status=?, idfuncionario=? WHERE idpagamento=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, p.getValor());
            pstm.setDate(2, new Date(p.getData().getTime()));
            pstm.setString(3, p.getStatus());
            pstm.setInt(4, p.getFuncionario().getIdfuncionario());
            if(p.getIdpagamento()>0)
                pstm.setInt(5, p.getIdpagamento());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
    
    public Pagamento getCarregaPorID(int idpagamento) throws Exception{
        Pagamento p = new Pagamento();
        String sql = "SELECT p.*, f.idfuncionario FROM pagamento p" 
                + " INNER JOIN funcionario f ON "
                + " f.idfuncionario = p.idfuncionario WHERE p.idpagamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idpagamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            p.setIdpagamento(rs.getInt("idpagamento"));
            p.setValor(rs.getDouble("valor"));
            p.setData(rs.getDate("data"));
            p.setStatus(rs.getString("status"));
            Funcionario f = new Funcionario();
            f.setIdfuncionario(rs.getInt("p.idfuncionario"));
            p.setFuncionario(f);
        }
        this.desconectar();
        return p;
        
    }
    
    public boolean excluir(Pagamento p){
        try{
            this.conectar();
            String sql ="UPDATE pagamento SET status = 2 WHERE idpagamento=? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdpagamento());
            pstm.execute();
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}

