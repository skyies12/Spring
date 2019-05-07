$('document').ready(function(){
    
    // mb,tb menu
    $('.button-wrap').click(function(){
      $(this).toggleClass("on");
      $(".menu").slideToggle();
    });
    // pc menu
    $('.pc-button').click(function(){
      $('.menu').toggleClass('visible');
      $(this).toggleClass("on");
    });
    
     $(window).resize(function () {
         if($(this).width() > 1262) {
            $('.menu').css('display','block');
         } else {
             $('.menu').css('display','none');
             $('.button-wrap').removeClass('on');
         };
     });
});