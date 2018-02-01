/**
 * Qlq   2018.1.8将菜单的js抽出去
 */
/** *****************菜单插件部分******************* */
;
(function($, window, document, undefined) {
	if ($('ul.mtree').length) {
		var collapsed = true;
		var close_same_level = false;
		var duration = 400;
		var listAnim = true;
		var easing = 'easeOutQuart';
		$('.mtree ul').css({
			'overflow' : 'hidden',
			'height' : collapsed ? 0 : 'auto',
			'display' : collapsed ? 'none' : 'block'
		});
		var node = $('.mtree li:has(ul)');
		node
				.each(function(index, val) {
					$(this).children(':first-child').css('cursor', 'pointer');
					$(this).addClass(
							'mtree-node mtree-'
									+ (collapsed ? 'closed' : 'open'));
					$(this)
							.children('ul')
							.addClass(
									'mtree-level-'
											+ ($(this).parentsUntil(
													$('ul.mtree'), 'ul').length + 1));
				});
		$('.mtree li > *:first-child').on(
				'click.mtree-active',
				function(e) {
					if ($(this).parent().hasClass('mtree-closed')) {
						$('.mtree-active').not($(this).parent()).removeClass(
								'mtree-active');
						$(this).parent().addClass('mtree-active');
					} else if ($(this).parent().hasClass('mtree-open')) {
						$(this).parent().removeClass('mtree-active');
					} else {
						$('.mtree-active').not($(this).parent()).removeClass(
								'mtree-active');
						$(this).parent().toggleClass('mtree-active');
					}
				});
		node.children(':first-child').on(
				'click.mtree',
				function(e) {
					var el = $(this).parent().children('ul').first();
					var isOpen = $(this).parent().hasClass('mtree-open');
					if ((close_same_level || $('.csl').hasClass('active'))
							&& !isOpen) {
						var close_items = $(this).closest('ul').children(
								'.mtree-open').not($(this).parent()).children(
								'ul');
						if ($.Velocity) {
							close_items.velocity({
								height : 0
							}, {
								duration : duration,
								easing : easing,
								display : 'none',
								delay : 100,
								complete : function() {
									setNodeClass($(this).parent(), true);
								}
							});
						} else {
							close_items.delay(100).slideToggle(duration,
									function() {
										setNodeClass($(this).parent(), true);
									});
						}
					}
					el.css({
						'height' : 'auto'
					});
					if (!isOpen && $.Velocity && listAnim)
						el.find(' > li, li.mtree-open > ul > li').css({
							'opacity' : 0
						}).velocity('stop').velocity('list');
					if ($.Velocity) {
						el.velocity('stop').velocity(
								{
									height : isOpen ? [ 0, el.outerHeight() ]
											: [ el.outerHeight(), 0 ]
								},
								{
									queue : false,
									duration : duration,
									easing : easing,
									display : isOpen ? 'none' : 'block',
									begin : setNodeClass($(this).parent(),
											isOpen),
									complete : function() {
										if (!isOpen)
											$(this).css('height', 'auto');
									}
								});
					} else {
						setNodeClass($(this).parent(), isOpen);
						el.slideToggle(duration);
					}
					e.preventDefault();
				});
		function setNodeClass(el, isOpen) {
			if (isOpen) {
				el.removeClass('mtree-open').addClass('mtree-closed');
			} else {
				el.removeClass('mtree-closed').addClass('mtree-open');
			}
		}
		if ($.Velocity && listAnim) {
			$.Velocity.Sequences.list = function(element, options, index, size) {
				$.Velocity.animate(element, {
					opacity : [ 1, 0 ],
					translateY : [ 0, -(index + 1) ]
				}, {
					delay : index * (duration / size / 2),
					duration : duration,
					easing : easing
				});
			};
		}
		if ($('.mtree').css('opacity') == 0) {
			if ($.Velocity) {
				$('.mtree').css('opacity', 1).children().css('opacity', 0)
						.velocity('list');
			} else {
				$('.mtree').show(200);
			}
		}
	}
}(jQuery, this, this.document));
$(document)
		.ready(
				function() {
					var mtree = $('ul.mtree');
					mtree.wrap('<div class=mtree-demo></div>');
					var skins = [ 'transit' ];
					mtree.addClass(skins[0]);
					$('body')
							.prepend(
									'<div class="mtree-skin-selector" style="display: none;"><ul class="button-group radius"></ul></div>');
					var s = $('.mtree-skin-selector');
					$.each(skins, function(index, val) {
						s.find('ul').append(
								'<li><button class="small skin">' + val
										+ '</button></li>');
					});
					s
							.find('ul')
							.append(
									'<li><button class="small csl active"></button></li>');
					s.find('button.skin').each(
							function(index) {
								$(this).on(
										'click.mtree-skin-selector',
										function() {
											s.find('button.skin.active')
													.removeClass('active');
											$(this).addClass('active');
											mtree.removeClass(skins.join(' '))
													.addClass(skins[index]);
										});
							});
					s.find('button:first').addClass('active');
					s.find('.csl').on('click.mtree-close-same-level',
							function() {
								$(this).toggleClass('active');
							});
				});

/** *********************菜单折叠部分******************** */
$(function() {
	var u = location.pathname;

	u = u.substring(11, u.length);
	var fir = u.indexOf("/");
	var filename = u.substring(0, fir)
	// 删除class
	$(".mtree-active").removeClass("mtree-active mtree-open");
	if (filename == "innerDepart") {
		// 检索内部的url
		$(".mtree a").each(
				function(i) {
					var url = $(this).prop("href");
					if (url.toString().indexOf(filename) > 0) {
						$(this).parents("[class^='mtree-level']").css({
							display : "block",
							height : "auto"
						});
						$(this).parents(".mtree-node").removeClass("mtree-closed");
						$(this).parents(".mtree-node").addClass("mtree-open");
						$(this).parents(".mtree-level-1").children(
								".mtree-open").addClass("mtree-active");
					}

				});
	} else if (filename == "outDepart") {
		// 检索内部的url
		$(".mtree a").each(
				function(i) {
					var url = $(this).prop("href");
					if (url.toString().indexOf(filename) > 0) {
						$(this).parents("[class^='mtree-level']").css({
							display : "block",
							height : "auto"
						});
						$(this).parents(".mtree-node").removeClass("mtree-closed");
						$(this).parents(".mtree-node").addClass("mtree-open");
						$(this).parents(".mtree-level-1").children(
								".mtree-open").addClass("mtree-active");
					}

				});
	}else{
		// 检索内部的url
		$(".mtree a").each(
				function(i) {
					var url = $(this).prop("href");
					if (url.toString().indexOf(filename) > 0) {
						$(this).parents("[class^='mtree-level']").css({
							display : "block",
							height : "auto"
						});
						$(this).parents(".mtree-node").removeClass("mtree-closed");
						$(this).parents(".mtree-node").addClass("mtree-open mtree-active");
					}

				});
	}
})
