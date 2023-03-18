<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
</head>

 <body class="text-center d-flex justify-content-center align-items-center" style="height: 100vh;">
<div class="col-4 " >
	  <form method="POST" action="signup.p">
	    
	    <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
	
	    <div class="form-floating">
	    	<label for="floatingInput">Nom</label>
	     	<input name="nom" type="text" class="form-control" id="floatingInput" placeholder="nom">
	    </div>
	    
	    <div class="form-floating">
	    	<label for="floatingInput">Prenom</label>
	      	<input name="prenom" type="text" class="form-control" id="floatingInput" placeholder="prenom">
	    </div>
	    
	    <div class="form-floating">
	    <label for="floatingInput">Email</label>
	      <input name="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
	    </div>
	    
	    <div class="form-floating">
	    	<label for="floatingInput">Password</label>
	     	<input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
	    </div>
	
	    <div class="checkbox mb-3">
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
	    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
	  </form>
	  <a href="login.jsp">Login</a>
</div>    


    
  </body>

</html>