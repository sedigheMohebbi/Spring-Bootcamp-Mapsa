begin
    execute immediate 'drop table product';
    dbms_output.put_line('Table product dropped');
exception
    when others then dbms_output.put_line('Table product did not exist');
end;
/
begin
    execute immediate 'drop sequence product_seq';
    dbms_output.put_line('Sequence product_seq dropped');
exception
    when others then dbms_output.put_line('Sequence product_seq did not exist');
end;
/

create table product(
    id           number(19)           not null,
    product_number         varchar2(10)         not null,
    name         varchar(200)       not null,
    color        varchar(200),
    classification_id  number(19)           not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint product_pk_id primary key (id),
    constraint product_un_product_number unique (product_number),
    constraint product_un_name unique (name),
    constraint product_fk_classification_id foreign key (classification_id) references classification
);
/
create sequence product_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
