<#import "parts/common.ftl" as c>
<#import "parts/conference.ftl" as conf>



<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Conference
    </a>
    <div class="collapse <#if newConf??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/addConference">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Name"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="location" placeholder="Location">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" name="numOfSlots" placeholder="Number of talk slots">
                </div>
                <input class="form-control" id="date" name="date" placeholder="Date: MM/DD/YYYY" type="text"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
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