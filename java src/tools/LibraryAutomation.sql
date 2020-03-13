drop database libraryautomation;
create database libraryautomation;
use libraryautomation;
-- insert into `book` VALUE('1','hlm','12','qq','3_dong','12341231','available','science');
-- insert into `book` VALUE('2','xyj','13','ww','3_dong','12341232','available','sciadadaw');
create table book(
	BookId int(6)  unsigned zerofill auto_increment not null,
    BookName varchar(255) not null,
    author varchar(255) not null,
    Press varchar(255) not null,
    Price varchar(255) not null,
    Position varchar(255) not null,
    ISBN varchar(255) not null,
    status varchar(255) not null, -- available/reserved/lent
    Category varchar(255) not null,
    description text,
    
    number int not null,
    PRIMARY KEY (BookId)
);
-- insert into `bookrecord` VALUE('1',12,'1221-2-2 23:11;11','1222-1-1 2:11;11','2','0001');
create table bookrecord(
	informationId int(6)  unsigned zerofill auto_increment not null,
    FineValue float,
    LentDate DATETIME not null,
    ReturnDate DATETIME,
    BookId int(6) unsigned zerofill not null,
    AcntNum int(6) unsigned zerofill not null,
    PRIMARY KEY (informationId)
    
);
create table optionRecord(
	informationId int(6)  unsigned zerofill auto_increment not null,
	BookId int(6) unsigned zerofill not null,
    AcntNum int(6) unsigned zerofill not null,
	reason  varchar(255),
	operation varchar(255) not null,
	creationDate DATETIME not null,
	PRIMARY KEY (informationId)
	
	
);
create table reserve(
	informationId int(6)  unsigned zerofill auto_increment not null,
	BookId int(6) unsigned zerofill not null,
    AcntNum int(6)  unsigned zerofill  not null,
	createDate DATETIME not null,
	PRIMARY KEY (informationId)
);
-- insert into `reader` VALUE('0001','123456','1765578099@qq.com',1,'1220-2-2 5:12;13',300);
-- insert into `reader` VALUE('0002','123456','1765513131@qq.com',2,'1220-2-1 5:12;13',300);
-- insert into `admin` VALUE('admin','test');
create table reader(
	AcntNum int(6)  unsigned zerofill auto_increment not null,
	Telephone varchar(255) not null,
    Password varchar(255) not null,
    Email varchar(255) not null,
    bookNumber int not null,
    registerDate DATETIME not null,
    Deposit float not null,
    PRIMARY KEY(AcntNum)
);
-- insert into `librarian` VALUE('0001','123456');
create table librarian(
	AcntNum varchar(255) not null,
    Password varchar(255) not null,
    PRIMARY KEY(AcntNum)
);

create table admin(
	AcntNum varchar(255) not null,
    Password varchar(255) not null,
    PRIMARY KEY(AcntNum)
);

create table announcement(
	TextId varchar(255) not null,
    Text text not null,
    Date DATETIME not null,
    PRIMARY KEY(TextId)
);

-- reader/book_bookrecord外键连接，关联更新，不关联删除
-- alter table bookrecord
-- add constraint reader_bookrecord foreign key (`acntnum`)
-- references reader(`acntnum`) on update cascade on delete no action;

-- alter table bookrecord
-- add constraint book_bookrecord foreign key (`bookId`)
-- references book(`bookId`) on update cascade on delete no action;

-- reader/book_optionRecord外键连接，关联更新，关联删除
alter table optionRecord
add constraint reader_optionRecord foreign key (`acntnum`)
references reader(`acntnum`) on update cascade on delete CASCADE;

alter table optionRecord
add constraint book_optionRecord foreign key (`bookId`)
references book(`bookId`) on update cascade on delete CASCADE;

-- reader/book_reserve外键连接，关联更新，关联删除
alter table reserve
add constraint reader_reserve foreign key (`acntnum`)
references reader(`acntnum`) on update cascade on delete CASCADE;

alter table reserve
add constraint book_reserve foreign key (`bookId`)
references book(`bookId`) on update cascade on delete CASCADE;

alter table bookrecord
add constraint reader_bookrecord foreign key (`acntnum`)
references reader(`acntnum`) on update cascade on delete CASCADE;

alter table bookrecord
add constraint book_bookrecord foreign key (`bookId`)
references book(`bookId`) on update cascade on delete CASCADE;
-- ALTER  TABLE 表名 MODIFY COLUMN 字段名 新数据类型 新类型长度  新默认值  新注释; -- COLUMN可以省略
-- alter  table bookrecord modify  column returndate  datetime 
