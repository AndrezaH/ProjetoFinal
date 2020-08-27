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
                                <h3><strong class="card-title">Cadastrar um novo usuário</strong></h3>
                            </div>
                            <div class="card-body">
                                    <form name="form_usuario.jsp"  action="gerenciar_usuario.do" method="POST">
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <input type="hidden" name="idusuario" id="idusuario"
                                                      class="form-control" value="${usuario.idusuario}"/>
                                               <label for="nome" class="control-label">
                                                   Nome
                                               </label>
                                               <input type="text" name="nome" id="nome"
                                                      class="form-control" required=""
                                                      value="${usuario.nome}"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="login" class="control-label">
                                                   Login
                                               </label>
                                               <input type="text" name="login" id="login"
                                                      class="form-control" required=""
                                                      value="${usuario.login}"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="senha" class="control-label">
                                                   Senha
                                               </label>
                                               <input type="password" name="senha" id="senha"
                                                      class="form-control" value="${usuario.senha}"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <input type="hidden" name="status" id="status" value="1"/>
                                           </div>
                                       </div>
                                       <div class="row">
                                           <div class="form-group col-sm-8">
                                               <label for="perfil" class="control-label">
      
                                                   Perfil
                                               </label>
                                               <select id="idperfil" name="idperfil" required="" class="form-control">
                                                   <option value="">Selecione o Perfil</option>
                                                   <jsp:useBean class="model.Perfil" id="perfil"/>
                                                   <c:forEach var="p" items="${perfil.lista}">
                                                       <option value="${p.idperfil}"
                                                               <c:if test="${p.idperfil==usuario.perfil.idperfil}">
                                                                   selected=""
                                                               </c:if>        
                                                       >${p.perfil}</option>
                                                   </c:forEach>
                                               </select>    
                                           </div>
                                       </div>
                                       <div class="row">
                                       <div class="row">
                                           <button class="btn btn-success">Gravar</button>
                                           <a href="listar_usuario.jsp" class="btn btn-warning">
                                               Voltar
                                           </a>    
                                       </div>
                                   </form>
                                </div>
                                </div>
                            </div>
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
