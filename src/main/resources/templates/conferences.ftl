<#import "parts/common.ftl" as c>

<@c.page>
<div>Conferences</div>
<br/>
<div>Find conference</div>
<form method="get" action="/conferences">
    <input type="text" name="filter" value="${filter}">
    <button type="submit">Find</button>
</form>
<br/>
<#list conferences as conference>
<div>
    <b>${conference.id}</b>
    <span>${conference.name}</span>
    <i>${conference.location}</i>
    <p>Number of participants: </p>
    <b>${conference.numberOfParticipants}</b>
    <form method="post" action="/addParticipant">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" name="id" value="${conference.id}" />
        <button type="submit">Assign Participant</button>
    </form>
</div>
<#else > No conference
</#list>
</@c.page>
