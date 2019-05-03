
create table TreatmentRequests  ( Id serial PRIMARY KEY);
alter table TreatmentRequests add age smallint not null;
alter table TreatmentRequests add startdate date not null;
alter table TreatmentRequests add enddate date not null;
alter table TreatmentRequests add location char(5) not null;
alter table TreatmentRequests add gender varchar(6);
alter table TreatmentRequests add network varchar(256) not null;
alter table TreatmentRequests add specialty varchar(64) not null;
alter table TreatmentRequests add patient_url varchar(1024) not null;
alter table TreatmentRequests add provider_url varchar(1024);
alter table TreatmentRequests add provider_name varchar(128);
alter table TreatmentRequests add provider_phone varchar(30);

create table AcceptedRequests  ( Id serial PRIMARY KEY);
alter table AcceptedRequests add provider_name varchar(128) not null;
alter table AcceptedRequests add provider_phone varchar(30) not null;
alter table AcceptedRequests add patient_url varchar(1024) not null;
alter table AcceptedRequests add provider_url varchar(1024) not null;

CREATE OR REPLACE FUNCTION accept_request()
RETURNS TRIGGER AS
$BODY$
  BEGIN
    IF (new.provider_url is not null) THEN
      INSERT INTO acceptedrequests(patient_url, provider_url, provider_name, provider_phone)
	    VALUES (new.patient_url, new.provider_url, new.provider_name, new.provider_phone);
    END IF;
    RETURN NEW;
  END;
$BODY$
 LANGUAGE plpgsql ;


CREATE TRIGGER accepted_request AFTER UPDATE ON treatmentrequests
FOR EACH ROW EXECUTE PROCEDURE accept_request();
