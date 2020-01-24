<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">
<@common.page>
    <link rel="stylesheet" href="/static/css/login.css">
    <form method="post" action="<#if isAdmin>/admin/activate/${users.username}<#else >/registration</#if>">
        <label for="name">ФИО:</label>
        <input type="text" class="form-control ${((nameError??)?string('is-invalid',''))}"
               value="<#if isAdmin>${users.name}</#if>" name="name" placeholder="Иванов Иван Иванович"/>
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <label for="post">Занимаемая должность:</label>
        <select class="form-control" name="post">
            <#if isAdmin>
                <option>${users.post.name}</option>
                <#list posts as post>
                    <#if users.post.name != post.name>
                        <option>${post.name}</option>
                    </#if>
                </#list>
            </#if>
            <#if !isAdmin>
                <#list posts as post>
                    <option value="${post.id}">${post.name}</option>
                </#list>
            </#if>
        </select>
        <#if !isAdmin>
            <label for="name">Email:</label>
            <input type="email" name="email" class="form-control ${((emailError??)?string('is-invalid',''))}"
                   placeholder="email@.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control ${((usernameError??)?string('is-invalid',''))}"
                   placeholder="username"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
            <label for="password">Пароль:</label>
            <#if passwordTooError??>
                <div class="invalid-feedback">
                    ${passwordTooError}
                </div>
            </#if>
            <input type="password" name="password" class="form-control ${((passwordError??)?string('is-invalid',''))}"/>
            <label for="passwordToo">Повторите пароль:</label>
            <input type="password" name="passwordToo"
                   class="form-control ${((passwordTooError??)?string('is-invalid',''))}"/>
            <#if passwordTooError??>
                <div class="invalid-feedback">
                    ${passwordTooError}
                </div>
            </#if>
            <input type="submit" class="btn btn-success" value="Регистрация"/>
            <a href="/login" class="btn btn-light">Войти</a>
        </#if>
        <#if isAdmin>
            <#list roles as role>
                <#if role != "USER">
                    <div>
                        <label>
                            <input type="checkbox"
                                   name="${role}" ${users.roles?seq_contains(role)?string("checked","")} />
                            ${role}
                        </label>
                    </div>
                </#if>
            </#list>
        </#if>
        <#if isAdmin>
            <div class="activate">
                <#if users.active>
                    <input type="submit" class="btn btn-warning" value="Сохранить">
                    <input type="hidden" name="save"/>
                    <a href="/admin/NoActive/${users.username}" class="btn btn-danger">Отказ в доступе</a>
                <#else >
                    <input type="submit" class="btn btn-success" value="Активировать">
                    <a href="/admin/NoActive/${users.username}" class="btn btn-danger">Отказ в доступе</a>
                </#if>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</@common.page>