<html>
    <head>
        <title>Login</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
    
  </div>
</br>
</br>

      <h3>front page</h3>
      </br>
<h2>User functions</h2>
      <g:link controller="item" class="create" action="create">Enter New Item</g:link>
      </br>
      <g:link controller="item" class="create" action="search">Search Items</g:link>
      </br>
      <g:link controller="item" class="create" action="fastSearch">Fast Search by Event</g:link>
      </br>
<g:link controller="item" class="create" action="myitems">Show My Items</g:link>
</br>
</br>
<h2>Approval Functions</h2>
      
      <g:link controller="approval" class="create" action="search">Approve Items</g:link>
      </br>
      <g:link controller="report" class="create" action="list">Generate Report</g:link>

      </br>
      
      
    </body>
</html>