INSERT INTO user(username, password, authorities)
VALUES('user', '$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa', 'USER');

INSERT INTO user(username, password, authorities)
VALUES('admin', '$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa', 'ADMIN');


INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Rome', '2018-11-14', '2018-11-20', 'bice super', 1);

INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Lisbon', '2018-09-14', '2018-09-20', 'odlicno', 1);

INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Havana', '2018-12-20', '2018-12-30', 'bice super', 1);

INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Ancona', '2019-04-15', '2019-04-25', 'bice super', 2);

INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Sevilla', '2017-10-11', '2017-10-25', 'vrhunski', 2);

INSERT INTO TRIP(destination, start_date, end_date, comment, user_id)
VALUES('Lisbon', '2018-12-18', '2018-12-24', 'vrhunski', 2);