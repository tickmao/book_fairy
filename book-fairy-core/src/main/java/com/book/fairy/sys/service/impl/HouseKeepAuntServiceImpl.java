package com.book.fairy.sys.service.impl;

import com.book.fairy.sys.dao.HouseKeepAuntDao;
import com.book.fairy.sys.model.HouseKeepAunt;
import com.book.fairy.sys.service.HouseKeepAuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseKeepAuntServiceImpl implements HouseKeepAuntService {
	@Autowired
	HouseKeepAuntDao houseKeepAuntDao;

	@Override
	public int deleteByPrimaryKey(Integer hkaId) {
		return houseKeepAuntDao.deleteByPrimaryKey(hkaId);
	}

	@Override
	public int insert(HouseKeepAunt record) {
		return houseKeepAuntDao.insert(record);
	}

	@Override
	public int insertSelective(HouseKeepAunt record) {
		return houseKeepAuntDao.insertSelective(record);
	}

	@Override
	public HouseKeepAunt selectByPrimaryKey(Integer hkaId) {
		return houseKeepAuntDao.selectByPrimaryKey(hkaId);
	}

	@Override
	public int updateByPrimaryKeySelective(HouseKeepAunt record) {
		return houseKeepAuntDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HouseKeepAunt record) {
		return houseKeepAuntDao.updateByPrimaryKey(record);
	}

	@Override
	public List<HouseKeepAunt> selectBySimpleCondition(Integer hksId, Integer hkiId, Integer hkaAgeBegin, Integer hkaAgeEnd, String hkaBirthPlace,
													   String hkaAddress, Integer hkaTreatmentBegin, Integer hkaTreatmentEnd,
													   String hkaName, String hkaPhone,Integer hkaWorkYearsBegin, Integer hkaWorkYearsEnd,
													   String hkaFamilyStatus, String auntLabelId, String hkaIdentityCard,
													   Integer beginNum, Integer pageSize) {
		return houseKeepAuntDao.selectBySimpleCondition(hksId, hkiId, hkaAgeBegin, hkaAgeEnd, hkaBirthPlace, hkaAddress, hkaTreatmentBegin,
				hkaTreatmentEnd, hkaName, hkaPhone, hkaWorkYearsBegin, hkaWorkYearsEnd,
				hkaFamilyStatus, auntLabelId, hkaIdentityCard, beginNum, pageSize);
	}


	public int countBySimpleCondition(Integer hksId, Integer hkiId, Integer hkaAgeBegin, Integer hkaAgeEnd, String hkaBirthPlace,
													   String hkaAddress, Integer hkaTreatmentBegin, Integer hkaTreatmentEnd,
													   String hkaName, String hkaPhone, Integer hkaWorkYearsBegin, Integer hkaWorkYearsEnd,
													   String hkaFamilyStatus, String auntLabelId, String hkaIdentityCard) {
		return houseKeepAuntDao.countBySimpleCondition(hksId, hkiId, hkaAgeBegin, hkaAgeEnd, hkaBirthPlace, hkaAddress, hkaTreatmentBegin,
				hkaTreatmentEnd, hkaName,hkaPhone, hkaWorkYearsBegin, hkaWorkYearsEnd,
				hkaFamilyStatus, auntLabelId, hkaIdentityCard);
	}


}
