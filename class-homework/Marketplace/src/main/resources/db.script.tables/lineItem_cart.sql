begin
    execute immediate 'drop table lineItem_cart';
    dbms_output.put_line('Table lineItem_cart dropped');
exception
    when others then dbms_output.put_line('Table lineItem_cart did not exist');
end;
/
begin
    execute immediate 'drop sequence lineItem_cart_seq';
    dbms_output.put_line('Sequence lineItem_cart_seq dropped');
exception
    when others then dbms_output.put_line('Sequence lineItem_cart_seq did not exist');
end;
/

create table lineItem_cart(
    cart_id  number(19)           not null,
    lineItem_id  number(19)           not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint lineItem_cart_pk_lineItem_cart primary key (lineItem_id,cart_id)
);
/
create sequence lineItem_cart_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
