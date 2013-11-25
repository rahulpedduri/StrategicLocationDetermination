/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var map;
var markers = [];
var lats = [];

function initialize() {
  var haightAshbury = new google.maps.LatLng(37.7699298, -122.4469157);
  var mapOptions = {
    zoom: 6,
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
  lats.push(location);
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
  lats=[];
}

function makeMap(lat, long, m){
    var loc = new google.maps.LatLng(lat, long);
  var mapOptions = {
    zoom: 12,
    center: loc,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(m,
      mapOptions);
     
  
}
function makeMapsForResults(){
    $(".map").each(function(){
       var lat = $(this).attr('lat');
       var long = $(this).attr('long');
       var m = this;
       makeMap(lat, long, m)
       
    });
}

google.maps.event.addDomListener(window, 'load', makeMapsForResults);