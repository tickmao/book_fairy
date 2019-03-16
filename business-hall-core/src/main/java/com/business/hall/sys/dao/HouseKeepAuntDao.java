package com.business.hall.sys.dao;

import com.business.hall.sys.model.HouseKeepAunt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseKeepAuntDao {
    int deleteByPrimaryKey(Integer hkaId);

    int insert(HouseKeepAunt record);

    int insertSelective(HouseKeepAunt record);

    HouseKeepAunt selectByPrimaryKey(Integer hkaId);

    int updateByPrimaryKeySelective(HouseKeepAunt record);

    int updateByPrimaryKey(HouseKeepAunt record);

    List<HouseKeepAunt> selectBySimpleCondition(@Param("hksId") Integer hksId,//家政商店id
                                                @Param("hkiId") Integer hkiId,//家政项目id
                                                @Param("hkaAgeBegin") Integer hkaAgeBegin,//年龄
                                                @Param("hkaAgeEnd") Integer hkaAgeEnd,//年龄
                                                @Param("hkaBirthPlace") String hkaBirthPlace,//籍贯
                                                @Param("hkaAddress") String hkaAddress,//住址
                                                @Param("hkaTreatmentBegin") Integer hkaTreatmentBegin,//待遇
                                                @Param("hkaTreatmentEnd") Integer hkaTreatmentEnd,//待遇
                                                @Param("hkaName") String hkaName,//名称
                                                @Param("hkaPhone") String hkaPhone,//手机
                                                @Param("hkaWorkYearsBegin") Integer hkaWorkYearsBegin,//工作年限
                                                @Param("hkaWorkYearsEnd") Integer hkaWorkYearsEnd,//工作年限
                                                @Param("hkaFamilyStatus") String hkaFamilyStatus,//婚姻状况
                                                @Param("auntLabelId") String auntLabelId,//阿姨评价标签
                                                @Param("hkaIdentityCard") String hkaIdentityCard,//身份证编码
                                                @Param("beginNum") Integer beginNum,
                                                @Param("pageSize") Integer pageSize);


    Integer countBySimpleCondition(@Param("hksId") Integer hksId,//家政商店id
                                   @Param("hkiId") Integer hkiId,//家政项目id
                                   @Param("hkaAgeBegin") Integer hkaAgeBegin,//年龄
                                   @Param("hkaAgeEnd") Integer hkaAgeEnd,//年龄
                                   @Param("hkaBirthPlace") String hkaBirthPlace,//籍贯
                                   @Param("hkaAddress") String hkaAddress,//住址
                                   @Param("hkaTreatmentBegin") Integer hkaTreatmentBegin,//待遇
                                   @Param("hkaTreatmentEnd") Integer hkaTreatmentEnd,//待遇
                                   @Param("hkaName") String hkaName,//名称
                                   @Param("hkaPhone") String hkaPhone,//手机
                                   @Param("hkaWorkYearsBegin") Integer hkaWorkYearsBegin,//工作年限
                                   @Param("hkaWorkYearsEnd") Integer hkaWorkYearsEnd,//工作年限
                                   @Param("hkaFamilyStatus") String hkaFamilyStatus,//婚姻状况
                                   @Param("auntLabelId") String auntLabelId,//阿姨评价标签
                                   @Param("hkaIdentityCard") String hkaIdentityCard);//身份证编码

}