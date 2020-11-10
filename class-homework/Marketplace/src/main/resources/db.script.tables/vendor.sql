begin
    execute immediate 'drop table vendor';
    dbms_output.put_line('Table vendor dropped');
exception
    when others then dbms_output.put_line('Table vendor did not exist');
end;
/
begin
    execute immediate 'drop sequence vendor_seq';
    dbms_output.put_line('Sequence vendor_seq dropped');
exception
    when others then dbms_output.put_line('Sequence vendor_seq did not exist');
end;
/
create table vendor(
    id           number(19)           not null,
    phone        varchar(200)         not null,
    name         varchar(200)         not null,
    email        varchar(200)          not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint vendor_pk_id primary key (id)

);

/
create sequence vendor_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
