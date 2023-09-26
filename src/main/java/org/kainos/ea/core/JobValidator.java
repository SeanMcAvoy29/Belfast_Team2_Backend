package org.kainos.ea.core;

import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;

public class JobValidator {
    public boolean isValidJob(JobRequest jobRequest) throws JobRoleTooLongException, BandIDDoesNotExistException, SpecificationsTooLongException, ResponsibilitiesTooLongException {
        if (jobRequest.getJobRole().length() > 60) {
            throw new JobRoleTooLongException();
        }
        if (jobRequest.getBandID() > 100000) {
            throw new BandIDDoesNotExistException();
        }
        if (jobRequest.getJobSpecification().length() > 100) {
            throw new SpecificationsTooLongException();
        }
        if (jobRequest.getResponsibilities().length() > 100) {
            throw new ResponsibilitiesTooLongException();
        }
        return true;
    }
}
