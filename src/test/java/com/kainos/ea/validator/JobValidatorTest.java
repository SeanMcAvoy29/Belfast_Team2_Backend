package com.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.core.JobValidator;

import static org.junit.jupiter.api.Assertions.*;

public class JobValidatorTest {

        JobValidator jobValidator = new JobValidator();

        @Test
        public void isValidEmployee_shouldReturnTrue_whenValidEmployee() {
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

            assertFalse(jobValidator.isValidJob(jobRequest));
        }

        @Test
        public void isValidJob_shouldThrowInvalidBandException_whenInvalidBand() {
            JobRequest jobRequest = new JobRequest(
                    "Admin",
                    -1,
                    "Placement Employee",
                    "Coding",
                    "Test",
                    2
            );

            assertFalse(jobValidator.isValidJob(jobRequest));
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

            assertFalse(jobValidator.isValidJob(jobRequest));

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

            assertFalse(jobValidator.isValidJob(jobRequest));
        }

    }
