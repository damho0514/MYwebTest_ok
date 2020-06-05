<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/Myweb2/css/bootstrap.css">
    <link rel="stylesheet" href="/Myweb2/css/custom.css">
    
</head>
<body>
	
	<%@include file="../include/header.jsp" %>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <form action="updateForm.user" method="post"></form>
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <p>*표시는 필수 입력 표시입니다</p>
                    <table class="table">
                        <tbody class="m-control">
                            <tr>
                                <td class="m-title">*ID</td>
                                <td><input type="text" name="id" class="form-control input-sm" name="id" value="${userVO.id }" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*이름</td>
                                <td><input class="form-control input-sm" name="name" value="${userVO.name }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호</td>
                                <td><input class="form-control input-sm" name="pw" type="password"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호확인</td>
                                <td><input class="form-control input-sm" name="pw" type="password"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*E-mail</td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="email" value="${email1 }">@
                                    <select class="form-control input-sm sel" onchange="change(this)">
                                        <option value="naver.com"${userVO.email2 == naver.com ? 'selected' : '' }>naver.com</option>
                                        <option value="gamil.com"${userVO.email2 == gmail.com? 'selected' : '' }>gmail.com</option>
                                        <option value="daum.net" ${userVO.email2 == daum.net? 'selected' : '' }>daum.net</option>
                                    </select>
                                    <button class="btn btn-info">중복확인</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*휴대폰</td>
                                <td>
                                    <input class="form-control input-sm sel" value="${phone1 }" name="phone1"> -
                                    <input class="form-control input-sm sel" value="${phone2 }" name="phone2"> -
                                    <input class="form-control input-sm sel" value="${phone3 }" name="phone3">
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*주소</td>
                                <td><input class="form-control input-sm add" name="name" value="${address1}"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*상세주소</td>
                                <td><input class="form-control input-sm add" name="name" value="${address2 }"></td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="titlefoot">
                        <button class="btn" onclick="location.href='mypage.user'">수정</button>
                        <button class="btn" onclick="location.href='mypage.user'">목록</button>
                    </div>
                    
                </div>


            </div>

        </div>

    </section>
    
    
 
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <%@include file="../include/footer.jsp" %>
</body>
</html>