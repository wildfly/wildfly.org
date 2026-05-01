(function($) {
  $(function() {
    $('nav ul li a:not(:only-child)').click(function(e) {
      $(this).siblings('.nav-dropdown').toggle();

      $('.nav-dropdown').not($(this).siblings()).hide();
      e.stopPropagation();
    });

    $('html').click(function() {
      $('.nav-dropdown').hide();
    });

    $('#nav-toggle').click(function() {
      $('nav ul').slideToggle();
    });

    $('#nav-toggle').on('click', function() {
      this.classList.toggle('active');
    });

    // Auto-hide navbar on scroll down (mobile only)
    var lastScrollTop = 0;
    var scrollThreshold = 5; // Minimum scroll distance to trigger hide/show

    $(window).scroll(function() {
      // Only apply on mobile/tablet screens
      if ($(window).width() <= 1024) {
        var scrollTop = $(this).scrollTop();

        // Scrolling down
        if (scrollTop > lastScrollTop + scrollThreshold && scrollTop > 100) {
          $('.navigation').addClass('nav-hidden');
        }
        // Scrolling up
        else if (scrollTop < lastScrollTop - scrollThreshold) {
          $('.navigation').removeClass('nav-hidden');
        }

        lastScrollTop = scrollTop;
      } else {
        // Always show on desktop
        $('.navigation').removeClass('nav-hidden');
      }
    });
  });
})(jQuery);
