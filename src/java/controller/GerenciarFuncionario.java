package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Usuario;

public class GerenciarFuncionario extends HttpServlet {


   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idfuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
        Funcionario f = new Funcionario();
        try{
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                Funcionario novo = new Funcionario();
                novo = f.getCarregaPorID(idfuncionario);
                if(novo.getIdfuncionario()>0){
                    RequestDispatcher disp = 
                       getServletContext().getRequestDispatcher("/form_funcionario.jsp");
                    request.setAttribute("funcionario", novo);
                    disp.forward(request, response);
                }else{
                    mensagem = "Funcionario não encontrado";
                }
                }else{
                  mensagem = "Acesso Negado";
              }
            }
            if(acao.equals("excluir")){
                if(GerenciarLogin.verificarPermissao(request, response)){
            
                if(idfuncionario>0){
                    f.setIdfuncionario(idfuncionario);
                    if(f.excluir()){
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
        out.println("location.href='listar_funcionario.jsp';");
        out.println("</script>");
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idfuncionario = request.getParameter("idfuncionario");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String cargo= request.getParameter("cargo");
        String idusuario = request.getParameter("idusuario");
        String mensagem = "";
       
        
        Funcionario f = new Funcionario();
        if(!idfuncionario.isEmpty())
            f.setIdfuncionario(Integer.parseInt(idfuncionario));
            f.setNome(nome);
            f.setTelefone(telefone);
            f.setCargo(cargo);
            Usuario u = new Usuario();
            u.setIdusuario(Integer.parseInt(idusuario));
            f.setUsuario(u);
        try{
            if(f.equals("")/*nome.equals("")||telefone.equals("")||cargo.equals("")*/)
                mensagem = "Campos obrigatorios devem ser preenchidos";
            else{
                if(f.gravar())
                    mensagem = "Funcionário gravado com sucesso";
                else
                    mensagem = "Erro ao gravar o funcionario no banco";
            }
                
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao gravar no banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_funcionario.jsp';");
        out.println("</script>");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
