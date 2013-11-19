//Document onload untuk seluruh JSP
$().ready(function(){
	
	//style all tooltips
	$(document).tooltip({ 
		position: { my: "right" }
	}); 
	
	//buttonify
	$("input[type=submit], button").button(); 
	
	//styling input text/pass
	$("input[type=text], input[type=password], textarea, select")
		.addClass("ui-widget-content ui-corner-all") 
		.click(function() { //select all text when focused in input form
		   $(this).select();
		});
	
	//styling readonly fields
	$("input[readonly]").addClass("ui-widget-header"); 
	
	//group radio buttons biar lebih keren
	$(".buttonset").buttonset(); 
	
	//create top horizontal menu bar
	$("#menubar").menubar();
	
	//create tabs
	$(".tabs").tabs(); 
	
	//style semua table dengan class ".gridTables"
	$(".gridTables").styleTable(); 
	
	//untuk semua input yg mempunyai class "datepicker" otomatis menjadi datepicker
	$(".datepicker").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd-mm-yy" //yy = 4 digit
	});
	
	//khusus untuk datepicker from dan to (biasanya di jsp berupa list)
	$("#periodFrom").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd-mm-yy", //yy = 4 digit
		onClose: function( selectedDate ) {
			$("#periodTo").datepicker( "option", "minDate", selectedDate );
			$("#periodTo").focus(); //onclose, pindah ke datepicker berikutnya
		}
	});
	$("#periodTo").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd-mm-yy", //yy = 4 digit
		onClose: function( selectedDate ) {
			$( "#periodFrom" ).datepicker( "option", "maxDate", selectedDate );
			$(this.form).submit(); //onclose, submit form
		}
	});	
	
	//untuk semua input yg mempunyai class "nominal" akan diformat 3 digit secara otomatis, dan di-align right
	$(".nominal").change(function(){
		$(this).val(formatCurrency($(this).val()));
		 $("#nominal").text("");
	}).keyup(function(){
		 $("#nominal").text(formatCurrency($(this).val()));
	}).addClass("right");
	
	//untuk semua input text yg namanya "search", bila pencet enter, submit form
	$("input[name=search]").enterKey(function () { $("form").submit(); });
	
	//modal dialog box untuk pesan error
	if($("#messageBox")) $("#messageBox").dialog({
		modal: true,
		title: "Pesan",
		minWidth: 500,
		buttons: {
			Tutup: function() {
				$( this ).dialog( "close" );
			}
		}
	});	
	
});

//Styling table agar mengikuti theme jqueryui
(function ($) {
    $.fn.styleTable = function (options) {
        var defaults = {
            css: 'styleTable'
        };
        options = $.extend(defaults, options);

        return this.each(function () {

            input = $(this);
            input.addClass(options.css);

            input.find("tr").on('mouseover mouseout', function (event) {
                if (event.type == 'mouseover') {
                    $(this).children("td").addClass("ui-state-hover");
                } else {
                    $(this).children("td").removeClass("ui-state-hover");
                }
            });

            input.find("th").addClass("ui-state-default");
            input.find("td").addClass("ui-widget-content");

            input.find("tr").each(function () {
                $(this).children("td:not(:first)").addClass("first");
                $(this).children("th:not(:first)").addClass("first");
            });
        });
    };
})(jQuery);

//onkeypress ENTER (http://stackoverflow.com/questions/979662/how-to-detect-pressing-enter-on-keyboard-using-jquery)
//usage : $("#input").enterKey(function () { alert('Enter!'); });
$.fn.enterKey = function (fnc) {
    return this.each(function () {
        $(this).keypress(function (ev) {
            var keycode = (ev.keyCode ? ev.keyCode : ev.which);
            if (keycode == '13') {
                fnc.call(this, ev);
                if (ev.preventDefault) {
                	ev.preventDefault();
                } else {
                	ev.returnValue = false;
                }
            }
        });
    });
};

//get number from field
function getNumber(num){
    num = num.toString().replace(/\,/g, '');
    if( isNaN(num) )
        num = "0";
    return new Number(num);
}

//format currency
function formatCurrency( num ){
    num = getNumber(num);
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if( cents < 10 )
        cents = "0" + cents;
    for( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++ )
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
              num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}