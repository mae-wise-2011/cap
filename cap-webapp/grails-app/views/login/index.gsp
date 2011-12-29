<%@ page import="cap.portal.User" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>

<div>

  <g:form>
    <label>
      Benutzername:
      <g:textField name="username" value="${user?.username}"/>
    </label>
    <label>
      Passwort:
      <g:passwordField name="password" value="${user?.password}"/>
    </label>
    <g:actionSubmit value="Login" action="login" />
  </g:form>

</div>

</body>
</html>
