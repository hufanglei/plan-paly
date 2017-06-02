$(function() {
	$('.reserve_clock').css('top', $('#reserve_content thead').height()-40);
    $('.carousel').carousel();
	//全局高度调整
	var screenWidth = $('.row').width();
	onload_do( $('.row').width() );
	
	//页面变化时重设宽度高度
	$(window).resize( function(){
		onload_do( $('.row').width() );
	})
	
	//在top中判断是否有标签设定缩进
	var btn_right = $('.btn_right');
	if ( btn_right.find('.badge').length > 0 ){
		btn_right.css('paddingRight', '2rem');
	}
	
	//阻止默认的a href="#"事件
	$('body').on('click','a', function(e){
		if ( $(this).attr('href') == '#' ){
			e.preventDefault();
		}
	})
	
	//开启首页轮播器
	if ( $('.banner').length > 0 ){
		banner(true,5000);
	}
	
	//reserve_list的轮播效果
	if ( $('.reserve_tab').length > 0 ){
		var $pointer = $('.reserve_pointer span');
		var flipsnap = Flipsnap('.reserve_box');
		flipsnap.element.addEventListener('fspointmove', function() {
			$pointer.filter('.current').removeClass('current');
			$pointer.eq(flipsnap.currentPoint).addClass('current');
		}, false);
	}
	//reserve_list的分类切换
	$('.reserve_menu').on('click', 'div', function(){
		$(this).addClass('active');
		var do_hide = $(this).siblings();
		do_hide.removeClass('active');
		$('.'+ do_hide.attr('id') ).hide();
		$('.'+this.id).show();
	})
	//reserve_mine_type的分类切换
	$('.reserve_mine_type').on('click', 'div', function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.'+this.id).show().siblings('.pay_box').hide();
	})
	$('.info_menu').on('click', 'a', function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.'+this.id).show().siblings('.info_date').hide();
	})
	
	//打开下拉列表
	$('#fast_order_btn1').click(function(){
		$('.index_select_box').slideDown(300);
	});
	$('.index_select_list').on('click', 'a', function(){
		$('#fast_order_btn1').text( $(this).text() );
        $("#sportType").val($(this).attr("sportType_value"));
		$(this).addClass('active').siblings().removeClass('active');
		$('.index_select_box').slideUp(300);
	});
	
	//slider拖动条
	//时间区间拖动条
	
    $('#bar_time').slider({
		range: true,
		min: 6,
		max: 23,
		values: [10, 14],
		slide: function (event, ui) {
			ui.handle.innerHTML = '<i>'+ui.value+':00</i>';
		}
	}).find('.ui-slider-handle').each(function( index ){
		$(this).append('<i>'+$( "#bar_time" ).slider( "values" )[index]+':00</i>');
	});
	//价格区间拖动条
	$('#bar_price').slider({
		range: true,
		min: 0,
		max: 500,
		values: [20, 200],
		slide: function (event, ui) {
			ui.handle.innerHTML = '<i>&#165;'+ui.value+'</i>';
		}
	}).find('.ui-slider-handle').each(function( index ){
		$(this).append('<i>&#165;'+$( "#bar_price" ).slider( "values" )[index]+'</i>');
	});

    //首页查询按钮
    $("#book_query").click(function(){
        var timeValue = $('#bar_time').slider("values");
        var priceValue = $('#bar_price').slider("values");

        $("#time_start").val(timeValue[0]);
        $("#time_end").val(timeValue[1]);
        $("#price_start").val(priceValue[0]);
        $("#price_end").val(priceValue[1]);

        $("#quickBookQueryForm").submit();

    });
	
	//隐藏select操作
	$('.hidden_select').change(function(){
		var _this = $(this);
		_this.parent().find('span').html( _this[0].options[_this[0].selectedIndex].text );
        $("#area").val(_this.val());
	})
	
	//时间选择器选择后的提示，在调试后可以关闭
	$('#reserve_datetime').change(function(){
		alert( $(this).val() )
	});
	
	
	
	
	//如果有footer，则container加5rem底部padding
	if ( $('.footer').length > 0 ){
		$('.container').css('paddingBottom','4rem');
	}
	
	//reserve页week按钮点击效果
	$('.reserve_week_box').on('click', 'a' , function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
	
	//reserve页表格点击效果
	var table = $('#reserve_content');
	$('#reserve_content').on('click', 'td', function(){
		var _this = $(this);
		if ( _this.hasClass('may') ){
			if ( table.find('td[class="selected"]').length > 3 ){
				alert('您选择的场次太多啦，请分两次下单结算哦');
				return false;
			}else{
				_this.removeClass('may').addClass('selected');
				reset_total();
				return false;
			}
		}
		if ( _this.hasClass('selected') ){
			_this.removeClass('selected').addClass('may');
			reset_total();
			return false;
		}
	})
	//刷新总价
	function reset_total(){
		var	selected = table.find('td[class="selected"]'),
			show_box = $('#total_price'),
			total_price = 0;
		if ( selected.length > 0 ){
			selected.each(function(){
				total_price += parseInt( $(this).text() );
			})
			show_box.text( total_price );
		}else{
			show_box.text(0);
		}
	};
	
	//
	$('.pay_little_sex').on('click', function(){
		var _this = $(this);
		_this.addClass('active').siblings().removeClass('active');
		$('input[name="pay_person_sex"]').val( _this.attr('value') );
	})
	
	
	//favorite长按事件 favorite_list
	var touching = undefined;
	$(".favorite_list a").bind("mousedown", function() {
		touching = setTimeout(function() {
			confirm('确定删除吗？');
		}, 1500);
	});
	$(".favorite_list a").bind("mouseup", function() {
		clearTimeout(touching);
	});
	
	//修改绑定手机需先输入手机号
	$('.get_vercode').on('click', function(){
		if ( $('#phone_number').val().length != 11 ){
			alert('请输入正确的手机号');
		}else{
			//执行发送验证码时间
			$('.hide').removeClass('hide').hide().slideDown(500);
		}
	})


    //场馆查看页面
    $(".info_menu_item").click(function(){
        var id = $(this ).attr("id");
        $(".info_date").hide();
        $("."+id ).show();
    });

    //评论
    $("#ok_comment").click(function(){
        var commentContent = $("#commentContent" ).val();
        if(commentContent == ""){
            alert("请输入评论内容！");
        }
        $("#frm")[0 ].submit();
    });


    //收藏
    $(".glyphicon-heart").click(function(){

        var venueId = $(this).attr("venueId");
        if(venueId){
            $.post("/venues/favorite",{"venueId":venueId},function(res){
                if(res.status){
                    alert("收藏成功！");
                }else{
                    alert("您已收藏该场馆");
                }
            })
        }
    });
	
});

//页面初始化设定高度与宽度
function onload_do( screenWidth ){
	$('.img_link_list > a').height( screenWidth / 4 );
	$('.top_box').width( screenWidth ).css('marginLeft',- screenWidth / 2 + 'px');
	$('.footer').width( screenWidth ).css('marginLeft',- screenWidth / 2 + 'px');
	$('.info_pic_box').height( screenWidth * 0.56 );
	
	//初始化reserve_list的3个分栏滚动宽度
	var reserve_tab = $('.reserve_tab');
	if ( reserve_tab.length > 0 ){
		$('.reserve_box').width( screenWidth * reserve_tab.length ).height( screenWidth / 1.6 );
		reserve_tab.width( screenWidth ).find('a').height( screenWidth / 5 ).css('paddingTop', screenWidth / 5);
	}
}

//banner
function banner(isAuto,delayTime){	
	var screenWidth = $('.row').width() ;
	var count = $('.banner li').size();
	$('.banner ul').width(screenWidth*count);
	$('.banner ul').height(screenWidth/2);
	$('.banner').height(screenWidth/2);
	$('.banner li').width(screenWidth).height(screenWidth/2);
	$('.banner li img').width(screenWidth).height(screenWidth/2);
	// With options
	$('.banner li .title').each(function(index, element) {
		$(this).text($(this).text().length>30?$(this).text().substring(0,30)+" ...":$(this).text());
	});
	var flipsnap = Flipsnap('.banner ul');
	flipsnap.element.addEventListener('fstouchend', function(ev) {
		$('.identify em').eq(ev.newPoint).addClass('cur').siblings().removeClass('cur');
	}, false);
	$('.identify em').eq(0).addClass('cur')
	if(isAuto){
		var point = 1;
		setInterval(function(){
			//console.log(point);
			flipsnap.moveToPoint(point);
			$('.identify em').eq(point).addClass('cur').siblings().removeClass('cur');
			if(point+1==$('.banner li').size()){
				point=0;
			}else{
				point++;
				}
			
			},delayTime)
	}
}

//上传图片辅助功能
function handleFiles(obj) {
		window.URL = window.URL || window.webkitURL;
		var	fileElem = document.getElementById("headimg_uploader"),
	   		fileList = document.getElementById("fileList");
		var files = obj.files,
			img = new Image();
		if(window.URL){
			//File API
			  alert(files[0].name + "," + files[0].size + " bytes");
		      img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
		      img.width = 200;
		      img.onload = function(e) {
		         window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
		      }
			  $('#fileList').find('img').remove();
		      fileList.appendChild(img);
		}else if(window.FileReader){
			//opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onload = function(e){
				alert(files[0].name + "," +e.total + " bytes");
				img.src = this.result;
				img.width = 200;
				$('#fileList').find('img').remove();
				fileList.appendChild(img);
			}
		}else{
			//ie
			obj.select();
			obj.blur();
			var nfile = document.selection.createRange().text;
			document.selection.empty();
			img.src = nfile;
			img.width = 200;
			img.onload=function(){
		      alert(nfile+","+img.fileSize + " bytes");
		    }
			$('#fileList').find('img').remove();
			fileList.appendChild(img);
			//fileList.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
		}
	}

//
//Published by Indream Luo
//Contact: indreamluo@qq.com
//Version: Chinese 1.0.0
!function ($) {
    window.indream = window.indream || {};
    $.indream = indream;
    //Define events
    indream.touch = {
        evenList: {
            touchStart: {
                htmlEvent: 'touchstart'
            },
            touchMove: {
                htmlEvent: 'touchmove'
            },
            touchEnd: {
                htmlEvent: 'touchend'
            },
            tapOrClick: {
                eventFunction: function (action) {
                    $(this).each(function () {
                        (function (hasTouched) {
                            $(this).touchEnd(function (e) {
                                hasTouched = true;
                                action.call(this, e);
                            });
                            $(this).click(function (e) {
                                if (!hasTouched) {
                                    action.call(this, e);
                                }
                            });
                        }).call(this, false);
                    });
                    return this;
                }
            },
            moveOrScroll: {
                eventFunction: function (action) {
                    $(this).each(function () {
                        (function (hasTouched) {
                            $(this).touchMove(function (e) {
                                hasTouched = true;
                                action.call(this, e);
                            });
                            $(this).scroll(function (e) {
                                if (!hasTouched) {
                                    action.call(this, e);
                                }
                            });
                        }).call(this, false);
                    });
                    return this;
                }
            }
        }
    }

    //Add events into jquery
    for (var eventName in indream.touch.evenList) {
        var event = indream.touch.evenList[eventName];
        $.fn[eventName] = event.eventFunction || (function (eventName, htmlEvent) {
            return function (action) {
                $(this).each(function () {
                    $(this).bind(htmlEvent, action);
                    //Add event listener method for IE or others
                    if (this.attachEvent) {
                        this.attachEvent('on' + htmlEvent, function (e) {
                            $(this).on(eventName);
                        });
                    } else {
                        this.addEventListener(htmlEvent, function (e) {
                            $(this).on(eventName);
                        });
                    }
                });
                return this;
            }
        })(eventName, event.htmlEvent);
    }
}(window.jQuery);




$(document ).ready(function(){
	var msg = $('#hidMessage').val();
    if(msg != null && msg !='') {
		alert(msg);
	}
    $("#submitOrderBtn" ).click(function(){
        var selectedFields = [];
        var isValid = true;
        $(".selected").each(function(){
            var str = $(this).attr("id");
            var values = str.split("_");
            if(values[3] == 'null'){isValid = false; return false;}
            selectedFields.push(str);
        });
        if(selectedFields.length == 0){
            alert("请选择您要预订的场地！");
            return;
        }
        if(!isValid){
            alert("您选择场地没有设置价格！暂时不能购买");
            return;
        }
        $("#chooseFields").val(selectedFields.join(","));
        $("#orderConfirmForm" ).submit();
    });
});
