/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



// load XML file
function loadStates()
{            

    $.ajax({
            type: "POST",
            url: "/Strategic_Location_Determination/handlerequests?requestType=states",
            dataType: "json"
        })
                .done(function(response)
            {
                //var data = JSON.parse(response);
                var length=0;
       for(var dummy in response) length++;
       
                for(var i=0;i<length;i++) {
                $('#select_state').append($('<option >', { value : response[i][0] }).text(response[i][1])); 
              // var m=response;
                //updateListing(data[x]);
            }
            
          $('#select_state').change(function() {
   
    
    //on change event for the select box
    var place = $('#select_state').val();
  $.ajax({
            type: "POST",
            url: "/Strategic_Location_Determination/handlerequests?requestType=cities",
            dataType: "json",
            data: { place: place}
        })
                .done(function(response)
            {
                //var data = JSON.parse(response);
                var length=0;
              for(var dummy in response) length++;
              $('#places').show();
                for(var i=0;i<length;i++) {
                
                $('#select_place').append($('<option>', { value : response[i][0] }).text(response[i][1])); 
              // var m=response;
                //updateListing(data[x]);
            }

    });
});
                    
    });
}

