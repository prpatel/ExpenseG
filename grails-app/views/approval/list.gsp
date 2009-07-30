

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main" />
  <title>Item List</title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
    <span class="menuButton"><g:link class="create" action="create">New Item</g:link></span>
  </div>
  <div class="body">
    <h1>Item List</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${item}">
      <div class="errors">
        <g:renderErrors bean="${item}" as="list" />
      </div>
    </g:hasErrors>
    <g:form method="post">
      <div class="list">
      <table>
        <thead>
          <tr>

            <th>Approve</th>
            <g:sortableColumn property="id" title="Id" />

            <th>Expense Type</th>

            <th>Event</th>
            <g:sortableColumn property="desc" title="Desc" />
            <g:sortableColumn property="createDate" title="createDate" />
            <g:sortableColumn property="amount" title="amount" />
            <g:sortableColumn property="user" title="user" />
            <g:sortableColumn property="paymentType" title="paymentType" />
            <g:sortableColumn property="payee" title="Payee" />
            <g:sortableColumn property="reimbursed" title="Reimbursed" />

            <g:sortableColumn property="submitted" title="Submitted" />

          </tr>
        </thead>
        <tbody>
          <g:each in="${itemList}" status="i" var="item">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td><g:checkBox name="approvedIds" value="${item?.id}" checked="${item?.approved}"/></td>

              <td><g:link action="show" id="${item.id}">${fieldValue(bean:item, field:'id')}</g:link>
              <g:hiddenField name="allIds" value="${item?.id}"></g:hiddenField></td>

              <td>${fieldValue(bean:item, field:'expenseType')}</td>

              <td>${fieldValue(bean:item, field:'event')}</td>

              <td>${fieldValue(bean:item, field:'desc')}</td>



              <td>${fieldValue(bean:item, field:'createDate')}</td>

              <td>${fieldValue(bean:item, field:'amount')}</td>

              <td>${fieldValue(bean:item, field:'user')}</td>

              <td>${fieldValue(bean:item, field:'paymentType')}</td>

              <td>${fieldValue(bean:item, field:'payee')}</td>
              <td><g:checkBox name="reimbursed"
                              value="${item?.reimbursed}" /></td>
              <td><g:checkBox name="submitted"
                              value="${item?.submitted}" /></td>

            </tr>
          </g:each>
        </tbody>
      </table>
      <div class="buttons">
        <span class="button"><g:actionSubmit class="save" value="Approve Items" /></span>
      </div>
    </g:form>
  </div>
  </div>
</body>
</html>
