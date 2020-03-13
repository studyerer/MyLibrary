drop database libraryautomation;
create database libraryautomation;
use libraryautomation;
-- insert into `book` VALUE('1','hlm','12','qq','3_dong','12341231','available','science');
-- insert into `book` VALUE('2','xyj','13','ww','3_dong','12341232','available','sciadadaw');
CREATE TABLE book(
	bISBN		 	varchar(20)		 	not null,
	bName		 	varchar(255)		 not null,
	bAuthor	 		varchar(255)	 	not null,
	bPress		 	varchar(255)		 not null,
	bDescription	 longtext,
	bPrice		 	decimal(6,2)	  	not null,
	bCategory	 	varchar(255)	  	not null,
	bFloor		 	varchar(20)	 	 	not null,
	bShelf		 	varchar(20)	  		not null,
	bAreaCode	 	varchar(20)		 	not null,
	
	PRIMARY  KEY(bISBN)
);

CREATE TABLE reader(
	rId		 	 	int(6)   unsigned  zerofill  auto_increment,
	rUsername	 	varchar(255)	 	not null,
	rEmail		 	varchar(255)	 	not null,
	rPassword	 	varchar(255)	 	not null,
	bookNumber	 	int 			 	not null,
	registerDate	 DATETIME		 	not null,
	Deposit		  	decimal(6,2)	  	not null,
	 
 	PRIMARY KEY(rId)
);

CREATE TABLE bookInfo(
	bId			 	int(6)  unsigned   zerofill  auto_increment,
	bISBN		 	varchar(20)		 	not null,
	bStatus		 	tinyint(1)	 	 	not null  default'1',	-- 1: available, -1: unavailable
	
	PRIMARY  KEY(bId),
	FOREIGN  KEY(bISBN)  REFERENCES  book(bISBN)
);


-- insert into `bookrecord` VALUE('1',12,'1221-2-2 23:11;11','1222-1-1 2:11;11','2','0001');
-- alter  table bookLendRecord add  column noFine  decimal(5,2)  not   null  default'0'
CREATE TABLE bookLendRecord(
	lenRId 	  		int(6)    unsigned zerofill   auto_increment,
	rId		   		int(6)    unsigned zerofill 	 not  null,
	bId	 	  		int(6)    unsigned zerofill 	 not  null,
	lenRLendTime  	datetime 	  		not  null,
	lenRDueTime	  	datetime   			not  null,
	lenRReturnTime  datetime, 
	lenRFine 	 	decimal(5,2)  	 	not   null  default'0',
	noFine			decimal(5,2)  	 	not   null  default'0',
	lenRStatus 	   	tinyint(1)	   		not   null  default'1',	-- 1: current borrowing, 0: returned with left fine, -1: returned without fine
	PRIMARY  KEY(lenRId),
	FOREIGN  KEY(rId)  REFERENCES  reader(rId),
	FOREIGN  KEY(bId)  REFERENCES  bookInfo(bId)
);

-- 原 reader readerReserveRecord
CREATE TABLE bookReserveRecord(
	resRId 		    int(6) unsigned zerofill  auto_increment,
	rId		 	    int(6) unsigned zerofill 	not  null,
	bId		 	    int(6) unsigned zerofill 	not  null,
	resRLendTime 	datetime	 		not  null,
	resRDueTime	 	datetime	 		not  null,
	resRStatus 		tinyint(1)	 		not  null  default '1',	-- 1: valid, -1:unvalid(timeout or lent)
	 
	PRIMARY  KEY(resRId),
	FOREIGN  KEY(rId)  REFERENCES  reader(rId),
	FOREIGN  KEY(bId)  REFERENCES  bookInfo(bId)
);

-- insert into `reader` VALUE('0001','123456','1765578099@qq.com',1,'1220-2-2 5:12;13',300);
-- insert into `reader` VALUE('0002','123456','1765513131@qq.com',2,'1220-2-1 5:12;13',300);
-- insert into `admin` VALUE('admin','test');

-- insert into `librarian` VALUE('0001','123456');
create table librarian(
	AcntNum  varchar(255) not null,
    Password varchar(255) not null,
    PRIMARY   KEY(AcntNum)
);

create table admin(
	AcntNum  varchar(255) not null,
    Password varchar(255) not null,
    PRIMARY KEY(AcntNum)
);

create table announcement(
	TextId varchar(255) not null,
    Text text not null,
    Date DATETIME not null,
    PRIMARY KEY(TextId)
);

CREATE TABLE Fine(
	rId		 	 	int(6)   unsigned  zerofill  auto_increment,
	bId	 			int(6)   unsigned  zerofill  auto_increment,
	rEmail		 	varchar(255)	 	not null,
	rPassword	 	varchar(255)	 	not null,
	bookNumber	 	int 			 	not null,
	registerDate	 DATETIME		 	not null,
	Deposit		  	decimal(6,2)	  	not null,
	 
 	PRIMARY KEY(rId)
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
