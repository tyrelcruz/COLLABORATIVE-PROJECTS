$(document).ready(function() {

    $('textarea').keyup(function() {

        var characterCount = $(this).val().length,
            current = $('#current'),
            maximum = $('#maximum'),
            theCount = $('#the-count');

        console.log("Character count: " + characterCount);

        current.text(characterCount);

        if (characterCount < 200) {
            current.css('color', '#666');
        } else if (characterCount > 200 && characterCount < 1000) {
            current.css('color', '#6d5555');
        } else if (characterCount > 1000 && characterCount < 1500) {
            current.css('color', '#793535');
        } else if (characterCount > 1500 && characterCount < 2000) {
            current.css('color', '#841c1c');
        } else if (characterCount > 2000 && characterCount < 2800) {
            current.css('color', '#8f0001');
        }

        if (characterCount >= 2800) {
            maximum.css('color', '#8f0001');
            current.css('color', '#8f0001');
            theCount.css('font-weight', 'bold');
        } else {
            maximum.css('color', '#666');
            theCount.css('font-weight', 'normal');
        }
    });
});
