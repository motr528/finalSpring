<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"> COnfMan</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if participant??>
            <li class="nav-item">
                <a class="nav-link" href="/conferences">Conferences</a>
            </li>
            </#if>

            <#if !participant??>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </#if>

            <#if !participant??>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Registration</a>
                </li>
            </#if>

            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
            </#if>

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/addConference">Add conference</a>
                </li>
            </#if>

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/addTalk">Add talk</a>
                </li>
            </#if>

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/assignSpeaker">Add speaker to talk</a>
                </li>
            </#if>

            <#if isSpeaker>
                <li class="nav-item">
                    <a class="nav-link" href="/talks">Talks</a>
                </li>
            </#if>

            <#if isSpeaker>
                <li class="nav-item">
                    <a class="nav-link" href="/proposeTalk">Propose talk to a conference</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${email}</div>
        <#if participant??>
        <a class="nav-link mr-3" href="/user/profile">Profile</a>
        </#if>
        <@l.logout />
    </div>
</nav>