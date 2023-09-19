package org.kainos.ea.core;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;

public class JobValidator {
    public String isValidJob (JobRequest job) throws JobRoleTooLongException, BandLengthTooLongException, SpecificationsTooLongException, ResponsibilitiesTooLongException, InvalidJobException, JobRoleDoesNotExistException, FailedToUpdateJobRoleException {
        if (job.getJobRole().length() > 60) {
            throw new JobRoleTooLongException();
        }

        if (job.getBand().length() > 100) {
            throw new BandLengthTooLongException();
        }

        if (job.getSpecifications().length() > 100) {
            throw new SpecificationsTooLongException();
        }

        if (job.getResponsibilities().length() > 100) {
            throw new ResponsibilitiesTooLongException();
        }
        return null;
    }
}
