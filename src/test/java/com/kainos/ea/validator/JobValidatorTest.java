package com.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.BandNameTooLongException;
import org.kainos.ea.client.JobRoleTooLongException;
import org.kainos.ea.client.ResponsibilitiesTooLongException;
import org.kainos.ea.client.SpecificationTooLongException;
import org.kainos.ea.core.JobValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobValidatorTest {

    JobValidator jobValidator = new JobValidator();

    @Test
    public void isValidEmployee_shouldReturnTrue_whenValidEmployee() throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        JobRequest jobRequest = new JobRequest(
                8,
                "Admin",
                "4",
                "Placement Employee",
                "Coding"
        );

        assertTrue(jobValidator.isValidJob(jobRequest));
    }

    @Test
    public void isValidJob_shouldThrowJobRoleTooLongException_whenJobRoleOver60Characters() throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        JobRequest jobRequest = new JobRequest(
                8,
                "Adminnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
                "4",
                "Placement Employee",
                "Coding"
        );

        assertThrows(JobRoleTooLongException.class,
                () -> jobValidator.isValidJob(jobRequest));
    }

    @Test
    public void isValidJob_shouldThrowBandNameTooLongException_whenBandNameOver100Characters() throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        JobRequest jobRequest = new JobRequest(
                8,
                "Admin",
                "44444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444",
                "Placement Employee",
                "Coding"
        );

        assertThrows(BandNameTooLongException.class,
                () -> jobValidator.isValidJob(jobRequest));
    }

    @Test
    public void isValidJob_shouldThrowSpecificationTooLongException_whenSpecificationOver100Characters() throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        JobRequest jobRequest = new JobRequest(
                8,
                "Admin",
                "4",
                "Placement EmployeePlacement EmployeePlacement EmployeePlacement EmployeePlacement EmployeePlacement Employee",
                "Coding"
        );

        assertThrows(SpecificationTooLongException.class,
                () -> jobValidator.isValidJob(jobRequest));
    }

    @Test
    public void isValidJob_shouldThrowResponsibilitiesTooLongException_whenResponsibilitiesOver100Characters() throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        JobRequest jobRequest = new JobRequest(
                8,
                "Admin",
                "4",
                "Placement Employee",
                "CodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCoding"
        );

        assertThrows(ResponsibilitiesTooLongException.class,
                () -> jobValidator.isValidJob(jobRequest));
    }
}
