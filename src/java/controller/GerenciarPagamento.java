package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Pagamento;


public class GerenciarPagamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idpagamento = Integer.parseInt(request.getParameter("idpagamento"));
        Pagamento p = new Pagamento();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Pagamento novo = new Pagamento();
                novo = p.getCarregaPorID(idpagamento);
                if(novo.getIdpagamento()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_pagamento.jsp");
                    request.setAttribute("pagamento", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "O Pagamento não foi encontrado";
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                if(idpagamento>0){
                    p.setIdpagamento(idpagamento);
                    if(p.excluir()){
                        mensagem ="Pagamento excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir do banco";
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
        out.println("location.href='listar_pagamento.jsp';");
        out.println("</script>");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idpagamento = request.getParameter("idpagamento");
        String valor = request.getParameter("valor");
        String data = request.getParameter("data");
        String status = request.getParameter("status");
        String idfuncionario = request.getParameter("idfuncionario");
        
        try{       
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
            Pagamento p = new Pagamento();
            if(!idpagamento.isEmpty())
                p.setIdpagamento(Integer.parseInt(idpagamento));
            
            double pagamento = 0;
            if (!valor.isEmpty())
                pagamento = Double.parseDouble(valor.replace(".","").replace(",","."));
                p.setValor(pagamento);
               
                p.setData( sdf.parse(data));
                p.setStatus(status);
                Funcionario f = new Funcionario();
                p.setFuncionario(f.getCarregaPorID(Integer.parseInt(idfuncionario)));
        
            if(valor.equals("")||data.equals("")||data.isEmpty() || status.equals("")||idfuncionario.equals(""))
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                if(p.gravar()){
                    mensagem = "Gravado com sucesso";
                }else{
                    mensagem = "Erro ao gravar no banco";
                }
            }
        }catch(Exception e){
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_pagamento.jsp';");
        out.println("</script>");
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}