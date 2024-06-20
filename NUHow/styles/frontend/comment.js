function autoResize() {
  const textarea = document.querySelector('.textarea-clone');
  textarea.style.height = 'auto'; // Reset the height to auto to get accurate scrollHeight
  textarea.style.height = `${textarea.scrollHeight}px`; // Set the height to the scrollHeight
  textarea.style.top = '-20px';

  textarea.style.boxShadow = '0px 7px 10px 1px rgba(174, 173, 173, 0.25)';

}

