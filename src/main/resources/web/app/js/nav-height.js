// Exposes the rendered height of the sticky navigation as --nav-height, used to offset anchor targets
document.addEventListener('DOMContentLoaded', () => {
  const navigation = document.querySelector('.navigation');
  if (!navigation) return;

  new ResizeObserver(() => {
    document.documentElement.style.setProperty('--nav-height', `${navigation.offsetHeight}px`);
  }).observe(navigation);
});
