export let comments = JSON.parse(localStorage.getItem('comments')) || [];

export function saveToStorage(){
  localStorage.setItem('comments', JSON.stringify(comments));
}

export function newComment(commentId, userId, comment, theme){
  comments.push({
    commentId,
    userId,
    comment,
    theme
  })
  saveToStorage();
};