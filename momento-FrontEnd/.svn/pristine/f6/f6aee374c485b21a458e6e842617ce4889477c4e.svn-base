/*
 * Creation : 24 juin 2016
 */
package com.inetpsa.pi201.manageprojects.rest.project;

import org.seedstack.business.assembler.BaseAssembler;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;

public class ProjectAssembler extends BaseAssembler<Project, ProjectRepresentation> {

    @Override
    protected void doAssembleDtoFromAggregate(ProjectRepresentation targetDto, Project sourceAggregate) {
        targetDto.setname(sourceAggregate.getname());
        // ArrayList<DeliverableRepresentation> idsList = new ArrayList<DeliverableRepresentation>();
        // for (Deliverable d : sourceAggregate.getDeliverables()) {
        // idsList.add(d.getname());
        // }
        // targetDto.setDeliverables(idsList);
        for (Deliverable lDeliverable : sourceAggregate.getDeliverables()) {
            targetDto.addDeliverable(lDeliverable.getname(), lDeliverable.getdateStart(), lDeliverable.getdateEnd(), lDeliverable.getdateNewStart(),
                    lDeliverable.getdateNewEnd(), lDeliverable.getcomment());
        }
        targetDto.setResponsable(sourceAggregate.getResponsable());

    }

    @Override
    protected void doMergeAggregateWithDto(Project targetAggregate, ProjectRepresentation sourceDto) {

        targetAggregate.setname(sourceDto.getname());
        // targetAggregate.setDeliverables(sourceDto.getdeliverables());
        targetAggregate.setResponsable(sourceDto.getResponsable());

    }

}
