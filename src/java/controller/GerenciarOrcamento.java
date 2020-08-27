package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Orcamento;

public class GerenciarOrcamento extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idorcamento = Integer.parseInt(request.getParameter("idorcamento"));
        Orcamento o = new Orcamento();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Orcamento novo = new Orcamento();
                novo = o.getCarregaPorID(idorcamento);
                if(novo.getIdorcamento()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_orcamento.jsp");
                    request.setAttribute("orcamento", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "Orçamento não encontrado";
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                if(idorcamento>0){
                    o.setIdorcamento(idorcamento);
                    if(o.excluir()){
                        mensagem ="Orçamento excluido com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir";
                    }
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
        }catch(Exception e){
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_orcamento.jsp';");
        out.println("</script>");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idorcamento = request.getParameter("idorcamento");
        String valor = request.getParameter("valor");
        String data_orcamento = request.getParameter("data_orcamento");
        String data_assinatura = request.getParameter("data_assinatura");
        String data_entrada = request.getParameter("data_entrada");
        String data_vencimento = request.getParameter("data_vencimento");
        String descricao = request.getParameter("descricao");
        String idcliente = request.getParameter("idcliente");
        String mensagem = "";
      
       try{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Orcamento o = new Orcamento();
        if(!idorcamento.isEmpty())
            o.setIdorcamento(Integer.parseInt(idorcamento));
       
        double total = 0;
        if (!valor.isEmpty())
            total = Double.parseDouble(valor.replace(".","").replace(",","."));
            o.setValor(total);
        o.setValor(Double.parseDouble(valor));
        o.setData_orcamento(sdf.parse(data_orcamento));
        o.setData_assinatura(sdf.parse(data_assinatura));
        o.setData_entrada(sdf.parse(data_entrada));
        o.setData_vencimento(sdf.parse(data_vencimento));
        o.setDescricao(descricao);
        Cliente c = new Cliente();
        c.setIdcliente(Integer.parseInt(idcliente));
        o.setCliente(c);
       
            if(valor.equals("") || data_orcamento.equals("") || data_assinatura.equals("") || data_entrada.equals("") || data_vencimento.equals("") || descricao.equals("")|| idcliente.equals("")) 
                mensagem = "Campos obrigatorios devem ser preenchidos";
        else{
                if(o.gravar())
                    mensagem = "Gravado com sucesso";
                else
                    mensagem = "Erro ao gravar o orçamento.";
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao gravar no banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_orcamento.jsp';");
        out.println("</script>");
            
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
