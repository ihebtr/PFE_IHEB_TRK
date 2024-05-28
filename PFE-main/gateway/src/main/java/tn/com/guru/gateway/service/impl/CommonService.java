package tn.com.guru.gateway.service.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.guru.gateway.dao.ICommonDao;
import tn.com.guru.gateway.model.tool.DaoObject;
import tn.com.guru.gateway.model.tool.SearchObject;
import tn.com.guru.gateway.model.tool.SendObject;
import tn.com.guru.gateway.service.ICommonService;
import tn.com.guru.gateway.tools.ConstanteService;
import tn.com.guru.gateway.tools.ConstanteWs;
import tn.com.guru.gateway.tools.UtilsDao;
import tn.com.guru.gateway.tools.UtilsWs;

@Service
public class CommonService implements ICommonService {

	private static final Logger logger = LogManager.getLogger(CommonService.class);

	@Autowired
	private ICommonDao commonDao;
	
	@Autowired
	private UtilsWs utilsWs;
	
	@Autowired
	private UtilsDao utilsDao;

	@Override
	public SendObject getListPaginator(SearchObject obj, Object objClass, String particularSpecifCondi) {
		try {
			obj = utilsDao.initSearchObject(obj);
			DaoObject daoObject = commonDao.getListPaginator(obj, objClass, particularSpecifCondi);
			if (!daoObject.getCode().equals(ConstanteService._CODE_DAO_SUCCESS))
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
			return utilsWs.resultPaginationWs(ConstanteWs._CODE_WS_SUCCESS, daoObject.getObjectReturn(), daoObject.getCountTotal());
		} catch (Exception e) {
			logger.error("Error CommonService in method getListPaginator of class " + objClass.getClass().getName() + " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi) {
		try {
			obj = utilsDao.initSearchObject(obj);
			DaoObject daoObject = commonDao.getListPaginatorNative(obj, objClass, particularSpecifCondi);
			if (!daoObject.getCode().equals(ConstanteService._CODE_DAO_SUCCESS))
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
			return utilsWs.resultPaginationWs(ConstanteWs._CODE_WS_SUCCESS, daoObject.getObjectReturn(),
					daoObject.getCountTotal());
		} catch (Exception e) {
			logger.error("Error CommonService in method getListPaginator of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getObjectById(Object objClass, String valueId, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getObjectById(objClass, valueId, nativeSQL, null);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectById of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getObjectById(Object objClass, String valueId, String particularSpecifCondi, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getObjectById(objClass, valueId, nativeSQL, particularSpecifCondi);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectById of class " + objClass.getClass().getName() + " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getListObject(Object objClass, SearchObject obj, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getListObject(obj, objClass, null, nativeSQL);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getListObject(Object objClass, SearchObject obj, String particularSpecifCondi,
			Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getListObject(obj, objClass, particularSpecifCondi, nativeSQL);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue) {
		try {
			DaoObject daoObject = commonDao.getUniqueCode(objClass, colCode, (idValue != null ? idValue.toString() : null), codeValue);
			if (daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(((Boolean) daoObject.getObjectReturn() ? ConstanteService._CODE_SERVICE_SUCCESS : ConstanteService._CODE_SERVICE_ERROR_UNIQUE_CODE), null, null);
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getSingleObject(Object objClass, String particularSpecifCondi, Boolean nativeSQL) {
		try {
			DaoObject daoObject = commonDao.getSingleObject(objClass, particularSpecifCondi, nativeSQL);
			if (daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObject.getCode(), daoObject.getObjectReturn());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getSingleObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		}
	}


	@Override
	public SendObject getDateSystemNow() {
		try {
			DaoObject daoObject = commonDao.getDateSystemNow();
			if (!daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObject.getCode());
			Timestamp date = (Timestamp) daoObject.getObjectReturn();
			return new SendObject(ConstanteService._CODE_SERVICE_SUCCESS, date); 
		} catch (Exception e) {
			logger.error("Error CommonService in method getDateSystemNow :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		}
	}

	@Override
	public SendObject getDateSystemNowWs() {
		try {
			SendObject so = this.getDateSystemNow();
			return utilsWs.resultWs(so.getCode(), so.getPayload());
		} catch (Exception e) {
			logger.error("Error CommonService in method getDateSystemNowWs " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getObjectByIdWs(Object objClass, String valueId, Boolean nativeSQL) {
		try {
			SendObject so = this.getObjectById(objClass, valueId, nativeSQL);
			return utilsWs.resultWs(so.getCode(), so.getPayload());
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectByIdWs of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

}
