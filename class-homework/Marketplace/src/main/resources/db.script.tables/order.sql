begin
    execute immediate 'drop table order';
    dbms_output.put_line('Table order dropped');
exception
    when others then dbms_output.put_line('Table order did not exist');
end;
/
begin
    execute immediate 'drop sequence order_seq';
    dbms_output.put_line('Sequence order_seq dropped');
exception
    when others then dbms_output.put_line('Sequence order_seq did not exist');
end;
/

create table "ORDER"(
    id           number(19)           not null,
    order_number         varchar2(10)         not null,
    status         number(2)       not null,
    order_date        timestamp not null,
    bill_id  number(19)           ,
    cart_id  number(19)           not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint order_pk_id primary key (id),
    constraint order_un_order_number unique (order_number),
    constraint order_fk_bill_id foreign key (bill_id) references bill,
    constraint order_fk_cart_id foreign key (cart_id) references cart
);
/
create sequence order_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
