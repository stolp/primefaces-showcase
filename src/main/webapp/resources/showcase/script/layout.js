$(function() {
    Showcase = {
        init: function() {    
            this.wrapper = $(document.body).children('.layout-wrapper');
            this.topbar = this.wrapper.children('.layout-topbar');
            this.topbarMenu = this.topbar.find('> form > .topbar-menu');
            this.configurator = this.wrapper.children('.layout-config');
            this.configuratorButton = $('#layout-config-button');
            this.configuratorCloseButton = $('#layout-config-close-button');
            
            this._bindEvents();
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

            $(document).off('click.showcase').on('click.showcase', function(event) {
                if (!$.contains($this.topbarMenu.get(0), event.target)) {
                    $this.hideTopbarSubmenu($this.topbarMenu.children('.topbar-submenu-active'));
                }

                if ($this.configurator.hasClass('layout-config-active') && !$.contains($this.configurator.get(0), event.target)) {
                    $this.configurator.removeClass('layout-config-active');
                }
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
        }
    }

    Showcase.init();
});

/*
$(document).ready(function() {
    
    var Showcase = {

        init: function() {    
            this.wrapper = $(document.body).children('.layout-wrapper');
            this.topbar = this.wrapper.children('.layout-topbar');
            this.sidebar = this.wrapper.children('.layout-sidebar');
            this.menu = $('#layout-menu');
            this.menulinks = this.menu.find('a');
            this.submenuItems = this.menu.find('> li');
            this.menuButton = $('#menu-button');
            this.expandedMenuitems = this.expandedMenuitems||[];
            this.searchInput = this.sidebar.find('.search-input > input');

            this.configButton = $('.layout-config-button');
            this.configMenu = $('.layout-config');
            this.configMenuClose = this.configMenu.find('.layout-config-content-wrapper > .layout-config-close');
            
            this.expandedItemsKey ='Showcase_ExpandedItems';
            this.newsbarKey = 'Showcase_Newsbar';
            
            this.initPrimeNewsBar();
            
            this.restoreMenuState();
            
            this._bindEvents();
        },
        
        initPrimeNewsBar: function() {
            var $this = this;
            var topbarNewsBar = this.wrapper.children('.layout-news');
            if (topbarNewsBar.length) {
               var topbarNewsCloseButton = topbarNewsBar.find('.layout-news-close'),
               removeTopbarNewsBar = function() {
                   localStorage.setItem($this.newsbarKey, true);
                   topbarNewsBar.removeClass('layout-news-active');
               };
               
                var newsbarstore = localStorage.getItem($this.newsbarKey);
                if (newsbarstore) {
                    topbarNewsBar.removeClass('layout-news-active');
                }
                else {
                    topbarNewsBar.addClass('layout-news-active');
                    topbarNewsCloseButton.on('click', function(e) {
                        removeTopbarNewsBar();
                        e.preventDefault();
                    });
                }
            }
        },
    
        _bindEvents: function() {
            var $this = this;

            this.sidebar.off('click.showcase').on('click.showcase', function() {
                $this.menuClick = true;
            });

            this.menuButton.off('click.showcase').on('click.showcase', function(e) {
                $this.menuClick = true;
                if($this.isMobile()) {
                    $this.wrapper.toggleClass('layout-sidebar-mobile-active');   
                    $(document.body).toggleClass('hidden-overflow-body');   
                }

                e.preventDefault();
            });    
    
            this.menulinks.off('click.showcase').on('click.showcase', function (e) {
                var link = $(this),
                item = link.parent('li'),
                submenu = item.children('div');
    
                if (item.hasClass('active-menuitem')) {
                    if (submenu.length) {
                        $this.removeMenuitem(item.attr('id'));
                        item.removeClass('active-menuitem');
    
                        submenu.slideUp();
                    }
                }
                else {
                    $this.addMenuitem(item.attr('id'));
                    $this.deactivateItems(item.siblings(), true);
                    $this.activate(item);
                }

                
                if (submenu.length) {
                    e.preventDefault();
                }
            });

            this.bindConfigEvents();

            $(document.body).off('click.showcase').on('click.showcase', function() {
                if(!$this.menuClick && $this.isMobile()) {
                    $this.wrapper.removeClass('layout-sidebar-mobile-active');
                    $(document.body).removeClass('hidden-overflow-body');  
                }

                if (!$this.topbarRootMenuItemClicked) {
                    $this.topbar.find('> .topbar-menu > li').removeClass('topbar-menuitem-active');
                }

                if (!$this.configMenuClicked) {
                    $this.configMenu.removeClass('layout-config-active');
                }
    
                $this.menuClick = false;
                $this.topbarRootMenuItemClicked = false;
                $this.configMenuClicked = false;
            });
            
            this.searchInput.on('keyup', function(e) {
                var input = $(this), 
                searchedValue = input.val().toLowerCase(),
                matchSub = false;

                for(var i = 0; i < $this.submenuItems.length; i++) {
                    var submenuItem = $this.submenuItems.eq(i),
                    submenuLink = submenuItem.children('a'),
                    submenuLinkVal = String.prototype.trim.call(submenuLink.children('span').text()).toLowerCase();
                    
                    if(submenuLinkVal.search(searchedValue) < 0 || searchedValue.length === 0) {  
                        var menulinksInSubmenu = submenuLink.next().find('a');
                        
                        for(var j = 0; j < menulinksInSubmenu.length; j++) {
                            var menulink = menulinksInSubmenu.eq(j),
                            menuitem = menulink.parent(),
                            itemVal = String.prototype.trim.call(menulink.children('span').text()).toLowerCase();
                            
                            if(itemVal.search(searchedValue) >= 0) {
                                menuitem.show();
                                matchSub = true;
                            }
                            else{
                                menuitem.hide();
                            }
                        }
                        
                        if(matchSub) {
                            submenuItem.show();
                            matchSub = false;
                        }
                        else {
                            submenuItem.hide();
                        }
                    }
                    else {
                        submenuItem.show();
                    }
                }   
            });

            this.topbar.find('> .topbar-menu > li > a').off('click.showcase').on('click.showcase', function(e) {
                var link = $(this),
                    href = $(this).attr('href');

                if(href && href !== '#') {
                    window.location.href = href;
                }
                else {
                    var parentItem = link.parent();

                    $this.topbarRootMenuItemClicked = true;
                    parentItem.siblings().removeClass('topbar-menuitem-active');
                    parentItem.addClass('topbar-menuitem-active');

                    e.preventDefault();
                }
            });
            
            this.topbar.find('> .topbar-menu .themes-overlay a').off('click.showcase').on("click.showcase", function(e) {
                var href = $(this).attr('href');
                if(href && href !== '#') {
                    window.location.href = href;
                }
                else {
                    var theme = $(this).data("theme");
                    
                    if(theme.startsWith('luna')) {
                        $('.content-implementation').addClass('dark-content');
                    }
                    else {
                        $('.content-implementation').removeClass('dark-content');
                    }
                    
                    changeTheme([{name:'globaltheme', value:theme}]);
                    PrimeFaces.changeTheme(theme);
                    e.preventDefault();
                }
            });
        },

        bindConfigEvents: function() {
            var $this = this;
            var changeConfigMenuState = function(e) {
                this.toggleClass(this.configMenu, 'layout-config-active');
                
                this.configMenuClicked = true;
                e.preventDefault();
            };
            
            this.configButton.off('click.config').on('click.config', changeConfigMenuState.bind(this));
            this.configMenuClose.off('click.config').on('click.config', changeConfigMenuState.bind(this));
            
            this.configMenu.off('click.config').on('click.config', function() {
                $this.configMenuClicked = true;
            });
        },

        toggleClass: function(el, className) {
            if (el.hasClass(className)) {
                el.removeClass(className);
            }
            else {
                el.addClass(className);
            }
        },

        removeMenuitem: function (id) {
            this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
                return value !== id;
            });
    
            this.saveMenuState();
        },
    
        addMenuitem: function (id) {
            if ($.inArray(id, this.expandedMenuitems) === -1) {
                this.expandedMenuitems.push(id);
            }
            this.saveMenuState();
        },
    
        saveMenuState: function() {
            localStorage.setItem(this.expandedItemsKey, this.expandedMenuitems.join(',').replace(/,\s*$/, ""))
        },
    
        clearMenuState: function() {
            localStorage.removeItem(this.expandedItemsKey);
        },
    
        activate: function(item) {
            var submenu = item.children('div');
            item.addClass('active-menuitem');

            if(submenu.length) {
                submenu.slideDown();
            }
        },
    
        deactivate: function(item) {
            var submenu = item.children('div');
            item.removeClass('active-menuitem');
    
            if(submenu.length) {
                submenu.hide();
            }
        },
    
        deactivateItems: function(items, animate) {
            var $this = this;
    
            for(var i = 0; i < items.length; i++) {
                var item = items.eq(i),
                submenu = item.children('div');
    
                if(submenu.length) {
                    if(item.hasClass('active-menuitem')) {
                        var activeSubItems = item.find('.active-menuitem');
                        item.removeClass('active-menuitem');
    
                        if(animate) {
                            submenu.slideUp('normal', function() {
                                $(this).parent().find('.active-menuitem').each(function() {
                                    $this.deactivate($(this));
                                });
                            });
                        }
                        else {
                            submenu.hide();
                            item.find('.active-menuitem').each(function() {
                                $this.deactivate($(this));
                            });
                        }
    
                        $this.removeMenuitem(item.attr('id'));
                        activeSubItems.each(function() {
                            $this.removeMenuitem($(this).attr('id'));
                        });
                    }
                    else {
                        item.find('.active-menuitem').each(function() {
                            var subItem = $(this);
                            $this.deactivate(subItem);
                            $this.removeMenuitem(subItem.attr('id'));
                        });
                    }
                }
                else if(item.hasClass('active-menuitem')) {
                    $this.deactivate(item);
                    $this.removeMenuitem(item.attr('id'));
                }
            }
        },
    
        restoreMenuState: function() {
            var menustore = localStorage.getItem(this.expandedItemsKey);
            if (menustore) {
                this.expandedMenuitems = menustore.split(',');
                for (var i = 0; i < this.expandedMenuitems.length; i++) {
                    var id = this.expandedMenuitems[i];
                    if (id) {
                        var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                        menuitem.addClass('active-menuitem');
    
                        var submenu = menuitem.children('div');
                        if(submenu.length) {
                            submenu.show();
                        }
                    }
                }
            }
        },
    
        isMobile: function( ){
            return window.innerWidth <= 1024;
        }

    };
    
    Showcase.init();
});

PrimeFaces.ShowcaseConfigurator = {
    
    changeTheme: function(theme) {
        var library = 'primefaces-';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library);
        var currentTheme = href.substring(index + library.length);

        this.replaceLink(linkElement, href.replace(currentTheme, theme));

        if(theme.startsWith('luna')) {
            $('.content-implementation').addClass('dark-content');
        }
        else {
            $('.content-implementation').removeClass('dark-content');
        }
    },

    replaceLink: function(linkElement, href) {
        var cloneLinkElement = linkElement.clone(false);
        
        cloneLinkElement.attr('href', href);
        linkElement.after(cloneLinkElement);
        
        cloneLinkElement.off('load').on('load', function() {
            linkElement.remove();
        });
    }
};
*/