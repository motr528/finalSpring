<#import "parts/common.ftl" as c>


<@c.page>

    <h5>${email}</h5>

    <form method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">First Name :</label>
            <div class="col-sm-6">
                <input type="text" name="firstName" class="form-control" placeholder="${firstName}" />
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Last Name :</label>
            <div class="col-sm-6">
                <input type="text" name="lastName" class="form-control" placeholder="${lastName}" />
            </div>
        </div>

<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label">Email :</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="email" name="email" class="form-control" placeholder="Email" />-->
<#--            </div>-->
<#--        </div>-->
        <div class="form-group row">
            <label  class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>