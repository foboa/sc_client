package test.java.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QuotaApp implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2435053264125120071L;

	private Long id;

    private String cardId;

    private String name;

    private String custType;

    private String sex;

    private String mobile;

    private String degree;

    private String isFullTimeStudent;

    private String qq;

    private String weixin;

    private String email;

    private String marry;

    private String liveProv;

    private String liveCity;

    private String liveTown;

    private String liveZhen;
    
    private String liveStr;

    private String liveRidge;

    private String liveHome;

    private String unitname;

    private String unitPhone;

    private Integer unitWorktime;

    private Integer incomeMonthly;

    private Integer incomeFamily;

    private Integer spendingMonthly;

    private String industryType;

    private String industry;

    private String careerType;

    private Integer socialTime;

    private String incomeSource;

    private String isLoaned;

    private String historyLoanType;

    private String otherPfLoan;

    private BigDecimal otherPfDebtsAmt;

    private Integer quota;

    private String appStatusInner;

    private String appStatusOuter;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getIsFullTimeStudent() {
        return isFullTimeStudent;
    }

    public void setIsFullTimeStudent(String isFullTimeStudent) {
        this.isFullTimeStudent = isFullTimeStudent == null ? null : isFullTimeStudent.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    public String getLiveProv() {
        return liveProv;
    }

    public void setLiveProv(String liveProv) {
        this.liveProv = liveProv == null ? null : liveProv.trim();
    }

    public String getLiveCity() {
        return liveCity;
    }

    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity == null ? null : liveCity.trim();
    }

    public String getLiveTown() {
        return liveTown;
    }

    public void setLiveTown(String liveTown) {
        this.liveTown = liveTown == null ? null : liveTown.trim();
    }

    public String getLiveZhen() {
        return liveZhen;
    }

    public void setLiveZhen(String liveZhen) {
        this.liveZhen = liveZhen == null ? null : liveZhen.trim();
    }

    public String getLiveStr() {
		return liveStr;
	}

	public void setLiveStr(String liveStr) {
		this.liveStr = liveStr;
	}

	public String getLiveRidge() {
        return liveRidge;
    }

    public void setLiveRidge(String liveRidge) {
        this.liveRidge = liveRidge == null ? null : liveRidge.trim();
    }

    public String getLiveHome() {
        return liveHome;
    }

    public void setLiveHome(String liveHome) {
        this.liveHome = liveHome == null ? null : liveHome.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone == null ? null : unitPhone.trim();
    }

    public Integer getUnitWorktime() {
        return unitWorktime;
    }

    public void setUnitWorktime(Integer unitWorktime) {
        this.unitWorktime = unitWorktime;
    }

    public Integer getIncomeMonthly() {
        return incomeMonthly;
    }

    public void setIncomeMonthly(Integer incomeMonthly) {
        this.incomeMonthly = incomeMonthly;
    }

    public Integer getIncomeFamily() {
        return incomeFamily;
    }

    public void setIncomeFamily(Integer incomeFamily) {
        this.incomeFamily = incomeFamily;
    }

    public Integer getSpendingMonthly() {
        return spendingMonthly;
    }

    public void setSpendingMonthly(Integer spendingMonthly) {
        this.spendingMonthly = spendingMonthly;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getCareerType() {
        return careerType;
    }

    public void setCareerType(String careerType) {
        this.careerType = careerType == null ? null : careerType.trim();
    }

    public Integer getSocialTime() {
        return socialTime;
    }

    public void setSocialTime(Integer socialTime) {
        this.socialTime = socialTime;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource == null ? null : incomeSource.trim();
    }

    public String getIsLoaned() {
        return isLoaned;
    }

    public void setIsLoaned(String isLoaned) {
        this.isLoaned = isLoaned == null ? null : isLoaned.trim();
    }

    public String getHistoryLoanType() {
        return historyLoanType;
    }

    public void setHistoryLoanType(String historyLoanType) {
        this.historyLoanType = historyLoanType == null ? null : historyLoanType.trim();
    }

    public String getOtherPfLoan() {
        return otherPfLoan;
    }

    public void setOtherPfLoan(String otherPfLoan) {
        this.otherPfLoan = otherPfLoan == null ? null : otherPfLoan.trim();
    }

    public BigDecimal getOtherPfDebtsAmt() {
        return otherPfDebtsAmt;
    }

    public void setOtherPfDebtsAmt(BigDecimal otherPfDebtsAmt) {
        this.otherPfDebtsAmt = otherPfDebtsAmt;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getAppStatusInner() {
        return appStatusInner;
    }

    public void setAppStatusInner(String appStatusInner) {
        this.appStatusInner = appStatusInner == null ? null : appStatusInner.trim();
    }

    public String getAppStatusOuter() {
        return appStatusOuter;
    }

    public void setAppStatusOuter(String appStatusOuter) {
        this.appStatusOuter = appStatusOuter == null ? null : appStatusOuter.trim();
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
}