--liquibase formatted sql

--changeset 20240111-1 author:gabriel.rodrigues@bonnasys.com.br label:create-schema

create table health_center (
    created_at   datetime(6),
    updated_at   datetime(6),
    id           varchar(36) not null,
    address      varchar(255),
    name         varchar(255),
    neighborhood varchar(255),
    state        varchar(255),
    primary key (id)
) engine = InnoDB;

create table patients (
    birthdate  date,
    created_at datetime(6),
    updated_at datetime(6),
    id         varchar(36) not null,
    email      varchar(255),
    name       varchar(255),
    phone      varchar(255),
    primary key (id)) engine = InnoDB;

create table patients_history (
    patient_id varchar(36)  not null,
    history_id varchar(255) not null
) engine = InnoDB;

create table vaccine_registration (
    registration_date datetime(6),
    health_center_id  varchar(36),
    patient_id        varchar(36),
    vaccine_id        varchar(36),
    id                varchar(36) not null,
    primary key (id)
) engine = InnoDB;
create table vaccine_stock (
    amount           integer,
    health_center_id varchar(36) not null,
    vaccine_id       varchar(36) not null,
    primary key (health_center_id, vaccine_id)
) engine = InnoDB;

create table vaccines (
    created_at datetime(6),
    updated_at datetime(6),
    id         varchar(36) not null,
    name       varchar(255),
    producer   varchar(255),
    primary key (id)
) engine = InnoDB;

alter table patients_history add constraint uk_patients_history_id unique (history_id);
alter table patients_history add constraint fk_ph_vr foreign key (history_id) references vaccine_registration (id);
alter table patients_history add constraint fk_ph_p foreign key (patient_id) references patients (id);
alter table vaccine_registration add constraint fk_vr_health_center_id foreign key (health_center_id) references health_center (id);
alter table vaccine_registration add constraint fk_vr_patient_id foreign key (patient_id) references patients (id);
alter table vaccine_registration add constraint fk_vr_vaccine_id foreign key (vaccine_id) references vaccines (id);
alter table vaccine_stock add constraint health_center_vaccine_id foreign key (vaccine_id) references vaccines (id);
alter table vaccine_stock add constraint health_center_hc_id foreign key (health_center_id) references health_center (id);