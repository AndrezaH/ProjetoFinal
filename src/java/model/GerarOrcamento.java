package model;


import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GerarOrcamento {
    
    
    private Cliente cliente;
    private Servico servico;
    private Parcelamento parcelamento;
    private Parcela parcela;
    private Tipo_Servico tipo_servico;
    private Orcamento orcamento;
    

public GerarOrcamento(){}

    public GerarOrcamento(Cliente cliente, Servico servico, Parcelamento parcelamento, Parcela parcela, Tipo_Servico tipo_servico, Orcamento orcamento) {
        this.cliente = cliente;
        this.servico = servico;
        this.parcelamento = parcelamento;
        this.parcela = parcela;
        this.tipo_servico = tipo_servico;
        this.orcamento = orcamento;
    }

public ArrayList<GerarOrcamento> getLista(int idcliente, int idservico, int idparcelamento, int idparcela, int idtipo_servico, int idorcamento) throws Exception{
    GerarOrcamentoDAO DAO = new GerarOrcamentoDAO();
    return DAO.getLista(idcliente, idservico, idparcelamento, idparcela, idtipo_servico, idorcamento);
}

public GerarOrcamento getCarregaPorID(int idcliente, int idservico, int idparcelamento, int idparcela, int idtipo_servico, int idorcamento) throws Exception{
      GerarOrcamentoDAO DAO = new GerarOrcamentoDAO();
      return DAO.getCarregaPorID(idcliente, idservico, idparcelamento, idparcela, idtipo_servico, idorcamento);
}
    
    
    
}
