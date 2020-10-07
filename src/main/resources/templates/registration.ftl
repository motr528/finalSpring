<#import "parts/common.ftl" as c>

<@c.page>
<#--    сюда тоже прикрутить bootstrap-->
<div class="mb-1">Registration</div>
<form action="/registration" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email :</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="email@example.com">
        </div>
    </div>

    <div class="form-group row">
        <label  class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">First Name :</label>
        <div class="col-sm-6">
            <input type="text" name="firstName" class="form-control" placeholder="John" />
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Last Name :</label>
        <div class="col-sm-6">
            <input type="text" name="lastName" class="form-control" placeholder="Doe" />
        </div>
    </div>

    <fieldset class="form-group">
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Status</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" value="USER" checked>
                    <label class="form-check-label" >
                        Participant
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" value="SPEAKER">
                    <label class="form-check-label" >
                        Speaker
                    </label>
                </div>
            </div>
        </div>
    </fieldset>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Register</button>
</form>
</@c.page>
