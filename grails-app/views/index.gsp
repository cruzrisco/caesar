<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <!-- <title>Welcome to Grails</title> -->
    <title>${g.message(code:"application.title.label")}</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu dropdown-menu-right">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>
<!--
<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
-->
<div id="experiment-carousel" class="carousel slide" data-ride="carousel">
    <!-- Carousel indicators -->
    <!--
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    -->
    <!-- Wrapper for carousel items -->
    <div class="carousel-inner" role="listbox">
        <g:each var="i" in="${0..2}">
        <div class="carousel-item ${i==0?'active':''}" >
            <div class="row">
                <g:each var="j" in="${1..3}">
                <div class="col">
                    <asset:image src="carousel_00${i*3+j}.jpg" class="w-100" />
                </div>
                </g:each>
            </div>
        </div>
        </g:each>
    </div>
    <!-- Carousel controls -->
    <a class="carousel-control-prev" href="#experiment-carousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    </a>
    <a class="carousel-control-next" href="#experiment-carousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
    </a>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <!-- <h1>Welcome to Grails</h1> -->
			<h1>
				<p class="text-center">
					<g:message code="caesar.acronym.label"/> - <g:message code="caesar.expanded.label"/><br/>
					<strong><g:message code="caesar.proof-of-concept" /></strong>
				</p>
			</h1>
			<!--
            <p>
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>
			-->			
            <div id="controllers" role="navigation">
                <!-- <h2>Available Controllers:</h2> -->
            <h2><g:message code="caesar.entities" />:</h2>
				<ul>
					<!--
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller">
							<g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
						</li>
					</g:each>
					-->
					<!-- <li><g:link controller="empiricalStudy"><g:message code="empiricalStudyController.label" /></g:link></li> -->
					<li><g:link controller="originalStudy"><g:message code="originalStudyController.label" /></g:link></li>
					<li><g:link controller="replication"><g:message code="replicationController.label" /></g:link></li>
					<li><g:link controller="change"><g:message code="changeController.label" /></g:link></li>
				</ul>				
            </div>
        </section>
    </div>
</div>

</body>
</html>
