<#import "parts/common.ftl" as c>
<#import "parts/talk.ftl" as t>

<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Talk
    </a>
    <div class="collapse <#if newTalk??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/addTalk">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Name"/>
                </div>

                <div class="md-form mx-5 my-5">
                    <label for="inputMDEx1">Time</label>
                    <input type="time" name="time" id="inputMDEx1" class="form-control">
                </div>

                <div class="form-group">
                    <label for="conferenceSelect">Select conference</label>
                    <select class="form-control" name="conference_id" id="conferenceSelect">
                        <#list conferences as conference>
                            <option value=${conference.id}>${conference.name}, ${conference.location}, ${conference.date}</option>
                        </#list>
                    </select>
                </div>

<#--                <div class="form-group">-->
<#--                    <input type="text" class="form-control" name="time" placeholder="Time">-->
<#--                </div>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

<@t.talkTable talks>

</@t.talkTable>

</@c.page>