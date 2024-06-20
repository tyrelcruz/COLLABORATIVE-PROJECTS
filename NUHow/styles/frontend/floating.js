
const wrapper = document.querySelector('.wrapper');
const floating = document.querySelector('.write-message-container');
console.log(floating);

document.querySelector('.js-write-button').addEventListener('click',()=>{
  wrapper.classList.add('visible');
  floating.classList.add('visible');
});

document.querySelector('.exit-button').addEventListener('click',()=>{
  wrapper.classList.remove('visible');
  floating.classList.remove('visible');

  // Remove error display styles
  document.querySelector('.author-error').style.display = "none";
  document.querySelector('.title-error').style.display = "none";
  document.querySelector('.author-container input').classList.remove('error');
  document.querySelector('.title-container input').classList.remove('error');
  document.querySelector('.author-container div').style.color = "initial";
  document.querySelector('.title-container div').style.color = "initial";
});

// Select theme elements and add event listeners for theme selection
document.querySelectorAll('.themes-choices-container div').forEach((theme) => {
  theme.addEventListener('click', (event) => {
    // Remove the black border from all themes
    document.querySelectorAll('.themes-choices-container div').forEach((t) => {
      t.style.border = 'none';
    });
    // Add the black border to the selected theme
    event.currentTarget.style.border = '1.5px solid black';
  });
});

// Select topic elements and add event listeners for topic selection
document.querySelectorAll('.topics-choices-container img').forEach((topic) => {
  topic.addEventListener('click', (event) => {
    // Reset the opacity and grayscale filter of all topics
    document.querySelectorAll('.topics-choices-container img').forEach((t) => {
      t.style.opacity = '0.80';
      t.style.filter = 'grayscale(100%)';
    });
    // Set the opacity of the selected topic to 100% and remove the grayscale filter
    event.currentTarget.style.opacity = '1';
    event.currentTarget.style.filter = 'none';
  });
});

