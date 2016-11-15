DELETE FROM ezprocure.user;
DELETE FROM ezprocure.role;
DELETE FROM ezprocure.user_role;

INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`)
VALUES
(1, 'douglas.falconieri@colorado.edu', 'password');
INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`)
VALUES
(2, 'Yi.Kuo@colorado.edu', 'password');
INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`)
VALUES
(3, 'wash7661@colorado.edu', 'password');


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

