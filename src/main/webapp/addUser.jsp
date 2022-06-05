<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить нового пользователя</title>
</head>
<body>
<form action = "/users" method="post">
    <input required type="text" name="name" placeholder="Имя">
    <input required type="text" name="age" placeholder="Возраст">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>