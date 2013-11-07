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
            url: "/Strategic_Location_Determination/handlerequests",
            dataType: "json",
            success: function(response)
            {
                var data = JSON.parse(response);
                for (var x = 0; x < data.length; x++) {
                $('#select_state').append($('<option>', { value : data[x].code })
          .text(data[x].name)); 
               
               // updateListing(data[x]);
            }
            }
    });
}