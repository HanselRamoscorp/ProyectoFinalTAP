
create table user(
  id_user int not null auto_increment,
  firstname varchar(20),
  lastlame varchar(40),
  password varchar(20) not null,
  id_store int,
  id_typeuser int not null ,
  constraint uPK primary key (id_user),
  constraint  uFK foreign key (id_store) references store(id_store),
  constraint uFK2 foreign key (id_typeuser) references typeuser(id_typeuser)

);

create table typeuser(
  id_typeuser int not null auto_increment,
  typeuser varchar(10) not null,
  constraint tuPK primary key (id_typeuser)

);

create table store(
  id_store int not null  auto_increment,
  name varchar(20) not null,
  street varchar(30),
  colony varchar (30),
  numAddress varchar(5),
  id_city int not null,
  id_state int not null,
  constraint sPK primary key (id_store),
  constraint sFK1 foreign key (id_city) references city(id_city),
  constraint sFK2 foreign key (id_state) references state(id_state)
);

create table city(
  id_city int not null auto_increment,
  city varchar(10),
  constraint cPK primary key (id_city)

);


create table state(
  id_state int not null auto_increment,
  state varchar(10),
  constraint cPK primary key (id_state)
);



create  table planHS(
  id_planHS int not null auto_increment,
  quantity int,
  constraint planHS primary key (id_planHS)

);


create table homeservice (
  id_HomeService int auto_increment not null,
  id_TypeHS int  not null ,
  id_company int not null ,
  id_planHS int not null ,
  constraint hsPK primary key (id_HomeService),
  constraint hsFK foreign key (id_TypeHS) references typehomeservice(id_TypeHS),
  constraint hsFK2 foreign key (id_company) references company(id_company),
    constraint hsFK3 foreign key (id_planHS) references planHS(id_planHS)


);
create  table typehomeservice(
  id_TypeHS int auto_increment not null,
  type varchar(20) not null,
  constraint  thsPK primary key (id_TypeHS)

);

create table clientHS(
  id_clientHS int not null auto_increment,
  firstname varchar(20),
  lastlame varchar(40),
  numAddress varchar(5),
  phonenumber varchar(12),
  id_city int not null,
  id_state int not null,
  constraint chsPK primary key (id_clientHS),
  constraint chsFK foreign key (id_city) references city(id_city),
  constraint chsFK2 foreign key (id_state) references state(id_state)

);
/* paymentHS su id es la referncia de Pago y ahi se actualiza el pago y la fecha de pago */
create table paymentHS(
id_paymentHS int not null auto_increment,

  pay_date datetime,
  pay_amount int ,
  id_HomeService int not null,
  id_user int,
  constraint pHSPK primary key (id_paymentHS),
  constraint  pHSFF foreign key (id_HomeService) references homeservice(id_HomeService),
    constraint  pHSFF2 foreign key (id_user) references user(id_user)
);

create table typecompany(
  id_typecompany int auto_increment not null ,
  typecompany varchar(30) not null,
  constraint tcPK primary key (id_typecompany)
);

create table commission(
  id_commission int auto_increment not null,
  percentage int not null,
constraint cPK primary key (id_commission)
);

create table company(
  id_company int auto_increment not null,
  name varchar(20),
id_commission int not null,
  constraint comPK primary key (id_company),
  constraint  comFK foreign key (id_commission) references commission(id_commission)
);

create table recharge(
  id_recharge int auto_increment not null ,
      phonenumber varchar(12) not null ,
    id_phoneplane int not null,
  constraint rPK primary key (id_recharge),
  constraint rFK foreign key (id_phoneplane) references phoneplan(id_phoneplane)

);

  create table phoneplan(
    id_phoneplane int auto_increment not null ,
    quantity int not null ,
    id_company int not null,
    constraint ppPK primary key (id_phoneplane),
    constraint ppFK foreign key (id_company) references company(id_company)
  );

create table giftcartcredit(
  id_giftcartcredit int not null auto_increment,
  credit int not null
);
create table giftcart(
  id_giftcard int not null auto_increment,
  id_company int not null,
  id_giftcartcredit int not null,
  constraint gcPK primary key (id_giftcard),
  constraint gcPF foreign key (id_company) references company(id_company),
  constraint gcPF2 foreign key (id_giftcartcredit) references giftcartcredit(id_giftcartcredit)


);