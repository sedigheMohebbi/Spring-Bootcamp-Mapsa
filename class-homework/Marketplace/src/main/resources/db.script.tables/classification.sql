begin
    execute immediate 'drop table classification';
    dbms_output.put_line('Table classification dropped');
exception
    when others then dbms_output.put_line('Table classification did not exist');
end;
/
begin
    execute immediate 'drop sequence classification_seq';
    dbms_output.put_line('Sequence classification_seq dropped');
exception
    when others then dbms_output.put_line('Sequence classification_seq did not exist');
end;
/
create table classification(
    id           number(19)           not null,
    name         nvarchar2(200)       not null,
    category_id number(19) not null,
    remarks      nvarchar2(400),
    lock_version number(19) default 1 not null,
    constraint classification_pk_id primary key (id),
    constraint classification_un_name unique (name),
    constraint classification_fk_category_id foreign key (category_id) references category
);
/
create sequence classification_seq
    minvalue 10000 maxvalue 9999999999999999999 cycle
    start with 10000 increment by 1000 cache 1000;
/
