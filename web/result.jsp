<%-- 
    Document   : result
    Created on : Nov 25, 2013, 1:24:07 AM
    Author     : Phani Rahul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
        <style>
            .map{
                width: 500px;
                height: 135px;
            }
        </style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js">
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="Javascript/maps.js"></script>
    </head>
    <body>
        <h1>Loaded</h1>
        <ul>
            <c:set var="name" value="selected" />
            <c:forEach items="${sessionScope[name]}" var="loc">
                <li>
                    <span>Name : ${loc.code}</span>
                    <br/>
                    <span>Population : ${loc.population}</span>
                    <div id="" class="map" lat="${loc.latitude}" long="${loc.longitude})" > </div>
                    <%-- TODO remaining values to be populated --%>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
