// import { featured } from "./data/featured.js";
import { formatTime } from "./utils/formatTime.js";
import { posts } from "./data/posts.js";
import { notAdmin } from "./data/admin.js";
import { submitPost } from "./data/submissions.js";
import dayjs from 'https://unpkg.com/dayjs@1.11.10/esm/index.js';
import { addEventListenerForThemes } from "./utils/addEventListenerThemes.js";
import { generatePostId } from "./data/posts.js";

notAdmin();

const featured = posts.filter(post => post.featured); // Posts that have featured set to true

function renderWall() {
  let featuredHTML = '';
  // add messages-found to below, if js-messages-found only, it clears out the entire div aside from the number of messages found
  document.querySelector('.js-messages-found .messages-found').innerHTML = `${featured.length} Messages Found`;

  if (featured.length == 0){
    //There's a bug wherein if there are no featured posts, the featured wall will display the posts from the normal wall.
    // This if-else check is a fix for that. It just displays "No posts have been selected to be featured as of the moment."
    featuredHTML += `<p>No posts have been selected to be featured as of the moment.</p>`;
    document.querySelector('.js-posts-list-container').innerHTML = featuredHTML;
  } else {
    featured.forEach((post)=>{
      featuredHTML += `
      <a href="comments.html?postId=${post.postId}"><div class="post-container js-post-container" data-post-id=${post.postId}>
        <div class="profile-container">
          <div class="profile-image-container">
            <img src="${post.profilePicture || 'images/bulldog.jpeg'}" alt="Profile Picture">
          </div>
          <div class="details-container">
            <p class="title">${post.title}</p>
            <p class="name">${post.author}</p>
            <p class="status">${formatTime(post)}</p>
          </div>
          <div class="topic-image-container">
            <div>
             <img src="${post.topic}">
            </div>
          </div>
        </div>
        <div class="message-container">
          <p style="background-color:${post.theme}">${post.message}</p>  
        </div>
      </div>
      </a>
    `;
    document.querySelector('.js-posts-list-container').innerHTML = featuredHTML;
  
    });
  }

  document.querySelectorAll('.js-post-container').forEach((container)=>{
    container.addEventListener('click',()=>{
      const {postId} = container.dataset;
      console.log(postId);
    })
  });  
}

// For filtering the posts via the search bar.
function filterPosts(filter, sort_by, search_value) {
  /* probably make everything lowercase for value in searching author? */
  let postsHTML = ``;
  let filtered = featured;

  const regex = new RegExp(search_value, 'i');      // Creates a case-insensitive regex object, that is based on the search_value
  const filter_regex = new RegExp(filter, 'i');

  // Filter sorting - Either All Posts or based on Topic (Technology, Literature...)
  if (!(filter === "all posts")) {
    filtered = filtered.filter(post => filter_regex.test(post.topic));
  }

  // Search by selected category for sort (Author, Message...), then searches for value inputted in search bar.
  if (!(sort_by === "none")){
    filtered = filtered.filter(post => regex.test(post[sort_by]));
  }
  
  document.querySelector('.js-messages-found .messages-found').innerHTML = `${filtered.length} Messages Found`;

  filtered.forEach((post) => {
    
    postsHTML += `
      <a href="comments.html?postId=${post.postId}"><div class="post-container js-post-container" data-post-id=${post.postId}>
        <div class="profile-container">
          <div class="profile-image-container">
            <img src="${post.profilePicture || 'images/bulldog.jpeg'}" alt="Profile Picture">
          </div>
          <div class="details-container">
            <p class="title">${post.title}</p>
            <p class="name">${post.author}</p>
            <p class="status">${formatTime(post)}</p>
          </div>
          <div class="topic-image-container">
            <div>
             <img src="${post.topic}">
            </div>
          </div>
        </div>
        <div class="message-container">
          <p style="background-color:${post.theme}">${post.message}</p>  
        </div>
      </div>
      </a>
    `;
  });

  document.querySelector('.js-posts-list-container').innerHTML = postsHTML;

  document.querySelectorAll('.js-post-container').forEach((container)=>{
    container.addEventListener('click',()=>{
      const {postId} = container.dataset;
      console.log(postId);
    })
  });
  
}



let theme;
let topic;
let profilePicture = 'images/bulldog.jpeg'

document.querySelector('.js-post-button').addEventListener('click', () => {
  const author = document.querySelector('.author-container input').value;
  const title = document.querySelector('.title-container input').value;
  const message = document.querySelector('.messager-container textarea').value;
  let time = dayjs();
  
  const hasError = validatePost(author, title, message);
  
  if (!hasError) {
    submitPost(generatePostId(), author, title, message, theme || 'rgb(99, 211, 130)', topic || 'images/technology.png', time, profilePicture);
    clearAddedPostInput();
    clearErrorStyles();
    wrapper.classList.remove('visible');
    floating.classList.remove('visible');
    renderWall();
  }
});


document.querySelector('#profile-picture-input').addEventListener('change', (event) => {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = function(e) {
    profilePicture = e.target.result;

    if (!profilePicture){
      profilePicture = 'images/bulldog.jpeg'
    }
    document.querySelector('#profile-picture-button').src = profilePicture;
  }

  if (file) {
    reader.readAsDataURL(file);
  }
});

// Trigger file input when the image is clicked
document.querySelector('#profile-picture-button').addEventListener('click', () => {
  document.querySelector('#profile-picture-input').click();
});


// THEME
addEventListenerForThemes(selectedTheme => {
  theme = selectedTheme;
});

// TOPIC
document.querySelector('.js-technology').addEventListener('click', (event) => {
  topic = event.target.getAttribute('src');
});
document.querySelector('.js-literature').addEventListener('click', (event) => {
  topic = event.target.getAttribute('src');
});
document.querySelector('.js-art').addEventListener('click', (event) => {
  topic = event.target.getAttribute('src');
});
document.querySelector('.js-politics').addEventListener('click', (event) => {
  topic = event.target.getAttribute('src');
});

const wrapper = document.querySelector('.wrapper');
const floating = document.querySelector('.write-message-container');

renderWall();

function clearAddedPostInput() {
  document.querySelector('.author-container input').value = '';
  document.querySelector('.title-container input').value = '';
  document.querySelector('.messager-container textarea').value = '';
  theme = undefined;
  topic = undefined;
  profilePicture = undefined;
};

function validatePost(author, title, message) {
  let hasError = false;

  if (!author) {
    displayAuthorError();
    hasError = true;
  }
  if (!title) {
    displayTitleError();
    hasError = true;
  }
  if (!message){
    displayMessageEmpty();
    hasError = true;
  } else if (message.length > 300){
    displayMessageMax();
    hasError = true;
  }

  return hasError;
}

function displayAuthorError() {
  document.querySelector('.write-message-container').classList.add('visible');
  document.querySelector('.wrapper').classList.add('visible');
  document.querySelector('.author-error').style.display = "flex";
  document.querySelector('.author-container input').classList.add('error');
  document.querySelector('.author-container div').style.color = "red";
}

function displayTitleError() {
  document.querySelector('.write-message-container').classList.add('visible');
  document.querySelector('.wrapper').classList.add('visible');
  document.querySelector('.title-error').style.display = "flex";
  document.querySelector('.title-container input').classList.add('error');
  document.querySelector('.title-container div').style.color = "red";
}

function displayMessageMax() {
  document.querySelector('.message-error-max').style.display = "flex";
  document.querySelector('.messager-container textarea').style.borderColor = "red";
  document.querySelector('.messager-container div').style.color = "red";
  document.querySelector('.message-error-none').style.display = "none";
}

function displayMessageEmpty() {
  document.querySelector('.message-error-none').style.display = "flex";
  document.querySelector('.messager-container textarea').style.borderColor = "red";
  document.querySelector('.messager-container div').style.color = "red";
  document.querySelector('.message-error-max').style.display = "none";
}

function clearErrorStyles() {
  document.querySelector('.author-error').style.display = "none";
  document.querySelector('.title-error').style.display = "none";
  document.querySelector('.message-error-none').style.display = "none";
  document.querySelector('.message-error-max').style.display = "none";

  document.querySelector('.author-container input').classList.remove('error');
  document.querySelector('.title-container input').classList.remove('error');
  document.querySelector('.messager-container textarea').classList.remove('error');

  document.querySelector('.author-container div').style.color = "";
  document.querySelector('.title-container div').style.color = "";
  document.querySelector('.messager-container div').style.color = "";
  document.querySelector('.messager-container textarea').style.borderColor = "";
}

// Adding event listeners to clear error styles when user starts typing
document.querySelector('.author-container input').addEventListener('input', () => {
  document.querySelector('.author-error').style.display = "none";
  document.querySelector('.author-container input').classList.remove('error');
  document.querySelector('.author-container div').style.color = "";
});

document.querySelector('.title-container input').addEventListener('input', () => {
  document.querySelector('.title-error').style.display = "none";
  document.querySelector('.title-container input').classList.remove('error');
  document.querySelector('.title-container div').style.color = "";
});

document.querySelector('.messager-container textarea').addEventListener('input', () => {
  document.querySelector('.message-error-none').style.display = "none";
  document.querySelector('.message-error-max').style.display = "none";
  document.querySelector('.messager-container textarea').style.borderColor = "";
  document.querySelector('.messager-container div').style.color = "";
});

document.getElementById('search-icon').addEventListener('click', () => {
  // input here to sort whether by topic, author, or message, or title?
  let filter = document.getElementById('filter-spantext').innerText.toLowerCase();
  let sort_by = document.getElementById('sort-spantext').innerText.toLowerCase();
  let value = document.querySelector('.search-bar').value;
  filterPosts(filter, sort_by, value);
});

// Function to toggle the visibility of the sublists
document.getElementById('filter-drop-text').addEventListener('click', () => {
  showSubList('filter-list');
});

document.getElementById('sort-drop-text').addEventListener('click', () => {
  showSubList('search-list');
});

function showSubList(subListId) {
  // Get all sublists and hide them
  const subMenus = document.querySelectorAll('.dropdown .dropdown-list');
  subMenus.forEach(subMenu => {
    subMenu.style.display = 'none';
  });

  // Toggle the visibility of the selected sublist
  const selectedSubMenu = document.getElementById(subListId);
  if (selectedSubMenu.style.display === 'none') {
    selectedSubMenu.style.display = 'block';
  } else {
    selectedSubMenu.style.display = 'none';
  }

  // Close the main dropdown list
  document.getElementById(subListId).classList.remove("show");
};

// Filter Dropdown
let filterDropdownBtn = document.getElementById("filter-drop-text");
let filterMainList = document.getElementById("filter-list");
let filterIcon = document.getElementById("filter-icon");
let filterSpan = document.getElementById("filter-spantext");

// Sort Dropdown
let sortDropdownBtn = document.getElementById("sort-drop-text");
let sortMainList = document.getElementById("search-list");
let sortIcon = document.getElementById("sort-icon");
let sortSpan = document.getElementById("sort-spantext");


// Show/hide the filter dropdown list
filterDropdownBtn.onclick = function() {
  if (filterMainList.classList.contains("show")) {
    filterIcon.style.rotate = "0deg";
  } else {
    filterIcon.style.rotate = "-180deg";
  }
  filterMainList.classList.toggle("show");
};

// Show/hide the sort dropdown list
sortDropdownBtn.onclick = function() {
  if (sortMainList.classList.contains("show")) {
    sortIcon.style.rotate = "0deg";
  } else {
    sortIcon.style.rotate = "-180deg";
  }
  sortMainList.classList.toggle("show");
};

// Hide the dropdown list is user clicks outside of it
window.onclick = function(e) {
  if (!e.target.closest('.dropdown-text')) {
    document.querySelectorAll('.dropdown-list').forEach(list => {
      list.classList.remove("show");
      list.style.display = 'none';
    });
    document.querySelectorAll('.fa-chevron-down').forEach(icon => {
      icon.style.rotate = "0deg";
    });
  }
};

// Handle clicking on filter dropdown items
filterMainList.querySelectorAll('.dropdown-list-item').forEach(item => {
  item.onclick = function(e) {
    if (!e.target.querySelector('ul')) {
      filterSpan.innerText = e.target.innerText;
    }
  }
});

// Handle clicking on sort dropdown items
sortMainList.querySelectorAll('.dropdown-list-item').forEach(item => {
  item.onclick = function(e) {
    if (!e.target.querySelector('ul')) {
      sortSpan.innerText = e.target.innerText;
    }
  }
});