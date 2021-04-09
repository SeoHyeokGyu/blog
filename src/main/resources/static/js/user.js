let index = {
    init: function () {
        $("#btn-save").on("click",()=>{ // this를 바인딩하기위한 사용
            this.save();
        });
    },

    save:function (){
        //alert('user의 save함수 호출');
        let data = {
            username: $("#username").val(),
            password:$("#pwd").val(),
            email:$("#email").val()
        }

        //console.log(data)
        //ajax 비동기호출
        $.ajax({
            type:"POST",
            url:"/api/user",
            data:JSON.stringify(data),
            contentType:"application/json; chrset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert("회원가입이 완료되었습니다.");
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    }
}
index.init();