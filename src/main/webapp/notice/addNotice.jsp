<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>공지사항 글 작성</title>
</head>

<body>
<div class="section section-properties">
    <div class="container">
        <div class="two_third first" style="height:auto">
            <div class="col-lg-12">
                <h2 class="font-weight-bold text-primary heading">글쓰기</h2>
                <hr/><br><br>
                <div class="two_third first">
                    <form method="post" action="/notice/addNotice" id="frm">
                        <table class="table">
                            <tr>
                                <th width="10%" class="text-right">이름</th>
                                <td width="80%">
                                    <input type="text" class="form-control" id="noticeName" name="noticeName" placeholder="이름">
                                </td>
                            </tr>
                            <tr>
                                <th width="20%" class="text-right">제목</th>
                                <td width="80%">
                                    <input type="text" class="form-control" id="noticeSubject" name="noticeSubject" placeholder="제목">
                                </td>
                            </tr>
                            <tr>
                                <th width="20%" class="text-right">내용</th>
                                <td width="80%">
                                    <textarea rows="10" cols="50" name="noticeContent" id="noticeContent"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th width="20%" class="text-right">비밀번호</th>
                                <td width="80%">
                                    <input type="password" name="noticePwd" size="10" class="input-sm" id="noticePwd">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center">
                                    <div class="wrap">
                                        <input type="submit" value="글쓰기" class="button" id="writeBtn">
                                        <input type="button" value="취소" class="button" onclick="javascript:history.back()">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
