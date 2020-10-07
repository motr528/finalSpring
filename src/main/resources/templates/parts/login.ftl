<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email :</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="Email" />
            </div>
        </div>
        <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Add new participant</a></#if>
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>