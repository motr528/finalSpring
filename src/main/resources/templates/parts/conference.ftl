<#include "security.ftl">

<#macro conf_view conference>
    <div class="card text-center mb-3">
        <div class="card-header text-left">
            <#if conference.date??>
            ${conference.dateAsString()}, ${conference.location}
                <#else>
                No date yet, ${conference.location}
            </#if>
        </div>
        <div class="card-body">
            <h5 class="card-title">${conference.name}</h5>
            <p class="card-text">Description</p>
            <#if !isAdmin>
            <form method="post" action="/addParticipant">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${conference.id}"/>
                <button type="submit" class="btn btn-primary">Assign Participant</button>
            </form>
            </#if>
        </div>
        <div class="card-footer text-muted">
            Participants: ${conference.numberOfParticipants}
        </div>
    </div>
</#macro>