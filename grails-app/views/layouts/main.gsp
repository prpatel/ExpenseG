<html>
  <head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
    <g:layoutHead />
    <g:javascript library="application" />
  </head>
  <div id="header" class="header">
    <h1 style="cursor: pointer" onclick="location.href='${createLinkTo(dir:'')}/'">Expense Tracker</h1>
    <p><g:message code="a grails demo app"/></p>
    <div id="loginStatus" class="loginStatus">

      <g:if test="${session.user}">
        <p> User: (${session.user.userid}) </p>
      </g:if>
      <g:else>
        <g:if test="${flash.loginError}">
          <div class="flash">${flash.loginError}</div>
        </g:if>

        <form action="login/login" method="post" >
          <b>User Id:  </b>
          <input type='text' name='userid' size="10" />
          
          <b>Password:</b>
          <input type="password" name='password' size="10"/>
          
          <span class="formButton">
            <input type="submit" value="login"/>
          </span>
        </form>
      </g:else>


    </div>
  </div>



  <body>
    <div id="spinner" class="spinner" style="display:none;">
      <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
    </div>

    <g:layoutBody />
  </body>
</html>