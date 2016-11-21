DELETE FROM ezprocure.user_role;
DELETE FROM ezprocure.user;
DELETE FROM ezprocure.role;

INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`, `first_name`, `last_name`)
VALUES
(1, 'douglas.falconieri@colorado.edu', 'password', 'Doug', 'Falconieri');
INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`, `first_name`, `last_name`)
VALUES
(2, 'Yi.Kuo@colorado.edu', 'password', 'Yi', 'Kuo');
INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`, `first_name`, `last_name`)
VALUES
(3, 'wash7661@colorado.edu', 'password', 'Walid', 'Sharif');


INSERT INTO `ezprocure`.`role`
(`id`,`name`)
VALUES
(1, 'Manager');
INSERT INTO `ezprocure`.`role`
(`id`,`name`)
VALUES
(2, 'Procurement Officer');
INSERT INTO `ezprocure`.`role`
(`id`,`name`)
VALUES
(3, 'Procurement Manager');
INSERT INTO `ezprocure`.`role`
(`id`,`name`)
VALUES
(4, 'Receiving Officer');

INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(1, 1);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(1, 2);

INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(2, 1);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(2, 2);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(2, 3);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(2, 4);

INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(3, 1);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(3, 2);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(3, 3);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(3, 4);

