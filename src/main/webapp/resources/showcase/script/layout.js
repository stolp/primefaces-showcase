App = {
    init: function() {    
        this.wrapper = $(document.body).children('.layout-wrapper');
        this.topbar = this.wrapper.children('.layout-topbar');
        this.topbarMenu = this.topbar.find('> form > .topbar-menu');
        this.sidebar = this.wrapper.children('.layout-sidebar');
        this.menu = this.sidebar.children('.layout-menu');
        this.menuLinks = this.menu.find('a');
        this.mask = this.wrapper.children('.layout-mask');
        this.menuButton = this.topbar.children('.menu-button');
        this.configurator = this.wrapper.children('.layout-config');
        this.configuratorButton = $('#layout-config-button');
        this.configuratorCloseButton = $('#layout-config-close-button');
        this.activeSubmenus = [];
        
        this._bindEvents();
        this.restoreMenu();
    },

    _bindEvents: function() {
        var $this = this;

        this.topbarMenu.find('> .topbar-submenu > a').off('click').on('click', function() {
            var item = $(this).parent();

            item.siblings('.topbar-submenu-active').removeClass('topbar-submenu-active');

            if (item.hasClass('topbar-submenu-active'))
                $this.hideTopbarSubmenu(item);
            else
                $this.showTopbarSubmenu(item);
        });

        this.topbarMenu.find('.connected-overlay-in a').off('click').on('click', function() {
            $this.hideTopbarSubmenu($this.topbarMenu.children('.topbar-submenu-active'));
        });

        this.menuLinks.off('click').on('click', function() {
            var link = $(this);
            
            if (link.hasClass('submenu-link')) {
                if (link.hasClass('submenu-link-active')) {
                    $this.activeSubmenus.filter(function(id) {return id !== link.attr('id')});
                    link.removeClass('submenu-link-active').next('.submenu').slideUp('fast');
                }
                else {
                    $this.activeSubmenus.push(link.attr('id'));
                    link.addClass('submenu-link-active').next('.submenu').slideDown('fast');
                }

                sessionStorage.setItem('active_submenus', $this.activeSubmenus.join(','));
            }
            else {
                link.addClass('router-link-active');
            }   
        });

        this.sidebar.off('scroll').on('scroll', function(event) {
            sessionStorage.setItem('scroll_position', $this.sidebar.scrollTop());
        });

        $(document).off('click.showcase').on('click.showcase', function(event) {
            if (!$.contains($this.topbarMenu.get(0), event.target)) {
                $this.hideTopbarSubmenu($this.topbarMenu.children('.topbar-submenu-active'));
            }

            if ($this.sidebar.hasClass('active') && !$.contains($this.sidebar.get(0), event.target) && !$this.isMenuButton(event.target)) {
                $this.sidebar.removeClass('active');
                $this.mask.removeClass('layout-mask-active');
            }

            if ($this.configurator.hasClass('layout-config-active') && !$.contains($this.configurator.get(0), event.target)) {
                $this.configurator.removeClass('layout-config-active');
            }
        });

        this.menuButton.off('click').on('click', function() {
            $this.sidebar.addClass('active');
            $this.mask.addClass('layout-mask-active');
        });

        this.configuratorButton.off('click').on('click', function() {
            $this.configurator.toggleClass('layout-config-active');
        });

        this.configuratorCloseButton.off('click').on('click', function() {
            $this.configurator.removeClass('layout-config-active');
        });
    },

    hideTopbarSubmenu(item) {
        var submenu = item.children('ul');
        submenu.addClass('connected-overlay-out');

        setTimeout(function () {
            item.removeClass('topbar-submenu-active'),
            submenu.removeClass('connected-overlay-out');
        }, 100);
    },

    showTopbarSubmenu(item) {
        item.addClass('topbar-submenu-active');
    },

    changeTheme: function(theme, dark) {
        var library = 'primefaces-';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library);
        var currentTheme = href.substring(index + library.length);

        this.replaceLink(linkElement, href.replace(currentTheme, theme));

        if (dark)
            $('#homepage-intro').addClass('introduction-dark');
        else
            $('#homepage-intro').removeClass('introduction-dark');
    },

    replaceLink: function(linkElement, href) {
        var cloneLinkElement = linkElement.clone(false);
        
        cloneLinkElement.attr('href', href);
        linkElement.after(cloneLinkElement);
        
        cloneLinkElement.off('load').on('load', function() {
            linkElement.remove();
        });
    },

    updateInputStyle: function(value) {
        if (value === 'filled')
            this.wrapper.addClass('ui-input-filled');
        else
            this.wrapper.removeClass('ui-input-filled');
    },

    isMenuButton(element) {
        return $.contains(this.menuButton.get(0), element) || this.menuButton.is(element);
    },

    restoreMenu() {
        var activeRouteLink = this.menuLinks.filter('[href^="' + window.location.pathname + '"]');
        if (activeRouteLink.length) {
            activeRouteLink.addClass('router-link-active');
        }

        var activeSubmenus = sessionStorage.getItem('active_submenus');
        if (activeSubmenus) {
            this.activeSubmenus = activeSubmenus.split(',');
            this.activeSubmenus.forEach(function(id) {
                $('#' + id).addClass('submenu-link-active').next().show();
            });
        }

        var scrollPosition = sessionStorage.getItem('scroll_position');
        if (scrollPosition) {
            this.sidebar.scrollTop(parseInt(scrollPosition));
        }
    }
}

App.init();