package qr.rest.main.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.lome.niceqr.QrConfiguration;
import org.lome.niceqr.QrEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.log4j.Log4j;
import qr.rest.main.service.MainRestService;

@Log4j
@Controller
@RequestMapping("/rest/main/*")
public class MainRestController {
	
	@Inject
	private MainRestService mainRestService;

	/*
	 *	Method		: POST
	 *	Role		: qr코드 생성
	 *	Table		: 
	 *	Parameter	: 
	 *	Return		: img name
	 */
	@PostMapping("/generateQR")
	public ResponseEntity<String> generateQR(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			QRCodeWriter writer = new QRCodeWriter();
			String param = mainRestService.generateQR();
			param = new String(param.getBytes("UTF-8"), "ISO-8859-1");

			String path = req.getSession().getServletContext().getRealPath("/").replace("\\qr", "");
			String logo = req.getSession().getServletContext().getRealPath("/") + "/resources/img/logo.jpg";
			
			QrEngine.buildQrCodeWithLogo(param
					, new File(logo)
					, new File(path + "/qrImg/" + param + ".jpg")
					, QrConfiguration.builder()
					.withSize(400)
					.withRelativeBorderSize(.05)
					.withRelativeBorderRound(.2)
					.withDarkColor(Color.black)
					.withLightColor(Color.white)
					.withPositionalsColor(Color.black)
					.withCircularPositionals(false)
					.withRelativePositionalsRound(.3)
					.withRelativeLogoSize(.249)
					.build());
			
			log.info(path);
			
			//ImageIO.write(qrimage, "jpg", new File(path + "/qrImg/" + param + ".jpg"));
			
			return new ResponseEntity<String>(param, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 *	Method		: POST
	 *	Role		: qr코드 체크
	 *	Table		: 
	 *	Parameter	: 
	 *	Return		: img name
	 */
	@PostMapping("/checkQR")
	public ResponseEntity<String> checkQR(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String qr = (String) req.getParameter("qr");
			String place = (String) req.getParameter("place");
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("qr", qr);
			param.put("place", place);
			
			int used = mainRestService.checkPlace(param);
			
			String result = "";
			if(used == 0) {
				mainRestService.updatePlace(param);
				result = "complete";
			}
			else if(used == 1) {
				result = "already";
			}
			
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 *	Method		: GET
	 *	Role		: qr 리스트 가져오기
	 *	Table		: 
	 *	Parameter	: 
	 *	Return		: 
	 */
	@GetMapping("/getQRList")
	public ResponseEntity<List<Map<String, Object>>> getQRList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String qr = (String) req.getParameter("qr");
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("qr", qr);
			
			List<Map<String, Object>> list = mainRestService.getQRList(param);
			
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
		}
	}
}