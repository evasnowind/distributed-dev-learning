# Order
DROP DATABASE IF EXISTS tcc_order;
CREATE DATABASE tcc_order;

CREATE TABLE tcc_order.orders
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    user_id          INT(11)        DEFAULT NULL,
    product_id       INT(11)        DEFAULT NULL,
    pay_amount       DECIMAL(10, 0) DEFAULT NULL,
    status           INT(11) NOT NULL DEFAULT 0,
    add_time         DATETIME       DEFAULT CURRENT_TIMESTAMP,
    last_update_time DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

# Product
DROP DATABASE IF EXISTS tcc_product;
CREATE DATABASE tcc_product;

CREATE TABLE tcc_product.product
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    stock            INT(11)  DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
INSERT INTO tcc_product.product (id, stock) VALUES (1, 10); # 插入一条产品的库存

# Account
DROP DATABASE IF EXISTS tcc_account;
CREATE DATABASE tcc_account;

CREATE TABLE tcc_account.account
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    balance          DOUBLE   DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1  DEFAULT CHARSET = utf8;

INSERT INTO tcc_account.account (id, balance) VALUES (1, 10);
