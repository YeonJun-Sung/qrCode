package qr.rest.main.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface MainRestService {

	void updatePlace(Map<String, Object> param) throws Exception;

	String generateQR() throws Exception;

	List<Map<String, Object>> getQRList(Map<String, Object> param) throws Exception;

	int checkPlace(Map<String, Object> param) throws Exception;
}
