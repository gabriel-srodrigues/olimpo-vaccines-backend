-- liquibase formatted sql

-- changeset 20240120-1 author:gabriel.rodrigues@bonnasys.com.br label:adding-patients

INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES ('afa12271-860d-4f2a-9987-c7b8fa439c99', 'gabriel.rodrigues@bonnasys.com.br', 'Gabriel Rodrigues', '(99) 99999-9999', '1999-12-17', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES ('36d00fb0-284d-421c-8ea0-8490201c595e', 'rick.sanches@bonnasys.com.br', 'Rick Sanches', '(11) 11111-1111', '1968-01-14', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES ('22f86df2-3d46-4c28-86cb-83e101cd3694', 'rodrigo.alvaro@bonnasys.com.br', 'Rodrigo Alvaro', '(22) 22222-2222', '1945-10-08', CURDATE(), CURDATE());
INSERT INTO patients (id, email, name, phone, birthdate, created_at, updated_at) VALUES ('97c56b54-c34d-4cf7-90d6-8c19f5f62765', 'gabriely.souza@bonnasys.com.br', 'Gabriely Souza', '(33) 33333-3333', '1999-02-13', CURDATE(), CURDATE());

INSERT INTO vaccines (id, name, producer, created_at, updated_at) VALUES ('8e75d0b1-d165-4de0-ad5e-9cb7c37cf422', 'Febre amarela', 'Tres irmãos', CURDATE(), CURDATE());
INSERT INTO vaccines (id, name, producer, created_at, updated_at) VALUES ('845d1fa5-ab12-4378-bd23-b32fcd994705', 'Tríplice viral', 'Farmacia da esquina', CURDATE(), CURDATE());
INSERT INTO vaccines (id, name, producer, created_at, updated_at) VALUES ('7d6c9680-8088-4a63-8238-0b99b45d13a5', 'Tetraviral', 'Velas da fabrica', CURDATE(), CURDATE());
INSERT INTO vaccines (id, name, producer, created_at, updated_at) VALUES ('d1dbc5fc-58bd-4a4c-81ea-a7dd6368749d', 'DTP (tríplice bacteriana)', 'Juarez', CURDATE(), CURDATE());
INSERT INTO vaccines (id, name, producer, created_at, updated_at) VALUES ('15c8ecf3-b894-41f1-a8fb-1ab13c1d7338', 'HPV quadrivalente', 'Altas feitas', CURDATE(), CURDATE());

INSERT INTO health_center (id, name, address, state, neighborhood, created_at, updated_at) VALUES ('8c161f3a-a366-41e7-9bc6-9c350f67ae16', 'Valhalla Health', 'Rua x', 'SP', 'Maria do bairro', CURDATE(), CURDATE());
INSERT INTO health_center (id, name, address, state, neighborhood, created_at, updated_at) VALUES ('8aa02f21-0700-4bd4-89e5-2dfcd2ce57b6', 'Centro Quimedez', 'Avenida y', 'AC', 'Jardim vielas', CURDATE(), CURDATE());
INSERT INTO health_center (id, name, address, state, neighborhood, created_at, updated_at) VALUES ('dda47166-abc3-4a69-b2af-3517c264d867', 'Casa da Ajuda', 'Viela de roma', 'MA', 'Congresso', CURDATE(), CURDATE());
INSERT INTO health_center (id, name, address, state, neighborhood, created_at, updated_at) VALUES ('df1e2058-b27a-4a65-bca8-0e45ea0e5664', 'Mão Amiga', 'Rua t', 'RC', 'Visitas', CURDATE(), CURDATE());
INSERT INTO health_center (id, name, address, state, neighborhood, created_at, updated_at) VALUES ('cb75b13e-4ced-4150-b113-1d6ac9d8528e', 'Apostulo Paulo', 'Avenida z', 'TA', 'Jardim do Bairro azul', CURDATE(), CURDATE());

INSERT INTO vaccine_registration (id, vaccine_id, patient_id, health_center_id, registration_date) VALUES ('0f661538-d798-49f9-83fa-a22ab003964c', '8e75d0b1-d165-4de0-ad5e-9cb7c37cf422', 'afa12271-860d-4f2a-9987-c7b8fa439c99', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', '2020-11-26');
INSERT INTO vaccine_registration (id, vaccine_id, patient_id, health_center_id, registration_date) VALUES ('aaea26ee-074e-49ac-9dea-3530a01754c0', '845d1fa5-ab12-4378-bd23-b32fcd994705', 'afa12271-860d-4f2a-9987-c7b8fa439c99', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', '2021-12-02');
INSERT INTO vaccine_registration (id, vaccine_id, patient_id, health_center_id, registration_date) VALUES ('f4cf9282-1453-4f61-9a8f-2955f01f0ecf', '7d6c9680-8088-4a63-8238-0b99b45d13a5', 'afa12271-860d-4f2a-9987-c7b8fa439c99', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', '2021-04-11');
INSERT INTO vaccine_registration (id, vaccine_id, patient_id, health_center_id, registration_date) VALUES ('3d99e999-136e-4be9-b17a-4dc7ea442718', '15c8ecf3-b894-41f1-a8fb-1ab13c1d7338', 'afa12271-860d-4f2a-9987-c7b8fa439c99', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', '2023-08-16');

INSERT INTO patients_history (patient_id, history_id) VALUES ('afa12271-860d-4f2a-9987-c7b8fa439c99', '0f661538-d798-49f9-83fa-a22ab003964c');
INSERT INTO patients_history (patient_id, history_id) VALUES ('afa12271-860d-4f2a-9987-c7b8fa439c99', 'aaea26ee-074e-49ac-9dea-3530a01754c0');
INSERT INTO patients_history (patient_id, history_id) VALUES ('afa12271-860d-4f2a-9987-c7b8fa439c99', 'f4cf9282-1453-4f61-9a8f-2955f01f0ecf');
INSERT INTO patients_history (patient_id, history_id) VALUES ('afa12271-860d-4f2a-9987-c7b8fa439c99', '3d99e999-136e-4be9-b17a-4dc7ea442718');

INSERT INTO vaccine_stock (vaccine_id, health_center_id, amount) VALUES ('8e75d0b1-d165-4de0-ad5e-9cb7c37cf422', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', 12);
INSERT INTO vaccine_stock (vaccine_id, health_center_id, amount) VALUES ('845d1fa5-ab12-4378-bd23-b32fcd994705', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', 45);
INSERT INTO vaccine_stock (vaccine_id, health_center_id, amount) VALUES ('7d6c9680-8088-4a63-8238-0b99b45d13a5', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', 90);
INSERT INTO vaccine_stock (vaccine_id, health_center_id, amount) VALUES ('d1dbc5fc-58bd-4a4c-81ea-a7dd6368749d', '8c161f3a-a366-41e7-9bc6-9c350f67ae16', 23);