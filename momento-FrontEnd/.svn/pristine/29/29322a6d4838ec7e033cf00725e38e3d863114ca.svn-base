package com.inetpsa.pi201.manageprojects.rest.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author THIPHAINE PEREZ ZANCA
 * @version 1.0
 * @created 24-juin-2016 09:25:57
 */
public class ProjectRepresentation {

    private String name;
    private List<DeliverableRepresentation> deliverable = new ArrayList<DeliverableRepresentation>();
    private String responsableId;

    public void addDeliverable(String pNameDeliverable, Date pDateStart, Date pDateEnd, Date pDateNewStart, Date pDateNewEnd, String pComment) {
        deliverable.add(new DeliverableRepresentation(pNameDeliverable, pDateStart, pDateEnd, pDateNewStart, pDateNewEnd, pComment));
    }

    public ProjectRepresentation() {

    }

    public void finalize() throws Throwable {

    }

    /**
     * @param newVal
     */
    public void setResponsable(String newVal) {
        this.responsableId = newVal;
    }

    public String getResponsable() {
        return responsableId;
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
    public void setDeliverables(List<DeliverableRepresentation> newVal) {
        this.deliverable = newVal;
    }

    public List<DeliverableRepresentation> getDeliverables() {
        return deliverable;
    }

}