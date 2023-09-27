CREATE DATABASE Belfast_team2_MylesS;

CREATE TABLE `Bands` (
 BandID SMALLINT NOT NULL AUTO_INCREMENT,
 BandName VARCHAR(64) NOT NULL,
 PRIMARY KEY (BandID)
);

INSERT INTO Bands(BandID, BandName) VALUES (1, 'Apprentice');
INSERT INTO Bands(BandID, BandName) VALUES (2, 'Trainee');
INSERT INTO Bands(BandID, BandName) VALUES (3, 'Associate');
INSERT INTO Bands(BandID, BandName) VALUES (4, 'Senior Associate');
INSERT INTO Bands(BandID, BandName) VALUES (5, 'Consultant');
INSERT INTO Bands(BandID, BandName) VALUES (6, 'Manager');
INSERT INTO Bands(BandID, BandName) VALUES (7, 'Principal');
INSERT INTO Bands(BandID, BandName) VALUES (8, 'Leadership Community');

CREATE TABLE `JobRoles` (
    JobID SMALLINT NOT NULL AUTO_INCREMENT,
    JobRoleName VARCHAR(64) NOT NULL,
    JobSpecification TEXT,
    BandID SMALLINT NOT NULL,
    CapabilityID TINYINT NOT NULL,
    Responsibilities TEXT,
    SharePointLink VARCHAR(500),
    PRIMARY KEY (JobID),
    FOREIGN KEY (BandID) REFERENCES Bands(BandID),
    FOREIGN KEY (CapabilityID) REFERENCES Capability(CapabilityID)
);

INSERT INTO `Belfast_team2_MylesS`.`JobRoles`
(`JobID`,
`JobRoleName`,
`JobSpecification`,
`BandID`,
`CapabilityID`,
`Responsibilities`,
`SharePointLink`)
VALUES
(1,
"Apprentice Software Engineer",
"Are you looking for the bright future that a fantastic career in IT will bring, but don't think that full time study is for you? Do you want to get a degree in Computing Systems while earning a salary at a Sunday Times Top 100 company",
1,
1,
"As an Apprentice Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. extensive training to set you off on the right foot, you will quickly work as a part of a team in developing solutions within our real projects, learning all about our development languages, projects and technologies. You will be fully supported by experienced colleagues in the team as well as an experienced mentor, who will provide training and mentoring throughout your studies. You’ll also get experience across a wide range of teams and projects, with built-in rotations to help you learn and work out which element of Software Engineering suits your interests and skills best",
"https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Apprentice%20Software%20Engineer%20(Apprentice).pdf?csf=1&web=1&e=0ZopF7");

INSERT INTO `Belfast_team2_MylesS`.`JobRoles`
(`JobID`,
`JobRoleName`,
`JobSpecification`,
`BandID`,
`CapabilityID`,
`Responsibilities`,
`SharePointLink`)
VALUES
(2,
"Senior Security Engineer",
"As a Senior Security Engineer, you will work in close collaboration with our technology teams to design and implement secure, cloud-based software solutions for our clients. Working as part of a multi-disciplinary Agile team, you will implement DevSecOps practices throughout the software development lifecycle, embedding security practices (e.g. vulnerability management, threat modelling etc.) and automating security artifact generation (e.g. secret scanning, container security, SAST, DAST etc.). You will provide subject matter expertise in application security or cloud security – sharing knowledge on threats and vulnerabilities, identifying appropriate security controls, and increasing cyber security awareness within teams.",
4,
2,
"Your key responsibilities will include:• Daily collaboration with the application development and cloud platform teams to plan and prioritise security requirements as part of the secure software development lifecycle (SSDLC).• Recommending security best practices for cloud platforms and automating compliance with cloud security baselines (e.g. CIS Benchmarks).• Implementation of automated security tooling (e.g. within a Continuous Integration (CI) pipeline) to validate security requirements and identify potential issues.• Working with external organisations to plan, scope and facilitate penetration tests.• Reviewing the outputs from security tools and security practices. You will filter and prioritise these into security stories that can be understood and actioned by the delivery teams.• Verifying the implementation of security principles, architectural patterns, and requirements.• Driving the adoption of cyber security practices (e.g. vulnerability management, threat modelling etc.) within Agile delivery teams.• Putting people first & developing others – You’ll help coach and develop members of the team.",
"https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Cyber%20Security/Job%20profile%20-%20Senior%20Security%20Engineer%20(Senior%20Associate).pdf?csf=1&web=1&e=qUsMkO");

INSERT INTO `Belfast_team2_MylesS`.`JobRoles`
(`JobID`,
`JobRoleName`,
`JobSpecification`,
`BandID`,
`CapabilityID`,
`Responsibilities`,
`SharePointLink`)
VALUES
(3,
"Senior Test Engineer",
"As a Senior Test Engineer (Senior Associate) in Kainos, you’ll work within a multi-skilled agile team, developing and executing functional automated and manual tests to help the team deliver working application software that meets user needs. You’ll do this whilst learning about new technologies and approaches, with talented colleagues who will help you learn, develop and grow.",
4,
1,
"Proven ability to learn a new business domain or architecture quickly and provide an excellent client experience.• Excellent communication skills with the ability to communicate technical detail to non-technical audiences. • Able to create, edit and manage the execution of automated tests covering both front and back-end, created in a coding language/tool (one of the following Selenium, Cypress, REST Assured• Demonstrable experience of using cloud architecture & being part of an Agile (Kanban, Scrum etc) team.• Experience working in Continuous Integration environment and using tools such as Jenkins and/or TeamCity and Experience with version control systems such as Git/SVN.• Experience of the fundamental concepts of Testing and Quality Assurance and demonstrable experience of improving the testing process • Knowledge or experience of at least one type of Non-functional testing",
"https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Senior%20Test%20Engineer%20(SA).pdf?csf=1&web=1&e=aGjl6Y");