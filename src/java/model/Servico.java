package model;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Servico {

    private int idservico;
    private String descricao;
    private Tipo_Servico tipo_servico;
   
    
    public Servico() {
    }

    public Servico(int idservico, String descricao, Tipo_Servico tipo_servico) {
        this.idservico = idservico;
        this.descricao = descricao;
        this.tipo_servico = tipo_servico;
    }
    public ArrayList<Servico> getLista() throws Exception{
        ServicoDAO DAO = new ServicoDAO();
        return DAO.getLista();
    }
    public boolean gravar() throws Exception{
       ServicoDAO DAO = new ServicoDAO();
         return DAO.gravar(this);
    }
    public Servico getCarregaPorID(int idservico) throws Exception{
        ServicoDAO DAO = new ServicoDAO();
        return DAO.getCarregaPorID(idservico);
    }
    public boolean excluir() throws Exception{
        ServicoDAO DAO = new ServicoDAO();
        return DAO.excluir(this);
    }
   
}

