document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('nav ul li a:not(:only-child)').forEach(link => {
    link.addEventListener('click', (e) => {
      const dropdown = link.nextElementSibling;
      if (dropdown && dropdown.classList.contains('nav-dropdown')) {
        dropdown.classList.toggle('show');
      }

      document.querySelectorAll('.nav-dropdown').forEach(d => {
        if (d !== dropdown) d.classList.remove('show');
      });

      e.stopPropagation();
    });
  });

  document.addEventListener('click', () => {
    document.querySelectorAll('.nav-dropdown').forEach(d => d.classList.remove('show'));
  });

  const navToggle = document.getElementById('nav-toggle');
  const navList = document.querySelector('nav ul');

  if (navToggle && navList) {
    navToggle.addEventListener('click', () => {
      navToggle.classList.toggle('active');
      navList.classList.toggle('open');
    });
  }

  let lastScrollTop = 0;
  const scrollThreshold = 5;

  window.addEventListener('scroll', () => {
    if (window.innerWidth <= 1024) {
      const scrollTop = window.scrollY;

      if (scrollTop > lastScrollTop + scrollThreshold && scrollTop > 100) {
        document.querySelector('.navigation')?.classList.add('nav-hidden');
      } else if (scrollTop < lastScrollTop - scrollThreshold) {
        document.querySelector('.navigation')?.classList.remove('nav-hidden');
      }

      lastScrollTop = scrollTop;
    } else {
      document.querySelector('.navigation')?.classList.remove('nav-hidden');
    }
  });
});
