begin
    execute immediate 'drop table wallet';
    dbms_output.put_line('Table wallet dropped');
exception
    when others then dbms_output.put_line('Table wallet did not exist');
end;

begin
    execute immediate 'drop sequence wallet_seq';
    dbms_output.put_line('Sequence wallet_seq dropped');
exception
    when others then dbms_output.put_line('Sequence wallet_seq did not exist');
end;
create table wallet(
    id           number(19)           not null,
    balance         number(19)        not null,
    score        number(19)          not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint wallet_pk_id primary key (id)

);


create sequence wallet_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;

