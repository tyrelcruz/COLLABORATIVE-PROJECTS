export let submissions = JSON.parse(localStorage.getItem('submissions')) || [];

function saveToStorage(){
  localStorage.setItem('submissions', JSON.stringify(submissions));
}
export function submitPost(postId, author, title, message, theme, topic, time, profilePicture) {
  submissions.push({
    postId,
    author,
    title,
    message,
    theme,
    topic,
    time,
    profilePicture,
    comments: null
  });
  saveToStorage();
}

export function getSubmissionByPostId(postId){
  let foundSubmission;
  submissions.forEach((submission)=>{
    if(submission.postId == postId){
      foundSubmission = submission;
    }
  })
  return foundSubmission;
}
export function removeSubmissionByPostId(postId){
  submissions.forEach((submission)=>{
    if(submission.postId == postId){
      submissions.splice(submissions.indexOf(submission),1);
    }
  });
  saveToStorage();
}
