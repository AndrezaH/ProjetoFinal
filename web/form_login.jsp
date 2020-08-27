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
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">


    <link rel="stylesheet" href="vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">

    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body class="bg-dark">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
			<%
              String mensagem = (String)request.getSession().getAttribute("mensagem");
              if(mensagem != null){
                  %>
                  <div class="alert alert-info"><%=mensagem%></div>
                  <%
                  request.getSession().removeAttribute("mensagem");
              }
            %> 
            <div class="login-content">
                <div class="login-logo">
                    <a href="">
                        <img class="align-content" src="images/luzati_logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <form action="gerenciar_login.do" method="POST">
                        <div class="form-group">
                            <label for="login">Login</label>
                            <input type="text" name="login" id="login" class="form-control" 
								value="" placeholder="Preencha com o seu login!">
                        </div>
                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" id="senha" class="form-control" 
									value="" placeholder="Preencha com a sua senha!">
                        </div>
                                <div class="checkbox">
                                    <label>
                                <input type="checkbox"> Lembre-me
                            </label>
                            </div>
                                <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Acessar</button>
                                <div class="social-login-content">
                                </div>                               
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <script src="vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="assets/js/main.js"></script>

</body>

</html>
