package model;

import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.Setter; 

@Getter
@Setter
//Classe BEAN é uma classe java que possui todos os atributos como métodos gets e sets

public class Orcamento {
    
    private int idorcamento;
    private double valor;
    private Date data_orcamento;
    private Date data_assinatura;
    private Date data_entrada;
    private Date data_vencimento;
    private String descricao;
    private Cliente cliente;
    private ArrayList<Servico> servico;
   private ArrayList<Servico> naoServico;
   private ArrayList<Parcelamento> parcelamento;
    
    public Orcamento(){}
    
    public Orcamento(int idorcamento, double valor, Date data_orcamento, Date data_assinatura, Date data_entrada, Date data_vencimento, String descricao, Cliente cliente, ArrayList<Servico> servico, ArrayList<Servico> naoServico, ArrayList<Parcelamento> parcelamento){
        this.idorcamento = idorcamento;
        this.valor = valor;
        this.data_orcamento = data_orcamento;
        this.data_assinatura = data_assinatura;
        this.data_entrada = data_entrada;
        this.data_vencimento = data_vencimento;
        this.descricao = descricao;
        this.cliente = cliente;
        this.servico = servico;
        this.naoServico = naoServico;
        this.parcelamento = parcelamento;
    }
    
   public ArrayList<Orcamento> getLista() throws Exception{
       OrcamentoDAO DAO = new OrcamentoDAO();
       return DAO.getLista();
   }
   
   public boolean gravar() throws Exception{
       OrcamentoDAO DAO = new OrcamentoDAO();
       return DAO.gravar(this);
   }
   
   public Orcamento getCarregaPorID(int idorcamento) throws Exception{
       OrcamentoDAO DAO = new OrcamentoDAO();
       return DAO.getCarregaPorID(idorcamento);
   }
   
   public boolean excluir() throws Exception{
       OrcamentoDAO DAO = new OrcamentoDAO();
       return DAO.excluir(this);
   }
    public boolean vincular(int idservico, int idorcamento) throws Exception{
        OrcamentoDAO DAO = new OrcamentoDAO();
        return DAO.vincular(idservico, idorcamento);
    }
    public boolean desvincular(int idservico, int idorcamento) throws Exception{
        OrcamentoDAO DAO = new OrcamentoDAO();
        return DAO.desvincular(idservico, idorcamento);
    }
}
