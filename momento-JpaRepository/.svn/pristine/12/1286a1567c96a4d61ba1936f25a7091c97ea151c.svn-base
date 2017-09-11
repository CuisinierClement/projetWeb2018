/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.user;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.seedstack.business.assembler.FluentAssembler;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.DataImporter;
import org.seedstack.seed.DataSet;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;

/**
 * @author adrien.lauer@mpsa.com Customer
 */
@DataSet(group = "manage-domain", name = "users")
public class UserDataImporter implements DataImporter<UserDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataImporter.class);

    @Inject
    private UserRepository userRepository;

    private Set<UserDTO> staging = new HashSet<>();

    @Inject
    private FluentAssembler fluentAssembler;

    @Transactional
    @JpaUnit("manage-domain")
    @Override
    public boolean isInitialized() {
        return userRepository.count() != 0;
    }

    @Override
    public void importData(UserDTO data) {
        staging.add(data);
    }

    @Transactional
    @JpaUnit("manage-domain")
    @Override
    public void commit(boolean clear) {
        if (clear) {
            LOGGER.info("Clearing users");
            userRepository.deleteAll();
        }

        LOGGER.info("staging size: " + staging.size());

        for (UserDTO userDTO : staging) {
            userRepository.persist(fluentAssembler.merge(userDTO).into(User.class).fromFactory());
        }

        LOGGER.info("Import of users completed");
        staging.clear();
    }

    @Override
    public void rollback() {
        staging.clear();
        LOGGER.warn("Rollback occurred during user import");
    }
}
