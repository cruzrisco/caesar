<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/> -->
    <!-- Caesar icon in 32x32 PNG format -->
    <asset:link rel="icon" href="favicon.png" type="image/png"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <div class="container-fluid">
        <a class="navbar-brand" href="/#">
			<!-- asset:image src="grails.svg" alt="Grails Logo"/> -->
			<!-- Caesar icon in 80x80 PNG format -->
			<asset:image id="caesar_icon"
				src="caesar_icon.png"
				alt="${g.message(code:'caesar.expanded.label')}"
				title="${g.message(code:'caesar.expanded.label')}"
			/>
			<span id="caesar_acronym"
				class="text-capitalize"
				title="${g.message(code:'caesar.expanded.label')}">
				<g:message code="caesar.acronym.label"/>
			</span>
		</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
            <ul class="nav navbar-nav ml-auto">
                <g:pageProperty name="page.nav"/>
            </ul>
        </div>
    </div>
	
</nav>

<g:layoutBody/>

<div class="footer" role="contentinfo">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
				<!--
                <a href="http://guides.grails.org" target="_blank">
                    <asset:image src="advancedgrails.svg" alt="Grails Guides" class="float-left"/>
                </a>
                <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>
                <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>
				-->
				<!-- Grupo ISA -->
				<a href="https://www.isa.us.es" target="_blank">
					<asset:image src="logo-isa.svg" alt="Grupo ISA" class="float-left"/>
				</a>
				<strong class="centered"><a href="https://www.isa.us.es" target="_blank"><g:message code="isaGroup.label"/></a></strong>
				<p class="social-address">
					<g:message code="isaExpanded.label"/><br/>
					ETSII, Avda. Reina Mercedes, s/n<br/>
					41012 Sevilla, Spain
				</p>				
            </div>
            <div class="col">
				<!--
                <a href="http://docs.grails.org" target="_blank">
                    <asset:image src="documentation.svg" alt="Grails Documentation" class="float-left"/>
                </a>
                <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>
                <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a href="http://docs.grails.org" target="_blank">User Guide</a>.</p>
				-->
				<!-- Contacto -->
				<a href="mailto:cruz@us.es" target="_blank">
					<asset:image src="email-icon-beige.png" alt="Contacto" class="float-left"/>
				</a>
				<strong class="centered"><a href="http://docs.grails.org" target="_blank"><g:message code="contact.label"/></a></strong>
				<p class="social-address">
					Margarita Cruz Risco<br/>
					Tel: +34 954 55 27 72<br/>
					E-mail: cruz@us.es
				</p>				
            </div>
            <div class="col">
				<!--
                <a href="https://slack.grails.org" target="_blank">
                    <asset:image src="slack.svg" alt="Grails Slack" class="float-left"/>
                </a>
                <strong class="centered"><a href="https://slack.grails.org" target="_blank">Join the Community</a></strong>
                <p>Get feedback and share your experience with other Grails developers in the community <a href="https://slack.grails.org" target="_blank">Slack channel</a>.</p>
				-->
				<!-- Social -->
				<a href="https://twitter.com/ISAGroupSevilla" target="_blank">
					<asset:image src="social-network-icon-beige.png" alt="Redes sociales" class="float-left"/>
				</a>
				<strong class="centered"><a href="https://twitter.com/ISAGroupSevilla" target="_blank"><g:message code="followUs.label"/></a></strong>
				<p>
					<a href="https://twitter.com/ISAGroupSevilla" target="_blank"><asset:image src="twitter-icon-beige-small.png" class="social-icon" alt="Twitter"/></a>
					<a href="https://www.youtube.com/channel/UC5L2OdKW7G5g-fgiqAczvUw" target="_blank"><asset:image src="youtube-icon-beige-small.png" class="social-icon" alt="YouTube" /></a>
					<a href="https://github.com/isa-group" target="_blank"><asset:image src="github-icon-beige-small.png" class="social-icon" alt="GitHub" /></a>
				</p>				
            </div>
        </div>
    </div>
</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
