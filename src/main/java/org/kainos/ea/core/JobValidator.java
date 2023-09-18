package org.kainos.ea.core;
import org.kainos.ea.cli.JobRequest;

public class JobValidator {
    public String isValidJob (JobRequest job) {
        if (job.getJobRole().length() > 60) {
            return "JobRole exceeds 60 characters";
        }

        if (job.getBand().length() > 100) {
            return "Band exceeds 100 characters.";
        }

        if (job.getSpecifications().length() > 100) {
            return "Specifications exceeds 100 characters.";
        }

        if (job.getResponsibilities().length() > 100) {
            return "Responsibilities exceeds 100 characters.";
        }
        return null;
    }
}
