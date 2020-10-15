<#include "security.ftl">

<#macro speakersToTalk talks>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Time</th>
            <th scope="col">Talk name</th>
            <th scope="col">Conference</th>
            <th scope="col">Speaker</th>
        </tr>
        </thead>
        <tbody>
        <#list talks as talk>
            <tr>
                <th scope="row">${talk.time?time?string("HH:mm")}</th>
                <td>${talk.name}</td>
                <#list talk.conferences as conference>
                    <td>${conference.name}, ${conference.location}</td>
                </#list>

                <td>
                    <div class="form-group">
                        <select class="form-control" name="speakerId">
                            <#list talk.possibleSpeaker as speaker>
                                <option value=${speaker.id}>${speaker.details.lastName}</option>
                            </#list>
                        </select>
                    </div>
                </td>

                <td></td>

            </tr>
        </#list>
        </tbody>
    </table>


</#macro>