<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'empiricalStudy.label', default: 'EmpiricalStudy')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-empiricalStudy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <%-- no create button --%>
                <%--
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                --%>
            </ul>
        </div>
        <div id="list-empiricalStudy" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <%-- new arguments for f:table --%>
            <g:set var="collection" value="${caesar.EmpiricalStudy.list(params)}" />
            <g:set var="domainClass" value="${grailsApplication.getDomainClass('caesar.EmpiricalStudy')}" />
            <g:set var="domainProperties" value="${domainClass.getConstrainedProperties().keySet()}" />
            <%-- old f:table --%>
            <%-- <f:table collection="${empiricalStudyList}" /> --%>
            <%-- new f:table --%>
            <f:table collection="${collection}" properties="${domainProperties}" />

            <div class="pagination">
                <g:paginate total="${empiricalStudyCount ?: 0}" />
            </div>
        </div>
    </body>
</html>