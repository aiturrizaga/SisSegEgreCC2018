$(document).ready(function () {
    $(".button-collapse").sideNav();
});

$('.menu-bar').on('click', function(){
	$('.contenido').toggleClass('main');
	$('.side-nav').toggleClass('fixed');
});