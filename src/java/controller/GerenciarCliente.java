package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;


public class GerenciarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        Cliente c = new Cliente();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Cliente novo = new Cliente();
                novo = c.getCarregaPorID(idcliente);
                if(novo.getIdcliente()>0){
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
                    request.setAttribute("cliente", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "Cliente não encontrado";
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                if(idcliente>0){
                    c.setIdcliente(idcliente);
                    if(c.excluir()){
                        mensagem ="Excluído com sucesso!";
                    }else{
                        mensagem = "Erro ao excluir do banco!";
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
        out.println("location.href='listar_cliente.jsp';");
        out.println("</script>");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idcliente = request.getParameter("idcliente");
        String nome_empresa = request.getParameter("nome_empresa");
        String nome_ceo = request.getParameter("nome_ceo");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String descricao = request.getParameter("descricao");
        String area_atuacao = request.getParameter("area_atuacao");
        String mensagem = "";
       
        Cliente c = new Cliente();
        if(!idcliente.isEmpty())
            c.setIdcliente(Integer.parseInt(idcliente));
            c.setNome_empresa(nome_empresa);
            c.setNome_ceo(nome_ceo);
            c.setTelefone(telefone);
            c.setEndereco(endereco);
            c.setDescricao(descricao);
            c.setArea_atuacao(area_atuacao);
         try{
            if(c.equals(""))
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                if(c.gravar())
                    mensagem = "Gravado com sucesso";
                else
                    mensagem = "Erro ao gravar o cliente";
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao gravar no banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_cliente.jsp';");
        out.println("</script>");
            
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
