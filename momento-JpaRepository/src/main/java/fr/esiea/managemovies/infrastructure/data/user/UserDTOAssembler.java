/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.user;

import org.seedstack.business.assembler.BaseAssembler;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;

/**
 * @author adrien.lauer@mpsa.com
 */
public class UserDTOAssembler extends BaseAssembler<User, UserDTO> {

    @Override
    protected void doAssembleDtoFromAggregate(UserDTO targetDto, User sourceAggregate) {
        targetDto.setlogin(sourceAggregate.getEntityId());
        targetDto.setFirstName(sourceAggregate.getfirstName());
        targetDto.setName(sourceAggregate.getname());

    }

    @Override
    protected void doMergeAggregateWithDto(User targetAggregate, UserDTO sourceDto) {
        targetAggregate.setfirstName(sourceDto.getFirstName());
        targetAggregate.setname(sourceDto.getName());

    }
}
