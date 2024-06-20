export const accounts = [
  { 
    email: 'curatp@students.national-u.edu.ph', password: 'tristancura', name: 'Tristan Cura', role: 'UI/UX', image:'images/tristan-img.jpg' }, 
  { 
    email: 'cruztp@students.national-u.edu.ph', password: 'tyrelcruz', name: 'Tyrel Cruz', role: 'Front-End', image:'images/tyrel-img.jpg'},
  { 
    email: 'davidnm@students.national-u.edu.ph', password: 'neodavid', name: 'Neo David', role: 'Front-End', image:'images/neo-img.jpg'},
  { 
    email: 'rapirg@students.national-u.edu.ph', password: 'russelrapi', name: 'Russel Rapi', role: 'Front-End', image:'images/russel-img.jpg'},
  { 
    email: 'admin@admin.admin', password: 'admin12345', name: 'Admin', role: 'Admin', image:'images/profile-pic.jpg'
  }
];

export let currentAccount = '' || JSON.parse(localStorage.getItem('currentAccount'));

function saveToStorage(){
  localStorage.setItem('currentAccount', JSON.stringify(currentAccount));
};

export function logInAccount(account){
  currentAccount = account;
  saveToStorage();
}

function saveAdmin(){
  localStorage.setItem('isAdmin', JSON.stringify(isAdmin));
};

export let isAdmin = false || JSON.parse(localStorage.getItem('isAdmin')) ;

export function notAdmin(){
  isAdmin = false;
  saveAdmin();
}

export function adminLogged(){
  isAdmin = true;
  saveAdmin();
}


