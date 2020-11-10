begin
    execute immediate 'drop table lineItem_order';
    dbms_output.put_line('Table lineItem_order dropped');
exception
    when others then dbms_output.put_line('Table lineItem_order did not exist');
end;
/
begin
    execute immediate 'drop sequence lineItem_order_seq';
    dbms_output.put_line('Sequence lineItem_order_seq dropped');
exception
    when others then dbms_output.put_line('Sequence lineItem_order_seq did not exist');
end;
/

create table lineItem_order(
    order_id  number(19)           not null,
    lineItem_id  number(19)           not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint lineItem_order_pk_line_order primary key (lineItem_id,order_id)

);
/
create sequence lineItem_order_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
