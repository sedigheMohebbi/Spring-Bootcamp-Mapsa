begin
    execute immediate 'drop table address';
    dbms_output.put_line('Table address dropped');
exception
    when others then dbms_output.put_line('Table address did not exist');
end;
/
begin
    execute immediate 'drop sequence address_seq';
    dbms_output.put_line('Sequence address_seq dropped');
exception
    when others then dbms_output.put_line('Sequence address_seq did not exist');
end;
/
create table address(
    id           number(19)           not null,
    city         varchar (200)        not null,
    province         varchar(200)      not null,
    unit         number(4) ,
    floor         number(3) ,
    street         varchar(200)    not null,
    phone         varchar(15)      not null,
    vendor_id  number(19)           ,
    customer_id  number(19)            ,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint address_pk_id primary key (id),
    constraint address_fk_vendor_id foreign key (vendor_id) references vendor,
    constraint address_fk_customer_id foreign key (customer_id) references customer
);

/
create sequence address_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
