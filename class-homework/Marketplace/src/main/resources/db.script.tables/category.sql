begin
    execute immediate 'drop table CATEGORY';
    dbms_output.put_line('Table category dropped');
exception
    when others then dbms_output.put_line('Table category did not exist');
end;
/

begin
    execute immediate 'drop sequence category_seq';
    dbms_output.put_line('Sequence category_seq dropped');
exception
    when others then dbms_output.put_line('Sequence category_seq did not exist');
end;
/


create table category
(
    id           number(19)           not null,
    name         nvarchar2(200)       not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    CONSTRAINT category_pk_id primary key (id),
    constraint category_un_name unique (name)
);

/

create sequence classification_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/