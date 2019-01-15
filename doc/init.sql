-- Create table
create table SVN_COMMIT_LOG
(
  uuid            VARCHAR2(32) primary key,
  version_id      NUMBER(12),
  commit_msg      VARCHAR2(1000),
  commit_user     VARCHAR2(20),
  commit_date     VARCHAR2(50),
  ins_date        DATE,
  is_uat_commit   CHAR(1) default 'N',
  uat_commit_date DATE,
  is_uat          CHAR(1) default 'N',
  uat_date        DATE,
  is_sc_commit    CHAR(1) default 'N',
  sc_commit_date  DATE,
  is_sc           CHAR(1) default 'N',
  sc_date         DATE
);

create table SVN_COMMIT_FILE
(
  uuid       VARCHAR2(32) primary key,
  version_id NUMBER(11),
  file_url   VARCHAR2(200),
  file_type  CHAR(2)
);

create table SVN_COMMIT_UAT
(
  uuid        VARCHAR2(32) primary key,
  commit_uuid VARCHAR2(32),
  commit_user VARCHAR2(20),
  commit_date DATE,
  update_user VARCHAR2(20),
  update_time DATE,
  flag        CHAR(1) default 'Y'
);

create table SVN_COMMIT_SC
(
  uuid        VARCHAR2(32) primary key,
  commit_uuid VARCHAR2(32),
  commit_user VARCHAR2(20),
  commit_date DATE,
  update_user VARCHAR2(20),
  update_time DATE,
  flag        CHAR(1) default 'Y'
);