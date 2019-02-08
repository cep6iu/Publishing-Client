package com.cts.academy.pc.dao.pMessageXX;

public class PMessageXX {

    public static final String OBJECT_CLASS = "pMessage10";
    public static final String MESSAGE_ID = "messageId";
    public static final String MESSAGE = "message";

    private String objectClass = OBJECT_CLASS;
    private String dn;
    private String messageID;
    private String partnerID;
    private String bucketTick;
    private String message;

    public String getRdnAttrName() {
        return MESSAGE_ID;
    }

    @Override
    public String toString() {
        return "Object: " + OBJECT_CLASS  + " :[dn=" + dn + "], messageId=" + messageID + ", message=" + message; }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getBucketTick() {
        return bucketTick;
    }

    public void setBucketTick(String bucketTick) {
        this.bucketTick = bucketTick;
    }
}
