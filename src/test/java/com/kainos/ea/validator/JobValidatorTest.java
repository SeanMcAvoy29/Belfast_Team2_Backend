package com.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobValidatorTest {

        JobValidator jobValidator = new JobValidator();

        @Test
        public void isValidEmployee_shouldReturnTrue_whenValidEmployee() throws SpecificationsTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandValueTooLongException, InvalidJobException {
            JobRequest jobRequest = new JobRequest(
                    "Admin",
                    1,
                    "Placement Employee",
                    "Coding",
                    "Test",
                    2
            );

            assertTrue(jobValidator.isValidJob(jobRequest));
        }

        @Test
        public void isValidJob_shouldThrowJobRoleTooLongException_whenJobRoleOver60Characters() {
            JobRequest jobRequest = new JobRequest(
                    "Adminnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
                    1,
                    "Placement Employee",
                    "Coding",
                    "Test",
                    2
            );

            assertThrows(JobRoleTooLongException.class,
                    () -> jobValidator.isValidJob(jobRequest));
        }

        @Test
        public void isValidJob_shouldThrowBandNameTooLongException_whenBandNameOver100Characters() {
            JobRequest jobRequest = new JobRequest(
                    "Admin",
                    100000,
                    "Placement Employee",
                    "Coding",
                    "Test",
                    2
            );

            assertThrows(BandValueTooLongException.class,
                    () -> jobValidator.isValidJob(jobRequest));
        }

        @Test
        public void isValidJob_shouldThrowSpecificationTooLongException_whenSpecificationOver100Characters() {
            JobRequest jobRequest = new JobRequest(
                    "Test",
                    1,
                    "Placement EmployeePlacement EmployeePlacement EmployeePlacement EmployeePlacement EmployeePlacement Employee",
                    "Test",
                    "Test",
                    2
            );

            assertThrows(SpecificationsTooLongException.class,
                    () -> jobValidator.isValidJob(jobRequest));
        }

        @Test
        public void isValidJob_shouldThrowResponsibilitiesTooLongException_whenResponsibilitiesOver100Characters() {
            JobRequest jobRequest = new JobRequest(
                    "Test",
                    1,
                    "Test",
                    "CodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCodingCoding",
                    "Test",
                    3
            );

            assertThrows(ResponsibilitiesTooLongException.class,
                    () -> jobValidator.isValidJob(jobRequest));
        }

    }
