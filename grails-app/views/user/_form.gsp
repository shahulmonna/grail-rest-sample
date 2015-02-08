<%@ page import="com.meetup.test.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailId', 'error')} ">
	<label for="emailId">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:textField name="emailId" value="${userInstance?.emailId}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'organization', 'error')} ">
	<label for="organization">
		<g:message code="user.organization.label" default="Organization" />
		
	</label>
	<g:select id="organization" name="organization.id" from="${com.meetup.test.Organization.list()}" optionKey="id" required="" value="${userInstance?.organization?.id}" class="many-to-one"/>

</div>


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${userInstance?.username}" />

</div>

