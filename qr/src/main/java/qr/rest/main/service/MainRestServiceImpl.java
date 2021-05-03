package qr.rest.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import qr.rest.main.dao.MainRestDAO;

@Service
@Log4j
public class MainRestServiceImpl implements MainRestService {
	@Inject
	private MainRestDAO mainRestDAO;

	@Override
	public void updatePlace(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mainRestDAO.updatePlace(param);
	}

	@Override
	public String generateQR() throws Exception {
		// TODO Auto-generated method stub
		return mainRestDAO.generateQR();
	}

	@Override
	public List<Map<String, Object>> getQRList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainRestDAO.getQRList(param);
	}

	@Override
	public int checkPlace(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainRestDAO.checkPlace(param);
	}
}
