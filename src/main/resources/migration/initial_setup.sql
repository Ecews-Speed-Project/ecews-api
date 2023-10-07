
-- CREATE TABLE IF NOT EXISTS public.users
-- (
--     id serial PRIMARY KEY,
--     email character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     username character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     password character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
-- );
--
-- INSERT  INTO users(id,email,first_name,last_name,username,password) values (1,'ihvnadmin@ihvn.com','IHVN','Admin','ihvnadmin','$2a$10$qHHyeiLujsib87bOmfAQneLWHw9vZnd86mjvn2TYWSAzfWnYS2TaK');


CREATE TABLE IF NOT EXISTS role
(
    id serial PRIMARY KEY,
    role_description character varying(255) COLLATE pg_catalog."default",
    role_name character varying(255) COLLATE pg_catalog."default",
    create_date timestamp,
    update_date timestamp
    );

INSERT  INTO role(id,role_description,role_name,create_date,update_date)
values (1,'Admin User','admin',NOW(),NOW()),(2,'User','user',NOW(),NOW()),(3,'MandE Users','mande',NOW(),NOW());
-- CREATE TABLE IF NOT EXISTS user_role
-- (
--     user_id integer NOT NULL,
--     role_id integer NOT NULL,
--     CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
--     CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id)
--         REFERENCES public.role (id) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION,
--     CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id)
--         REFERENCES public.users (id) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION
-- );
--
-- INSERT INTO user_role(user_id,role_id) values (1,1);

create table if not exists art_linelist
(
    id  serial not null, state varchar(255),
    lga varchar(255), datim_code varchar(255),
    facility_name varchar(255),
    patient_unique_id varchar(255),
    patient_hospital_no varchar(255),
    anc_no_identifier varchar(255),
    anc_no_concept_id varchar(255),
    hts_no varchar(255),
    sex varchar(255),
    age_at_start_of_art_years numeric(19, 2),
    age_at_start_of_art_months numeric(19, 2),
    care_entry_point varchar(255),
    kp_type varchar(255),
    months_on_art numeric(19, 2),
    date_transferred_in date,
    transfer_in_status varchar(255),
    art_start_date date,
    last_pickup_date date,
    last_visit_date date,
    days_of_arv_refil numeric(19, 2),
    pill_balance numeric(19, 2),
    initial_regimen_line varchar(255),
    initial_regimen varchar(255),
    initial_cd4_count numeric(19, 2),
    initial_cd4_count_date date,
    current_cd4_count numeric(19, 2),
    current_cd4_count_date date,
    last_eac_date date,
    current_regimen_line varchar(255),
    current_regimen varchar(255),
    pregnancy_status varchar(255),
    pregnancy_status_date date,
    edd date,
    last_delivery_date date,
    lmp date,
    gestation_age_weeks numeric(19, 2),
    current_viral_load float8,
    viral_load_encounter_date date,
    viral_load_sample_collection_date date,
    viral_load_reported_date date,
    result_date date,
    assay_date date,
    approval_date date,
    viral_load_indication varchar(255),
    patient_outcome varchar(255),
    patient_outcome_date date,
    current_art_status varchar(255),
    dispensing_modality varchar(255),
    facility_dispensing_modality varchar(255),
    ddd_dispensing_modality varchar(255),
    mmd_type varchar(255),
    date_returned_to_care date,
    date_of_termination date,
    pharmacy_next_appointment date,
    clinical_next_appointment date,
    current_age_yrs numeric(19, 2),
    current_age_months numeric(19, 2),
    date_of_birth date,
    mark_as_deceased varchar(255),
    mark_as_deceased_death_date date,
    registration_phone_no varchar(255),
    next_of_kin_phone_no varchar(255),
    treatment_supporter_phone_no varchar(255),
    biometric_captured varchar(255),
    biometric_capture_date date,
    valid_capture varchar(255),
    current_weight float8,
    current_weight_date date,
    tb_status varchar(255),
    tb_status_date date,
    baseline_inh_start_date date,
    baseline_inh_stop_date date,
    current_inh_start_date date,
    current_inh_outcome varchar(255),
    current_inh_outcome_date date,
    last_inh_dispensed_date date,
    baseline_tb_treatment_start_date date,
    baseline_tb_treatment_stop_date date,
    last_viral_load_sample_collection_form_date date,
    last_sample_taken_date date,
    otz_enrollment_date date,
    otz_outcome_date date,
    enrollment_date date,
    initial_first_line_regimen varchar(255),
    initial_first_line_regimen_date date,
    initial_second_line_regimen varchar(255),
    initial_second_line_regimen_date date,
    last_pickup_date_previous_quarter date,
    drug_duration_previous_quarter numeric(19, 2),
    patient_outcome_previous_quarter varchar(255),
    patient_outcome_date_previous_quarter date,
    art_status_previous_quarter varchar(255),
    art_confirmation_date date,
    first_pickup_date date,
    last_qty_of_arv_refill numeric(19, 2),
    patient_id numeric(19, 2),
    patient_uuid varchar(255),
    primary key (id)
    );

CREATE TABLE IF NOT EXISTS state
(
    id bigserial PRIMARY KEY ,
    create_date timestamp,
    update_date timestamp,
    state_name varchar(255)
    );

INSERT INTO "state" ("id", "create_date", "update_date", "state_name") VALUES (1, '2023-03-18 00:00:00', '2023-03-18 00:00:00', 'Delta');
INSERT INTO "state" ("id", "create_date", "update_date", "state_name") VALUES (2, '2023-03-18 00:00:00', '2023-03-18 00:00:00', 'Ekiti');
INSERT INTO "state" ("id", "create_date", "update_date", "state_name") VALUES (3, '2023-03-18 00:00:00', '2023-03-18 00:00:00', 'Osun');


create table if not exists lga(
                                  "id" bigserial not null,
                                  "state_id" int8,
                                  "lga" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "create_date" timestamp(6),
    "update_date" timestamp(6),
    "datim_code" varchar(255) COLLATE "pg_catalog"."default",
    primary key (id)
    );
alter table lga
    add constraint FKico033qs5d65rj1gkn698em1c
        foreign key (state_id)
            references state;
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (1, 1, 'Ndokwa East', '2023-03-18 00:00:00', '2023-03-18 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (2, 1, 'Ika South', '2023-03-19 00:00:00', '2023-03-19 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (3, 1, 'Oshimili North', '2023-03-20 00:00:00', '2023-03-20 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (4, 1, 'Udu', '2023-03-21 00:00:00', '2023-03-21 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (5, 1, 'Oshimili South', '2023-03-22 00:00:00', '2023-03-22 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (6, 1, 'Ika North East', '2023-03-23 00:00:00', '2023-03-23 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (7, 1, 'Ukwuani', '2023-03-24 00:00:00', '2023-03-24 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (8, 1, 'Bomadi', '2023-03-25 00:00:00', '2023-03-25 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (9, 1, 'Burutu', '2023-03-26 00:00:00', '2023-03-26 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (10, 1, 'Isoko North', '2023-03-27 00:00:00', '2023-03-27 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (11, 1, 'Uvwie', '2023-03-28 00:00:00', '2023-03-28 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (12, 1, 'Ethiope West', '2023-03-29 00:00:00', '2023-03-29 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (13, 1, 'Aniocha South', '2023-03-30 00:00:00', '2023-03-30 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (14, 1, 'Ughelli South', '2023-03-31 00:00:00', '2023-03-31 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (15, 1, 'Warri North', '2023-04-01 00:00:00', '2023-04-01 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (16, 1, 'Okpe', '2023-04-02 00:00:00', '2023-04-02 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (17, 1, 'Ndokwa West', '2023-04-03 00:00:00', '2023-04-03 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (18, 1, 'Isoko South', '2023-04-04 00:00:00', '2023-04-04 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (19, 1, 'Patani', '2023-04-05 00:00:00', '2023-04-05 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (20, 1, 'Sapele', '2023-04-06 00:00:00', '2023-04-06 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (21, 1, 'Ethiope East', '2023-04-07 00:00:00', '2023-04-07 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (22, 1, 'Aniocha North', '2023-04-08 00:00:00', '2023-04-08 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (23, 1, 'Ughelli North', '2023-04-09 00:00:00', '2023-04-09 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (24, 1, 'warri south west', '2023-04-10 00:00:00', '2023-04-10 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (25, 1, 'Warri South', '2023-04-11 00:00:00', '2023-04-11 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (26, 2, 'Ado-Ekiti', '2023-04-12 00:00:00', '2023-04-12 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (27, 2, 'Ekiti West', '2023-04-13 00:00:00', '2023-04-13 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (28, 2, 'Gbonyin', '2023-04-14 00:00:00', '2023-04-14 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (29, 2, 'Ido-Osi', '2023-04-15 00:00:00', '2023-04-15 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (30, 2, 'Efon', '2023-04-16 00:00:00', '2023-04-16 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (31, 2, 'Emure', '2023-04-17 00:00:00', '2023-04-17 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (32, 2, 'Irepodun-Ifelodun', '2023-04-18 00:00:00', '2023-04-18 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (33, 2, 'Ijero', '2023-04-19 00:00:00', '2023-04-19 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (34, 2, 'Ikere', '2023-04-20 00:00:00', '2023-04-20 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (35, 2, 'Ikole', '2023-04-21 00:00:00', '2023-04-21 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (36, 2, 'Ekiti South-West', '2023-04-22 00:00:00', '2023-04-22 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (37, 2, 'Ekiti East', '2023-04-23 00:00:00', '2023-04-23 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (38, 2, 'Ise/Orun', '2023-04-24 00:00:00', '2023-04-24 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (39, 2, 'Moba', '2023-04-25 00:00:00', '2023-04-25 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (40, 2, 'Oye', '2023-04-26 00:00:00', '2023-04-26 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (41, 2, 'Ilejemeje', '2023-04-27 00:00:00', '2023-04-27 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (42, 3, 'Isokan', '2023-04-28 00:00:00', '2023-04-28 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (43, 3, 'Odo Otin', '2023-04-29 00:00:00', '2023-04-29 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (44, 3, 'Ede South', '2023-04-30 00:00:00', '2023-04-30 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (45, 3, 'Ejigbo', '2023-05-01 00:00:00', '2023-05-01 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (46, 3, 'Ife East', '2023-05-02 00:00:00', '2023-05-02 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (47, 3, 'Ife Central', '2023-05-03 00:00:00', '2023-05-03 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (48, 3, 'Ife South', '2023-05-04 00:00:00', '2023-05-04 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (49, 3, 'Aiyedaade', '2023-05-05 00:00:00', '2023-05-05 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (50, 3, 'Oriade', '2023-05-06 00:00:00', '2023-05-06 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (51, 3, 'Irewole', '2023-05-07 00:00:00', '2023-05-07 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (52, 3, 'Ilesa East', '2023-05-08 00:00:00', '2023-05-08 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (53, 3, 'Ilesa West', '2023-05-09 00:00:00', '2023-05-09 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (54, 3, 'Ila', '2023-05-10 00:00:00', '2023-05-10 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (55, 3, 'Ife North', '2023-05-11 00:00:00', '2023-05-11 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (56, 3, 'Osogbo', '2023-05-12 00:00:00', '2023-05-12 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (57, 3, 'Atakunmosa West', '2023-05-13 00:00:00', '2023-05-13 00:00:00', NULL);
INSERT INTO "lga" ("id", "state_id", "lga", "create_date", "update_date", "datim_code") VALUES (58, 3, 'Iwo', '2023-05-14 00:00:00', '2023-05-14 00:00:00', NULL);


create table facility
(
    id  bigserial not null,
    partner varchar(255),
    state_id int8,
    lga_id int8,
    facility_name varchar(255),
    datim_code varchar(255) unique not null,
    facility_short_name varchar(255),
    create_date timestamp,
    update_date timestamp,
    primary key (id)
);

alter table facility
    add constraint FKsnaull1dsilfw4n0mnitejfgh
        foreign key (lga_id)
            references lga;

alter table facility
    add constraint FKthatut1ljkbawv4qwo2v9oice
        foreign key (state_id)
            references state;
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (1, 'ECEWS_SPEED', 1, 1, 'Ashaka Government Hospital', 'U8uVowE8iye', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (2, 'ECEWS_SPEED', 1, 1, 'de Ndokwa East', 'nzvY9GKkpym', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (3, 'ECEWS_SPEED', 1, 2, 'de Ika South', 'Q7RlCFBv0PT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (4, 'ECEWS_SPEED', 1, 2, 'St John''s Catholic Hospital - Agbor', 'TbS2d1Ssxxr', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (5, 'ECEWS_SPEED', 1, 2, 'KPIF_Ika South OSS', 'Eg8r2MQFWsb', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (6, 'ECEWS_SPEED', 1, 2, 'Agbor Central Hospital', 'HFp2lb9f404', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (7, 'ECEWS_SPEED', 1, 2, 'Agbor-Aladinma General Hospital', 'bYJNuHGbWbm', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (8, 'ECEWS_SPEED', 1, 3, 'Ebu General Hospital', 'incGVPWjyyl', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (9, 'ECEWS_SPEED', 1, 3, 'Akwukwu-Igbo General Hospital', 'OCeUspBcr4M', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (10, 'ECEWS_SPEED', 1, 3, 'Ibusa General Hospital', 'sbDEuNvdPoF', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (11, 'ECEWS_SPEED', 1, 3, 'Okwe General Hospital', 'ATzoX3X0tMC', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (12, 'ECEWS_SPEED', 1, 3, 'de Oshimili North', 'qUFyVUUSqUb', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (13, 'ECEWS_SPEED', 1, 4, 'de Udu', 'KEKFFPFTneN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (14, 'ECEWS_SPEED', 1, 4, 'KPIF_Udu OSS', 'VfmC3h87LGo', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (15, 'ECEWS_SPEED', 1, 4, 'Otor Udu General Hospital', 'BpQ9erT015U', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (16, 'ECEWS_SPEED', 1, 5, 'KPIF_Oshimili South OSS', 'jWcwSw0FuEZ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (17, 'ECEWS_SPEED', 1, 5, 'Federal Medical Center - Asaba', 'Fd3GeuuIXaT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (18, 'ECEWS_SPEED', 1, 5, 'St. Rebecca Clinic', 'qUxQsMMNXQU', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (19, 'ECEWS_SPEED', 1, 5, 'St. Joseph''s Hospital - Asaba', 'LlwwvYfpYbE', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (20, 'ECEWS_SPEED', 1, 5, 'de Oshimili South', 'pA7KmJWZWi5', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (21, 'ECEWS_SPEED', 1, 6, 'de Ika North East', 'fSlbRfrxgBX', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (22, 'ECEWS_SPEED', 1, 6, 'Owa-Alero General Hospital', 'wLWyHppn07A', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (23, 'ECEWS_SPEED', 1, 6, 'Umunede General Hospital', 'VZu1pq8LtlH', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (24, 'ECEWS_SPEED', 1, 7, 'Obiaruku General Hospital', 'J8rcx4FWI0Z', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (25, 'ECEWS_SPEED', 1, 7, 'Umutu Government Hospital', 'URcMqk46VJ4', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (26, 'ECEWS_SPEED', 1, 7, 'de Ukwuani', 'h1vcs9cuaTf', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (27, 'ECEWS_SPEED', 1, 8, 'de Bomadi', 'vIWJt7NIoFp', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (28, 'ECEWS_SPEED', 1, 8, 'Bomadi General Hospital', 'GqCJ9QOkG4j', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (29, 'ECEWS_SPEED', 1, 8, 'Our Lady of Waters', 'Th3nEKm0Ygv', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (30, 'ECEWS_SPEED', 1, 9, 'de Burutu', 'AMSQzhoDGn0', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (31, 'ECEWS_SPEED', 1, 10, 'Ozoro General Hospital', 'XFDsmmVDEC6', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (32, 'ECEWS_SPEED', 1, 10, 'de Isoko North', 'RXyzOWGy8sf', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (33, 'ECEWS_SPEED', 1, 10, 'Owhelogbo Government Hospital', 'tYmoLzwgMQi', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (34, 'ECEWS_SPEED', 1, 10, 'St. Mary"s Clinic And Maternity - Ozoro', 'HeiGp9vfyLJ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (35, 'ECEWS_SPEED', 1, 11, 'Ekpan General Hospital', 'MarzoBBhHwN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (36, 'ECEWS_SPEED', 1, 11, 'de Uvwie', 'yyaZXER8YfR', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (37, 'ECEWS_SPEED', 1, 12, 'de Ethiope West', 'ozapfg9XcmM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (38, 'ECEWS_SPEED', 1, 12, 'Delta State University Teaching Hospital', 'kCCokKFoPnt', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (39, 'ECEWS_SPEED', 1, 12, 'Oghara General Hospital', 'AA0oZC6PNTA', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (40, 'ECEWS_SPEED', 1, 13, 'Ogwashi-uku General Hospital', 'y7KNPgCcSY0', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (41, 'ECEWS_SPEED', 1, 13, 'Isheagu General Hospital', 'Kjd6wgYYxvb', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (42, 'ECEWS_SPEED', 1, 13, 'de Aniocha South', 'jjdlSy7XvmM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (43, 'ECEWS_SPEED', 1, 14, 'de Ughelli South', 'EQLuOekanSM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (44, 'ECEWS_SPEED', 1, 14, 'Otu-Jeremi General Hospital', 'ez4wPzQEzfv', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (45, 'ECEWS_SPEED', 1, 15, 'Assumption Catholic Hospital - Warri', 'XLx2UT9cWWv', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (46, 'ECEWS_SPEED', 1, 15, 'Koko General Hospital', 'A69wkzO3hTM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (47, 'ECEWS_SPEED', 1, 15, 'de Warri North', 'zKZKB0aNzsZ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (48, 'ECEWS_SPEED', 1, 16, 'de Okpe', 'TF6DZGTsVO2', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (49, 'ECEWS_SPEED', 1, 16, 'Orerokpe General Hospital', 'YVvZ4NOLpBr', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (50, 'ECEWS_SPEED', 1, 17, 'Brema Hospital - Kwale', 'x0a9eO6b8TN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (51, 'ECEWS_SPEED', 1, 17, 'Kwale Central Hospital', 'fjoJ6huN7nn', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (52, 'ECEWS_SPEED', 1, 17, 'de Ndokwa West', 'ip6npch0teY', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (53, 'ECEWS_SPEED', 1, 18, 'de Isoko South', 'X8NUW1sxr3s', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (54, 'ECEWS_SPEED', 1, 18, 'Oleh Central Hospital', 'bktNZf2mtaX', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (55, 'ECEWS_SPEED', 1, 18, 'Uzere Cottage Hospital', 'Ef3tjwoMSDu', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (56, 'ECEWS_SPEED', 1, 19, 'Patani General Hospital', 'uZacLGUVGss', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (57, 'ECEWS_SPEED', 1, 19, 'de Patani', 'G1wJbuwm1Pj', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (58, 'ECEWS_SPEED', 1, 20, 'de Sapele', 'c8wRDtzO14l', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (59, 'ECEWS_SPEED', 1, 20, 'St. Elizabeth Catholic Hospital - Sapele', 'HcJMUzznNH2', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (60, 'ECEWS_SPEED', 1, 20, 'de Sapele Central Hospital', 'hk0BNOtE2JW', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (61, 'ECEWS_SPEED', 1, 21, 'Our Lady of Nigeria Clinic - Oghara', 'aTSvjQIz6Ge', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (62, 'ECEWS_SPEED', 1, 21, 'Erhoike Cottage Hospital', 'CKaHg4rNsaT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (63, 'ECEWS_SPEED', 1, 21, 'Abraka General Hospital', 'SQ2uBMPr2BM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (64, 'ECEWS_SPEED', 1, 21, 'Turberculosis Referral Center - Eku', 'Ngmb3dhUlmv', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (65, 'ECEWS_SPEED', 1, 21, 'St. Francis Catholic Hospital - Okpara Inland', 'ZXt1KCneHT3', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (66, 'ECEWS_SPEED', 1, 21, 'Eku Government Hospital', 'gqkfDLCWOkn', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (67, 'ECEWS_SPEED', 1, 21, 'de Ethiope East', 'rGJKS4OLyqd', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (68, 'ECEWS_SPEED', 1, 22, 'de Aniocha North', 'sOvdseHOfPM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (69, 'ECEWS_SPEED', 1, 22, 'Issele-Ukwu General Hospital', 'FIsFQ5s5zDA', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (70, 'ECEWS_SPEED', 1, 22, 'Onicha-Olona General Hospital', 'YWzIt3oCGhh', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (71, 'ECEWS_SPEED', 1, 22, 'St. Theresa''s Catholic Hospital - Issele Uku', 'YoNalkNNrRL', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (72, 'ECEWS_SPEED', 1, 23, 'KPIF_Ughelli North OSS', 'mcYWXMmN2Vm', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (73, 'ECEWS_SPEED', 1, 23, 'Ughelli Central Hospital', 'YDJerY4DYpz', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (74, 'ECEWS_SPEED', 1, 23, 'Agbarho Primary Health Center - General Hospital', 'G9emPQgNqnD', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (75, 'ECEWS_SPEED', 1, 23, 'de Ughelli North', 'XYbM1RspaLs', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (76, 'ECEWS_SPEED', 1, 24, 'de Warri South-West', 'F2sisT8xwGl', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (77, 'ECEWS_SPEED', 1, 25, 'de Warri South', 'MNVESOkGZ0T', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (78, 'ECEWS_SPEED', 1, 25, 'KPIF_Warri South OSS', 'J8nZRFuguyN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (79, 'ECEWS_SPEED', 1, 25, 'de Warri Central Hospital', 'GD8JnhtnrEL', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (80, 'ECEWS_SPEED', 1, 25, 'Sage Clinic', 'BncpoEEyXQf', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (81, 'ECEWS_SPEED', 1, 25, 'St. Andrews Hospital and Maternity', 'BIOMY3jCoMQ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (82, 'ECEWS_SPEED', 2, 26, 'Ekiti State University Teaching Hospital Ado-Ekiti', 'NG8ugV9Xv57', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (83, 'ECEWS_SPEED', 2, 26, 'Okesa Ekiti Comprehensive Health Center', 'itv14Kob5b5', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (84, 'ECEWS_SPEED', 2, 26, 'St. Gregory Hospital Ado-Ekiti', 'ftEV1iyWtuH', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (85, 'ECEWS_SPEED', 2, 26, 'ek Ado-Ekiti', 'w537PeRTb2k', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (86, 'ECEWS_SPEED', 2, 27, 'ek Ekiti West', 'WFsEvMCIS56', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (87, 'ECEWS_SPEED', 2, 27, 'Aramoko Ekiti General Hospital', 'iYLBTAWo29b', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (88, 'ECEWS_SPEED', 2, 28, 'ek Gbonyin', 'MtwzdW6p7T4', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (89, 'ECEWS_SPEED', 2, 29, 'Ifaki Ekiti General Hospital', 'FjUk8l3lLuT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (90, 'ECEWS_SPEED', 2, 29, 'Federal Medical Center -Ido-Ekiti', 'tvMCpBfPpNr', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (91, 'ECEWS_SPEED', 2, 30, 'ek Efon', 'v6VKDsRhry2', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (92, 'ECEWS_SPEED', 2, 31, 'ek Emure', 'LoolSVHi1Jq', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (93, 'ECEWS_SPEED', 2, 31, 'Emure Ekiti General Hospital', 'oEgdXZ6hqwZ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (94, 'ECEWS_SPEED', 2, 32, 'Iyin Ekiti General Hospital', 'bxXIciySrPz', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (95, 'ECEWS_SPEED', 2, 33, 'Ijero Ekiti State Specialist Hospital', 'iwKXh9EaNPG', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (96, 'ECEWS_SPEED', 2, 34, 'Ikere Ekiti State Specialist Hospital', 'H9w6ZU7BCh5', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (97, 'ECEWS_SPEED', 2, 34, 'ek Ikere', 'FLIkT6NShZE', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (98, 'ECEWS_SPEED', 2, 35, 'Ikole-Ekiti State Hospital', 'ClLtOoKCTAz', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (99, 'ECEWS_SPEED', 2, 35, 'ek Ikole', 'n3F9ZEqPUtq', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (100, 'ECEWS_SPEED', 2, 36, 'ek Ekiti South-West', 'gaRACh9QB6a', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (101, 'ECEWS_SPEED', 2, 36, 'Ilawe Ekiti Comprehensive Health Center', 'iSMGSwSkbiY', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (102, 'ECEWS_SPEED', 2, 37, 'Omuo Ekiti General Hospital', 'IaAbEGvnhF7', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (103, 'ECEWS_SPEED', 2, 37, 'ek Ekiti East', 'Z8NBMmojNEN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (104, 'ECEWS_SPEED', 2, 38, 'ek Ise-Orun', 'jeHlpPF831R', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (105, 'ECEWS_SPEED', 2, 39, 'Otun Ekiti General Hospital', 'AcEfKip0bnN', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (106, 'ECEWS_SPEED', 2, 40, 'Oye Ekiti General Hospital', 'IjAiByZWjsw', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (107, 'ECEWS_SPEED', 2, 41, 'Iye Ekiti General Hospital', 'm0MGjVaytWB', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (108, 'ECEWS_SPEED', 3, 42, 'Catholic Hospital and Maternity - Okeola', 'NrAixqBOeRR', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (109, 'ECEWS_SPEED', 3, 42, 'os Isokan', 'rhg7o351Ogx', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (110, 'ECEWS_SPEED', 3, 43, 'Seventh Day Hospital Inisha', 'Q8PqGlJOMVT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (111, 'ECEWS_SPEED', 3, 44, 'Ede South KP OSS', 'wVmQt7UrWYq', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (112, 'ECEWS_SPEED', 3, 44, 'os Ede South', 'PWfDHBvn1hw', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (113, 'ECEWS_SPEED', 3, 44, 'Ede Specialist Hospital', 'qoiO0tapOKa', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (114, 'ECEWS_SPEED', 3, 45, 'Ejigbo Baptist Hospital', 'LH8aXd0aKgT', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (115, 'ECEWS_SPEED', 3, 45, 'os Ejigbo', 'dzhjTRHGqu0', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (116, 'ECEWS_SPEED', 3, 46, 'Oke-Ogbo General Hospital - Ile Ife', 'nE05pmOYy9Z', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (117, 'ECEWS_SPEED', 3, 46, 'os Ife East', 'Wf7A67DZQDR', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (118, 'ECEWS_SPEED', 3, 47, 'os Ife Central', 'tr5rm0vaxGG', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (119, 'ECEWS_SPEED', 3, 47, 'Obafemi Awolowo University Teaching Hospital Complex', 'u4jq9CmP9dk', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (120, 'ECEWS_SPEED', 3, 47, 'Seventh Day Adventist Hospital - Ile-Ife', 'xGKk2hlvtUz', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (121, 'ECEWS_SPEED', 3, 48, 'Olode Primary Health Center', 'FGnX2mw9haA', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (122, 'ECEWS_SPEED', 3, 49, 'Primary Health Center Wakajaye', 'HfVwENHZxBr', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (123, 'ECEWS_SPEED', 3, 50, 'Ijebu-Ijesha General Hospital', 'u3qzTFubA8g', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (124, 'ECEWS_SPEED', 3, 51, 'os Irewole', 'AIWlVFpaxFZ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (125, 'ECEWS_SPEED', 3, 51, 'Ikire Specialist Hospital', 'gqn7FlDPWzJ', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (126, 'ECEWS_SPEED', 3, 52, 'Wesley Guild OAUTHC Annex', 'VhS5G57BPrh', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (127, 'ECEWS_SPEED', 3, 53, 'Ilesa General Hospital', 'JE2mJgfuG8h', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (128, 'ECEWS_SPEED', 3, 54, 'os Ila', 'k9ilIScUQLb', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (129, 'ECEWS_SPEED', 3, 54, 'Ila Orangun State Hospital', 'hln2LIDV1Xl', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (130, 'ECEWS_SPEED', 3, 55, 'Our Lady of Lourdes Hospital - Ipetumodun', 'ikrzY7sZjRk', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (131, 'ECEWS_SPEED', 3, 55, 'os Ife North', 'OqZ11xWhJlB', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (132, 'ECEWS_SPEED', 3, 56, 'os Osogbo', 'wLldH8OpVNF', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (133, 'ECEWS_SPEED', 3, 56, 'os State Hospital- Asubiaro', 'kzB32IGuzWj', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (134, 'ECEWS_SPEED', 3, 56, 'Ladoke Akintola Teaching Hospital - Oshogbo', 'mgEojaVd0QS', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (135, 'ECEWS_SPEED', 3, 56, 'Our Lady of Fatima Catholic Hospital - Jaleyemi', 'tuPG3r8X0RC', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (136, 'ECEWS_SPEED', 3, 57, 'Osu Primary Health Center', 'qwpy1tt5iNe', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (137, 'ECEWS_SPEED', 3, 58, 'os Iwo', 'VWzOuFbOVsE', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (138, 'ECEWS_SPEED', 3, 58, 'Victory Specialist Hospital - Iwo', 'd5p5Ehrl5IH', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
INSERT INTO "facility" ("id", "partner", "state_id", "lga_id", "facility_name", "datim_code", "facility_short_name", "create_date", "update_date") VALUES (139, 'ECEWS_SPEED', 3, 58, 'State Hospital - Iwo', 'RjMnBpvNTLM', NULL, '2023-03-18 00:00:00', '2023-03-18 00:00:00');
