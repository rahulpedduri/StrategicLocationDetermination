<%-- 
    Document   : detail
    Created on : Nov 25, 2013, 12:39:14 PM
    Author     : Phani Rahul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
         <script type="text/javascript">
            google.maps.event.addDomListener(window, 'load', mapForDetail);
        </script>
    </head>
    <body>
        <h1>In Detail</h1>
        
        <c:set var="name" value="place" />
        <c:forEach items="${sessionScope[name]}" var="p">
                <li>
                    <span>Name : ${p.code}</span>
                    <br/>
                    <span>Population : ${p.population}</span>
                    <br/>
                    <span>Population : ${p.zvalue}</span>
                    <div id="" class="map" lat="${p.latitude}" long="${p.longitude}" comp="${p.competitorJSON}" > </div>
                   
                    <%-- TODO remaining values to be populated --%>
                </li>
            </c:forEach>
    </body>
</html>
