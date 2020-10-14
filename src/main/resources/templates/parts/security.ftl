<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
    >
<#if know>
    <#assign
        participant = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        email = participant.getEmail()
        isAdmin = participant.isAdmin()
        isSpeaker = participant.isSpeaker()
        >
<#else>
    <#assign
        email = "unknown"
        isAdmin = false
        isSpeaker = false
        >

</#if>