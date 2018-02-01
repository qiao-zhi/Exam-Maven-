/**
 * Created by yorge on 2017/9/13.
 */
$(function(){

    $('#paginationIDU').pagination({
        //			组件属性
        "total": 1000,//数字 当分页建立时设置记录的总数量 1
        "pageSize": 10,//数字 每一页显示的数量 10
        "pageNumber": 1,//数字 当分页建立时，显示的页数 1
        "pageList": [10, 20],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
            alert("pageNumber=" + pageNumber);
            alert("pageSize=" + b);
        }
    });

    $('#paginationID').pagination({
        //			组件属性
        "total": 1000,//数字 当分页建立时设置记录的总数量 1
        "pageSize": 10,//数字 每一页显示的数量 10
        "pageNumber": 1,//数字 当分页建立时，显示的页数 1
        "pageList": [10, 20],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
            alert("pageNumber=" + pageNumber);
            alert("pageSize=" + b);
        }
    });

    $('#paginationID2').pagination({
        //			组件属性
        "total": 100,//数字 当分页建立时设置记录的总数量 1
        "pageSize": 6,//数字 每一页显示的数量 10
        "pageNumber": 1,//数字 当分页建立时，显示的页数 1
        "pageList": [5, 10],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
            alert("pageNumber=" + pageNumber);
            alert("pageSize=" + b);
        }
    });

    /*模态框中*/
    $('#paginationIDM').pagination({
        //			组件属性
        "total": 100,//数字 当分页建立时设置记录的总数量 1
        "pageSize": 6,//数字 每一页显示的数量 10
        "pageNumber": 1,//数字 当分页建立时，显示的页数 1
        "pageList": [5, 10],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
            alert("pageNumber=" + pageNumber);
            alert("pageSize=" + b);
        }
    });
})
