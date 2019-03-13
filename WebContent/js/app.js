var template = $('#sections .section:first').clone();

var sectionsCount = str;

$('body').on('click', '.addsection', function() {
    sectionsCount++;
    var section = template.clone().find(':input,p').each(function(){
        var newId = $(this).attr('name') + '_generated' +'_' + sectionsCount;
        $(this).val("").prev().attr('for', newId);
        this.id = newId;
	}).end()

	$("#limit").append(section)
	/*.appendTo('#sections').val("");*/
    return false;
});
$('.section, #limit').on('click', '.remove', function() {
	if(sectionsCount > 1 ){
		$(this).parent().fadeOut(800, function() {
			$(this).parent().parent().empty();
			return false;
		});
		sectionsCount--;
	}
	else{
		alert("Can not remove all the addresses...!");
	}
	return false;
	
});


/*
 * for(var i=1;i<count;i++){ $('.UpdateButton').on('click', function() {
 * sectionsCount++; var section =
 * template.clone().find(':input').each(function(){ var newId = this.id +
 * sectionsCount; $(this).prev().attr('for', newId); this.id = newId; }).end()
 * 
 * .appendTo('#sections'); return false; }); }
 */

