begin
    execute immediate 'drop table customer';
    dbms_output.put_line('Table customer dropped');
exception
    when others then dbms_output.put_line('Table customer did not exist');
end;
/
begin
    execute immediate 'drop sequence customer_seq';
    dbms_output.put_line('Sequence customer_seq dropped');
exception
    when others then dbms_output.put_line('Sequence customer_seq did not exist');
end;
create table customer
(
    id              number(19)           not null,
    customer_number varchar(200)         not null,
    first_name      varchar(200)         not null,
    last_name       varchar(200)         not null,
    email           varchar(200)         not null,
    phone           varchar(200)         not null,
    wallet_id       number(19),
    remarks         nvarchar2(400),
    lock_version    number(19) default 1 not null,
    constraint customer_pk_id primary key (id),
    constraint product_fk_wallet_id foreign key (wallet_id) references wallet


);


create sequence customer_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
