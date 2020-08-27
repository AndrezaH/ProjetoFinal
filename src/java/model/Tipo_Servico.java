package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Tipo_Servico {
   
    private int idtipo_servico;
    private String tipo_servico;
    private Funcionario funcionario;
    
    public Tipo_Servico(){}
    
    public Tipo_Servico(String tipo_servico, Funcionario funcionario){
        this.tipo_servico = tipo_servico;
        this.funcionario = funcionario;
    }
    public ArrayList<Tipo_Servico> getLista() throws Exception{
        Tipo_ServicoDAO DAO = new Tipo_ServicoDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        Tipo_ServicoDAO DAO = new Tipo_ServicoDAO();
        return DAO.gravar(this);
    }
    
    public Tipo_Servico getCarregaPorID(int idtipo_servico) throws Exception{
        Tipo_ServicoDAO DAO = new Tipo_ServicoDAO();
        return DAO.getCarregaPorID(idtipo_servico);
    }
    
    public boolean excluir() throws Exception{
        Tipo_ServicoDAO DAO = new Tipo_ServicoDAO();
        return DAO.excluir(this);
    }
}
