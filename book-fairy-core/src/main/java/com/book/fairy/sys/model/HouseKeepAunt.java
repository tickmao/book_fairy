package com.book.fairy.sys.model;

import java.io.Serializable;
import java.util.Date;

public class HouseKeepAunt implements Serializable {
    private static final long serialVersionUID = -6525908145032868827L;

    private Integer hkaId;

    private String hkaName;

    private String hkaPhone;

    private Integer hkaAge;

    private Integer hkiId;

    private Integer hksId;

    private String hkaAddress;

    private String hkaWorkStatus;

    private Integer hkaWorkYears;

    private String hkaWorkContent;

    private String hkaFamilyStatus;

    private String hkaBirthPlace;

    private Integer hkaTreatment;

    private String hkaRecommend;

    private Integer auntLabelId;

    private String hkaIdentityCard;

    private String hkaIdentityCardPhoto;

    private String hkaLifePhoto;

    private String hkaRemark;

    private Date createTime;

    private Date updateTime;

    public Integer getHkaId() {
        return hkaId;
    }

    public void setHkaId(Integer hkaId) {
        this.hkaId = hkaId;
    }

    public String getHkaName() {
        return hkaName;
    }

    public void setHkaName(String hkaName) {
        this.hkaName = hkaName == null ? null : hkaName.trim();
    }

    public String getHkaPhone() {
        return hkaPhone;
    }

    public void setHkaPhone(String hkaPhone) {
        this.hkaPhone = hkaPhone;
    }

    public Integer getHkaAge() {
        return hkaAge;
    }

    public void setHkaAge(Integer hkaAge) {
        this.hkaAge = hkaAge;
    }

    public Integer getHkiId() {
        return hkiId;
    }

    public void setHkiId(Integer hkiId) {
        this.hkiId = hkiId;
    }

    public String getHkaAddress() {
        return hkaAddress;
    }

    public void setHkaAddress(String hkaAddress) {
        this.hkaAddress = hkaAddress == null ? null : hkaAddress.trim();
    }

    public Integer getHkaWorkYears() {
        return hkaWorkYears;
    }

    public void setHkaWorkYears(Integer hkaWorkYears) {
        this.hkaWorkYears = hkaWorkYears;
    }

    public String getHkaWorkContent() {
        return hkaWorkContent;
    }

    public void setHkaWorkContent(String hkaWorkContent) {
        this.hkaWorkContent = hkaWorkContent == null ? null : hkaWorkContent.trim();
    }

    public String getHkaFamilyStatus() {
        return hkaFamilyStatus;
    }

    public void setHkaFamilyStatus(String hkaFamilyStatus) {
        this.hkaFamilyStatus = hkaFamilyStatus == null ? null : hkaFamilyStatus.trim();
    }

    public String getHkaBirthPlace() {
        return hkaBirthPlace;
    }

    public void setHkaBirthPlace(String hkaBirthPlace) {
        this.hkaBirthPlace = hkaBirthPlace == null ? null : hkaBirthPlace.trim();
    }

    public Integer getHkaTreatment() {
        return hkaTreatment;
    }

    public void setHkaTreatment(Integer hkaTreatment) {
        this.hkaTreatment = hkaTreatment;
    }

    public String getHkaRecommend() {
        return hkaRecommend;
    }

    public void setHkaRecommend(String hkaRecommend) {
        this.hkaRecommend = hkaRecommend == null ? null : hkaRecommend.trim();
    }

    public Integer getAuntLabelId() {
        return auntLabelId;
    }

    public void setAuntLabelId(Integer auntLabelId) {
        this.auntLabelId = auntLabelId;
    }

    public String getHkaIdentityCard() {
        return hkaIdentityCard;
    }

    public void setHkaIdentityCard(String hkaIdentityCard) {
        this.hkaIdentityCard = hkaIdentityCard == null ? null : hkaIdentityCard.trim();
    }

    public String getHkaIdentityCardPhoto() {
        return hkaIdentityCardPhoto;
    }

    public void setHkaIdentityCardPhoto(String hkaIdentityCardPhoto) {
        this.hkaIdentityCardPhoto = hkaIdentityCardPhoto;
    }

    public String getHkaLifePhoto() {
        return hkaLifePhoto;
    }

    public void setHkaLifePhoto(String hkaLifePhoto) {
        this.hkaLifePhoto = hkaLifePhoto == null ? null : hkaLifePhoto.trim();
    }

    public String getHkaRemark() {
        return hkaRemark;
    }

    public void setHkaRemark(String hkaRemark) {
        this.hkaRemark = hkaRemark == null ? null : hkaRemark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getHksId() {
        return hksId;
    }

    public void setHksId(Integer hksId) {
        this.hksId = hksId;
    }

    public String getHkaWorkStatus() {
        return hkaWorkStatus;
    }

    public void setHkaWorkStatus(String hkaWorkStatus) {
        this.hkaWorkStatus = hkaWorkStatus;
    }
}