<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
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
    <script type="text/javascript">
            function confirmarExclusao(id, nome){
                if(confirm('Deseja realmente excluir o usuario '+nome+'?')){
                  location.href='gerenciar_usuario.do?acao=excluir&idusuario='+id;
                }
            }
    </script>
</head>

<body>


    <!-- Left Panel -->

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
                                <h3><strong class="card-title">Lista dos Usuários</strong></h3>
                            </div>
                            <div class="card-body">
                                <table id="bootstrap-data-table-export" class="table table-striped table-bordered" id="listaUsuario">
                                    <a href="form_usuario.jsp" class="btn btn-primary">Novo Cadastro</a>
                                    <thead>
                                        <tr>
                                           <th>ID</th>
                                           <th>Nome</th>
                                           <th>Login</th>
                                           <th>Status</th>
                                           <th>Perfil</th>
                                           <th>Opções</th>
                                       </tr>
                                   </thead>
                                   <tfoot>
                                       <tr>
                                           <th>ID</th>
                                           <th>Nome</th>
                                           <th>Login</th>
                                           <th>Status</th>
                                           <th>Perfil</th>
                                           <th>Opções</th>
                                       </tr>
                                   </tfoot>
                                   <jsp:useBean class="model.Usuario" id="usuario"/>
                                   <tbody>
                                     <c:forEach var="u" items="${usuario.lista}">
                                     <tr>
                                        <td>${u.idusuario}</td>
                                        <td>${u.nome}</td>
                                        <td>${u.login}</td>
                                        <td>
                                            <c:if test="${u.status==1}">Ativo</c:if>
                                            <c:if test="${u.status==2}">Inativo</c:if>
                                        </td>
                                        <td>${u.perfil.perfil}</td>
                                        <td>
                                            <a class="btn btn-primary" href="gerenciar_usuario.do?acao=alterar&idusuario=${u.idusuario}">
                                             <i class="fa fa-pencil"></i>
                                            </a>
                                            <button class="btn btn-danger" onclick="confirmarExclusao(${u.idusuario},'${u.nome}')">
                                                <i class="fa fa-trash-o"></i>
                                            </button>    
                                        </td>
                                    </tr>
                                     </c:forEach>
                                   </tbody>
                                </table>
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
<script type="text/javascript" src="datatables/jquery.js"></script>
    <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#listaUsuario").dataTable({
                "bJQueryUI": true,
                "oLanguage":{
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Não foram encontrados resultados",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfopostFix": "",
                    "sSearch": "Pesquisar:",
                    "sURL": "",
                    "oPaginate":{
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Próximos",
                        "sLast": "Último"
                    }
                }
            })
        })
    </script> 
</html>
