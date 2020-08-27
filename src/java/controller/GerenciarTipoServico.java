package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Tipo_Servico;


public class GerenciarTipoServico extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idtipo_servico = Integer.parseInt(request.getParameter("idtipo_servico"));
        Tipo_Servico ts = new Tipo_Servico();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Tipo_Servico novo = new Tipo_Servico();
                novo = ts.getCarregaPorID(idtipo_servico);
                if(novo.getIdtipo_servico()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_tipo_servico.jsp");
                    request.setAttribute("tipo_servico", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "O Tipo de Serviço não foi encontrado";
                }
                }else{
                    mensagem = "Acesso Negado";
                }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                if(idtipo_servico>0){
                    ts.setIdtipo_servico(idtipo_servico);
                    if(ts.excluir()){
                        mensagem ="Excluído com sucesso!";
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
        out.println("location.href='listar_tipo_servico.jsp';");
        out.println("</script>");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idtipo_servico = request.getParameter("idtipo_servico");
        String tipo_servico = request.getParameter("tipo_servico");
        String idfuncionario = request.getParameter("idfuncionario");
        
        try{       
            
        
            Tipo_Servico ts = new Tipo_Servico();
            if(!idtipo_servico.isEmpty())
                ts.setIdtipo_servico(Integer.parseInt(idtipo_servico));
                ts.setTipo_servico(tipo_servico);
                Funcionario f = new Funcionario();
                ts.setFuncionario(f.getCarregaPorID(Integer.parseInt(idfuncionario)));
        
            if(tipo_servico.equals("")||idfuncionario.isEmpty())
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                if(ts.gravar()){
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
        out.println("location.href='listar_tipo_servico.jsp';");
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
