/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var cloned;
var p = 0;
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
                var length = 0;
                for (var dummy in response)
                    length++;

                for (var i = 0; i < length; i++) {
                    $('#select_state').append($('<option >', {value: response[i][0]}).text(response[i][1]));
                    // var m=response;
                    //updateListing(data[x]);
                }
                cloned = $("#maindiv").clone(false);


                 dropdowneventlistner();
                $("#btn_place").click(function() {
                    $('.btn').hide();
                    p++;
                    
                    var c = cloned.clone(false);
                            c.find("*[id]").andSelf().each(function() {
                        $(this).attr("id", $(this).attr("id") + "_" + p);
                    })
                   // alert(c);
                    $("#content").append(c);
                    dropdowneventlistner();
                });



            });
}

function dropdowneventlistner(){

                $('.states').change(function() {

                   var parent =$(this).parent();
                    //on change event for the select box
                   var place = $(this).val();
                   //  var place = $('#select_state').val();
                    $.ajax({
                        type: "POST",
                        url: "/Strategic_Location_Determination/handlerequests?requestType=cities",
                        dataType: "json",
                        data: {place: place}
                    })
                            .done(function(response)
                            {
                                //var data = JSON.parse(response);
                                var length = 0;
                                for (var dummy in response)
                                    length++;
                               parent.next().show();
                                for (var i = 0; i < length; i++) {

                                    parent.next().find("select").append($('<option>', {value: response[i][1]}).text(response[i][0]));
                                    // var m=response;
                                    //updateListing(data[x]);
                                }
                                $(".btn").show();

                            });
                });
            }
            
            function submitrequest()
            {
                var x=0;
                var statesArray= new Array();
                var jsonData = {};
                var jsonArray = new Array();
                $("body").find("select").each(function(){
                    x++;
                     //jsonData['state']=$(this).val();
                   // statesArray.push($(this).val());
                   if(x%2==0) {
                       x=0;
                       //jsonData['state']=statesArray[0];
                       jsonData['place']=$(this).val();
                      // statesArray=[];
                       jsonArray.push(jsonData);
                       jsonData={};
                   }else{
                        jsonData['state']=$(this).val();
                   }
                });
            }
