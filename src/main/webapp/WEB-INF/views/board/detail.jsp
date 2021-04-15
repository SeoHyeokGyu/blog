<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<div class="container">

    <div>
        글 번호: <span id="id"><i>${board.id}</i></span>
        작성자: <span id="username"><i>${board.user.username}</i></span>
    </div>
    <br/>
    <div>
        <h3>${board.title}</h3>
    </div>
        <hr />
            <div>
                ${board.content}
            </div>
        </div>
        <hr />


<button class="btn btn-secondary" onclick="history.back()">back</button>
<c:if test="${board.user.id == principal.user.id}">
    <a href="/board/${board.id}/updateForm" id="btn-delete" class="btn btn-danger">delete</a>
    <button id="btn-update" class="btn btn-warning">modify</button>

</c:if>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
