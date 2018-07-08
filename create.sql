create table rooms(
roomtype varchar(255) not null,
price double not null,
roomid char(4) primary key not null
);

create table guesses(
id char(18) primary key not null,
name varchar(10) not null,
sex int not null,
/*-1,female 1,male*/
check (sex in(-1,1)),
phone char(11)
);

create table operators(
username varchar(20) primary key not null,
password varchar(30) not null,
name varchar(10) not null,
id char(18) not null,
phone char(11) not null,
sex int not null,
check(sex in(1,-1))
);

create table orders(
opname varchar(20) not null,
starttime date not null,
endtime date not null,
guest char(18) not null,
room char(4) not null,
price double not null,
isdone int,
orderid int not null AUTO_INCREMENT primary key,
check (isdone in (1,0)),
foreign key (opname) references operators(username) ON DELETE CASCADE,
foreign key (guest) references guesses(id) ON DELETE CASCADE,
foreign key (room) references rooms(roomid) ON DELETE CASCADE
);
