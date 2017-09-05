package com.inetpsa.pi201.manageprojects.rest.project;

import java.util.Date;

/**
 * @author THIPHAINE PEREZ ZANCA
 * @version 1.0
 * @created 24-juin-2016 09:25:57
 */
public class DeliverableRepresentation {

    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Date dateNewStart;
    private Date dateNewEnd;
    private String responsableId;
    private String comment;

    public DeliverableRepresentation() {

    }

    public DeliverableRepresentation(String pName, Date pDateStart, Date pDateEnd, Date pDateNewStart, Date pDateNewEnd, String pComment) {
        this.name = pName;
        this.dateStart = pDateStart;
        this.dateEnd = pDateEnd;
        this.dateNewStart = pDateNewStart;
        this.dateNewEnd = pDateNewEnd;
        this.comment = pComment;
    }

    public void finalize() throws Throwable {

    }

    /**
     * @param newVal
     */
    public void setdateNewEnd(Date newVal) {
        this.dateNewEnd = newVal;
    }

    public Date getdateNewEnd() {
        return dateNewEnd;
    }

    /**
     * @param newVal
     */
    public void setdateNewStart(Date newVal) {
        this.dateNewStart = newVal;
    }

    public Date getdateNewStart() {
        return dateNewStart;
    }

    /**
     * @param newVal
     */
    public void setdateEnd(Date newVal) {
        this.dateEnd = newVal;
    }

    public Date getdateEnd() {
        return dateEnd;
    }

    /**
     * @param newVal
     */
    public void setdateStart(Date newVal) {
        this.dateStart = newVal;
    }

    public Date getdateStart() {
        return dateStart;
    }

    /**
     * @param newVal
     */
    public void setname(String newVal) {
        this.name = newVal;
    }

    public String getname() {
        return name;
    }

    /**
     * @param newVal
     */
    public void setresponsableId(String newVal) {
        this.responsableId = newVal;
    }

    public String getresponsableId() {
        return responsableId;
    }

    /**
     * @param newVal
     */
    public void setcomment(String newVal) {
        this.comment = newVal;
    }

    public String getcomment() {
        return comment;
    }

}