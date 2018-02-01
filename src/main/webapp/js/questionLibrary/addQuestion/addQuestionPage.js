/***************************kindEditor编辑器的初始化操作*********************************/
KindEditor.ready(function (K) {
    var editor = K.create('#editor_id', {
        resizeType: 0,
        items: [
            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image'],
        afterBlur:function(){
        	//将编辑器的内容同步到textarea中
        	this.sync();
        } ,
    	height: 100
    });

    
  
    var editor2 = K.create('#editor_id2', {
        resizeType: 0,
        items: [
            'table', '|', 'fontsize', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image'],
        afterBlur:function(){
        	//将编辑器的内容同步到textarea中
        	this.sync();
        }
    });
});


$(function(){
	//试题框的高
	var height1 = $("#editor_id").css("height");
	height1 = parseInt(height1.substring(0,height1.length-2)) + 7 + "px";
	$("#editor_id").parents(".el_bigBlock").css("height",height1);
	
	/*//解析框的高
	var height2 = $("#editor_id2").css("height");
	height2 = parseInt(height2.substring(0,height2.length-2)) + 7 + "px";
	$("#editor_id2").parents(".el_bigBlock").css("height",height2);*/
})



/*其他*/