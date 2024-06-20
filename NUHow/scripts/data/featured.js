export let featured = JSON.parse(localStorage.getItem('featured')) || [{
  postId: '22',
  author: 'ANONYMOUS USER',
  title: 'NU Exchange',
  message: 'Kailan po ang restock ng NU uniforms?',
  theme: 'rgb(99, 211, 130)',
  topic: 'images/technology.png',
  time: 'June 1, 2024',
  profilePicture: null,
  comments: [1, 2]
}];

export function saveToStorage(){
  localStorage.setItem('featured', JSON.stringify(featured));
}

export function featurePost(postId, author, title, message, theme, topic, time, profilePicture) {
  featured.push({
    postId,
    author,
    title,
    message,
    theme,
    topic,
    time,
    profilePicture,
    comments: [1, 2,3] 
  });
  saveToStorage();
}