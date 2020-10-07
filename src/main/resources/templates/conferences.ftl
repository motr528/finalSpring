<#import "parts/common.ftl" as c>

<@c.page>

    <h4>Conferences</h4>
    <br/>
    <div class="form-row">
        <label  class="col-sm-2 col-form-label">Find conference: </label>
        <div class="form-group col-md-6">
            <form method="get" action="/conferences" class="form-inline">
                <input type="text" name="filter" class="form-control" id="search" value="${filter!}" placeholder="Search by location">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <br/>
    <#list conferences as conference>
        <div>
            <b>${conference.id}</b>
            <span>${conference.name}</span>
            <i>${conference.location}</i>
            <p>Number of participants: </p>
            <b>${conference.numberOfParticipants}</b>
            <form method="post" action="/addParticipant">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${conference.id}"/>
                <button type="submit">Assign Participant</button>
            </form>
        </div>
    <#else > No conference
    </#list>
</@c.page>
