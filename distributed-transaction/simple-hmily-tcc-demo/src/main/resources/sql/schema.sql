drop table if exists hmily_dubbo_account;

create table hmily_dubbo_account (
    `id` int(11) NOT NULL,
    `name` varchar(64) not null ,
    `us_wallet` int(11) not null ,
    `cn_wallet` int(11) not null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;