package qr.rest.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import qr.common.dao.AbstractDAO;

@Repository("mainRestDAO")
public class MainRestDAO extends AbstractDAO {

	public void updatePlace(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mainRest.updatePlace", param);
	}

	public String generateQR() {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		
		insert("mainRest.generateQR", param);
		
		return (String) param.get("qrCode");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getQRList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("mainRest.getQRList", param);
	}

	public int checkPlace(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return (Integer) selectOne("mainRest.checkPlace", param);
	}
}
