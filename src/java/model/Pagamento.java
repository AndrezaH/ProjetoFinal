package model;

import java.util.Date;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Pagamento {
    
    private int idpagamento;
    private double valor;
    private Date  data;
    private String status;
    private Funcionario funcionario;
    
    public Pagamento(){}
    
    public Pagamento(int idpagamento, double valor, Date data, String status, Funcionario funcionario){
        this.idpagamento = idpagamento;
        this.valor = valor;
        this.data = data;
        this.status = status;
        this.funcionario = funcionario;
    }
    
    public ArrayList<Pagamento> getLista() throws Exception{
       PagamentoDAO DAO = new PagamentoDAO();
       return DAO.getLista();
   }
   
   public boolean gravar() throws Exception{
       PagamentoDAO DAO = new PagamentoDAO();
       return DAO.gravar(this);
   }
   
   public Pagamento getCarregaPorID(int idpagamento) throws Exception{
       PagamentoDAO DAO = new PagamentoDAO();
       return DAO.getCarregaPorID(idpagamento);
   }
   
   public boolean excluir() throws Exception{
       PagamentoDAO DAO = new PagamentoDAO();
       return DAO.excluir(this);
   }

}
