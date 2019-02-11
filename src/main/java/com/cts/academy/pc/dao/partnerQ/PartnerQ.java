package com.cts.academy.pc.dao.partnerQ;

/**
 * Temporal Object for  PartnerQDAO
 * @author polun.piotr
 *
 */

public class PartnerQ {

    public static final String OBJECT_CLASS = "partnerQ";
    public static final String partnerQ_ID = "partnerId";
    public static final String partnerQ_START_TICK = "startTick";
    public static final String partnerQ_END_TICK = "endTick";

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    private String objectClass = OBJECT_CLASS;

    private String dn;
    private String partnerId;
    private String startTick;
    private String endTick;
    public String getRdnAttrName() {
        return startTick;
    }

    public String getObjectClass() {
        return objectClass;
    }

    @Override
    public String toString() {
        return "Object: " + OBJECT_CLASS  + " :[dn=" + dn + "], partnerId=" + partnerId + ", startTick=" + startTick + ", endTick" + endTick; }


    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getStartTick() {
        return startTick;
    }

    public void setStartTick(String startTick) {
        this.startTick = startTick;
    }

    public String getEndTick() {
        return endTick;
    }

    public void setEndTick(String endTick) {
        this.endTick = endTick;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }
}
