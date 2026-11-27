/**
* Template Name: NiceAdmin
* Updated: Sep 18 2023 with Bootstrap v5.3.2
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/
(function() {
  "use strict";

  window.alerts = function(message, bgColor = 'alert-danger', ico = 'bi-exclamation-octagon', delay = 4000) {
    // 1. Supprimer l'ancienne alerte si elle existe
    const existingContainer = document.getElementById('custom-alert-container');
    if (existingContainer) {
      existingContainer.remove();
    }

    // 2. Créer le HTML de la nouvelle alerte (fixée en haut)
    const alertHtml = `
        <div id="custom-alert-container" class="position-fixed top-0 start-50 translate-middle-x mt-3 w-75 alert-slide-down" style="z-index: 9999; max-width: 550px;">
            <div class="alert ${bgColor} alert-dismissible fade show text-center shadow" role="alert">
                <i class="bi ${ico} me-1"></i>
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
    `;

    // 3. Injecter dans le DOM
    document.body.insertAdjacentHTML('beforeend', alertHtml);

    // 4. Disparition progressive après le délai
    setTimeout(() => {
      const container = document.getElementById('custom-alert-container');
      if (container) {
        if (typeof $ !== 'undefined') {
          $(container).fadeOut(400, function() {
            $(this).remove();
          });
        } else {
          container.remove();
        }
      }
    }, delay);
  };

  /** =========================================================================
   * AJOUT : Activation du lien du menu courant et ouverture du sous-menu parent
   * ========================================================================= */
  const setActiveSidebarLink = () => {
    const navLinks = select('#sidebar-nav a', true);
    if (navLinks.length > 0) {
      const currentPath = window.location.pathname;

      navLinks.forEach(link => {
        const href = link.getAttribute('href');

        // Vérification si l'attribut href correspond au chemin d'URL de la page
        if (href && href !== '#' && href !== '' && currentPath.endsWith(href)) {
          // 1. Ajouter la classe active au lien
          link.classList.remove('collapsed');
          link.classList.add('active');

          // 2. Ouvrir le sous-menu parent (ul) s'il existe
          const parentUl = link.closest('.nav-content');
          if (parentUl) {
            parentUl.classList.add('show');

            // Retirer 'collapsed' du bouton déroulant parent
            const parentToggleBtn = select(`[data-bs-target="#${parentUl.id}"]`);
            if (parentToggleBtn) {
              parentToggleBtn.classList.remove('collapsed');
            }
          }
        }
      });
    }
  };
  window.addEventListener('load', setActiveSidebarLink);
  /* ========================================================================= */

  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener 
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
      select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Search bar toggle
   */
  if (select('.search-bar-toggle')) {
    on('click', '.search-bar-toggle', function(e) {
      select('.search-bar').classList.toggle('search-bar-show')
    })
  }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
  let selectHeader = select('#header')
  if (selectHeader) {
    const headerScrolled = () => {
      if (window.scrollY > 100) {
        selectHeader.classList.add('header-scrolled')
      } else {
        selectHeader.classList.remove('header-scrolled')
      }
    }
    window.addEventListener('load', headerScrolled)
    onscroll(document, headerScrolled)
  }

  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  /**
   * Initiate tooltips
   */
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  })

  /**
   * Initiate quill editors
   */
  if (select('.quill-editor-default')) {
    new Quill('.quill-editor-default', {
      theme: 'snow'
    });
  }

  if (select('.quill-editor-bubble')) {
    new Quill('.quill-editor-bubble', {
      theme: 'bubble'
    });
  }

  if (select('.quill-editor-full')) {
    new Quill(".quill-editor-full", {
      modules: {
        toolbar: [
          [{
            font: []
          }, {
            size: []
          }],
          ["bold", "italic", "underline", "strike"],
          [{
              color: []
            },
            {
              background: []
            }
          ],
          [{
              script: "super"
            },
            {
              script: "sub"
            }
          ],
          [{
              list: "ordered"
            },
            {
              list: "bullet"
            },
            {
              indent: "-1"
            },
            {
              indent: "+1"
            }
          ],
          ["direction", {
            align: []
          }],
          ["link", "image", "video"],
          ["clean"]
        ]
      },
      theme: "snow"
    });
  }

  /**
   * Initiate Bootstrap validation check
   */
  var needsValidation = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(needsValidation)
    .forEach(function(form) {
      form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

  /**
   * Initiate Datatables
   */
  const datatables = select('.datatable', true)
  datatables.forEach(datatable => {
    new simpleDatatables.DataTable(datatable);
  })

  /**
   * Autoresize echart charts
   */
  const mainContainer = select('#main');
  if (mainContainer) {
    setTimeout(() => {
      new ResizeObserver(function() {
        select('.echart', true).forEach(getEchart => {
          echarts.getInstanceByDom(getEchart).resize();
        })
      }).observe(mainContainer);
    }, 200);
  }

})();