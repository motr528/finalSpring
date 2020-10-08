<#import "parts/common.ftl" as c>
<#import "parts/conference.ftl" as conf>



<@c.page>
<div>
    <form method="post" action="/main">
        <input type="text" name="name" placeholder="Name" />
        <input type="text" name="location" placeholder="Location"/>
        <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
        <button type="submit">Add</button>
    </form>
</div>
    <h4>List of Conferences</h4>
    <#list conferences as conference>
        <div>
            <@conf.conf_view conference>
            </@conf.conf_view>
        </div>
    <#else > No conference
    </#list>

<#--<form method="post" action="filter">-->
<#--    <input type="text" name="filter">-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--    <button type="submit">Find</button>-->
<#--</form>-->
<#--<#list conferences as conference>-->
<#--    <div>-->
<#--        <b>${conference.id}</b>-->
<#--        <span>${conference.name}</span>-->
<#--        <i>${conference.location}</i>-->
<#--        <p>Number of participants: </p>-->
<#--        <b>${conference.numberOfParticipants}</b>-->
<#--        <form method="post" action="/addParticipant">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--            <input type="hidden" name="id" value="{{id}}" />-->
<#--            <button type="submit">Assign Participant</button>-->
<#--        </form>-->
<#--    </div>-->
<#--    <#else > No conference-->
<#--</#list>-->
</@c.page>