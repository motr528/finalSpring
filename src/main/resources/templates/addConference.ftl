<#import "parts/common.ftl" as c>
<#import "parts/conference.ftl" as conf>



<@c.page>
    <div>
        <form method="post" action="/addConference">
            <input type="text" name="name" placeholder="Name" />
            <input type="text" name="location" placeholder="Location"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
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

</@c.page>