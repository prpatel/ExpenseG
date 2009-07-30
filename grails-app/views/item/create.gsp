

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Item</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Item List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Item</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${item}">
            <div class="errors">
                <g:renderErrors bean="${item}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expenseType">Expense Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'expenseType','errors')}">
                                    <g:select optionKey="id" from="${ExpenseType.list()}" name="expenseType.id" value="${item?.expenseType?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr>  
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="event">Event:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'event','errors')}">
                                    <g:select optionKey="id" from="${ExpenseEvent.list()}" name="event.id" value="${item?.event?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="desc">Desc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'desc','errors')}">
                                    <input type="text" id="desc" name="desc" value="${fieldValue(bean:item,field:'desc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="payee">Payee:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'payee','errors')}">
                                    <input type="text" id="payee" name="payee" value="${fieldValue(bean:item,field:'payee')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user">User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'user','errors')}">
                                    <g:select optionKey="id" from="${User.list()}" name="user.id" value="${item?.user?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount">Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:item,field:'amount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createDate">Create Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'createDate','errors')}">
                                    <g:datePicker name="createDate" value="${item?.createDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentType">Payment Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'paymentType','errors')}">
                                    <g:select  from="${PaymentType?.values()}" value="${item?.paymentType}" name="paymentType" ></g:select>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="reimbursed">Reimbursed:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'reimbursed','errors')}">
                                    <g:checkBox name="reimbursed" value="${item?.reimbursed}" ></g:checkBox>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="submitted">Submitted:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'submitted','errors')}">
                                    <g:checkBox name="submitted" value="${item?.submitted}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
