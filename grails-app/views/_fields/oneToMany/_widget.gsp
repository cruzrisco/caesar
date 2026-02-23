<!-- https://stackoverflow.com/questions/37737493/grails-3-1-8-scaffolded-edit-gsp-doesnt-indent-hasmany-set -->
<!-- https://gorm.grails.org/latest/api/org/grails/datastore/mapping/model/types/Association.html -->
<!-- https://github.com/grails-fields-plugin/grails-fields/blob/master/grails-app/taglib/grails/plugin/formfields/FormFieldsTagLib.groovy -->
<%@ page import="org.grails.datastore.mapping.model.types.*" %>
<g:set var="association"         value="${beanClass.associations.find{ it.name == property }}"              />
<g:set var="associatedEntity"    value="${association?.associatedEntity}"                                   />
<g:set var="controllerName"      value="${associatedEntity?.decapitalizedName}"                             />
<g:set var="controllerNameLabel" value="${controllerName}.label"                                            />
<g:set var="shortName"           value="${associatedEntity?.decapitalizedName}"                             />
<g:set var="referencedTypeLabel" value="${g.message(code:controllerNameLabel, default:shortName)}"          />
<g:set var="addLabel"            value="${g.message(code:'default.add.label', args:[referencedTypeLabel])}" />
<g:set var="paramName"           value="${beanClass.decapitalizedName}.id"                                  />
<g:set var="paramValue"          value="${bean.id}"                                                         />

<div class="property-value">
        <!-- <ul style="list-style-type: none; margin-top: -25px;"> -->
        <!-- a negative margin... shame on me! -->
        <ul style="margin-top:-25px; margin-left:-15px; list-style-type:circle">
            <g:each in="${value?.sort()}">
                <li>
                    <g:link controller="${controllerName}" action="show" id="${it.id}">
                        ${it.toString().encodeAsHTML()}
                    </g:link>
                </li>
            </g:each>
            <g:if test="${bean.id}">
                <li>
                    <a href="/${controllerName}/create?${paramName}=${paramValue}">${addLabel}</a>
                    <%-- g:link sometimes it works, sometimes does not show paramValue --%>
                    <%--
                    <g:link controller="${controllerName}" action="create" params="${[(paramName):{paramValue}]}">
                        ${addLabel}
                    </g:link>
                    --%>
                </li>
            </g:if>
            <g:else>
                <li>
                    <g:message code="oneToMany.newObject.message"/>
                </li>
            </g:else>
        </ul>
</div>