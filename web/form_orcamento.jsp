<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SG Financeiro</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="vendors/jqvmap/dist/jqvmap.min.css">


    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
    
    <script type="text/javascript" src="mascara/jquery-1.2.6.pack.js"></script>
    <script type="text/javascript" src="mascara/jquery.maskedinput-1.1.4.pack.js"/></script>
    <script src="dist/jquery.maskMoney.min.js" type="text/javascript"></script>

    <script type="text/javascript">
       $(document).ready(function () {
            $("#data_orcamento").mask("99/99/9999");
            $("#data_assinatura").mask("99/99/9999");
            $("#data_entrada").mask("99/99/9999");
            $("#data_vencimento").mask("99/99/9999");
       });
    </script>
	
</head>

<body>

<aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">

            <div class="navbar-header">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="index.jsp/"><img src="images/luzati_logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="index.jsp/"><img src="images/luzati_logo.png" alt="Logo"></a>
            </div>

           <br><br><%@include file="menu.jsp" %>
        </nav>
    </aside><!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <header id="header" class="header">

            <div class="header-menu">

                <div class="col-sm-7">
                    <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
                    <p>Bem vindo, <c:if test="${ulogado!=null}">${ulogado.nome}</c:if>!</p>
                </div>

                <div class="col-sm-5">
                    <div class="user-area dropdown float-right">
                         <a class="nav-link" href="gerenciar_login.do"><i class="fa fa-power-off"></i> Logout</a>
                    </div>

                </div>
            </div>

        </header><!-- /header -->
        <!-- Header-->

        <div class="breadcrumbs">
            
        </div>
        <div class="content mt-3"><!--corpo das páginas-->
            <div class="animated fadeIn">
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h3><strong class="card-title">Cadastrar um novo orçamento</strong></h3>
                            </div>
                            <div class="card-body">
                                    <form name="form_orcamento.jsp"   action="gerenciar_orcamento.do" method="POST">
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <input type="hidden" name="idorcamento" id="idorcamento"
                                                      class="form-control" value="${orcamento.idorcamento}"/>
                                               <label for="valor" class="control-label">
                                                  Valor 
                                               </label>
                                               <input type="text" name="valor" id="valor"
                                                      class="form-control" required=""
                                                      value="<fmt:formatNumber pattern="#,##0.00" value="${orcamento.valor}"/>"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="data_orcamento" class="control-label">
                                                   Data do orcamento
                                               </label>
                                               <input type="text" name="data_orcamento" id="data_orcamento"
                                                      class="form-control" required=""
                                                     value= "<fmt:formatDate pattern="dd/MM/yyyy" value="${orcamento.data_orcamento}"/>"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="data_assinatura" class="control-label">
                                                   Data da assinatura
                                               </label>
                                               <input type="text" name="data_assinatura" id="data_assinatura" class="form-control"
                                                     value="<fmt:formatDate pattern="dd/MM/yyyy" value="${orcamento.data_assinatura}"/>"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="data_entrada" class="control-label">
                                                   Data da entrada
                                               </label>
                                               <input type="text" name="data_entrada" id="data_entrada" class="form-control" 
                                                      value="<fmt:formatDate pattern="dd/MM/yyyy" value="${orcamento.data_entrada}"/>"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="data_vencimento" class="control-label">
                                                   Data do vencimento
                                               </label>
                                               <input type="text" name="data_vencimento" id="data_vencimento" class="form-control" 
                                                      value="<fmt:formatDate pattern="dd/MM/yyyy" value="${orcamento.data_vencimento}"/>"/>
                                           </div>
                                       </div>                                          
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="descricao" class="control-label">
                                                   Descrição do orçamento
                                               </label>
                                               <input type="text" name="descricao" id="descricao"
                                                      class="form-control" value="${orcamento.descricao}"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="idcliente" class="control-label">
                                                   Cliente
                                               </label>
                                               <select id="idcliente" name="idcliente" required="" class="form-control">
                                                   <option value="">Selecione o Cliente</option>
                                                   <jsp:useBean class="model.Cliente" id="cliente"/>
                                                   <c:forEach var="c" items="${cliente.lista}">
                                                       <option value="${c.idcliente}"
                                                               <c:if test="${c.idcliente==orcamento.cliente.idcliente}">
                                                                   selected=""
                                                               </c:if>        
                                                       >${c.nome_empresa}</option>
                                                   </c:forEach>

                                               </select>    
                                           </div>
                                       </div>    

                                       <div class="row">
                                           <button class="btn btn-success">Gravar</button>
                                           <a href="listar_orcamento.jsp" class="btn btn-warning">
                                               Voltar
                                           </a>    
                                       </div>
                                   </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- .animated -->
        </div> <!-- .content -->
    </div><!-- /#right-panel -->
    
    <!-- Right Panel -->

    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <script src="vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="assets/js/main.js"></script>


   <script src="vendors/jqvmap/dist/jquery.vmap.min.js"></script>
    <script src="vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <script src="vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    

</body>

</html>
