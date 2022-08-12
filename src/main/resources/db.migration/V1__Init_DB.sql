create table products (
    id bigint not null auto_increment,
    description varchar(2048),
    is_checked_like bit,
    is_liked bit,
    price double precision,
    title varchar(255),
    primary key (id)
);

create table users (
    id bigint not null auto_increment,
    email varchar(255),
    password varchar(255),
    user_name varchar(255),
    primary key (id)
);

create table users_products (
    user_id bigint not null,
    product_id bigint not null
);

create table users_who_disliked (
    product_id bigint not null,
    user_id bigint not null
);

create table users_who_liked (
    product_id bigint not null,
    user_id bigint not null
);

alter table users_products
    add constraint users_products_product_id_fk
    foreign key (product_id)
    references products (id);

alter table users_products
    add constraint users_products_user_id_fk
    foreign key (user_id)
    references users (id);

alter table users_who_disliked
    add constraint users_who_disliked_user_id_fk
    foreign key (user_id)
    references users (id);

alter table users_who_disliked
    add constraint users_who_disliked_product_id_fk
    foreign key (product_id)
    references products (id);

alter table users_who_liked
    add constraint users_who_liked_user_id_fk
    foreign key (user_id)
    references users (id);

alter table users_who_liked
    add constraint users_who_liked_product_id_fk
    foreign key (product_id)
    references products (id);
