package com.cts.academy.pc.dao.partnerQ;

/**
 * Temporal Object for  PartnerQDAO
 * @author polun.piotr
 *
 */

public class PartnerQ {

    public static final String OBJECT_CLASS = "partnerQ";
    public static final String PARTNER_ID = "partnerId";
    public static final String PARTNER_START_TICK = "startTick";
    public static final String PARTNER_END_TICK = "endTick";

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    private String objectClass = OBJECT_CLASS;

    private String dn;
    private int partnerId;
    private int startTick;
    private int endTick;

    public String getRdnAttrName() {
        return PARTNER_ID;
    }

    public String getObjectClass() {
        return objectClass;
    }

    @Override
    public String toString() {
        return "Object: " + OBJECT_CLASS  + " :[dn=" + dn + "], partnerId=" + partnerId + ", startTick=" + startTick + ", endTick" + endTick; }


    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getStartTick() {
        return startTick;
    }

    public void setStartTick(int startTick) {
        this.startTick = startTick;
    }

    public int getEndTick() {
        return endTick;
    }

    public void setEndTick(int endTick) {
        this.endTick = endTick;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }
}
