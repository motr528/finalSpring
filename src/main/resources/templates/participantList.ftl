<#import "parts/common.ftl" as c>

<@c.page>
    List of participants:
    <table>
        <thead>
        <tr>
            <th> </th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <#list participants as participant>
            <tr>
                <td> </td>
                <td>${participant.getDetails().getFirstName()}</td>
                <td>${participant.getDetails().getLastName()}</td>
                <td>${participant.getEmail()}</td>
                <td><#list participant.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${participant.getId()}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
