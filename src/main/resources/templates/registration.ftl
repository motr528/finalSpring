<#import "parts/common.ftl" as c>

<@c.page>
Registration
<form action="/registration" method="post">
    <div><label> Email : <input type="email" name="email"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> First Name: <input type="text" name="firstName"/> </label></div>
    <div><label> Last Name: <input type="text" name="lastName"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Register"/></div>
</form>
</@c.page>
