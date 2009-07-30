

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Item</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Item List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Item</g:link></span>
        </div>
        <div class="body">
            <h1>Show Item</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:item, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Expense Type:</td>
                            
                            <td valign="top" class="value"><g:link controller="expenseType" action="show" id="${item?.expenseType?.id}">${item?.expenseType?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Event:</td>
                            
                            <td valign="top" class="value"><g:link controller="expenseEvent" action="show" id="${item?.event?.id}">${item?.event?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Desc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:item, field:'desc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payee:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:item, field:'payee')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">User:</td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${item?.user?.id}">${item?.user?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:item, field:'amount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Create Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:item, field:'createDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payment Type:</td>
                            
                            <td valign="top" class="value">${item?.paymentType?.encodeAsHTML()}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Reimbursed:</td>

                            <td valign="top" class="value">${fieldValue(bean:item, field:'reimbursed')}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Submitted:</td>

                            <td valign="top" class="value">${fieldValue(bean:item, field:'submitted')}</td>

                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Approved:</td>

                            <td valign="top" class="value">${fieldValue(bean:item, field:'approved')}</td>

                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${item?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
