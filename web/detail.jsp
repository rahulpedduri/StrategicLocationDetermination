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
        <style>
            .map{
                width: 600px;
                height: 400px;
                position: relative;
                top: -31px;
                left: 500px;
            }
            .map:after{
                content: " ";

            }
            li{
               
                padding: 14px;
background: darkseagreen;
margin-bottom: 16px;
            }

        </style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js">
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="Javascript/maps.js"></script>

         <script type="text/javascript">
            google.maps.event.addDomListener(window, 'load', mapForDetail);
        </script>
    </head>
    <body>
        <h1>In Detail</h1>
        
        <c:set var="name" value="detail" />
        <c:set var="p" value="${sessionScope[name]}" />
                
                    <span>Name : ${p.code}</span>
                    <br/>
                    <span>Population : ${p.population}</span>
                    <br/>
                    <span>Z Value : ${p.zvalue}</span>
                     <div id="map-canvas" class="map" lat="${p.latitude}" long="${p.longitude}" comp="${p.competitorJSON}" > </div>
              
                   
                     <%--TODO remaining values to be populated --%>
               
           
    </body>
</html>
