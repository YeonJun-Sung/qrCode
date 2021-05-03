package qr.main.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import qr.main.service.MainService;

@Log4j
@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Inject
	private MainService mainService;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest req, HttpSession session, Model model) {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/qrGenerator")
	public void qrGenerator(HttpServletRequest req, HttpSession session, Model model) {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/qrReader")
	public void qrReader(HttpServletRequest req, HttpSession session, Model model) {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}