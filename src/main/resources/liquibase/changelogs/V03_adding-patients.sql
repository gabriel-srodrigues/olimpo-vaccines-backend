-- liquibase formatted sql

-- changeset 20240120-1 author:gabriel.rodrigues@bonnasys.com.br label:adding-patients

INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES (uuid(), 'gabriel.rodrigues@bonnasys.com.br', 'Gabriel Rodrigues', '(99) 99999-9999', '1999-12-17', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES (uuid(), 'rick.sanches@bonnasys.com.br', 'Rick Sanches', '(11) 11111-1111', '1968-01-14', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES (uuid(), 'rodrigo.alvaro@bonnasys.com.br', 'Rodrigo Alvaro', '(22) 22222-2222', '1945-10-08', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES (uuid(), 'gabriely.souza@bonnasys.com.br', 'Gabriely Souza', '(33) 33333-3333', '1999-02-13', CURDATE(), CURDATE());
