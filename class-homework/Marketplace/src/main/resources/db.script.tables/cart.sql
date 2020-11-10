begin
    execute immediate 'drop table cart';
    dbms_output.put_line('Table cart dropped');
exception
    when others then dbms_output.put_line('Table cart did not exist');
end;
/
begin
    execute immediate 'drop sequence cart_seq';
    dbms_output.put_line('Sequence cart_seq dropped');
exception
    when others then dbms_output.put_line('Sequence cart_seq did not exist');
end;
/

create table cart(
    id           number(19)      not null,
    cart_date         TIMESTAMP       not null,
    customer_id number(19) not null ,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint cart_pk_id primary key (id),
    constraint cart_fk_customer_id foreign key (customer_id) references customer


);
create sequence cart_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
