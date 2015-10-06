/**
 * 
 */

$(document).ready(function() {	
	$.datetimepicker.setLocale('vi');
	$('.eventTime').datetimepicker({
		dayOfWeekStart : 1,
		lang:'vi',
		//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
		startDate:	'05/09/2015',
		format:'l, d/m/Y H:i',
		step:30,
		onChangeDateTime:function(ct,$input){
			  //$('#information').val(Date.parse(ct) + ':' + new Date(Date.parse(ct)));			
			$input.next().val(Date.parse(ct));
	      }
		});
	
	$('#placeType').change(function() {
		changeType($(this).val()); 
	});
	// UPDATE CASE -> auto show
	if ($('#placeType').val() && $('#placeType').val() != 'NONE') {
		changeType($('#placeType').val());
	}
	
	// SUBMIT
	$('#newplaceform').submit(function(event) {
		console.log(event);
	});
});

/**
 * Action when user chooses a type of the new place to be added.
 * @param type
 */
function changeType(type) { 
	$('#contentGroup').removeClass('hide');
	switch (type) {

    case "EVENT":
    	$('#eventTimeGroup').removeClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;

    case "NEWS":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').addClass('hide');
    	$('#emailGroup').addClass('hide');
    	$('#referenceUrlGroup').addClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;

    case "ANNOUCEMENT":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').addClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;

    case "RESTAURANT":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;
   
    case "FRIENDSMAP":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;    	

    case "ADMINISTRATION":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;
        
    case "COMPANY":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;
        
    case "ASSOCIATION":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
    	$('#organisedByGroup').removeClass('hide');    	
        break;

    case "TOURISM":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;
        
    case "SPORT":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;
        
    case "MARKET":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;
        
    case "SERVICE":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').removeClass('hide');
        break;
        
    case "INDIVIDUAL":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;
        
    case "COUNTRY":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').addClass('hide');
    	$('#emailGroup').addClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
    	$('#organisedByGroup').addClass('hide');
        break;        
        
    case "USEFULINFO":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	$('#organisedByGroup').addClass('hide');
    	break;
    	
    default:
        break;
    }


}