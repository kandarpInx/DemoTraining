window.addEventListener( "pageshow", function ( event ) {
  var historyTraversal = event.persisted || 
                         ( typeof window.performance != "undefined" && 
                              window.performance.navigation.type === 2 );
  if ( historyTraversal ) {
    // Handle page restore.
    window.location.reload();
  }
});

$(document).ready(function() {
		$('#example').DataTable({
			"aLengthMenu" : [ [ 5, 10, 25, -1 ], [ 5, 10, 25, "All" ] ],
			"iDisplayLength" : 5,
			responsive: true,
		});
	});

	$(document).find('.theButton').on('click',function(){
	/* alert($(this).closest("tr").find(".nr").text()); */
    $.ajax({
        type: "POST",
        url: "AdminDataServlet",
        data: { userId: $(this).closest("tr").find(".nr").text() },
        success:function(result){
        	$("#address").find("tr:not(:first)").remove();
        	for(var i = 0; i < result.length; i++)
            {  
              $('#address tr:nth-child(1)').after(
                 '<tr> <td>' + result[i].addressId + '</td> '+
                 '<td>' + result[i].street1 + '</td> '+
                 '<td>' + result[i].street2 + '</td> '+
                 '<td>' + result[i].pincode + '</td> '+
                 '<td>' + result[i].city + '</td> '+
                 '<td>' + result[i].state + '</td> '+
                 '<td>' + result[i].country + '</td> '+
                 '</tr>');
            }
        }
    });
});
	
	// popover demo
	$('#example').find('.popovers')
	.popover({html:true,
		trigger: "focus"})