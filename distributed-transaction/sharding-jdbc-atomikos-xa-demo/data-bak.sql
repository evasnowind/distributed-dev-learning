# Order
DROP DATABASE IF EXISTS xa_order;
CREATE DATABASE xa_order;

CREATE TABLE xa_order.orders
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    user_id          INT(11)        DEFAULT NULL,
    product_id       INT(11)        DEFAULT NULL,
    pay_amount       DECIMAL(10, 0) DEFAULT NULL,
    add_time         DATETIME       DEFAULT CURRENT_TIMESTAMP,
    last_update_time DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;


# Product
DROP DATABASE IF EXISTS xa_product;
CREATE DATABASE xa_product;

CREATE TABLE xa_product.product
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    stock            INT(11)  DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

INSERT INTO xa_product.product (id, stock) VALUES (1, 10); # 插入一条产品的库存


# Account
DROP DATABASE IF EXISTS xa_account;
CREATE DATABASE xa_account;

CREATE TABLE xa_account.account
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    balance          DOUBLE   DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1  DEFAULT CHARSET = utf8;

INSERT INTO xa_account.account (id, balance) VALUES (1, 10);
