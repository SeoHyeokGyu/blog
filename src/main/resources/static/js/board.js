let index = {
    init: function () {
        $("#btn-save").on("click",()=>{ // this를 바인딩하기위한 사용
            this.save();
        })
    },

    save:function (){
        let data = {
            title: $("#title").val(),
            content:$("#content").val(),
        };

        //console.log(data)
        //ajax 비동기호출
        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data),
            contentType:"application/json; chrset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    }

}
index.init();