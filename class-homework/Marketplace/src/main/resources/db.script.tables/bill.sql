begin
    execute immediate 'drop table bill';
    dbms_output.put_line('Table bill dropped');
exception
    when others then dbms_output.put_line('Table bill did not exist');
end;
/
begin
    execute immediate 'drop sequence bill_seq';
    dbms_output.put_line('Sequence bill_seq dropped');
exception
    when others then dbms_output.put_line('Sequence bill_seq did not exist');
end;
/

create table bill(
    id           number(19)           not null,
    bill_number         varchar2(10)         not null,
    total         REAL       not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint bill_pk_id primary key (id),
    constraint bill_un_bill_number unique (bill_number)

);
create sequence bill_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
