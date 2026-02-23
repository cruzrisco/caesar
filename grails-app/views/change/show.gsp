<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'change.label', default: 'Change')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-change" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-change" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
					<%-- Default display --%>
					<%-- <f:display bean="change" /> --%>
					<%-- Alternative display --%>
					<%-- Styles in assets/stylesheets/caesar.css --%>
					<div class="table-responsive mx-auto shadow caesar">
						<table class="table-bordered caesar">
							<%-- Change title row --%>
							<tr>
								<th>
									<g:message code="change.label" />
									#<f:display bean="change" property="number" />
								</th>
								<td>
									<f:display bean="change" property="name" />
									(<g:link controller="replication" action="show" id="${change.replication.id}"><f:display bean="change" property="replication.acronym" /></g:link>)
								</td>
							</tr>
							<%-- Description row --%>
							<tr>
								<th><g:message code="change.description.label" /></th>
								<%-- Change description L-pattern --%>
								<td>
									<g:message code="change.description.first" args="${[change.baseSituation.uncapitalize()]}"/>
									<br/>
									<g:message code="change.description.second" args="${[change.replicationSituation.uncapitalize()]}"/>
									<br/>
									<g:message code="change.description.third"  args="${[change.purpose.uncapitalize()]}"/>
								</td>
							</tr>
							<%-- Modified dimension row --%>
							<tr>
								<th><g:message code="change.modifiedDimension.label" /></th>
								<%-- Modified dimension L-pattern --%>
								<td>
									<g:message code="change.modifiedDimension"
											   args="${[message(code:change.dimension.getCodes().getAt(0)),
														message(code:change.affectedElement,
																default:change.affectedElement).uncapitalize()]}"
									/>
									<br/>
								</td>
							</tr>
							<%-- Threats to validity row --%>
							<tr>
								<th><g:message code="change.threatsToValidity.label"/></th>
								<%-- Modified dimension L-pattern --%>
								<td>
									<g:if test="${change.impacts.isEmpty()}">
										<g:message code="change.noChangeEffects"/>
									</g:if>
									<g:else>
										<ul>
											<g:each in="${change.impacts}" var="impact">
												<li>
												<%-- Validity effect L-pattern --%>
												<%-- This change {0} {1} because {2} --%>
												<g:message code="change.changeImpact" args="${[
														message(code:impact.effect.getCodes().getAt(0)).uncapitalize(),
														message(code:impact.validity.getCodes().getAt(0)).uncapitalize(),
														impact.rationale.uncapitalize()
												]}"/>
												</li>
											</g:each>
										</ul>
									</g:else>
								</td>
							</tr>
							<%-- Comments row --%>
							<tr>
								<th><g:message code="change.comments.label" /></th>
								<td><f:display bean="change" property="comments" /></td>
							</tr>
						</table>
					</div>
					<!-- End alternative display -->
                    <g:form resource="${this.change}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.change}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
