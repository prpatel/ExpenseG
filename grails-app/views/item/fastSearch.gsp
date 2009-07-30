

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Search Items</title>
        <resource:autoComplete skin="default" />


    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Item List</g:link></span>
        </div>
        <div class="body">
            <h1>Fast Search By Event</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${item}">
            <div class="errors">
                <g:renderErrors bean="${item}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="search" method="POST" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expenseType">Enter event name</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:item,field:'expenseType','errors')}">
                                    <richui:autoComplete name="event.id" action="${createLinkTo('dir': 'item/fastSearchAJAX')}"
                                    onItemSelect="document.location.href = \'${createLinkTo(dir: 'item/show')}/\' + id;"/>
                                </td>
                            </tr> 
                        
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="search" type="submit" value="Search" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
