<%-- 
    Document   : index
    Created on : Nov 6, 2013, 4:47:23 PM
    Author     : san
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
           <script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js">
</script>

<script src="Javascript/common.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strategic Location Determination</title>
    </head>
    <body onload="javascript:loadStates()">
        <div id="content">
        <div id="maindiv">
        <div>
            <label>State: </label><select id="select_state" class="states">
            </select>            
        </div>
        
        <div id="places" style="display:none" >
            <label>Places: </label> <select id="select_place" ></select>
            
        </div>
            <hr>
            </div>
            </div>
        <input type="button" style="display: none" value="Add more" id="btn_place" class="btn"/>
        <input type="submit" style="display: none" value="Submit" id="btn_submit" class="btn" onclick="javascript:submitrequest()"/>

    </body>
</html>
