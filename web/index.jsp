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
<style>
    #map-canvas {
        height: 350px;
        margin: 0px;
        padding: 0px
      }
</style>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true" type="text/javascript"></script>
   
<script>
// In the following example, markers appear when the user clicks on the map.
// The markers are stored in an array.
// The user can then click an option to hide, show or delete the markers.
var map;
var markers = [];

function initialize() {
  var haightAshbury = new google.maps.LatLng(37.7699298, -122.4469157);
  var mapOptions = {
    zoom: 12,
    center: haightAshbury,
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);

  google.maps.event.addListener(map, 'click', function(event) {
    addMarker(event.latLng);
  });
}

// Add a marker to the map and push to the array.
function addMarker(location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  markers.push(marker);
}

// Sets the map on all markers in the array.
function setAllMap(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setAllMap(null);
}

// Shows any markers currently in the array.
function showMarkers() {
  setAllMap(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
  clearMarkers();
  markers = [];
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strategic Location Determination</title>
        <script src="Javascript/common.js"></script>
    </head>
    <body onload="javascript:loadStates()">
        <div>
            <label>State: </label><select id="select_state" >
            </select>            
        </div>
        
        <div id="places" style="display:none" >
            <label>Places: </label> <select id="select_place" ></select>
            
        </div>
        <input type="button" style="display: none" value="Add more"/>
        
         <div id="map-canvas"></div>
    <p>Click on the map to add markers.</p>
    </body>
</html>
