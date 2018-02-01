/**
 * 监考中心详情
 * Created by yorge on 2017/9/14.
 */

/*<!-- 模态框 违规次数-->*/
function breakTimes(obj) {
    console.log(obj);
    $('#breakTimes').modal();
    var a = $(".el_outParperTable").children("tbody").children("tr").length;
    $(".el_times").prop("rowspan","3")

}