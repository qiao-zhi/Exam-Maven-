/**
 * Created by yorge on 2017/9/15.
 */
KindEditor.ready(function (K) {
    var editor = K.create('#editor_id', {
        resizeType: 0,
        items: [
            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
    });

    // 取得HTML内容
    //var html = editor.html();

    // 同步数据后可以直接取得textarea的value
    editor.sync();
    var html = $('#editor_id').val(); // jQuery
    // 设置HTML内容
    editor.html('HTML内容');


    var editor2 = K.create('#editor_id2', {
        resizeType: 1,
        items: [
            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
    });
});


/*其他*/