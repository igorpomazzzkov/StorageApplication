<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getName()
    username = user.getUsername()
    userID = user.getId()
    isAdmin = user.isAdmin()
    >
<#else >
    <#assign
    name = "NoName"
    userID = -1
    isAdmin = false
    >
</#if>