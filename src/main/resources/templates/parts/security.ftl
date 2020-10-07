<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
    >
<#if know>
    <#assign
        participant = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        email = participant.getEmail()
        isAdmin = participant.isAdmin()
        >
<#else>
    <#assign
        email = "unknown"
        isAdmin = false
        >

</#if>