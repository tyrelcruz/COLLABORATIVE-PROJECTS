let ChangeIcon =function(icon) {
    icon.classList.toggle("fa-eye");
    icon.classList.toggle("fa-eye-slash");
    Toggle();
}

function Toggle() {
    let temp = document.getElementById("password");
     
    if (temp.type === "password") {
        temp.type = "text";
    }
    else {
        temp.type = "password";
    }
}