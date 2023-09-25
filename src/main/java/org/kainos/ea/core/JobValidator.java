package org.kainos.ea.core;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.BandNameTooLongException;
import org.kainos.ea.client.JobRoleTooLongException;
import org.kainos.ea.client.ResponsibilitiesTooLongException;
import org.kainos.ea.client.SpecificationsTooLongException;

public class JobValidator {
    public boolean isValidJob (JobRequest jobRequest) throws JobRoleTooLongException, BandNameTooLongException, SpecificationsTooLongException, ResponsibilitiesTooLongException {
        if (jobRequest.getJobRole().length() > 60) {
            throw new JobRoleTooLongException();
        }

        if (jobRequest.getBand().length() > 100) {
            throw new BandNameTooLongException();
        }

        if (jobRequest.getSpecifications().length() > 100) {
            throw new SpecificationsTooLongException();
        }

        if (jobRequest.getResponsibilities().length() > 100) {
            throw new ResponsibilitiesTooLongException();
        }
        return true;
    }
}
