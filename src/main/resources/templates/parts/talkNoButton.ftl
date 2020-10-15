<#include "security.ftl">

<#macro talkTable talks>
    <#if talks?has_content>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Time</th>
            <th scope="col">Name</th>
            <th scope="col">Conference</th>
        </tr>
        </thead>
        <tbody>
        <#list talks as talk>
            <tr>
                <th scope="row">${talk.time?time?string("HH:mm")}</th>
                <td>${talk.name}</td>
                <#--        <#if talk.speaker?null>-->
                <#if talk.speaker?has_content>
                    <td>${talk.speaker.name}</td>
                <#else>
                    <td>No speaker yet</td>
                </#if>

                <#list talk.conferences as conference>
                    <td>${conference.name}, ${conference.location}</td>
                </#list>

            </tr>

        </#list>

        <#--    </#if>-->
        </tbody>
    </table>
    <#else> No talks
    </#if>
</#macro>

