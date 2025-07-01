/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2024 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
