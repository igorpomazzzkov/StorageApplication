<#import "parts/common.ftl" as common>
<@common.page>
    <link rel="stylesheet" href="/static/css/login.css">
    <form method="post" action="/login">
        <#if Session?has_content && Session.SPRING_SECURITY_LAST_EXCEPTION?has_content>
            <div class="alert alert-danger" role="alert">
                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </#if>
        <label>
            <input type="text" class="form-control" name="username" placeholder="username">
        </label>
        <label>
            <input type="password" class="form-control" name="password" placeholder="password">
        </label>
        <input type="submit" class="btn btn-info" value="Войти"/>
        <a href="/registration" class="btn btn-light">Регистрация</a>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</@common.page>