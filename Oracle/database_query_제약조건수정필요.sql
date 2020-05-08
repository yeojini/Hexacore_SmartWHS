
/*********초기 테이블 삭제*********/
drop table employee;
drop table forklift;
drop table warehouse;
drop table item;
drop table forkcheck;
drop table invoice;

/*********테이블 생성*********/
create table employee(
empno varchar(10) PRIMARY KEY NOT NULL,
wareid varchar(10),
warename varchar(30),
empname varchar(30),
emppw varchar(20),
empjob varchar(30),
empemail varchar(30),
empphone varchar(15),
primary key(empno),
constraint emp_ware_fk foreign key (wareid) references warehouse(wareid)
);


create table forklift(
forkid varchar(10) PRIMARY KEY NOT NULL, 
wareid varchar(10), 
forkpurdate date, 
forkmodel varchar(30), 
forklastcheckdate date, 
forkdist number,
constraint fork_ware_fk foreign key (wareid) references warehouse(wareid)
);


create table warehouse(
wareid varchar(10) PRIMARY KEY, 
warename varchar(30),
warecate varchar(30),
waresize number,
waretype varchar(30)
); 

/**수정중**/
create table item(
itemid varchar(10) PRIMARY KEY NOT NULL, 
wareid varchar(10), 
itemname varchar(30), 
warename varchar(30), 
itemcate varchar(30), 
itemprice number, 
itemstock number, 
itemweightpb number, 
itemqtypb number, 
itemloc varchar(10),
constraint item_ware_fk foreign key (wareid) references warehouse(wareid)
);

/** forkchkid sequence **/
create sequence forkchkseq increment by 1 start with 1;

create table forkcheck(
forkchkid number PRIMARY KEY,
forkid varchar(10),
forkdist number,
forkbatt number,
forkbrk number,
forklastchkdate DATE DEFAULT sysdate,
constraint forkchk_fork_fk foreign key (forkid) references forklift(forkid)
);

/** invoiceid sequence **/
create sequence invoiceseq increment by 1 start with 1;

create table invoice(
invoiceid number PRIMARY KEY, 
empno varchar(10), 
empname varchar(30),
invoicedate DATE DEFAULT sysdate
);

/** invoicedtlid sequence **/
create sequence invoicedtlseq increment by 1 start with 1;

CREATE TABLE invoicedetail(
invoicedtlid number PRIMARY KEY,
itemid varchar(10), 
itemname varchar(30), 
wareid varchar(10), 
warename varchar(30), 
invoicestat varchar(20), 
invoicedtlqty number, 
empno varchar(10), 
empname varchar(30),
invoiceid number,
invoicedtldate DATE DEFAULT sysdate,
constraint invoice_item_fk foreign key (itemid) references item(itemid),
constraint invoice_ware_fk foreign key (wareid) references warehouse(wareid)
)

/*********데이터 추가*********/

/*employee*/

/*forklift*/
insert into forklift values(
'id1111', 'wh1111', now(), 'slfk-234-sgh', now(), 2400);

/*warehouse*/
insert into warehouse values(
'wh1111', '이천 제1물류창고', '가전제품', 1624.1, '일반창고'); 

/*item*/

/*forkcheck*/

/*invoice*/
