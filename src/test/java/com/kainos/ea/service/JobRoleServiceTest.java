package com.kainos.ea.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {

    JobRoleDao JobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(JobRoleDao, databaseConnector);

    JobRole jobRole = new JobRole(
            1,
            "Apprentice Software Engineer",
            "Are you looking for the bright future that a fantastic career in IT will bring, but don't think that full time study is for you? Do you want to get a degree in Computing Systems while earning a salary at a Sunday Times Top 100 company.",
            "1",
            "1",
            "As an Apprentice Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. extensive training to set you off on the right foot, you will quickly work as a part of a team in developing solutions within our real projects, learning all about our development languages, projects and technologies. You will be fully supported by experienced colleagues in the team as well as an experienced mentor, who will provide training and mentoring throughout your studies. You’ll also get experience across a wide range of teams and projects, with built-in rotations to help you learn and work out which element of Software Engineering suits your interests and skills best.",
            "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&viewid=4ad66b2c%2D695f%2D4d2b%2D9622%2D07ad809bd9a9&parent=%2Fpeople%2FJob%20Specifications%2FEngineering"

    );
    Connection conn;

    @Test
    public void getAllJobRoles_shouldReturnJobRolesList_whenDaoReturnsJobRoleList() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException {
        List<JobRole> expectedResult = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobRoleDao.getAllJobRoles()).thenReturn(expectedResult);

        List<JobRole> result = null;
        try {
            result = jobRoleService.getAllJobRoles();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JobRoleDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        assertEquals(result, expectedResult);
    }
}
