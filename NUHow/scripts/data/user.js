export let user = JSON.parse(localStorage.getItem('users')) || [{
  userId: 1,
  name: 'Neo'
}];

export function saveToStorage(){
  localStorage.setItem(JSON.stringify('comments'));
}