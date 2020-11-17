PrimeFaces.widget.Showcase = PrimeFaces.widget.BaseWidget.extend({
    
    init: function(cfg) {
        this._super(cfg);  
        this.wrapper = $(document.body).children('.layout-wrapper');

        this.topbar = this.wrapper.children('.layout-topbar');
        this.topbarMenu = this.topbar.find('.topbar-menu');
        this.topbarItems = this.topbarMenu.children('li');
        this.topbarLinks = this.topbarItems.children('a');

        this.sidebar = this.wrapper.children('.layout-sidebar');
        this.sidebarButton = this.topbar.find('.menu-button');
        this.menu = this.sidebar.children('.layout-menu');
        this.menuCategories = this.menu.find('.menu-category');
        this.submenuItems = this.menu.find('.menu-items');
        this.menulinks = this.menu.find('a');

        this.layoutMask = this.wrapper.children('.layout-mask');

        this.searchInput = this.sidebar.find('.search-input > input');
        this.expandedMenuitems = this.expandedMenuitems||[];

        this.configMenu = this.wrapper.children('.layout-config');
        this.configButton = this.configMenu.find('.layout-config-content-wrapper > .layout-config-button');
        this.configMenuClose = this.configMenu.find('.layout-config-content-wrapper > .layout-config-close');

        this.restoreMenuState();
        this.bindEvents();
    },

    toggleClass: function(el, className) {
        if (el.hasClass(className)) {
            el.removeClass(className);
        }
        else {
            el.addClass(className);
        }
    },

    bindEvents: function() {
        var $this = this;
        
        this.bindSidebarEvents();
        this.bindTopbarEvents();
        this.bindConfigEvents();

        $(document.body).off('click.showcase-body').on('click.showcase-body', function() {            
            if (!$this.sidebarClick) {
                if ($this.sidebar.hasClass('active')) {
                    $(document.body).removeClass('blocked-scroll');
                    $this.sidebar.removeClass('active');
                    $this.layoutMask.removeClass('layout-mask-active');
                }
            }
            
            if (!$this.topbarItemClicked) {
                $this.topbarItems.siblings('.active-menuitem ').removeClass('active-menuitem ');
            }

            if (!$this.configMenuClicked) {
                $this.configMenu.removeClass('layout-config-active');
            }
            
            $this.sidebarClick = false;
            $this.topbarItemClicked = false;
            $this.configMenuClicked = false;
        });
    },

    bindSidebarEvents: function() {
        var $this = this;
        
        this.sidebar.off('click.sidebar').on('click.sidebar', function() {
            $this.sidebarClick = true;
        });
        
        this.sidebarButton.off('click.sidebar').on('click.sidebar', function(e) {
            $this.sidebarClick = true;
            $this.toggleClass($this.sidebar, 'active');

            if ($this.sidebar.hasClass('active')) {
                $(document.body).addClass('blocked-scroll');
                $this.layoutMask.addClass('layout-mask-active');
            }
            else {
                $(document.body).removeClass('blocked-scroll');
                $this.layoutMask.removeClass('layout-mask-active');
            }
            
            e.preventDefault();
        });

        this.menulinks.off('click.sidebar').on('click.sidebar', function(e) {
            var link = $(this),
            submenu = link.next('.p-toggleable-content');

            if(submenu.length) {
                submenu.slideDown();
                e.preventDefault();
            }

            $this.saveScrollPosition();
        });

        this.searchInput.on('keyup', function(e) {
            var input = $(this),
                searchedValue = input.val().toLowerCase();

            for(var i = 0; i < $this.submenuItems.length; i++) {

                var submenuItem = $this.submenuItems.eq(i),
                    submenuCategory = $this.menuCategories.eq(i),
                    submenuLinks = submenuItem.find('a'),
                    matchSub = false;

                submenuCategory.hide();

                for(var j = 0; j < submenuLinks.length; j++) {
                    var submenuLinkVal = submenuLinks.eq(j).data('meta').toLowerCase().trim();
                    if(submenuLinkVal.search(searchedValue) >= 0) {
                        submenuCategory.show();
                        submenuItem.show();
                        submenuLinks.eq(j).show();
                        if (submenuLinks.eq(j).parents().hasClass('p-toggleable-content')) {
                            submenuLinks.eq(j).parents('.p-toggleable-content').prev().show();
                        }
                        matchSub = true;
                    } else {
                        submenuLinks.eq(j).hide();
                        if (submenuLinks.eq(j).parents().hasClass('p-toggleable-content')) {
                            submenuLinks.eq(j).parents('.p-toggleable-content').hide();
                        }
                    }
                }

                if (!matchSub) {
                    submenuItem.hide();
                }
            }

            if (searchedValue.length === 0) {
                $this.submenuItems.find('.p-toggleable-content').hide();
            } else {
                for(var a = 0; a < $this.submenuItems.length; a++) {
                    var showItem = $this.submenuItems.eq(a),
                        showLinks = showItem.find('a');

                    for(var b = 0; b < showLinks.length; b++) {
                        var linkItem = showLinks.eq(b);
                        if(linkItem.css('display') !== 'none' && linkItem.parents().hasClass('p-toggleable-content')){
                            linkItem.parents('.p-toggleable-content').show();
                        }
                    }
                }
            }

        });
    },
    
    bindTopbarEvents: function() {
        var $this = this;

        this.topbarLinks.off('click.topbar').on('click.topbar', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = item.children('ul');

            $this.topbarItems.siblings('.active-menuitem ').removeClass('active-menuitem ');
            item.addClass('active-menuitem ');

            $this.topbarItemClicked = true;
            
            if (submenu.length) {
                e.preventDefault();
            }
        });
    },

    bindConfigEvents: function() {
        var $this = this;
        var changeConfigMenuState = function(e) {
            this.toggleClass($this.configMenu, 'layout-config-active');
            this.configMenuClicked = true;
            e.preventDefault();
        };

        this.configButton.off('click.config').on('click.config', changeConfigMenuState.bind(this));
        this.configMenuClose.off('click.config').on('click.config', changeConfigMenuState.bind(this));

        this.configMenu.off('click.config').on('click.config', function() {
            $this.configMenuClicked = true;
        });
    },

    activate: function(item) {
        var submenu = item.children('ul');
        item.addClass('active-menuitem');

        if(submenu.length) {
            submenu.slideDown();
        }
    },
    
    deactivate: function(item) {
        var submenu = item.children('ul');
        item.removeClass('active-menuitem');
        
        if(submenu.length) {
            submenu.hide();
        }
    },
        
    deactivateItems: function(items, animate) {
        var $this = this;
        
        for(var i = 0; i < items.length; i++) {
            var item = items.eq(i),
            submenu = item.children('ul');

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
        $.cookie('showcase_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
    },
    
    clearMenuState: function() {
        $.removeCookie('showcase_expandeditems', {path: '/'});
    },

    saveScrollPosition: function() {
        $.cookie('showcase_scroll_position', this.sidebar.scrollTop(), { path: '/' });
    },
    
    restoreMenuState: function() {
        this.sidebar.scrollTop($.cookie('showcase_scroll_position') || 0);

        var menuCookie = $.cookie('showcase_expandeditems');
        if (menuCookie) {
            this.expandedMenuitems = menuCookie.split(',');
            for (var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if (id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                    menuitem.addClass('active-menuitem');
                    
                    var submenu = menuitem.children('ul');
                    if(submenu.length) {
                        submenu.show();
                    }
                }
            }
        }
    },
    
    hideTopBar: function() {
        var $this = this;
        this.topbarMenu.addClass('fadeOutUp');
        
        setTimeout(function() {
            $this.topbarMenu.removeClass('fadeOutUp topbar-menu-visible');
        },500);
    },
    
    isMobile: function() {
        return window.innerWidth <= 1024;
    }
});

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD (Register as an anonymous module)
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {

    var pluses = /\+/g;

    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape...
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            // Replace server-side written pluses with spaces.
            // If we can't decode the cookie, ignore it, it's unusable.
            // If we can't parse the cookie, ignore it, it's unusable.
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch (e) { }
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (arguments.length > 1 && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {},
            // To prevent the for loop in the first place assign an empty array
            // in case there are no cookies at all. Also prevents odd result when
            // calling $.cookie().
            cookies = document.cookie ? document.cookie.split('; ') : [],
            i = 0,
            l = cookies.length;

        for (; i < l; i++) {
            var parts = cookies[i].split('='),
                name = decode(parts.shift()),
                cookie = parts.join('=');

            if (key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, { expires: -1 }));
        return !$.cookie(key);
    };

}));