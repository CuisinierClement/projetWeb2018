package com.inetpsa.pi201.domains.manageprojects.services.project;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.seedstack.business.Service;

/**
 * @author THIPHAINE PEREZ ZANCA
 * @version 1.0
 * @created 18-mai-2016 10:06:27
 */
@Service
public interface ProjectService {

    /**
     * @param p_fileName
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public void importProjectsWithDeliverablesFromCsvFile(String pFileName) throws FileNotFoundException, ParseException;

    /**
     * Import in data base projects from a csv file
     * 
     * @param p_fileName
     * @throws FileNotFoundException
     */
    public void importProjectsFromCsvFile(String pFileName) throws FileNotFoundException;

}