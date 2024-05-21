<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script type="text/javascript">
	 $(function() {
		 
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(".glyphicon-chevron-down").on("click", function() {

					var noticeNo = $(this).next().val();
					var $tr = $(this).closest("tr");
					var $nextTr = $tr.next(".notice-content-row");
					
					if ($nextTr.length > 0) {
	                    // 공지 내용이 이미 열려 있을 경우 닫기
	                    $nextTr.remove();
	                    $(this).toggleClass("glyphicon-chevron-up glyphicon-chevron-down");
					} else {
				
					$.ajax( 
							{
								url : "/notice/json/getNotice/"+noticeNo ,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
			                    success: function(JSONData, status) {
			                        // 기존의 상세 내용을 제거
			                        $tr.next(".notice-content-row").remove();

			                        // 새로운 상세 내용 행을 추가
			                        var newContentRow = '<tr class="notice-content-row"><td colspan="3"><h1>' + JSONData.noticeContent + '</h1></td></tr>';
			                        $tr.after(newContentRow);
			                        $("#" + noticeNo).toggleClass("glyphicon-chevron-down glyphicon-chevron-up");
			                        
								}
						});
					}
						////////////////////////////////////////////////////////////////////////////////////////////
					
			});

		});	
	</script>
    <meta charset="UTF-8">
    <title>공지사항</title>
</head>
<body>
<div>
    <h2>공지사항 목록</h2>
    <table>
        <thead>
        <tr>
            <th>제목</th>
            <th>작성일</th>
            <th>자세히보기</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="i" value="0"/>
        <c:forEach var="note" items="${list}">
        <c:set var='i' value="${i+1}"/>
            <tr>
                <td>${note.noticeSubject}</td>
                <td>${note.noticeRegDate}</td>
                <td><i class="glyphicon glyphicon-chevron-down" id = "${note.noticeNo }"></i>
                <input type="hidden" value="${note.noticeNo}">
            </tr>


    </c:forEach>
            </tbody>
                </table>
    <div>
        <a href="${pageContext.request.contextPath}/notice/addNotice">공지 추가</a>
    </div>
</div>
</body>
</html>
