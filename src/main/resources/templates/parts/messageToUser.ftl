<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100vw;
        height: 100vh;
        overflow-y: hidden;
        overflow-x: hidden;">
<div>
    <h1>Storage Application</h1>
    <h2>Администратор активировал учетную запись с указанными данным:</h2>
    <p style="font-size: 16px"><span style="font-weight: bold;
        font-size: 18px">ФИО:</span> ${name} </p>
    <p style="font-size: 16px"><span style="font-weight: bold;
        font-size: 18px">Должность:</span> ${post} </p>
    <p style="font-size: 16px"><span style="font-weight: bold;
        font-size: 18px">Username:</span> ${username} </p>
    <p style="font-size: 16px"><span style="font-weight: bold;
        font-size: 18px">Email:</span> ${email} </p>
    <a onmouseover="this.style.backgroundColor = 'black';" onmouseout="this.style.backgroundColor='#28a745';"
       style="color: #fff;
    background-color: #28a745;
    border-color: #28a745;
    width: 250px;
    display: inline-block;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    padding: .375rem .75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: .25rem;
    text-decoration: none;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;"
       href="http://127.0.0.1:8080/login">Войти</a>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</html>