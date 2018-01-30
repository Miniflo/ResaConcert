create table spectacle(

id int not null identity,
titre varchar(200) not null,
artiste varchar(200) not null,
lieu varchar(200),
date date not null,
places_disponibles int not null
);

alter table spectacle add constraint spectacle_pk primary key(id);

create table reservation(
code_reservation char(20) not null,
spectacle_id int not null,
client_id int not null,
nombre_places int not null,
date_reservation date not null
);

alter table reservation add constraint reservation_pk primary key(code_reservation);
alter table reservation add constraint reservation_spectacle_fk foreign key(spectacle_id)
							references spectacle(id);
alter table reservation add constraint reservation_client_fk foreign key(client_id)
							references client(id);

create table client(
id int not null,
nom varchar(200) not null,
prenom varchar(200) not null,
email varchar(200) not null,
adresse text not null,
code_postal char(5) not null,
ville varchar(200) not null
);

alter table client add constraint client_pk primary key(id);
