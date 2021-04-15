let index = {
    init: function () {
        $("#btn-save").on("click",()=>{ // this를 바인딩하기위한 사용
            this.save();
        }),
            $("#btn-delete").on("click",()=>{ // this를 바인딩하기위한 사용
                this.deleteById();
            }),
            $("#btn-update").on("click",()=>{ // this를 바인딩하기위한 사용
                this.update();
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
            alert("글쓰기 완료.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    },

    deleteById:function (){

        let id = $("#id").text();
        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; chrset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert("글 삭제 완료.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    },

    update:function (){
        let id = $("#id").val()
        let data = {
            title: $("#title").val(),
            content:$("#content").val(),
        };

        //console.log(data)
        //ajax 비동기호출
        $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; chrset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert("글수정 완료.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    }

}
index.init();