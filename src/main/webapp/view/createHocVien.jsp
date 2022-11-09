
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/2/2022
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Registation Form * Form Tutorial</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h2 class="text-center">Add new student</h2>
        </div>
        <form method="post" action="/home?action=create">
        <div class="panel-body">
            <div class="form-group">
                <label for="usr">Name</label>
                <input required="true" type="text" class="form-control" name="name" id="usr" placeholder="Enter Name">
            </div>
            <div class="form-group">
                <label for="pwd">Email Address</label>
                <input required="true" type="text" class="form-control" id="pwd" name="email" placeholder="Enter Email">
            </div>
            <div class="form-group">
                <label for="brt">Date of Birth</label>
                <input required="true" type="date" class="form-control" name="date" id="brt" placeholder=>
            </div>
            <div class="form-group">
                <label for="add">Address</label>
                <input type="text" class="form-control" id="add" name="address" placeholder="Enter Address">
            </div>
            <div class="form-group">
                <label for="phone">Phone number</label>
                <input required="true" type="number" class="form-control" id="phone" name="phonenumber" placeholder="Enter PhoneNumber">
            </div>

            <div class="form-group">
                <label>Class Room</label>
                <select class="form-control" name="idClassRoom" >
                    <c:forEach items="${listClass}" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-success">Register</button>

        </div></form>
    </div>
</div>
</body>
</html>
