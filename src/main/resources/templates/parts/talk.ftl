<#include "security.ftl">

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
                <#if talk.speaker?has_content>
                    <td>${talk.speaker.name}</td>
                <#else>
                    <td>No speaker yet</td>
                </#if>

                <#list talk.conferences as conference>
                    <td>${conference.name}, ${conference.location}</td>
                </#list>
                <#if isSpeaker>
                    <td>
                        <form method="post" action="/assignToTalk">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="speakerId" value="${participant.id}"/>
                            <input type="hidden" name="talkId" value="${talk.id}"/>
                            <button type="submit" class="btn btn-primary">Assign</button>
                        </form>
                    </td>
                </#if>


            </tr>
        <#else> No talks
        </#list>

        <#--    </#if>-->
        </tbody>
    </table>
</#macro>

