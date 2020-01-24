<#import "parts/common.ftl" as common>
<@common.page>
    <link rel="stylesheet" href="/static/css/storage.css" type="text/css">
    <script src="/static/js/main.js"></script>

    <div class="context_menu">
        <a href="/logout" class="btn btn-dark">Выйти</a>
        <form method="post" action="/storageAdd" class="form">
            <input type="text" name="addStorage" class="form-control" placeholder="Storage"/>
            <input type="submit" name="sub" class="btn btn-success" value="Добавить"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
        <div class="storage">
            <#list storages as storage>
                <div class="store <#if storage.id == storageID>isActive</#if>" data-toggle="tooltip"
                     data-placement="bottom" title="${storage.address}">
                    <a href="/storage/id=${storage.id}">${storage.name}</a>
                </div>
            </#list>
        </div>
        <div class="buttons">
            <a href="" data-toggle="modal" data-target="#exampleModalToo" class="btn btn-info">Изменить</a>
            <a href="/deleteStorage/id=${storageID}" class="btn btn-danger">Удалить</a>
        </div>
    </div>
    <div class="main_page">
        <div class="table">
            <table>
                <tr>
                    <th>№</th>
                    <th>Barcode</th>
                    <th>Name</th>
                    <th>Article</th>
                    <th>Count</th>
                    <th>In stock</th>
                    <th>Country</th>
                    <th>Producer</th>
                    <th>Delete</th>
                </tr>
                <#list products as product>
                    <tr class="<#if !product.presence>noActive</#if>">
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product_index+1}</a>
                        </td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.barCode? string.computer}</a>
                        </td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.name}</a></td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.article.name}</a></td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.count}</a></td>
                        <td>
                            <a href="" data-toggle="modal" data-target="#ex${product.barCode?string.computer}">
                                <#if product.presence>
                                    В наличии
                                <#else >
                                    Отсутствует
                                </#if>
                            </a>
                        </td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.country.name}</a></td>
                        <td><a href="" data-toggle="modal"
                               data-target="#ex${product.barCode?string.computer}">${product.producer.name}</a></td>
                        <td align="center"><a href="/product/deleteProduct/${product.id}/storage=${storageID}"><font
                                        color="red">X</font></a>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
        <div class="button">
            <a href="" class="btn btn-success" data-toggle="modal" data-target="#exampleModal">Добавить</a>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="post" action="/product/addProduct">
                    <div class="modal-body">
                        <label for="barcode"> Barcode
                            <input type="number" name="barcode" class="form-control"/>
                        </label>
                        <label for="name"> Наименование
                            <input type="text" name="name" class="form-control"/>
                        </label>
                        <label for="article"> Article
                            <select class="form-control" name="article">
                                <#list articles as article>
                                    <option value="${article.id}">${article.name}</option>
                                </#list>
                            </select>
                        </label>
                        <label for="count"> How many
                            <input type="number" name="count" class="form-control"/>
                        </label>
                        <label for="producer"> Producer
                            <select class="form-control" name="producer">
                                <#list producers as producer>
                                    <option value="${producer.id}">${producer.name}</option>
                                </#list>
                            </select>
                        </label>
                        <label for="country"> Country
                            <select class="form-control" name="country">
                                <#list countries as country>
                                    <option value="${country.id}">${country.name}</option>
                                </#list>
                            </select>
                        </label>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="storageId" value="${storageID}"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="exampleModalToo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form class="update-storage" method="post" action="/updateStorage/${storage.id}">
                    <div class="modal-body">
                        <label> Наименование
                            <input type="text" name="name" value="${storage.name}" class="form-control"/>
                        </label>
                        <label> Адрес
                            <textarea class="form-control" name="address">${storage.address}</textarea>
                        </label>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Save changes">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <#list products as product>
        <div class="modal fade" id="ex${product.barCode?string.computer}" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="/product/addProduct">
                        <div class="modal-body">
                            <label for="barcode"> Barcode
                                <input type="number" name="barcode" class="form-control"
                                       value="${product.barCode?string.computer}"/>
                            </label>
                            <label for="name"> Наименование
                                <input type="text" name="name" class="form-control" value="${product.name}"/>
                            </label>
                            <label for="article"> Article
                                <select class="form-control" name="article">
                                    <option value="${product.article.id}">${product.article.name}</option>
                                    <#list articles as article>
                                        <#if product.article.name != article.name>
                                            <option value="${article.id}">${article.name}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </label>
                            <label for="count"> How many
                                <input type="number" name="count" class="form-control" value="${product.count}"/>
                            </label>
                            <label for="producer"> Producer
                                <select class="form-control" name="producer">
                                    <option value="${product.producer.id}">${product.producer.name}</option>
                                    <#list producers as producer>
                                        <#if product.producer.name != producer.name>
                                            <option value="${producer.id}">${producer.name}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </label>
                            <label for="country"> Country
                                <select class="form-control" name="country">
                                    <option value="${product.country.id}">${product.country.name}</option>
                                    <#list countries as country>
                                        <#if product.country.name != country.name>
                                            <option value="${country.id}">${country.name}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </label>
                            <input type="hidden" name="id" value="${product.id}"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="storageId" value="${storageID}"/>
                            <div class="check">
                                <label class="checkbox">
                                    <input type="checkbox" checked name="check"/>
                                    <div class="checkbox__text">В наличии</div>
                                </label>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#list>
</@common.page>