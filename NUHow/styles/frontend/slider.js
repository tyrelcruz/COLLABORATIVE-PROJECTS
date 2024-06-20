const menu = document.querySelector('.header .menu');
const slider = document.querySelector('.slider');

menu.addEventListener('click', () => {
  if (slider.classList.contains('show')) {
    slider.classList.remove('show');
    setTimeout(() => {
      slider.style.display = 'none';
    }, 500); // Wait for the transition to complete before setting display to none
  } else {
    slider.style.display = 'flex';
    setTimeout(() => {
      slider.classList.add('show');
    }, 10); // Slight delay to ensure transition is applied
  }
  
  menu.classList.toggle('active');
});
