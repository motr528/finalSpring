<#include "security.ftl">

<#macro speakersToTalk>

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
            <tr>
<#--                <th scope="row">${talk.time?time?string("HH:mm")}</th>-->
<#--                <td></td>-->


            </tr>
        </tbody>
    </table>


</#macro>