$(function(){
    $("#uploadForm").submit(upload);
});

$('#aButton').on('click', function(){
    alert('test');
});

function upload() {
    $.ajax({
        url: "http://upload-z2.qiniup.com",
        method: "post",
        processData: false,
        contentType: false,
        data: new FormData($("#uploadForm")[0]),
        success: function(data) {
            if(data && data.code == 0) {
                console(data);
                // 更新头像访问路径
                // $.post(
                //     CONTEXT_PATH + "/user/header/url",
                //     {"fileName":$("input[name='key']").val()},
                //     function(data) {
                //         data = $.parseJSON(data);
                //         if(data.code == 0) {
                //             window.location.reload();
                //         } else {
                //             alert(data.msg);
                //         }
                //     }
                // );
                alert("上传成功!");
            } else {
                alert("上传失败!");
            }
        }
    });
    return false;
}