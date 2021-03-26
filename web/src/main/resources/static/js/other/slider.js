$(document).ready(function() {

    const $valueSpan = $('.sliderValue');
    const $value = $('#maxFileSize');
    $valueSpan.html($value.val() + "MB");
    $value.on('input change', () => {

        $valueSpan.html($value.val() + "MB");
    });
});