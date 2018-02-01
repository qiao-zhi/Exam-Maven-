$(document).ready(function () {
        	
        	//======//小轮播========
            var slideShow = $(".slideShow"), //获取最外层框架的名称
                ul = slideShow.children("ul"),
                showNumber = slideShow.find(".showNav span"),//获取按钮
                oneWidth = slideShow.css("width"); //获取每个图片的宽度
                ul.children("li").css("width",oneWidth);
				oneWidth = oneWidth.substring(0,oneWidth.length-2);
            var timer = null; //定时器返回值，主要用于关闭定时器
            var iNow = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0

            showNumber.on("click", function () {  //为每个按钮绑定一个点击事件
                $(this).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
                var index = $(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
                iNow = index;
                ul.animate({
                    "left": -oneWidth * iNow, //注意此处用到left属性，所以ul的样式里面需要设置position: relative; 让ul左移N个图片大小的宽度，N根据被点击的按钮索引值iNOWx确定
                })
            });

            timer = setInterval(function () { //打开定时器
                iNow++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
                if (iNow > showNumber.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
                    iNow = 0;
                }
                showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
            }, 2000); //2000为轮播的时间
            
            
            //==============大轮播=================
            var slideShow2 = $(".slideShow2"), //获取最外层框架的名称
            ul2 = slideShow2.children("ul"),
            showNumber2 = slideShow2.find(".showNav2 span"),//获取按钮
            oneWidth2 = $(window).width(); //获取每个图片的宽度
	        var timer2 = null; //定时器返回值，主要用于关闭定时器
	        var iNow2 = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0
	        
	        ul2.children("li").css("width",$(window).width());
	        
	        showNumber2.on("click", function () {  //为每个按钮绑定一个点击事件
	            $(this).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
	            var index2 = $(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
	            iNow2 = index2;
	            ul2.animate({
	                "left": -oneWidth2 * iNow2, //注意此处用到left属性，所以ul的样式里面需要设置position: relative; 让ul左移N个图片大小的宽度，N根据被点击的按钮索引值iNOWx确定
	            })
	        });
	
	        timer2 = setInterval(function () { //打开定时器
	            iNow2++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
	            if (iNow2 > showNumber2.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
	                iNow2 = 0;
	            }
	            showNumber2.eq(iNow2).trigger("click"); //模拟触发数字按钮的click
	        }, 3000); //2000为轮播的时间
        })
