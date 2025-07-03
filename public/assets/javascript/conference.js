/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */

(() => {
  "use strict";

  window.addEventListener("load", () => {
    const supportsPopover = HTMLElement.prototype.hasOwnProperty("popover");
    if (supportsPopover) {
      const timeFormat = new Intl.DateTimeFormat(navigator.language, {
        hour: "numeric",
        minute: "numeric"
      });
      const date = document.querySelector("#c-date").dataset["date"];
      const timeElements = document.querySelectorAll(".c-time");
      timeElements.forEach(timeElement => {
        const from = new Date(date + "T" + timeElement.querySelector(".c-from").textContent + "Z");
        const to = new Date(date + "T" + timeElement.querySelector(".c-to").textContent + "Z");
        const localTime = timeFormat.format(from) + " - " + timeFormat.format(to);
        const popover = document.createElement("div");
        popover.classList.add("c-local-time")
        popover.textContent = localTime;
        popover.popover = "auto";
        popover.addEventListener("toggle", (event) => {
          if (event.newState === 'open') {
            popover.style.left = timeElement.getBoundingClientRect().left + "px";
            popover.style.top = timeElement.getBoundingClientRect().top + "px";
            popover.style.width = timeElement.getBoundingClientRect().width + "px";
          }
        });
        timeElement.appendChild(popover);
        timeElement.addEventListener("click", () => popover.togglePopover());
      });

      let ticking = false;
      document.addEventListener("scroll", () => {
        if (!ticking) {
          window.requestAnimationFrame(() => {
            document.querySelectorAll(".c-local-time:popover-open")
              .forEach(p => p.hidePopover());
            ticking = false;
          });
          ticking = true;
        }
      });
    }
  });
})();
