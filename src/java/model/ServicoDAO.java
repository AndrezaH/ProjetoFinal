package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class ServicoDAO extends DataBaseDAO {

    public ServicoDAO() throws Exception {
    }
    
    
   
    public ArrayList<Servico> getLista() throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>();
        String sql = "SELECT * FROM servico";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Servico s = new Servico();
            s.setIdservico(rs.getInt("idservico" ));
            s.setDescricao(rs.getString("descricao"));
            Tipo_Servico ts = new Tipo_Servico();
            s.setTipo_servico(ts.getCarregaPorID(rs.getInt("idtipo_servico")));
            lista.add(s);
        }
        this.desconectar();
        return lista;
    }
    public boolean gravar(Servico s){
        try{
            this.conectar();
            String sql;
            if(s.getIdservico()==0)
                sql ="INSERT INTO servico (descricao, idtipo_servico) VALUES (?,?)";
            else
                sql = "UPDATE servico SET descricao=?, idtipo_servico=? WHERE idservico=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.getDescricao());
            pstm.setInt(2, s.getTipo_servico().getIdtipo_servico());
            if(s.getIdservico()>0)
                pstm.setInt(3, s.getIdservico());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
    public Servico getCarregaPorID(int idservico) throws Exception{
        Servico s = new Servico();
        String sql = "SELECT s.*, ts.idtipo_servico FROM servico s"
                + " INNER JOIN tipo_servico ts ON "
                + " ts.idtipo_servico = s.idtipo_servico WHERE s.idservico=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idservico);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            s.setIdservico(rs.getInt("idservico"));
            s.setDescricao(rs.getString("descricao"));
            Tipo_Servico ts = new Tipo_Servico();
            s.setTipo_servico(ts.getCarregaPorID(rs.getInt("idtipo_servico")));
        }
        this.desconectar();
        return s;
        
    }
    
    public boolean excluir(Servico s){
        try{
            this.conectar();
            String sql ="DELETE FROM servico WHERE idservico=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, s.getIdservico());
            pstm.execute();
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}