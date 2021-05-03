package qr.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import qr.main.dao.MainDAO;

@Service
@Log4j
public class MainServiceImpl implements MainService {
	@Inject
	private MainDAO mainDAO;
}
