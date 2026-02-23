<%@ page import="caesar.Validity" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'originalStudy.label', default: 'OriginalStudy')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-originalStudy" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-originalStudy" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
					<%-- Default display --%>
					<%-- <f:display bean="originalStudy" /> --%>
					<%-- Alternative display --%>
					<%-- Styles in assets/stylesheets/caesar.css --%>
					<div class="table-responsive mx-auto shadow caesar">
						<table class="table-bordered caesar">

							<%-- OriginalStudy title row --%>
							<tr>
								<th><g:message code="originalStudy.label" /></th>
								<td>
									<%-- L-pattern for OriginalStudy title --%>
									<span class="caesar"><f:display bean="originalStudy" property="acronym" /></span>
									<g:if test="${originalStudy.report}">
										<span class="caesar url">(<a href="${originalStudy.report}" target="_blank">${originalStudy.report}</a>)</span>
									</g:if>
								</td>
							</tr>

							<%-- Goal row --%>
							<tr>
								<th><g:message code="originalStudy.goal.label" /></th>
								<td>
									<%-- discard markdown for the moment --%>
									<%--
									<g:markdown md="${goal}" />
									--%>
									<f:display bean="originalStudy" property="goal"/>
								</td>
							</tr>

							<%-- Description row --%>
							<tr>
								<th><g:message code="originalStudy.description.label" /></th>
								<td>
									<%-- discard markdown for the moment --%>
									<%--
									<g:markdown md="${description}" />
									--%>
									<f:display bean="originalStudy" property="description"/>
								</td>
							</tr>

							<%-- Site and date row --%>
							<tr>
								<th>
									<g:message code="originalStudy.site.label" /> and
									<g:message code="originalStudy.date.label" />
								</th>
								<td>
									<g:message code="originalStudy.site.date"
											   args="[originalStudy.site,originalStudy.date]"/>
								</td>
							</tr>

							<%-- Family row --%>
							<tr>
								<th><g:message code="originalStudy.family.label" /></th>
								<td>
									<div class="collapse.show" id="familyCollapse">
										<ul>
											<g:each var="f" in="${originalStudy.family}">
												<li>
													<g:link controller="replication" action="show" id="${f.id}">${f}</g:link>
													(${f.site}, ${f.date},
													 <span class="caesar url"><a href="${f.report}" target="_blank">${f.report}</a></span>)
												</li>
											</g:each>
										</ul>
									</div>
								</td>
							</tr>

							<%-- Validity evolution graph row --%>
							<tr>
								<th><g:message code="originalStudy.validityEvolution.label" /></th>
								<td>
									<canvas id="caesarChart"></canvas>
								</td>
							</tr>

							<%-- Comments row --%>
							<tr>
								<th><g:message code="originalStudy.comments.label" /></th>
								<td><f:display bean="originalStudy" property="comments" /></td>
							</tr>
						</table>
					</div>
					<!-- End alternative display -->
                    <g:form resource="${this.originalStudy}" method="DELETE">
                        <fieldset class="buttons">
                            <g:link class="edit" action="edit" resource="${this.originalStudy}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
	<!-- generate chart -->
	<!-- GSP code optimized for debugging JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		const familyLabels = [
			'${originalStudy.acronym}'<g:each var="f" in="${originalStudy.family}" >,
			'${f}'</g:each>
		];

		<g:set var="numberOfValidities" value="${caesar.Validity.values().size()}"/>

		const validityLabels = [
			<g:each status="i" var="v" in="${caesar.Validity}">'<g:message code="${v}"/>'<g:if test="${i < numberOfValidities - 1}">,</g:if>
			</g:each>
		];

		// https://learnui.design/tools/data-color-picker.html
		const validityColors = [
			'#73a81e',     // Validity.CONSTRUCT_VALIDITY
			'#7a5195',     // Validity.INTERNAL_VALIDITY
			'#ef5675',     // Validity.EXTERNAL_VALIDITY
			'#ffa600'      // Validity.CONCLUSION_VALIDITY
		];

		const validityDatasets = [
			<g:each status="i" var="v" in="${caesar.Validity}">
			{
				label: '<g:message code="${v}"/>',
				backgroundColor: validityColors[${i}],
				borderColor: validityColors[${i}],
				data: ${originalStudy.getValidityEvolution(v)}
			}<g:if test="${i < numberOfValidities-1}">,</g:if>
			</g:each>
		];

		const data = {
			labels: familyLabels,
			datasets: validityDatasets,
		};

		const config = {
			type: 'line',
			data: data,
			options: {}
		};

		const myChart = new Chart(
			document.getElementById('caesarChart'),
			config
		);
	</script>
    </body>
</html>
