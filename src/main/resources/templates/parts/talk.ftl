<#macro talkTable talks>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Time</th>
            <th scope="col">Name</th>
            <th scope="col">Speaker</th>
            <th scope="col">Conference</th>
        </tr>
        </thead>
        <tbody>
        <#--    <#if talks?has_content>-->
        <#list talks as talk>
            <tr>
                <th scope="row">${talk.time?time?string("HH:mm")}</th>
                <td>${talk.name}</td>
                <#--        <#if talk.speaker?null>-->
                <td>No speaker yet</td>
                <#list talk.conferences as conference>
                <td>${conference.name}, ${conference.location}</td>
                </#list>

            </tr>
        <#else> No talks
        </#list>

        <#--    </#if>-->
        </tbody>
    </table>
</#macro>

