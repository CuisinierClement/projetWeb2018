/*
 * Creation : 19 avr. 2016
 */
package com.inetpsa.pi201.manageprojects.services;

import java.net.URL;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.core.utils.SeedReflectionUtils;
import org.seedstack.seed.it.AbstractSeedIT;

import com.inetpsa.pi201.domains.manageprojects.services.user.UserService;

public class UserServiceIT extends AbstractSeedIT {

    @Inject
    private UserService mUserService;

    @Configuration("com.inetpsa.pi201.domains.manageEntity-JpaRepository.importUserCsvexample")
    private String mPathFileImportCsv;

    @Before
    @Test
    public void testInjection() {
        Assertions.assertThat(mUserService).isNotNull();
        Assertions.assertThat(mPathFileImportCsv).isNotNull();
    }

    @Test
    public void getUserFromCsvFile() {

        // complete the total path
        // Create a class loader associated to the current class in oder to load resources in the META-INF
        ClassLoader lClassLoader = SeedReflectionUtils.findMostCompleteClassLoader(UserServiceIT.class);
        Assertions.assertThat(lClassLoader).isNotNull();

        URL lURL = lClassLoader.getResource(mPathFileImportCsv);
        Assertions.assertThat(lURL).isNotNull();
        String lReferenceCompletePathFileCsv = lURL.getPath();
        lReferenceCompletePathFileCsv = lReferenceCompletePathFileCsv.replace("%20", " ");

    }

}
