import { accounts, logInAccount } from "./data/admin.js";
import { notAdmin } from "./data/admin.js";

notAdmin();

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    console.log('Form submitted');

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    console.log('Email:', email);
    console.log('Password:', password);

    const user = accounts.find(login => login.email === email);
    
    console.log(user);
    if (user) {
        if (user.password === password) {
            logInAccount(user);
            window.location.href = 'admin-wall.html';
        } else {
            alert('Invalid password. Please try again.');
        }
    } else {
        alert('Invalid email. Please try again.');
    }
});
