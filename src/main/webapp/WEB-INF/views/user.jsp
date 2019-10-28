<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>User Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<%--<body background="http://carpets.com.ua/assets/images/kovropedia/Afganskie/%D0%90%D1%84%D0%B3%D0%B0%D0%BD%D1%81%D0%BA%D0%B8%D0%B5-%D0%BA%D0%BE%D0%B2%D1%80%D1%8B-%D1%84%D0%BE%D1%82%D0%BE-14.png" marginwidth=10% >--%>

<h1>
    Add a user
</h1>

<c:url var="addAction" value="/user/add" ></c:url>

<form:form action="${addAction}" modelAttribute="user">
    <table>
        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="surname">
                    <spring:message text="Surname"/>
                </form:label>
            </td>
            <td>
                <form:input path="surname" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="login">
                    <spring:message text="Login"/>
                </form:label>
            </td>
            <td>
                <form:input path="login" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="password" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Edit user"/>" />
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add user"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Surname</th>
            <th width="60">Login</th>
            <th width="60">Password</th>

        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td><a href="<c:url value='/edit/${user.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/remove/${user.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

