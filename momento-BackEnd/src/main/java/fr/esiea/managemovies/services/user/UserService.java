package com.inetpsa.pi201.domains.manageprojects.services.user;

import org.seedstack.business.Service;

@Service
public interface UserService {

    /**
     * Import in data base users from a csv file
     * 
     * @param p_fileName
     */
    public void importUsersFromCsvFile(String pFileName);

}