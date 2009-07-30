

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Report List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Report</g:link></span>
        </div>
        <div class="body">
            <h1>Report List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="code" title="Code" />
                        
                   	        <th>Items</th>
                   	    
                   	        <g:sortableColumn property="title" title="Title" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${reportList}" status="i" var="report">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${report.id}">${fieldValue(bean:report, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:report, field:'code')}</td>
                        
                            <td>${fieldValue(bean:report, field:'items')}</td>
                        
                            <td>${fieldValue(bean:report, field:'title')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Report.count()}" />
            </div>
        </div>
    </body>
</html>
