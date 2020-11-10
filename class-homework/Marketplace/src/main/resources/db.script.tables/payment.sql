begin
    execute immediate 'drop table payment';
    dbms_output.put_line('Table payment dropped');
exception
    when others then dbms_output.put_line('Table payment did not exist');
end;
/
begin
    execute immediate 'drop sequence payment_seq';
    dbms_output.put_line('Sequence payment_seq dropped');
exception
    when others then dbms_output.put_line('Sequence payment_seq did not exist');
end;
/

create table payment(
    id        number(19)           not null,
    payment_number         varchar2(10)         not null,
    status         number(10)     not null,
    total        REAL ,
    bill_id  number(19)           not null,
    wallet_id  number(19)           ,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint payment_pk_id primary key (id),
    constraint payment_un_payment_number unique (payment_number),
    constraint payment_fk_bill_id foreign key (bill_id) references bill,
    constraint payment_fk_wallet_id foreign key (wallet_id) references wallet

);
create sequence payment_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
