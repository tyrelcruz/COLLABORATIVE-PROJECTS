import { formatTime } from "./utils/formatTime.js";
import { submissions,getSubmissionByPostId,removeSubmissionByPostId} from "./data/submissions.js";
import { addPost, getPostById} from "./data/posts.js";
//import { featurePost,featured } from "./data/featured.js";
import { posts, setFeatureToPost, deletePostByPostId } from "./data/posts.js";
import { currentAccount } from "./data/admin.js";
import { adminLogged } from "./data/admin.js";

let wallPosts = posts.filter(post => !post.featured); // Posts that have featured set to false
let featuredPosts = posts.filter(post => post.featured); // Posts that have featured set to true

let currentlyLoggedData = [];

adminLogged();
renderAdminWall();

function renderAdminWall() {

  console.log(currentAccount);

  document.querySelector('header img').src = currentAccount.image;

  document.querySelector('.profile-container .name').innerHTML = currentAccount.name;

  document.querySelector('.profile-container .role').innerHTML = currentAccount.role;
  document.querySelector('.profile-container img').src = currentAccount.image;

  // renderSubmissionInWall(submissions);
  renderWall(submissions, true);

  document.querySelectorAll('.js-post-container').forEach((container)=>{
    container.addEventListener('click',()=>{
      const {postId} = container.dataset;
    })
  });
  
  document.querySelector('.js-nav-wall').addEventListener('click',()=>{
    document.querySelector('.js-title-top').innerHTML='Wall';
    // renderNonSubmissionsInWall(wallPosts);
    renderWall(wallPosts, false);
  });
  document.querySelector('.js-nav-submissions').addEventListener('click',()=>{
    document.querySelector('.js-title-top').innerHTML='Submissions';
    // renderSubmissionInWall(submissions);
    renderWall(submissions, true);
  });
  document.querySelector('.js-nav-featured').addEventListener('click',()=>{
    document.querySelector('.js-title-top').innerHTML='Featured';
    // renderNonSubmissionsInWall(featuredPosts);
    renderWall(featuredPosts, false);
  });

}

function renderWall(data, isSubmission) {
  console.log(data);

  let adminWallHTML = ``;
  document.querySelector('.messages-found').innerHTML = `${data.length} Messages Found`;

  data.slice().reverse().forEach((post) => {
      adminWallHTML += `
          <div class="post-container js-post-container" data-post-id=${post.postId}>
              <div class="action-container">
                  ${isSubmission ? `
                      <div class="approve" data-post-id=${post.postId}>Approve</div>
                      <div class="reject" data-post-id=${post.postId}>Reject</div>
                      <div class="feature" data-post-id=${post.postId}>Feature</div>
                  ` : `
                      <div class="delete" data-post-id=${post.postId}>Delete</div>
                      <div class="set-feature" data-post-id=${post.postId}>Feature/Unfeature</div>
                  `}
              </div>
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
              ${isSubmission ? `
                  <div class="message-container">
                      <p style="background-color:${post.theme}">${post.message}</p>
                  </div>
              ` : `
                  <a href="comments.html?postId=${post.postId}" class="message-container">
                      <p style="background-color:${post.theme}">${post.message}</p>
                  </a>
              `}
              ${isSubmission ? `
                  <div class="approve-container" style="display: none;">
                      <div class="confirm-message">
                          <img class="confirm-image" src="images/check-mark.png">
                          <p class="thank-you">Approve post?</p>
                          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                      </div>
                      <div class="confirm-choices-container">
                          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                          <div class="cancel">Cancel</div>
                      </div>
                  </div>
                  <div class="reject-container" style="display: none;">
                      <div class="confirm-message">
                          <img class="confirm-image" src="images/bin.png">
                          <p class="thank-you">Reject post?</p>
                          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                      </div>
                      <div class="confirm-choices-container">
                          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                          <div class="cancel">Cancel</div>
                      </div>
                  </div>
                  <div class="feature-container" style="display: none;">
                      <div class="confirm-message">
                          <img class="confirm-image" src="images/star.png">
                          <p class="thank-you">Feature post?</p>
                          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                      </div>
                      <div class="confirm-choices-container">
                          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                          <div class="cancel">Cancel</div>
                      </div>
                  </div>
              ` : `
                  <div class="delete-container" style="display: none;">
                      <div class="confirm-message">
                          <img class="confirm-image" src="images/bin.png">
                          <p class="thank-you">Delete post?</p>
                          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                      </div>
                      <div class="confirm-choices-container">
                          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                          <div class="cancel">Cancel</div>
                      </div>
                  </div>
                  <div class="setFeature-container" style="display: none;">
                      <div class="confirm-message">
                          <img class="confirm-image" src="images/star.png">
                          <p class="thank-you">Change the feature status of this post?</p>
                          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                      </div>
                      <div class="confirm-choices-container">
                          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                          <div class="cancel">Cancel</div>
                      </div>
                  </div>
              `}
          </div>
      `;
  });

  currentlyLoggedData = data;
  document.querySelector('.js-posts-list-container').innerHTML = adminWallHTML;
  addEventListenerForAdminChoices();
}

function refreshWall(currentWall){
  document.querySelector('.js-posts-list-container').innerHTML == "";
  wallPosts = posts.filter(post => !post.featured);
  featuredPosts = posts.filter(post => post.featured);

  let adminWallHTML = ``;

  const generatePostsHTML = (post) => `
    <div class="post-container js-post-container" data-post-id=${post.postId}>
      <div class="action-container">
        <div class="delete" data-post-id=${post.postId}>Delete</div>
        <div class="set-feature" data-post-id=${post.postId}>Feature/Unfeature</div>
      </div>
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
      <div class="approve-container" style="display: none;">
        <div class="confirm-message">
          <img class="confirm-image" src="images/check-mark.png">
          <p class="thank-you">Approve post?</p>
          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
        </div>
        <div class="confirm-choices-container">
          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
          <div class="cancel">Cancel</div>
        </div>
      </div>
      <div class="reject-container" style="display: none;">
        <div class="confirm-message">
          <img class="confirm-image" src="images/bin.png">
          <p class="thank-you">Reject post?</p>
          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
        </div>
        <div class="confirm-choices-container">
          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
          <div class="cancel">Cancel</div>
        </div>
      </div>
      <div class="feature-container" style="display: none;">
        <div class="confirm-message">
          <img class="confirm-image" src="images/star.png">
          <p class="thank-you">Feature post?</p>
          <p class="confirm-details">Once you confirm this, you cannot undo.</p>
        </div>
        <div class="confirm-choices-container">
          <div class="confirm" data-post-id=${post.postId}>Confirm</div>
          <div class="cancel">Cancel</div>
        </div>
      </div>
    </div>
  `;
  const postsToRender = currentWall === 'Wall' ? wallPosts : featuredPosts;
  adminWallHTML = postsToRender.slice().reverse().map(generatePostsHTML).join('');

  document.querySelector('.js-posts-list-container').innerHTML = adminWallHTML;
  addEventListenerForAdminChoices();

}

function addEventListenerForAdminChoices() {
  document.querySelectorAll('.approve').forEach((approveButton) => {
    approveButton.addEventListener('click', () => {
      const { postId } = approveButton.dataset;
      const approveContainer = approveButton.closest('.post-container').querySelector('.approve-container');
      approveContainer.style = "display: flex";

      approveContainer.querySelector('.confirm').addEventListener('click', () => {
        const submission = getSubmissionByPostId(postId);
        
        addPost(submission.postId, submission.author, submission.title, submission.message, submission.theme || 'rgb(99, 211, 130)', submission.topic || 'images/technology.png', submission.time, submission.profilePicture, false);
        removeSubmissionByPostId(submission.postId);
        renderAdminWall();
        
      });

      approveContainer.querySelector('.cancel').addEventListener('click', () => {
        approveContainer.style = "display: none";
      });
    });
  });

  document.querySelectorAll('.reject').forEach((rejectButton) => {
    rejectButton.addEventListener('click', () => {
      const { postId } = rejectButton.dataset;
      const rejectContainer = rejectButton.closest('.post-container').querySelector('.reject-container');
      rejectContainer.style = "display: flex";

      rejectContainer.querySelector('.confirm').addEventListener('click', () => {
        removeSubmissionByPostId(postId);
        renderAdminWall();
      });

      rejectContainer.querySelector('.cancel').addEventListener('click', () => {
        rejectContainer.style = "display: none";
      });
    });
  });

  document.querySelectorAll('.feature').forEach((featureButton) => {
    featureButton.addEventListener('click', () => {
      const { postId } = featureButton.dataset;
      const featureContainer = featureButton.closest('.post-container').querySelector('.feature-container');
      featureContainer.style = "display: flex";

      featureContainer.querySelector('.confirm').addEventListener('click', () => {
        const featured = getSubmissionByPostId(postId);
        addPost(featured.postId, featured.author, featured.title, featured.message, featured.theme || 'rgb(99, 211, 130)', featured.topic || 'images/technology.png', featured.time, featured.profilePicture, true);
        removeSubmissionByPostId(featured.postId);
        renderAdminWall();
      });

      featureContainer.querySelector('.cancel').addEventListener('click', () => {
        featureContainer.style = "display: none";
      });
    });
  });

  document.querySelectorAll('.delete').forEach((deleteButton) => {
    deleteButton.addEventListener('click', () => {
      const { postId } = deleteButton.dataset;
      const deleteContainer = deleteButton.closest('.post-container').querySelector('.delete-container');
      deleteContainer.style = "display: flex";

      deleteContainer.querySelector('.confirm').addEventListener('click', () => {
        // removeSubmissionByPostId(postId);
        deletePostByPostId(postId);
        // renderAdminWall();
        refreshWall(document.querySelector('.js-title-top').innerHTML);
      });

      deleteContainer.querySelector('.cancel').addEventListener('click', () => {
        deleteContainer.style = "display: none";
      });
    });
  });

  document.querySelectorAll('.set-feature').forEach((setFeatureButton) => {
    setFeatureButton.addEventListener('click', () => {
      const { postId } = setFeatureButton.dataset;
      const setFeatureContainer = setFeatureButton.closest('.post-container').querySelector('.setFeature-container');
      setFeatureContainer.style = "display: flex";

      setFeatureContainer.querySelector('.confirm').addEventListener('click', () => {
        // removeSubmissionByPostId(postId);
        setFeatureToPost(postId);
        // renderAdminWall();
        refreshWall(document.querySelector('.js-title-top').innerHTML);
      });

      setFeatureContainer.querySelector('.cancel').addEventListener('click', () => {
        setFeatureContainer.style = "display: none";
      });
    });
  });
}

document.querySelector('.admin-searchbar').addEventListener('keypress', function(event) {
  if (event.key === "Enter"){
    let value = document.querySelector('.admin-searchbar').value;
    filterPosts(value);
  }
});

// Handling the filtering of the wall based on search.
function filterPosts(value){
  let wallPosts = currentlyLoggedData;
  let filtered = [];

  const regex = new RegExp(value, 'i'); 
  filtered = wallPosts.filter(post => regex.test(post.author) || regex.test(post.title) || regex.test(post.message));
  renderFilteredWall(filtered);
}

function renderFilteredWall(data) {
  let currentWall = document.querySelector('.js-title-top').innerText;

  let isSubmission = currentWall === 'Submissions';
  
  let adminWallHTML = ``;
  document.querySelector('.messages-found').innerHTML = `${data.length} Messages Found`;
  data.slice().reverse().forEach((post) => {
    adminWallHTML += `
        <div class="post-container js-post-container" data-post-id=${post.postId}>
            <div class="action-container">
                ${isSubmission ? `
                    <div class="approve" data-post-id=${post.postId}>Approve</div>
                    <div class="reject" data-post-id=${post.postId}>Reject</div>
                    <div class="feature" data-post-id=${post.postId}>Feature</div>
                ` : `
                    <div class="delete" data-post-id=${post.postId}>Delete</div>
                    <div class="set-feature" data-post-id=${post.postId}>Feature/Unfeature</div>
                `}
            </div>
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
            ${isSubmission ? `
                <div class="message-container">
                    <p style="background-color:${post.theme}">${post.message}</p>
                </div>
            ` : `
                <a href="comments.html?postId=${post.postId}" class="message-container">
                    <p style="background-color:${post.theme}">${post.message}</p>
                </a>
            `}
            ${isSubmission ? `
                <div class="approve-container" style="display: none;">
                    <div class="confirm-message">
                        <img class="confirm-image" src="images/check-mark.png">
                        <p class="thank-you">Approve post?</p>
                        <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                    </div>
                    <div class="confirm-choices-container">
                        <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                        <div class="cancel">Cancel</div>
                    </div>
                </div>
                <div class="reject-container" style="display: none;">
                    <div class="confirm-message">
                        <img class="confirm-image" src="images/bin.png">
                        <p class="thank-you">Reject post?</p>
                        <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                    </div>
                    <div class="confirm-choices-container">
                        <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                        <div class="cancel">Cancel</div>
                    </div>
                </div>
                <div class="feature-container" style="display: none;">
                    <div class="confirm-message">
                        <img class="confirm-image" src="images/star.png">
                        <p class="thank-you">Feature post?</p>
                        <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                    </div>
                    <div class="confirm-choices-container">
                        <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                        <div class="cancel">Cancel</div>
                    </div>
                </div>
            ` : `
                <div class="delete-container" style="display: none;">
                    <div class="confirm-message">
                        <img class="confirm-image" src="images/bin.png">
                        <p class="thank-you">Delete post?</p>
                        <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                    </div>
                    <div class="confirm-choices-container">
                        <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                        <div class="cancel">Cancel</div>
                    </div>
                </div>
                <div class="setFeature-container" style="display: none;">
                    <div class="confirm-message">
                        <img class="confirm-image" src="images/star.png">
                        <p class="thank-you">Change the feature status of this post?</p>
                        <p class="confirm-details">Once you confirm this, you cannot undo.</p>
                    </div>
                    <div class="confirm-choices-container">
                        <div class="confirm" data-post-id=${post.postId}>Confirm</div>
                        <div class="cancel">Cancel</div>
                    </div>
                </div>
            `}
          </div>
    `;
  });
  // currentlyLoggedData = data;
  document.querySelector('.js-posts-list-container').innerHTML = adminWallHTML;
  addEventListenerForAdminChoices();
}