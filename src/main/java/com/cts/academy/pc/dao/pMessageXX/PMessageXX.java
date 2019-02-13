package com.cts.academy.pc.dao.pMessageXX;

/**
 * Temporal Object for  PMessageXXDAO Object Class
 * @author polun.piotr
 *
 */

public class PMessageXX {

    public static final String OBJECT_CLASS = "pMessage10";
    public static final String MESSAGE_ID = "messageId";
    public static final String MESSAGE = "message";
    public static final String MESSAGE_RE_ENQ = "nbReEnqueueAttempts";
    public static final String MESSAGE_CUSTOMER_ID = "customerIdentifier";

    private String objectClass = OBJECT_CLASS;
    private String dn;
    private int messageID;
    private int partnerID;
    private int bucketTick;
    private int messageReEnq;

    private String message;
    private String customerIdentifier;


    public String getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(String customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public int getMessageReEnq() {
        return messageReEnq;
    }

    public void setMessageReEnq(int messageReEnq) {
        this.messageReEnq = messageReEnq;
    }

    public String getRdnAttrName() {
        return MESSAGE_ID;
    }

    @Override
    public String toString() {
        return "Object: " + OBJECT_CLASS  + " :[dn=" + dn + "], messageId=" + messageID + ", reEnq="+ messageReEnq + ", message=" + message; }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageID() {
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

    public int getPartnerID() {
        return partnerID;
    }

    public void setPartnerId(int partnerID) {
        this.partnerID = partnerID;
    }

    public int getBucketTick() {
        return bucketTick;
    }

    public void setBucketTick(int bucketTick) {
        this.bucketTick = bucketTick;
    }
}
