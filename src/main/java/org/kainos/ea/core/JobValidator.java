package org.kainos.ea.core;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;

public class JobValidator {
    public boolean isValidJob (JobRequest jobRequest) throws JobRoleTooLongException, BandNameTooLongException, SpecificationTooLongException, ResponsibilitiesTooLongException {
        if (jobRequest.getJobRole().length() > 60) {
            throw new JobRoleTooLongException();
        }
        if (jobRequest.getBand().length() > 100) {
            throw new BandNameTooLongException();
        }
        if (jobRequest.getSpecifications().length() > 100) {
            throw new SpecificationTooLongException();
        }
        if (jobRequest.getResponsibilities().length() > 100) {
            throw new ResponsibilitiesTooLongException();
        }
        return true;
    }
}
