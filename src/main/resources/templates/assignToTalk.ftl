<#import "parts/common.ftl" as c>
<#import "parts/talk.ftl" as t>

<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Propose new talk
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/proposeTalk">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Name"/>
                </div>

                <div class="md-form mx-5 my-5">
                    <label for="inputMDEx1">Time</label>
                    <input type="time" name="time" id="inputMDEx1" class="form-control">
                </div>

                <div class="form-group">
                    <label for="conferenceSelect">Select conference</label>
                    <select class="form-control" name="conferenceId" id="conferenceSelect">
                        <#list conferences as conference>
                                <option value=${conference.id}>${conference.name}, ${conference.location}, ${conference.date}</option>
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

    <div>Assign to a talk:</div>
    <@t.talkTable talks>
    </@t.talkTable>

</@c.page>