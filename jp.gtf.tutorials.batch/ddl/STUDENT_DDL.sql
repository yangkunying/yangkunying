BEGIN;
CREATE TABLE STUDENT
(
    STUDENT_ID VARCHAR(10) NOT NULL,
    STUDENT_NAME VARCHAR(10) NOT NULL,
    PRIMARY KEY(STUDENT_ID)
);
COMMIT;