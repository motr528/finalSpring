<#import "parts/common.ftl" as c>
<#import "parts/talk.ftl" as t>
<#import "parts/talkNoButton.ftl" as tnb>

<@c.page>

    <h4>Available talks</h4>
    <@t.talkTable talks>
    </@t.talkTable>
    <h4>Assigned talks</h4>
    <@tnb.talkTable talksAssigned>
    </@tnb.talkTable>

</@c.page>