function validateAndSendMail() {
    var name = document.getElementById('name').value.trim();
    var email = document.getElementById('email').value.trim();
    var message = document.getElementById('message').value.trim();

    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (name === '' || email === '' || message === '') {
        alert('Please fill in all the required fields.');
        return false;
    }

    if (!emailPattern.test(email)) {
        alert('Please enter a valid email address.');
        return false;
    }

    sendMail();
}

function sendMail() {
        var link = "mailto:NUHowContact@example.com"
                 + "?cc=AdminNUHow@CCexample.com"
                 + "&subject=" + encodeURIComponent(document.getElementById('name').value + "'s Question")
                 + "&body=" + encodeURIComponent(document.getElementById('message').value);
    
        var a = document.createElement('a');
        a.href = link;
        a.target = '_blank';
    
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    
}
