document.addEventListener('DOMContentLoaded', (event) => {

    const currentPageUrl = window.location.href;
  
    const inboxLink = document.getElementById('inboxpage-link');
    const featuredLink = document.getElementById('featuredpage-link');
    const aboutLink = document.getElementById('aboutpage-link');
    const contactLink = document.getElementById('contactpage-link');
  
    const isActivePage = (link) => {
      return currentPageUrl.includes(link.getAttribute('href'));
    };
  
    if (isActivePage(inboxLink)) {
      inboxLink.classList.add('active');
    } else if (isActivePage(featuredLink)) {
      featuredLink.classList.add('active');
    } else if (isActivePage(aboutLink)) {
      aboutLink.classList.add('active');
    } else if (isActivePage(contactLink)) {
      contactLink.classList.add('active');
    }
  });
  