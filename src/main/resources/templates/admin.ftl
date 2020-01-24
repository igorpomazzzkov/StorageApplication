<#import "parts/common.ftl" as common>
<@common.page>
    <link rel="stylesheet" href="/static/css/storage.css" type="text/css">
    <script src="/static/js/main.js"></script>
    <div class="context_menu">
        <a href="/logout" class="btn btn-dark">Log out</a>
        <div class="search">
            <form method="post" action="/admin/search">
                <h3>Поиск</h3>
                <label> Username
                    <input type="text" name="username" placeholder="username" class="form-control"/>
                </label>
                <label> Post
                    <select name="post" class="form-control">
                        <option>-- Должность --</option>
                        <#list posts as post>
                            <option>${post.name}</option>
                        </#list>
                    </select>
                </label>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Search" class="btn btn-success"/>
            </form>
        </div>
    </div>
    <div class="main_page">
        <div class="table">
            <table>
                <tr>
                    <th>№</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Post</th>
                    <th>Active</th>
                    <th>Roles</th>
                    <th>Remove</th>
                </tr>
                <#list users as user>
                    <#list user.roles as role>
                        <#if role != "ADMIN">
                            <tr class="<#if !user.active>noActive</#if>">
                                <td><a href="/admin/activate/${user.username}">${user_index}</a>
                                </td>
                                <td><a href="/admin/activate/${user.username}">${user.name}</a>
                                </td>
                                <td><a href="/admin/activate/${user.username}">${user.username}</a></td>
                                <td><a href="/admin/activate/${user.username}">${user.email}</a></td>
                                <td><a href="/admin/activate/${user.username}">${user.post.name}</a></td>
                                <td>
                                    <a href="/admin/activate/${user.username}">
                                        <#if user.active>
                                            Активирован
                                        <#else >
                                            Не Активирован
                                        </#if>
                                    </a>
                                </td>
                                <td>
                                    <a href="/admin/activate/${user.username}">
                                        <#list user.roles as role>
                                            ${role.name()}<#sep>,
                                        </#list>
                                    </a>
                                </td>
                                <td><a href="/admin/delete/${user.username}" style="font-weight: bold">X</a></td>
                            </tr>
                        </#if>
                    <#else >
                        <#break >
                    </#list>
                </#list>
            </table>
        </div>
</@common.page>