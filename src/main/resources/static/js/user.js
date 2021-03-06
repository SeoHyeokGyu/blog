let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this를 바인딩하기위한 사용
            this.save();
        });
        $("#btn-update").on("click", () => { // this를 바인딩하기위한 사용
            this.update();
        });
    },

    save: function () {
        //alert('user의 save함수 호출');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data)
        //ajax 비동기호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; chrset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("회원가입이 실패 하였습니다.");

            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));

        });
    },
    update: function () {
        //alert('user의 save함수 호출');
        let data = {
            id: $("#id").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            username: $("#username").val()
        };

        //console.log(data)
        //ajax 비동기호출
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; chrset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));

        });
    }

}
index.init();