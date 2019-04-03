create table EMPLOYEE
(
    EMP_ID NUMBER(19) not null primary key,
    EMP_FIRST_NAME VARCHAR2(255 char),
    EMP_LAST_NAME VARCHAR2(255 char),
    DEPT_CD VARCHAR2(10 char),
    JOIN_DATE TIMESTAMP(6)
);

create table DEPARTMENT
(
    DEPT_CD VARCHAR2(10 char) not null primary key,
    DEPT_NAME VARCHAR2(255 char)
);

