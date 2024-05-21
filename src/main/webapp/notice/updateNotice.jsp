<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<script type="text/javascript">
function fncAddNotice(){
	//Form 유효성 검증
 	var name = document.detailForm.noticeSubject.value;
	var detail = document.detailForm.noticeContent.value;

	if(name == null || name.length<1){
		alert("상품명은 반드시 입력하여야 합니다.");
		return;
	}
	if(detail == null || detail.length<1){
		alert("상품상세정보는 반드시 입력하여야 합니다.");
		return;
	}
		
	document.detailForm.action='/notice/updateNotice';
	document.detailForm.submit();
	
	document.detailForm.onsubmit = function() {
		location.href = '/notice/list';
		return false;
	}
}
</script>
<body bgcolor="#ffffff" text="#000000">
<jsp:include page="/layout/toolbar.jsp" />
<form name="detailForm" method="post" enctype="multipart/form-data">
	<div class="container" id = "center-test">

		<div class="page-header text-center">
			<h3 class=" text-info">상품정보 수정</h3>
			<h5 class="text-muted">상품 정보를 <strong class="text-danger">최신정보로 수정</strong>해 주세요.</h5>
		</div>

		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
			<input type="hidden" name="noticeNo" value="${ UpdateNotice.noticeNo }"/>
			<div class="form-group text-center">
				<label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상품명</label>
				<div class="col-sm-4" style="display: block; margin-left: auto; margin-right: auto;">
					<input type="text" id = "noticeSubject" name = "noticeSubject" class="form-control" placeholder="${ UpdateNotice.noticeSubject}">
				</div>
			</div>

			<div class="form-group">
				<label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품 상세정보</label>
				<div class="col-sm-4" style="display: block; margin-left: auto; margin-right: auto;">
					<input type="text" class="form-control" id="noticeContent" name="noticeContent" placeholder="${UpdateNotice.noticeContent}">
				</div>
			</div>
				<div class="form-group">
					<div class="col-sm-4" style="display: block; margin-left: auto; margin-right: auto;">
						<a href="javascript:fncAddNotice();" class="btn btn-primary" id = "add">수 &nbsp;정</a>
						<a href="javascript:resetData();" class = "btn btn-primary">취 &nbsp;소</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- form Start /////////////////////////////////////-->

</form>
</body>
</html>