$(function()
{
    responsiveNavbar();
    fixCategoryNav();
});

/**
 * make the navbar responsivable
 *
 * @access public
 * @return void
 */
function responsiveNavbar()
{
    var lis = $('#mainNavbar .navbar-nav').first().addClass('mainNavbarNav').find('li');
    var lisSize = lis.length;
    if(lisSize>5)
    {
        var i = 0;
        lis.each(function()
        {
            if(i++>10) $(this).addClass('simple-mode-b'); else $(this).addClass('simple-mode-a');
        });
    }

    $('#navbarSwitcher').click(function()
    {
        var navbar = $(this).closest('.navbar');
        if(navbar.hasClass('navbar-simple')) navbar.removeClass('navbar-simple');
        else  navbar.addClass('navbar-simple');
    });
}

/**
 * fix height of category nav in 'leftmenu'
 *
 * @access public
 * @return void
 */
function fixCategoryNav()
{
    var $categoryNav = $('.category-nav');
    if($categoryNav.length)
    {
        var $panelBody = $categoryNav.find('.panel-body');
        var ajustHeight = function()
        {

            if($('html').hasClass('screen-phone'))
            {
                $panelBody.css('max-height', 'auto');
            }
            else
            {
                $panelBody.css('max-height', $(window).height() - 170 - $('.leftmenu > .nav-stacked').height());
            }
        };
        ajustHeight();
        $(window).resize(ajustHeight);
    }
}
