-- liquibase formatted sql

-- changeset 20240116-1 author:gabriel.rodrigues@bonnasys.com.br label:adding-iban

ALTER TABLE vaccines ADD COLUMN iban VARCHAR(42);

-- rollback ALTER TABLE vaccines DROP COLUMN iban;