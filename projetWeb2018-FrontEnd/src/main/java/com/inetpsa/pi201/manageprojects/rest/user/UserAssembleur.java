/*
 * Creation : 9 mai 2016
 */
package com.inetpsa.pi201.manageprojects.rest.user;

import org.seedstack.business.assembler.BaseAssembler;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;

public class UserAssembleur extends BaseAssembler<User, UserRepresentation> {

    @Override
    protected void doAssembleDtoFromAggregate(UserRepresentation targetDto, User sourceAggregate) {

        targetDto.setFirstName(sourceAggregate.getfirstName());
        targetDto.setLogin(sourceAggregate.getlogin());
        targetDto.setName(sourceAggregate.getname());

    }

    @Override
    protected void doMergeAggregateWithDto(User targetAggregate, UserRepresentation sourceDto) {

        targetAggregate.setfirstName(sourceDto.getFirstName());
        targetAggregate.setlogin(sourceDto.getLogin());
        targetAggregate.setname(sourceDto.getName());

    }

}
