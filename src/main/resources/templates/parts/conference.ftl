<#include "security.ftl">
<#import "talk.ftl" as t>

<#macro conf_view conference confTalks>
    <div class="card text-center mb-3">
        <div class="card-header text-left">
            <#if conference.date??>
                ${conference.dateAsString()}, ${conference.location},
            <#else>
                No date yet, ${conference.location}
            </#if>
        </div>
        <div class="card-body">
            <h5 class="card-title">${conference.name}</h5>
            <p class="card-text">Description</p>

<#--            <#include "talk.ftl">-->
            <div>
                <#if confTalks?has_content>
                    <@t.talkTable confTalks>
                    </@t.talkTable>
                <#else> Number of talk slots: ${conference.availableSlots}
                </#if>
            </div>

            <#if !isAdmin && !isSpeaker  >
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