<%@ page import="caesar.OriginalStudy" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'replication.label', default: 'Replication')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-replication" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-replication" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
					
					<%-- Default display --%>
					<%-- <f:display bean="replication" /> --%>
					<%-- Alternative display --%>
					<%-- Styles in assets/stylesheets/caesar.css --%>
					<%-- instanceof doesn't work with replication.base instanceof caesar.OriginalStudy, why? --%>
					<%-- replication.base instanceof OriginalStudy = ${replication.base instanceof OriginalStudy} --%>
					<g:set var="base_class">
						<g:if test="${replication.base.class == caesar.OriginalStudy}">
							<g:message code="replication.title.original"/>
						</g:if>
						<g:else>
							<g:message code="replication.title.replication"/>
						</g:else>
					</g:set>

					<g:set var="base_controller"
						   value="${replication.base.class == caesar.OriginalStudy ? 'originalStudy' : 'replication'}"
					/>

					<div class="table-responsive mx-auto shadow caesar">
						<table class="table-bordered caesar replication">
							<%-- Replication title row --%>
							<tr>
								<th><g:message code="replication.label" /></th>
								<td colspan="5">
									<%-- L-pattern for replication title --%>
									<span class="caesar"><f:display bean="replication" property="acronym" /></span>
									<g:if test="${replication.report}">
										<span class="caesar url">(<a href="${replication.report}" target="_blank">${replication.report}</a>)</span>
									</g:if>
									<br/>
									<g:message code="replication.title.first"
											   args="${[message(code:replication.type.getCodes().getAt(0))]}" />
									<g:link controller="${base_controller}" action="show" id="${replication.base.id}">
										${replication.base.acronym}
									</g:link>
									${base_class}
								</td>
							</tr>
							<%-- Original experiment row --%>
							<tr>
								<th><g:message code="originalStudy.label" /></th>
								<td colspan="5">
									<%-- discard markdown for the moment --%>
									<%--
									<g:markdown
											md="${'**' + message(code:'originalStudy.goal.label') + '**: ' + replication.original.goal}" />
									--%>
									<span class="caesar"><g:message code="originalStudy.goal.label"/></span>:
									<f:display bean="replication" property="original.goal"/>
									<br/>
									<span class="caesar"><g:message code="originalStudy.description.label"/></span>:
									<f:display bean="replication" property="original.description"/>
								</td>
							</tr>
							<%-- Site and date row --%>
							<tr>
								<th>
									<g:message code="replication.site.label" /> and
									<g:message code="replication.date.label" />
								</th>
								<td colspan="5">
									<g:message code="replication.base.site.date"
											   args="[replication.base.site,replication.base.date]"/>
									<br/>
									<g:if test="${replication.base.site == replication.site}">
										<g:message code="replication.sameSite.date" args="[replication.date]"/>
									</g:if>
									<g:else>
										<g:message code="replication.site.date" args="[replication.site,replication.date]"/>
									</g:else>
								</td>
							</tr>
							<%-- Purposes row --%>
							<tr>
								<th><g:message code="replication.purposes.label" /></th>
								<td colspan="5"><f:display bean="replication" property="purposes" /></td>
							</tr>
							<%-- Changes row --%>
							<tr>
								<th rowspan="${replication.changes.size()+2}">
									<g:message code="replication.changes.label" />
								</th>
								<th rowspan="2" class="caesar change-name"><g:message code="change.name.label" /></th>
								<th colspan="4" class="caesar change-validity"><g:message code="change.threatsToValidity.label" /></th>
							</tr>
							<tr>
								<th class="caesar change-validity"><g:message code="CONSTRUCT"  /></th>
								<th class="caesar change-validity"><g:message code="INTERNAL"   /></th>
								<th class="caesar change-validity"><g:message code="EXTERNAL"   /></th>
								<th class="caesar change-validity"><g:message code="CONCLUSION" /></th>
							</tr>
							<g:each var="change" in="${replication.changes}">
								<tr>
									<td>
										<g:link controller="change" action="show" id="${change.id}">
											${change}
										</g:link>
										%{-- ${change.acronym} --}%
									</td>
									<g:each var="v" in="${caesar.Validity}">
										<td class="caesar change-validity">
											${change.getValidityDelta(v) > 0 ? "+" : ""}${change.getValidityDelta(v) ? change.getValidityDelta(v) : ""}
										</td>
									</g:each>
								</tr>
							</g:each>
								%{--
								<td>
									<div class="collapse.show" id="changesCollapse">
										<f:display bean="replication" property="changes" />
									</div>
								</td>
								--}%
							<%-- Comments row --%>
							<tr>
								<th><g:message code="replication.comments.label" /></th>
								<td colspan="5"><f:display bean="replication" property="comments" /></td>
							</tr>
						</table>
					</div>
					<!-- End alternative display -->
                    <g:form resource="${this.replication}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.replication}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
