$(document).ready(function () {
    $(".delete").click(function () {
        var url = $(this).attr("url");
        //alert("删除！//TODO");
    });

    $(window).resize(function(){$('#main').css('min-height', $(window).height());}).resize();

    /* set lite version label */
    $('#main > section[data-lite] > .page-header > h2').append(' <small class="label label-info" title="" data-original-title="">LITE</small>');

    // prettyPrint();

    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // popover demo
    $("[data-toggle=popover]").popover();


    $('section .page-header h2 > small.label').tooltip({placement: 'right'});

    $('#changeTheme').click(function()
    {
        var link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '../dist/css/zui-theme.css';
        document.getElementsByTagName('head')[0].appendChild(link);
    });

    if($.fn.dashboard) $('#dashboard').dashboard();

    if($.fn.droppable)
    {
        $('[data-toggle="droppable"]').droppable(
            {
                start: function()
                {
                    $('.droppable-target').removeClass('panel-warning').removeClass('panel-success').find('.panel-heading').text('鎷栧姩鍒拌繖閲屻€�');
                },
                drop: function(event)
                {
                    messager.show('鐪熸锛�');
                    $('.droppable-target').removeClass('panel-success').removeClass('panel-warning');
                    if(event.target)
                    {
                        event.target.addClass('panel-success').find('.panel-heading').text('鎴愬姛鎷栧埌鐩殑鍦般€�');
                    }
                },
                drag: function(event)
                {

                    $('.droppable-target').removeClass('panel-success').removeClass('panel-warning');
                    if(event.target) event.target.addClass('panel-warning');
                }
            });
    }

    if($.fn.boards) $('.boards').boards();

    // Chosen
    if($.fn.chosen) $('.chosen-select').chosen();
    if($.fn.chosenIcons) $('#chosenIcons').chosenIcons();

    // datetime picker
    if($.fn.datetimepicker)
    {
        $('.form-datetime').datetimepicker(
            {
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                format: 'yyyy-mm-dd hh:ii'
            });
        $('.form-date').datetimepicker(
            {
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: 'yyyy-mm-dd'
            });
        $('.form-time').datetimepicker({
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 1,
            minView: 0,
            maxView: 1,
            forceParse: 0,
            format: 'hh:ii'
        });
    }

    if(window.KindEditor)
    {
        KindEditor.ready(function(K)
        {
            K.create('textarea.kindeditor',
                {
                    allowFileManager : true,
                    bodyClass : 'article-content',
                    cssPath: '/assets/stylesheets/zui.css',
                    afterBlur: function(){$('#content').prev('.ke-container').removeClass('focus');},
                    afterFocus: function(){$('#content').prev('.ke-container').addClass('focus');}
                });

            K.create('textarea.kindeditorSimple',
                {
                    bodyClass : 'article-content',
                    cssPath: '/assets/stylesheets/zui.css',
                    resizeType : 1,
                    allowPreviewEmoticons : false,
                    allowImageUpload : false,
                    items : [
                        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                        'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
                    afterBlur: function(){$('#contentSimple').prev('.ke-container').removeClass('focus');},
                    afterFocus: function(){$('#contentSimple').prev('.ke-container').addClass('focus');}
                });

            K.create('textarea.customStyle',
                {
                    themeType : 'simple',
                    bodyClass : 'article-content',
                    cssPath: '/assets/stylesheets/zui.css',
                    afterBlur: function(){$('#contentCustom').prev('.ke-container').removeClass('focus');},
                    afterFocus: function(){$('#contentCustom').prev('.ke-container').addClass('focus');}
                });
        });
    }
});
