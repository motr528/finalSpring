<#import "parts/common.ftl" as c>
<#import "parts/conference.ftl" as conf>

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

            <form method="post" action="filterByDate" class="form-inline">
                <input type="text" name="dateFrom" class="form-control" value="${dateFrom!}" placeholder="Date from: DD/MM/YYYY" >
                <input type="text" name="dateTo" class="form-control" value="${dateTo!}" placeholder="Date to: DD/MM/YYYY">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <br/>
    <#list conferences as conference>
        <div>
            <@conf.conf_view conference conference.talks>
            </@conf.conf_view>
        </div>
    <#else > No conference
    </#list>
</@c.page>
