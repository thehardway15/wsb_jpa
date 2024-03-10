insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');

insert into doctor (id, first_name, last_name, specialization, telephone_number, email, doctor_number)
            values (1, 'Jan', 'Kowalski', 1, '123456789', 'qYUeh@example.com', '123456789');

insert into doctor_to_address (doctor_id, address_id)
            values (1, 1);

insert into patient (id, first_name, last_name, telephone_number, email, date_of_birth, patient_number)
            values (1, 'Jan', 'Kowalski', '123456789', 'qYUeh@example.com', '2021-01-01', '123456789');

insert into patient_to_address (patient_id, address_id)
            values (1, 1);

insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'description', '2021-01-01 12:00:00', 1, 1);

insert into medical_treatment (id, description, type, visit_id)
            values (1, 'description', 'USG', 1);

