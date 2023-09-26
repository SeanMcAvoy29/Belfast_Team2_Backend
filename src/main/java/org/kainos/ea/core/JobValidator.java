package org.kainos.ea.core;

import org.kainos.ea.cli.JobRequest;

public class JobValidator {
    public boolean isValidJob(JobRequest jobRequest) {
        if (jobRequest.getJobRole().length() > 60) {
            return false;
        }
        if (jobRequest.getBandID() < 0) {
            return false;
        }
        if (jobRequest.getJobSpecification().length() > 100) {
            return false;
        }
        if (jobRequest.getResponsibilities().length() > 100) {
            return false;
        }
        return true;
    }
}
