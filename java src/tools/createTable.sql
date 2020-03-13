CREATE TABLE book(
	bISBN			varchar(20)			not null,
	bName			varchar(255)		not null,
	bAuthor			varchar(255)		not null,
	bPress			varchar(255)		not null,
	bDescription	longtext,
	bPrice			decimal(6,2)		not null,
	bCategory		varchar(255)		not null,
	bFloor			varchar(20)			not null,
	bShelf			varchar(20)			not null,
	bAreaCode		varchar(20)			not null,
	
	PRIMARY KEY(bISBN)
);

CREATE TABLE bookInfo(
	bId				int(6) unsigned zerofill auto_increment,
	bISBN			varchar(20)			not null,
	bStatus			tinyint(1)			not null default(1),	-- 1: available, -1: unavailable
	
	PRIMARY KEY(bId),
	FOREIGN KEY(bISBN) REFERENCES book(bISBN)
);

CREATE TABLE reader(
	rId				int(6) unsigned zerofill auto_increment,
	rUsername		varchar(255)		not null,
	rEmail			varchar(255)		not null,
	rPassword		varchar(255)		not null,
	bookNumber		int 				not null,
	registerDate	DATETIME			not null,
	Deposit			decimal(6,2)		not null,
	
	PRIMARY KEY(rId)
);

-- 原 librarian bookrecord
CREATE TABLE bookLendRecord(
	lenRId			int(6) unsigned zerofill auto_increment,
	rId				int(6) unsigned zerofill	not null,
	bId				int(6) unsigned zerofill	not null,
	lenRLendTime	datetime			not null,
	lenRDueTime		datetime			not null,
	lenRReturnTime	datetime,
	lenRFine		decimal(5,2)		not null default(0),
	lenRStatus		tinyint(1)			not null default(1),	-- 1: current borrowing, 0: returned with left fine, -1: returned without fine
	
	PRIMARY KEY(lenRId),
	FOREIGN KEY(rId) REFERENCES reader(rId),
	FOREIGN KEY(bId) REFERENCES bookInfo(bId)
);

-- 原 reader readerReserveRecord
CREATE TABLE bookReserveRecord(
	resRId			int(6) unsigned zerofill auto_increment,
	rId				int(6) unsigned zerofill	not null,
	bId				int(6) unsigned zerofill	not null,
	resRLendTime	datetime			not null,
	resRDueTime		datetime			not null,
	resRStatus		tinyint(1)			not null default(1),	-- 1: valid, -1:unvalid(timeout or lent)
	
	PRIMARY KEY(resRId),
	FOREIGN KEY(rId) REFERENCES reader(rId),
	FOREIGN KEY(bId) REFERENCES bookInfo(bId)
);
