        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Добавление</title>
</head>
<body>

<h2>Регистрация водителей (Анкет: ${countFields})</h2>

<form action="${pageContext.request.contextPath}/save" method="post">

    <c:forEach begin="1" end="${countFields}" var="i">
        <h4>Водитель №${i}</h4>

        Email: <input type="email" name="email" required><br>
        Имя: <input type="text" name="name" required><br>
        Фамилия: <input type="text" name="surname" required><br>
        Пароль: <input type="password" name="password" required><br>
        Возраст: <input type="number" name="age" required min="18"><br>
        Город: <input type="text" name="city" required><br>
        Машина: <input type="text" name="carModel" required><br>
        Рейтинг: <input type="number" name="rating" required step="0.1" min="0" max="5"><br>
        <hr>
    </c:forEach>

    <button type="submit">Сохранить всех</button>
</form>

</body>
</html>