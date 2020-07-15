package com.dustin.springbootblog.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonPropertyOrder({ "type", "textingTime", "invoiceDate", "invoiceNumber", "buyerId", "buyerName", "bTel", "bMail", "processStatus", "processResult" })
//@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsProcessing {

	private String productCode;
	private String invoiceNumber;
	private String buyerId;
	private String textingTime;
	private String seriesNo;
	private String processStatus;
	private String uniCode;
	private String companyCode;
	private String processResult;
	private String bTel;
	private String bMail;
	private String type;
	private String buyerName;
	private String invoiceDate;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getTextingTime() {
		return textingTime;
	}

	public void setTextingTime(String textingTime) {
		this.textingTime = textingTime;
	}

	public String getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getUniCode() {
		return uniCode;
	}

	public void setUniCode(String uniCode) {
		this.uniCode = uniCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getbTel() {
		return bTel;
	}

	public void setbTel(String bTel) {
		this.bTel = bTel;
	}

	public String getbMail() {
		return bMail;
	}

	public void setbMail(String bMail) {
		this.bMail = bMail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public SmsProcessing() {
		super();
	}

	public SmsProcessing(String productCode, String invoiceNumber, String buyerId, String textingTime, String seriesNo, String processStatus, String uniCode, String companyCode, String processResult,
			String bTel, String bMail, String type, String buyerName, String invoiceDate) {
		super();
		this.productCode = productCode;
		this.invoiceNumber = invoiceNumber;
		this.buyerId = buyerId;
		this.textingTime = textingTime;
		this.seriesNo = seriesNo;
		this.processStatus = processStatus;
		this.uniCode = uniCode;
		this.companyCode = companyCode;
		this.processResult = processResult;
		this.bTel = bTel;
		this.bMail = bMail;
		this.type = type;
		this.buyerName = buyerName;
		this.invoiceDate = invoiceDate;
	}

	@Override
	public String toString() {
		return "SmsProcessing [productCode=" + productCode + ", invoiceNumber=" + invoiceNumber + ", buyerId=" + buyerId + ", textingTime=" + textingTime + ", seriesNo=" + seriesNo
				+ ", processStatus=" + processStatus + ", uniCode=" + uniCode + ", companyCode=" + companyCode + ", processResult=" + processResult + ", bTel=" + bTel + ", bMail=" + bMail + ", type="
				+ type + ", buyerName=" + buyerName + ", invoiceDate=" + invoiceDate + "]";
	}
	
}
