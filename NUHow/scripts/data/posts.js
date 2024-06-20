export let posts = JSON.parse(localStorage.getItem('posts')) || [{
  postId: '201',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'NUIS',
  message: 'Hello may di po ako na-OTE na dalawang subject. Ano po gagawin uli pag di nakapag-OTE na complete huhu😢',
  theme: 'rgb(27, 49, 91)',
  topic: 'images/technology.png',
  time: 'June 18, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
}, {
  postId: '202',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'NU MOA',
  message: 'Hello! Mag aask lang if may shs po ba sa nu moa?',
  theme: 'rgb(216, 198, 35)',
  topic: 'images/technology.png',
  time: 'June 19, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
}, {
  postId: '203',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'Accountancy uniform',
  message: 'GOOD DAYYY! hm kaya yung uniform pang-accountancy',
  theme: 'rgb(67, 177, 98)',
  topic: 'images/technology.png',
  time: 'June 20, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
}, {
  postId: '204',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'OTE Reminder',
  message: 'This is a gentle reminder that TODAY (June 19, 2024) is the last day for the OTE. Failure to do so may result in restrictions on certain applications within NUIS.',
  theme: 'rgb(211, 99, 99)',
  topic: 'images/technology.png',
  time: 'June 20, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
}, {
  postId: '205',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'NSTP department',
  message: 'Hello bulldogs, saan na floor yung NSTP department? Tysia!🫶🫶',
  theme: 'rgb(211, 99, 99)',
  topic: 'images/technology.png',
  time: 'June 20, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
},{
  postId: '265',
  author: 'ANONYMOUS PARTICIPANT',
  title: 'biochem',
  message: 'hello po! ano po pwede ma-expect sa biochem? ano pong mahirap na part sa biochem?',
  theme: 'rgb(216, 198, 35))',
  topic: 'images/literature.png',
  time: 'June 20, 2024',
  profilePicture: 'images/bulldog.jpeg',
  comments: [],
  featured: false // By default, posts are not featured unless inputted otherwise
}];

export function saveToStorage() {
  localStorage.setItem('posts', JSON.stringify(posts));
}

export function addPost(postId, author, title, message, theme, topic, time, profilePicture, featured) {
  posts.push({
    postId,
    author,
    title,
    message,
    theme,
    topic,
    time,
    profilePicture,
    comments: [1,2],
    featured
  });
  saveToStorage();
}

export function getPostById(id){
  let matchingPost;
  console.log(posts);
  posts.forEach((post) => {
    if (post.postId == id) {
      matchingPost = post;
    }
  });
  return matchingPost;
}
export function addCommentToPost(postId, commentId){
  posts.forEach((post)=>{
    if(post.postId === postId){
      post.comments.push(commentId);
    }
  })
  saveToStorage();
};

// For deleting posts
export function deletePostByPostId(postId){
  // Dealing with modifying the length of the array, it's apparently recommended to iterate over the array backwards.
  for (let i = posts.length - 1; i >= 0; i--) {
    if (posts[i].postId == postId) {
      posts.splice(i, 1);
    }
  }
  saveToStorage();
}

// For changing the feature value of the post into the opposite of its inital value.
export function setFeatureToPost(postId){
  posts.forEach((post)=>{
    if(post.postId == postId){
      post.featured = !post.featured;
    }
  });
  saveToStorage();
}

let postId = JSON.parse(localStorage.getItem('postId')) || 0;

export function generatePostId(){
  postId++;
  localStorage.setItem('postId', JSON.stringify(postId));
  return postId;
};

export function deleteComment(matchingPost, commentId) {
  const commentIndex = matchingPost.comments.indexOf(Number(commentId));
  if (commentIndex !== -1) {
    matchingPost.comments.splice(commentIndex, 1);
    saveToStorage();
  }
}
