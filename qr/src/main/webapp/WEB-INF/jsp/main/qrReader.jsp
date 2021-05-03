<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- fontAwesome kit code -->
<script src="https://kit.fontawesome.com/6147d10ea4.js" crossorigin="anonymous"></script>
<!-- DatePicker CDN -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/index.css">

<script src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>

<title>MAGAZINE P</title>

<script type="text/javascript">

$(document).ready(function(){
	/*
		값 설정 시작
	*/
	/*
		값 설정 종료
	*/

	/*
		페이지 이동 시작
	*/
	/*
		페이지 이동 종료
	*/
	
	/*
		이벤트 시작
	*/
	let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
	scanner.addListener('scan', function (content) {
		var place = $('#place').val();

		$.ajax({
			type : 'post'
			, url : '/rest/main/checkQR'
			, data : {
				qr : content
				, place : place
			}
			, success : function(data) {
				if(data == 'already') {
					$('#result').text('이미 사용된 장소입니다.');
					$('#modal').show();
				}
				else if(data == 'complete') {
					$('#result').text('인증되었습니다.');
					$('#modal').show();
				}
				
				setTimeout(function() {
					$('#result').text('');
					$('#modal').hide();
				}, 3000);
			}
			, error : function(e) {
				console.log(e.result);
			}
		});
	});
	Instascan.Camera.getCameras().then(function (cameras) {
		if (cameras.length > 0) {
			var selectedCam = cameras[0];
/*
			$.each(cameras, (i, c) => {
				if (c.name.indexOf('전면') != -1) {
					selectedCam = c;
					return false;
				}
			});
*/
			scanner.start(selectedCam);
		} else {
			alert('No cameras found.');
		}
	}).catch(function (e) {
		console.error(e);
	});
	/*
		이벤트 종료
	*/
});
</script>
</head>
<body>
<!-- 본문 영역 (시작) -------------------------->
		<!-- 
<input type="hidden" id="place" value="APlace">
		 -->
	<div>
		<select id="place" style="font-size:3rem;">
			<option value="APlace">A 장소</option>
			<option value="BPlace">B 장소</option>
			<option value="CPlace">C 장소</option>
		</select>
	</div>
	
	<div>
		<video id="preview" playsinline width="100%" height="100%"></video>
	</div>
    
    <div style="display:none;position:absolute;width:100%;height:100%;z-index:99;left:0;top:0;background-color:#00000055" id="modal">
	    <div style="position:absolute;left:50%;top:50%;width:100%;height:15rem;z-index:100;background-color:white;transform: translateX(-50%) translateY(-50%);font-size:3rem;line-height:15rem;text-align:center" id="result">
	    </div>
    </div>
	<!-- 본문 영역 (종료) -->
</body>
</html>