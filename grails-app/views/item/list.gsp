

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
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
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
                        
                            <td><g:link action="show" id="${item.id}">${fieldValue(bean:item, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:item, field:'expenseType')}</td>                                                    
                        
                            <td>${fieldValue(bean:item, field:'event')}</td>
                        
                            <td>${fieldValue(bean:item, field:'desc')}</td>
                        
                            

                            <td>${fieldValue(bean:item, field:'createDate')}</td>

                            <td>${fieldValue(bean:item, field:'amount')}</td>

                            <td>${fieldValue(bean:item, field:'user')}</td>

                            <td>${fieldValue(bean:item, field:'paymentType')}</td>

                            <td>${fieldValue(bean:item, field:'payee')}</td>
                            <td>${fieldValue(bean:item, field:'submitted')}</td>

                            <td>${fieldValue(bean:item, field:'reimbursed')}</td>

                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Item.count()}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
