<#import "parts/common.ftl" as c>
<#import "parts/talkNoButton.ftl" as t>

<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Propose talk to a conference
    </a>
    <div class="collapse show" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/addTalk">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Name"/>
                </div>

                <div class="form-group">
                    <label for="conferenceSelect">Select conference</label>
                    <select class="form-control" name="conferenceId" id="conferenceSelect">
                        <#list conferences as conference>
                            <#if (conference.availableSlots > 0) >
                                <option value=${conference.id}>${conference.name}, ${conference.location}, ${conference.date}</option>
                            </#if>
                        </#list>
                    </select>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Propose</button>
                </div>
            </form>
        </div>
    </div>

    <h4>Proposed Talks</h4>


    <@t.talkTable talks>

    </@t.talkTable>

</@c.page>