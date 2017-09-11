/**Creation:29 juin 2016*/
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seedstack.business.assembler.MatchingEntityId;
import org.seedstack.business.assembler.MatchingFactoryParameter;

public class ProjectDTO {

    private String name;
    private List<DeliverableDTO> deliverables = new ArrayList<>();
    private String responsableId;

    public void addDeliverableDTO(String name, Date dateStart, Date dateEnd, Date dateNewStart, Date dateNewEnd, String comment) {
        deliverables.add(new DeliverableDTO(name, dateStart, dateEnd, dateNewStart, dateNewEnd, comment));
    }

    @MatchingEntityId
    @MatchingFactoryParameter(index = 0)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeliverableDTO> getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(List<DeliverableDTO> deliverables) {
        this.deliverables = deliverables;
    }

    public String getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(String responsableId) {
        this.responsableId = responsableId;
    }

}
