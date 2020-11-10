begin
    execute immediate 'drop table lineItem';
    dbms_output.put_line('Table lineItem dropped');
exception
    when others then dbms_output.put_line('Table lineItem did not exist');
end;
/
begin
    execute immediate 'drop sequence lineItem_seq';
    dbms_output.put_line('Sequence lineItem_seq dropped');
exception
    when others then dbms_output.put_line('Sequence lineItem_seq did not exist');
end;
create table lineItem(
    id           number(19)           not null,
    quantity         number(19)        not null,
    price         REAL       not null,
    product_id number(19) not null,
    vendor_id number(19) not null ,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint lineItem_pk_id primary key (id),
    constraint lineItem_fk_vendor_id foreign key (vendor_id) references vendor

);

create sequence lineItem_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
