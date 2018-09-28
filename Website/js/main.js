//Manjyot Sandhu 2018, Royal Holloway University of London
$(document).ready(function() {

  var web_window = $(window);
  var extras_icons = $.find('.extras-icons'); //Icons to animate
  var progress_bars_loaded = false;
  var contact_titles_loaded = false;

  //Navbar changing colour as scrolling off main header
  web_window.on("scroll", function() {
    var win = web_window.scrollTop();
    //If scrolled past header, change bg colour of navbar
    if (win > 120) {
      $(".navbar").css("background", "#141414");
      $(".navbar").css("transition", "background-color 0.5s ease 0s");
    }
    else {
      $(".navbar").css("background", "transparent");
    }
  });

  /*
   * Smooth scrolling, idea from https://stackoverflow.com/questions/7717527/smooth-scrolling-when-clicking-an-anchor-link/7717625
   */
  $("a").on('click', function(event) {
    if (this.hash !== "") {
      event.preventDefault();
      var currhash = this.hash;

      $('html, body').animate({
        scrollTop: $(currhash).offset().top
      }, 700, function() {
        window.location.hash = currhash;
      });
    }
  });

  //Disabling default form validation
  (function() {
    'use strict';
    window.addEventListener('load', function() {
      var customValidationFields = document.getElementsByClassName('needs-validation');
      var validation = Array.prototype.filter.call(customValidationFields, function(field) {
        field.addEventListener('submit', function(event) {
          if (field.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          field.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();

  //Extras icon animations
  $(window).scroll(function() {
    
    //If the section to animate is in view, fade in icons
    if (check_if_in_view('#animation-icons-section') == true) {
      $('#animation-icon-1').fadeIn(2000);
      $('#animation-icon-2').fadeIn(3000);
      $('#animation-icon-3').fadeIn(4000);
    }

    //If progress bar section in view, animate them
    if (check_if_in_view('#progress-bars-section') == true && progress_bars_loaded == false) {
      $('#progress-bar-html-100').animate({ width: "100%" }, 1000);
      $('#progress-bar-css-90').animate({ width: "90%" }, 1000);
      $('#progress-bar-jquery-40').animate({ width: "40%" }, 1000);

      $('#progress-bar-photoshop-90').animate({ width: "90%" }, 1000);
      $('#progress-bar-android-60').animate({ width: "60%" }, 1000);
      $('#progress-bar-git-50').animate({ width: "50%" }, 1000);

      $('#progress-bar-java-80').animate({ width: "80%" }, 1000);
      $('#progress-bar-python-50').animate({ width: "50%" }, 1000);
      $('#progress-bar-c-30').animate({ width: "30%" }, 1000);

      progress_bars_loaded = true;
    }
  });


  /**
   * Function to check if element is in current viewport
   * Ideas from https://medium.com/talk-like/detecting-if-an-element-is-in-the-viewport-jquery-a6a4405a3ea2
  */
  
  function check_if_in_view(element) {
    var top_of_element = $(element).offset().top;
    var bottom_of_element = $(element).offset().top + $(element).outerHeight();
    var bottom_of_screen = $(window).scrollTop() + window.innerHeight;
    var top_of_screen = $(window).scrollTop();

    if ((bottom_of_screen > top_of_element) && (top_of_screen < bottom_of_element)) {
      return true;
    }
    else {
      return false;
    }
  };
});
