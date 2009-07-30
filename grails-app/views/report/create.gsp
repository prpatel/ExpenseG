

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Report</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Report List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Report</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${report}">
            <div class="errors">
                <g:renderErrors bean="${report}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:report,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:report,field:'code')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="items">Items:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:report,field:'items','errors')}">
                                    
<ul>
<g:each var="i" in="${report?.items?}">
    <li><g:link controller="item" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="item" params="['report.id':report?.id]" action="create">Add Item</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:report,field:'title','errors')}">
                                    <input type="text" id="title" name="title" value="${fieldValue(bean:report,field:'title')}"/>
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
