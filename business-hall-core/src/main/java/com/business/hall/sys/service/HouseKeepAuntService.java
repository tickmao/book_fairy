package com.business.hall.sys.service;

import com.business.hall.sys.model.HouseKeepAunt;

import java.util.List;

public interface HouseKeepAuntService {
	int deleteByPrimaryKey(Integer hkaId);

	int insert(HouseKeepAunt record);

	int insertSelective(HouseKeepAunt record);

	HouseKeepAunt selectByPrimaryKey(Integer hkaId);

	int updateByPrimaryKeySelective(HouseKeepAunt record);

	int updateByPrimaryKey(HouseKeepAunt record);

	List<HouseKeepAunt> selectBySimpleCondition(Integer hksId, Integer hkiId, Integer hkaAgeBegin, Integer hkaAgeEnd, String hkaBirthPlace,
                                                String hkaAddress, Integer hkaTreatmentBegin, Integer hkaTreatmentEnd,
                                                String hkaName, String hkaPhone, Integer hkaWorkYearsBegin, Integer hkaWorkYearsEnd,
                                                String hkaFamilyStatus, String auntLabelId, String hkaIdentityCard,
                                                Integer beginNum, Integer pageSize);
}
