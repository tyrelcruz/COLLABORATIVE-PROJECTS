$(document).ready(function(){
    $(window).scroll(function(){
        if(this.scrollY > 20){
            $('.navbar').addClass("sticky");
        } else {
            $('.navbar').removeClass("sticky");
        }
        if(this.scrollY > 500){ // Use this.scrollY instead of this.scroll
            $('.scroll-up-btn').addClass("show");
        } else {
            $('.scroll-up-btn').removeClass("show");
        }
    });
    $('.scroll-up-btn').click(function(){
        $('html,body').animate({scrollTop: 0});
    });
});

document.addEventListener('scroll', function() {
    const footer = document.getElementById('footer');
    const scrollUpBtn = document.querySelector('.scroll-up-btn');
    const footerRect = footer.getBoundingClientRect();
    const buttonRect = scrollUpBtn.getBoundingClientRect();
  
    if (buttonRect.bottom > footerRect.top) {
      scrollUpBtn.style.bottom = `${buttonRect.bottom - footerRect.top + 40}px`; // Adjust the 20px to your preference
    } else {
      scrollUpBtn.style.bottom = '20px'; // Original bottom value
    }
  });