/**Creation:29 juin 2016*/
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.project;

import javax.inject.Inject;

import org.seedstack.business.assembler.BaseAssembler;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;

public class ProjectDTOAssembler extends BaseAssembler<Project, ProjectDTO> {

    @Inject
    ProjectFactory projectFactory;

    @Override
    protected void doAssembleDtoFromAggregate(ProjectDTO targetDto, Project sourceAggregate) {

        targetDto.setName(sourceAggregate.getname());
        targetDto.setResponsableId(sourceAggregate.getResponsable());
        for (Deliverable d : sourceAggregate.getDeliverables()) {
            targetDto.addDeliverableDTO(d.getname(), d.getdateStart(), d.getdateEnd(), d.getdateNewStart(), d.getdateNewEnd(), d.getcomment());
        }

    }

    @Override
    protected void doMergeAggregateWithDto(Project targetAggregate, ProjectDTO sourceDto) {

        targetAggregate.setname(sourceDto.getName());
        targetAggregate.setResponsable(sourceDto.getResponsableId());
        for (DeliverableDTO deliverable : sourceDto.getDeliverables()) {
            targetAggregate.addDeliverable(deliverable.getName(), deliverable.getdateStart(), deliverable.getdateEnd(), deliverable.getdateNewStart(),
                    deliverable.getdateNewEnd(), deliverable.getcomment());
        }

    }

}
