/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.user;

import org.seedstack.business.assembler.MatchingEntityId;
import org.seedstack.business.assembler.MatchingFactoryParameter;

/**
 * @author adrien.lauer@mpsa.com Customer
 */
public class UserDTO {
    private String login;
    private String firstName;
    private String name;

    @MatchingEntityId
    @MatchingFactoryParameter(index = 0)
    public String getLogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    @MatchingFactoryParameter(index = 1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @MatchingFactoryParameter(index = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
