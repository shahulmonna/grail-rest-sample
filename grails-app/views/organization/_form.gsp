<%@ page import="com.meetup.test.Organization" %>



<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="organization.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${organizationInstance?.name}" />

</div>


<div class="fieldcontain ${hasErrors(bean: organizationInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="organization.user.label" default="User" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${organizationInstance?.user?}" var="u">
    <li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="user" action="create" params="['organization.id': organizationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'user.label', default: 'User')])}</g:link>
</li>
</ul>


</div>

