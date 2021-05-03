<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- fontAwesome kit code -->
<script src="https://kit.fontawesome.com/6147d10ea4.js" crossorigin="anonymous"></script>
<!-- DatePicker CDN -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://kit.fontawesome.com/6147d10ea4.js" crossorigin="anonymous"></script>
<title>MAGAZINE P</title>

<script type="text/javascript">
var qr = "";
$(document).ready(function(){
	/*
		값 설정 시작
	*/
	listRefresh();
	list();
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
	$('#generate').on('click', function() {
		$.ajax({
			type : 'post'
			, url : '/rest/main/generateQR'
			, data : {}
			, success : function(data) {
				$('#show').css('background-image', 'url(../qrImg/' + data + '.jpg)');
				$('#selectUser').text('USER ' + ($('.getQR').length + 1));
				qr = data;
				listRefresh();
			}
			, error : function(e) {
				console.log(e.result);
			}
		});
	});
	$('#generateURL').on('click', function() {
		$.ajax({
			type : 'post'
			, url : '/rest/main/generateQR'
			, data : { }
			, success : function(data) {
				$('#show').css('background-image', 'url(../qrImg/' + data + '.jpg)');
				$('#selectUser').text('USER ' + ($('.getQR').length + 1));
				qr = data;
				listRefresh();
			}
			, error : function(e) {
				console.log(e.result);
			}
		});
	});
	
	$('body').on('click', '.getQR', function() {
		var qrCode = $(this).data('qr');
		qr = $(this).data('qr');
		var index = $('.getQR').index(this);
		
		$('#selectUser').text('USER ' + (index + 1));
		$('#show').css('background-image', 'url(../qrImg/' + qrCode + '.jpg)');
		$('html, body').animate({scrollTop : 0}, 500);
	});
	$('#list').on('click', function() {
		$('html, body').animate({scrollTop : $('#qrList').offset().top}, 500);
	});
	/*
		이벤트 종료
	*/
});


var listInterval = null;
function list() {
	listInterval = setInterval(function(){
		if(qr != null && qr != ''){
			listRefresh();
		}
	}, 1000);
}

function listRefresh() {
	$.ajax({
		type : 'GET'
		, url : '/rest/main/getQRList'
		, data : {
			qrCode : qr
		}
		, success : function(data) {
			var rtvHtml = '';
			
			for(var i = 0;i < data.length;i++) {
				var d = data[i];
				rtvHtml += '<tr class="getQR" data-qr="' + d.qrCode + '">';
				rtvHtml += 	'<td>';
				rtvHtml += 		'USER ' + (i + 1);
				rtvHtml += 	'</td>';
				rtvHtml += 	'<td>';
				rtvHtml += 		'<img src="/qrImg/' + d.qrCode + '.jpg" width="100%">';
				rtvHtml += 	'</td>';
				rtvHtml += 	'<td>';
				rtvHtml += 		'' + (d.APlace == 1?'<i class="far fa-circle"></i>':'<i class="fas fa-times"></i>');
				rtvHtml += 	'</td>';
				rtvHtml += 	'<td>';
				rtvHtml += 		'' + (d.BPlace == 1?'<i class="far fa-circle"></i>':'<i class="fas fa-times"></i>');
				rtvHtml += 	'</td>';
				rtvHtml += 	'<td>';
				rtvHtml += 		'' + (d.CPlace == 1?'<i class="far fa-circle"></i>':'<i class="fas fa-times"></i>');
				rtvHtml += 	'</td>';
				rtvHtml += '</tr>';
			}

			$('#qrList tbody').empty();
			$('#qrList tbody').append(rtvHtml);
		}
		, error : function(e) {
			console.log(e.result);
		}
	});
}
</script>
</head>
<body>
    <!-- 본문 영역 (시작) -------------------------->
    <!-- 
	<div>
     -->
	<div style="display: grid;grid-template-columns: 50% 50%;">
		<div id="selectUser" style="font-size:1.8rem;margin-bottom: 2rem;">NONE</div>
		
		<div>
		<!--
			<input type="button" id="list" value="QR 코드 리스트 보기" style="font-size:1.8rem;">
		 -->
			<input type="button" id="generate" value="QR ID 생성" style="font-size:1.8rem;">
		</div>
		
		<div id="show" style="width: 500px;height: 500px;background-repeat: no-repeat;background-size: cover;background-position: center;margin-bottom: 2rem;"></div>
		
		<div>
			<table id="qrList" style="width:700px;text-align: center;font-size:1.8rem;">
				<colgroup>
					<col width="*"/>
					<col width="40%"/>
					<col width="*"/>
					<col width="*"/>
					<col width="*"/>
				</colgroup>
				<thead>
					<tr>
						<th>유저</th>
						<th>QRCODE</th>
						<th>A 장소</th>
						<th>B 장소</th>
						<th>C 장소</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 본문 영역 (종료) -->
</body>
</html>