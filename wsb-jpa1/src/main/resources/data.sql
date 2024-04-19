insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030'),
                   (2, 'zz', 'yy', 'city', '62-030'),
                   (3, 'xx', 'yy', 'city', '62-030');

insert into doctor (id, first_name, last_name, specialization, telephone_number, email, doctor_number)
            values (1, 'Jan', 'Kowalski', 'SURGEON', '1-123456789', 'doc1@example.com', '1'),
                   (2, 'Jan', 'Nowak', 'OCULIST', '2-123456789', 'doc2@example.com', '2'),
                   (3, 'Jan', 'Kowalski', 'SURGEON', '3-123456789', 'doc3@example.com', '3');

insert into doctor_to_address (doctor_id, address_id)
            values (1, 1), (2, 3);

insert into patient (id, first_name, last_name, telephone_number, email, date_of_birth, patient_number, verified)
            values (1, 'Jan', 'Kowalski', '3-123456789', 'qYUeh2@example.com', '2021-01-01', '123456788', true),
                   (2, 'Jan', 'Nowak', '4-123456789', 'qYUeh3@example.com', '2021-01-01', '123456789', false),
                   (3, 'Jan', 'Kowalski', '5-123456789', 'qYUeh4@example.com', '2021-01-01', '123456780', true);

insert into patient_to_address (patient_id, address_id)
            values (1, 1), (2, 2);

insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'description', '2021-01-01 12:00:00', 1, 1),
                   (2, 'description', '2021-11-01 12:00:00', 1, 2),
                   (3, 'description', '2021-11-01 12:00:00', 3, 2),
                   (4, 'description', '2021-11-01 12:00:00', 3, 3);

insert into medical_treatment (id, description, type, visit_id)
            values (1, 'description', 'EKG', 1),
                   (2, 'description', 'USG', 2),
                   (3, 'description', 'RTG', 2),
                   (4, 'description', 'RTG', 3);

