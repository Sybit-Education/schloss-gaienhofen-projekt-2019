$(document).ready(function(){
  var p = document.getElementById("password");
  var p2 = document.getElementById("confirmPassword");

  var $form = $('#register_form');
  var $error = $('#error');

  $form.submit(function(e) {
    if (p.value !== p2.value) {
      e.preventDefault();
      $error.stop().fadeIn(200).delay(2000).fadeOut(200);
    }
  });
});
