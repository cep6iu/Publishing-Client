/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cts.academy.pc.dao.partnerConfig;

/**
 *
 * @author serghei.sciurenco
 */
public class PartnerConfig {

    public static final String OBJECT_CLASS = "partnerConfig";
    public static final String PARTNER_URL = "partnerURL";
    public static final String PARTNER_ID = "partnerId";
    public static final String PARTNER_NAME = "partnerName";
    public static final String PARTNER_MAX_NUM_RETRY = "partnerMaxNumRetry";

    private String objectClass = OBJECT_CLASS;
    private String partnerUrl = PARTNER_URL;
    private String dn;
    private String partnerName;
    private int partnerMaxNumRetry;
    private int partnerId;

    public String getObjectClass() {
        return objectClass;
    }

    public int getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public void setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
    }

    public String getDn() { return dn; }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public int getPartnerMaxNumRetry() {
        return partnerMaxNumRetry;
    }

    public void setPartnerMaxNumRetry(int partnerMaxNumRetry) {
        this.partnerMaxNumRetry = partnerMaxNumRetry;
    }

    @Override
    public String toString() {
        return "Object:" + OBJECT_CLASS + " :[dn=" + dn + "]" + ", partnerId=" + partnerId + ", partnerUrl=" + partnerUrl + ", partnerName=" + partnerName + ", partnerMaxNumRetry=" + partnerMaxNumRetry;
    }

}
