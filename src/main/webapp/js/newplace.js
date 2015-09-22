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
		step:30
		});
});

/**
 * Action when user chooses a type of the new place to be added.
 * @param type
 */
function changeType(type) { 
	$('#contentGroup').removeClass('hide');
	switch (type) {

    case "event":
    	$('#eventTimeGroup').removeClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;

    case "news":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').addClass('hide');
    	$('#emailGroup').addClass('hide');
    	$('#referenceUrlGroup').addClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;

    case "annoucement":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').addClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;

    case "restaurant":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
        break;
   
    case "friendsmap":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;    	

    case "administration":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "company":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
        break;
        
    case "association":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
        break;

    case "tourism":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "sport":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "market":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "service":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "individual":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').removeClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
        break;
        
    case "countries":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').addClass('hide');
    	$('#emailGroup').addClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').removeClass('hide');
        break;        
        
    case "usefulinfo":
    	$('#eventTimeGroup').addClass('hide');
    	$('#openTimeInput').addClass('hide');
    	$('#telephoneGroup').removeClass('hide');
    	$('#emailGroup').removeClass('hide');
    	$('#referenceUrlGroup').removeClass('hide');
    	$('#sizeGroup').addClass('hide');
    	break;
    	
    default:
        break;
    }


}