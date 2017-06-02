;(function($){
	$.fn.jQSelect = function(settings){
	
		var $div = this;
		var $cartes = $div.find(".cartes");
		var $lists = $div.find(".lists");
		
		var listTxt = $cartes.find(".listtxt");
		var listVal = $cartes.find(".listval");

		var items = $lists.find("ul > li");
		
		$div.hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");	
		});
		
		//绑定点击事件
		items.click(function(){
			listVal.val($(this).attr("id"));
			listTxt.val($(this).text());
			$div.removeClass("hover");
		}).mouseover(function(){
			$(this).removeClass("cwhite");
			$(this).addClass("cgray");
		}).mouseout(function(){
			$(this).removeClass("cgray");
			$(this).addClass("cwhite");
		});
		
	};
})(jQuery);