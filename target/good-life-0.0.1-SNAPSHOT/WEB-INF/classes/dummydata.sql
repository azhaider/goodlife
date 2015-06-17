-- You can copy and paste this whole document into the MySQL Workbench sql editor and it will execute all below
-- Dummy data for testing the UI
-- Run a clean install and refresh just to make sure everything is up to date
-- If you have an outdated version of the schema it might be a good idea enter the following sql:
-- drop schema goodlife; create schema goodlife; commit;
-- Then redeploy and let hibernate populate the schema for you

USE goodlife;

-- Delete old data from tables
DELETE FROM USERS WHERE 1=1;
-- Reset the auto increment
ALTER TABLE USERS AUTO_INCREMENT = 1;
-- Insert 12 different users, 4 students, 4 admins, 4 facilitators
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force0@tsgforce.com','Hello.','Chicago','force',101010,'force0',current_time(),'0','goodlife','http://google.com',current_time(),true,'a','IL','force0');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force1@tsgforce.com','Hello.','Chicago','force',101011,'force1',current_time(),'1','goodlife','http://google.com',current_time(),true,'s','IL','force1');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force2@tsgforce.com','Hello.','Chicago','force',101012,'force2',current_time(),'2','goodlife','http://google.com',current_time(),true,'f','IL','force2');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force3@tsgforce.com','Hello.','Chicago','force',101013,'force3',current_time(),'3','goodlife','http://google.com',current_time(),true,'a','IL','force3');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force4@tsgforce.com','Hello.','Chicago','force',101014,'force4',current_time(),'4','goodlife','http://google.com',current_time(),true,'s','IL','force4');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force5@tsgforce.com','Hello.','Chicago','force',101015,'force5',current_time(),'5','goodlife','http://google.com',current_time(),true,'f','IL','force5');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force6@tsgforce.com','Hello.','Chicago','force',101016,'force6',current_time(),'6','goodlife','http://google.com',current_time(),true,'a','IL','force6');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force7@tsgforce.com','Hello.','Chicago','force',101017,'force7',current_time(),'7','goodlife','http://google.com',current_time(),true,'s','IL','force7');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force8@tsgforce.com','Hello.','Chicago','force',101018,'force8',current_time(),'8','goodlife','http://google.com',current_time(),true,'f','IL','force8');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force9@tsgforce.com','Hello.','Chicago','force',101019,'force9',current_time(),'9','goodlife','http://google.com',current_time(),true,'a','IL','force9');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force10@tsgforce.com','Hello.','Chicago','force',101020,'force10',current_time(),'10','goodlife','http://google.com',current_time(),true,'s','IL','force10');
INSERT INTO USERS
(EMAIL,ABT_ME,CITY,FRST_NM,INVIT_CD,INVIT_BY,INVIT_DT,LST_NM,PWD,PRF_IMG_PATH,PROMO_DT,RGSTRD,ROLE_TYP_CD,STATE,USR_NM)
VALUES('force11@tsgforce.com','Hello.','Chicago','force',101021,'force11',current_time(),'11','goodlife','http://google.com',current_time(),true,'f','IL','force11');

DELETE FROM STUDENT WHERE 1=1;
INSERT INTO STUDENT
VALUES(2,1,1,current_time());
INSERT INTO STUDENT
VALUES(5,1,2,current_time());
INSERT INTO STUDENT
VALUES(8,1,3,current_time());
INSERT INTO STUDENT
VALUES(11,1,4,current_time());

DELETE FROM SUPER_ADMIN WHERE 1=1;
INSERT INTO SUPER_ADMIN
VALUES(1);
INSERT INTO SUPER_ADMIN
VALUES(4);
INSERT INTO SUPER_ADMIN
VALUES(7);
INSERT INTO SUPER_ADMIN
VALUES(10);

DELETE FROM INSTRUCTOR WHERE 1=1;
ALTER TABLE INSTRUCTOR AUTO_INCREMENT = 1;
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,3);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,6);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,9);
INSERT INTO INSTRUCTOR
(N_STDNT,START_DT,TOT_CAP,USERID)
VALUES(1,current_time(),5,12);

DELETE FROM USER_STATUS WHERE 1=1;
ALTER TABLE USER_STATUS AUTO_INCREMENT = 1;
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES(current_time(),current_time(),'s',2);
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES(current_time(),current_time(),'s',5);
INSERT INTO USER_STATUS
(END_DT,STRT_DT,STS_TYP_CD,USERID)
VALUES(current_time(),current_time(),'s',8);

-- Verify that the data went into the tables
SELECT * FROM USERS;
SELECT * FROM STUDENT;
SELECT * FROM SUPER_ADMIN;
SELECT * FROM INSTRUCTOR;
SELECT * FROM USER_STATUS;