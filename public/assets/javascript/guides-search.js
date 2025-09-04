/**
 * Client-side search functionality for WildFly guides
 * Filters guides based on keywords matching
 */
(() => {
  'use strict';

  let searchInput;
  let guides;
  let categories;
  let debounceTimer;

  const initializeSearch = () => {
    searchInput = document.getElementById('guides-search');
    guides = document.querySelectorAll('[data-guide-title]');
    categories = document.querySelectorAll('.guide-category');

    if (!searchInput) {
      console.warn('Guides search input not found');
      return;
    }

    searchInput.addEventListener('input', handleSearchInput);

    const clearButton = document.getElementById('guides-search-clear');
    if (clearButton) {
      clearButton.addEventListener('click', clearSearch);
    }

    searchInput.addEventListener('keydown', (e) => {
      if (e.key === 'Escape') {
        clearSearch();
      }
    });
  };

  const handleSearchInput = (event) => {
    const searchTerm = event.target.value.trim();

    clearTimeout(debounceTimer);

    debounceTimer = setTimeout(() => {
      filterGuides(searchTerm);
    }, 150);
  };

  const filterGuides = (searchTerm) => {
    const normalizedSearchTerm = searchTerm.toLowerCase();
    let visibleGuidesCount = 0;
    let visibleCategoriesCount = 0;

    if (!normalizedSearchTerm) {
      showAllGuides();
      updateClearButtonVisibility(false);
      return;
    }

    // Filter each guide from the keywords
    guides.forEach((guide) => {
      const guideKeywords = (guide.getAttribute('data-guide-keywords') || '').toLowerCase();

      // Create a version of keywords where hyphens are replaced with spaces for flexible matching
      const keywordsWithSpaces = guideKeywords.replace(/-/g, ' ');
      const combinedKeywords = `${guideKeywords} ${keywordsWithSpaces}`;

      // Split search term into individual words and filter out empty strings
      const searchWords = normalizedSearchTerm.split(/\s+/).filter(word => word.length > 0);

      // Check if ALL search words are found in the keywords
      // This supports both single words, multi-word keywords, and hyphen-to-space matching
      const isVisible = searchWords.every(word => combinedKeywords.includes(word));

      if (isVisible) {
        guide.classList.remove('guide-hidden');
        guide.classList.add('guide-visible');
        visibleGuidesCount++;
      } else {
        guide.classList.add('guide-hidden');
        guide.classList.remove('guide-visible');
      }
    });

    // Filter categories (hide empty categories but preserve structure)
    categories.forEach((category) => {
      let guidesInCategory = [];

      // Collect all guide elements that belong to this category
      let currentElement = category.nextElementSibling;
      while (currentElement && !currentElement.classList.contains('guide-category')) {
        if (currentElement.hasAttribute('data-guide-title')) {
          guidesInCategory.push(currentElement);
        }
        currentElement = currentElement.nextElementSibling;
      }

      // Check if any guides in this category are visible
      const visibleGuidesInCategory = guidesInCategory.filter(guide =>
        !guide.classList.contains('guide-hidden')
      ).length;

      if (visibleGuidesInCategory > 0) {
        category.classList.remove('category-hidden');
        visibleCategoriesCount++;
      } else {
        category.classList.add('category-hidden');
      }
    });

    // Show/hide no results message
    showNoResultsMessage(visibleGuidesCount === 0);
    updateClearButtonVisibility(true);

    // Update search results count (optional)
    updateSearchResultsCount(visibleGuidesCount);
  };

  const showAllGuides = () => {
    guides.forEach((guide) => {
      guide.classList.remove('guide-hidden');
      guide.classList.remove('guide-visible');
    });

    categories.forEach((category) => {
      category.classList.remove('category-hidden');
    });

    showNoResultsMessage(false);
    updateSearchResultsCount(guides.length);
  };

  const clearSearch = () => {
    searchInput.value = '';
    showAllGuides();
    updateClearButtonVisibility(false);
    searchInput.focus();
  };

  const showNoResultsMessage = (show) => {
    let noResultsElement = document.getElementById('guides-no-results');

    if (show && !noResultsElement) {
      noResultsElement = document.createElement('div');
      noResultsElement.id = 'guides-no-results';
      noResultsElement.className = 'guides-no-results';
      noResultsElement.innerHTML = '<p>No guides found matching your search.</p>';

      const guidesContainer = document.querySelector('.guides .grid-wrapper');
      if (guidesContainer) {
        guidesContainer.appendChild(noResultsElement);
      }
    } else if (!show && noResultsElement) {
      noResultsElement.remove();
    }
  };

  const updateClearButtonVisibility = (show) => {
    const clearButton = document.getElementById('guides-search-clear');
    if (clearButton) {
      clearButton.style.display = show ? 'inline-block' : 'none';
    }
  };

  const updateSearchResultsCount = (count) => {
    const countElement = document.getElementById('guides-search-count');
    if (countElement) {
      if (searchInput.value.trim()) {
        countElement.textContent = `${count} guide${count !== 1 ? 's' : ''} found`;
        countElement.style.display = 'block';
      } else {
        countElement.style.display = 'none';
      }
    }
  };

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initializeSearch);
  } else {
    initializeSearch();
  }

})();