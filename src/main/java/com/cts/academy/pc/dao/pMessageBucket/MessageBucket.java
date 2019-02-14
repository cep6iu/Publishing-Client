package com.cts.academy.pc.dao.pMessageBucket;

public class MessageBucket {

    public static final String OBJECT_CLASS = "pMsgBucket";
    public static final String PARTNER_ID = "partnerId";
    public static final String TICK = "tick";

    private String objectClass = OBJECT_CLASS;
    private String dn;
    private int partnerId;
    private int tick;

    public int getTick() {
        return tick;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerID) {
        this.partnerId = partnerID;
    }

    public String getRdnAttrName() {
        return TICK;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public String toString() {
        return "ObjectClass= " + objectClass +  ", partnerId=" + partnerId + ", dn='" + dn  + ", tick='" + tick;
    }
}

