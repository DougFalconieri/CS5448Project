DELETE FROM ezprocure.order;
DELETE FROM ezprocure.vendor;
DELETE FROM ezprocure.facility;
DELETE FROM ezprocure.item;
DELETE FROM ezprocure.item_category;
DELETE FROM ezprocure.user_role;
DELETE FROM ezprocure.user;
DELETE FROM ezprocure.role;

/* Add people */
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
INSERT INTO `ezprocure`.`user`
(`id`,`email`,`password`, `first_name`, `last_name`, `manager_id`)
VALUES
(4, 'test', 'test', 'Test', 'User', 2);

/* Add roles */
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

/* Add user roles */
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

INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(4, 1);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(4, 2);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(4, 3);
INSERT INTO ezprocure.user_role
(user_id, role_id)
VALUES
(4, 4);

/* Add categories */
INSERT INTO `ezprocure`.`item_category`
(`id`,`name`)
VALUES
(1, 'Office Supplies');
INSERT INTO `ezprocure`.`item_category`
(`id`,`name`)
VALUES
(2, 'Electronics');

/* Add items */
INSERT INTO `ezprocure`.`item`
(`id`, `description`, `item_category_id`)
VALUES
(1, 'Stapler', 1);
INSERT INTO `ezprocure`.`item`
(`id`, `description`, `item_category_id`)
VALUES
(2, 'Desk Chair', 1);
INSERT INTO `ezprocure`.`item`
(`id`, `description`, `item_category_id`)
VALUES
(3, 'Paper Clips', 1);
INSERT INTO `ezprocure`.`item`
(`id`, `description`, `item_category_id`)
VALUES
(4, 'Laptop Computer', 2);
INSERT INTO `ezprocure`.`item`
(`id`, `description`, `item_category_id`)
VALUES
(5, 'Tablet', 2);

/* Add facilities */
INSERT INTO `ezprocure`.`facility`
(`id`, `name`, `address`, `city`, `state`, `zip`)
VALUES
(1, 'South Denver Facility', '123 Elbert Cr.', 'Littleton', 'CO', 80127);

/* Add vendors */
INSERT INTO `ezprocure`.`vendor`
(`id`, `name`, `preferred`)
VALUES
(1, 'ACME Industries', false);

/* Add orders */
INSERT INTO `ezprocure`.`order`
(`id`, `description`, `quantity`, `justification`, `room`, `created_date`,
`status`, `item_id`, `employee_id`, `facility_id`)
VALUES
(1, 'I need a new, more powerful laptop', 1, 'My old laptop is too old to run the new version of our CAD software',
'123', sysdate(), 'Submitted', 4, 4, 1);
INSERT INTO `ezprocure`.`order`
(`id`, `description`, `quantity`, `justification`, `room`, `created_date`,
`status`, `item_id`, `employee_id`, `facility_id`)
VALUES
(2, 'I need a new stapler', 1, 'I lost mine',
'123', sysdate(), 'Approved', 4, 4, 1);

