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
id int not null identity,
nom varchar(200) not null,
prenom varchar(200) not null,
email varchar(200) not null,
adresse text not null,
code_postal char(5) not null,
ville varchar(200) not null
);

alter table client add constraint client_pk primary key(id);

/*----------------------------------------------------------------------------*/

LISTE DES SPECTACLES : 

Pour chaque ligne :
Select titre, artiste, lieu, date, places disponibles from Spectacle
|-> On affiche tout sauf les places disponibles qui servent pour le bouton indisponible;

CLIENTS : 

Pour chaque ligne  : 
Select nom, prenom, email from Client.


Select nom, prenom, email from Client;

RESERVATIONS : 
Select nom, prenom, email from client jointure Select nombre places reservation jointure select titre, artiste, date from spectacle
Delete resa from reservations + alter table spectacle pour modifier nb places



RESA EFFECTUEE : 
Select Places disponibles, artiste, lieu, date, titre from spectacle jointure codereservation from reservation. 


insert : 

insert into reservation(spectacle_id, client_id, nombre_places, date_reservation) values();
insert into reservation(nombre_places) values(?);

 
 -- jeu d'essai--
 
insert into spectacle(titre, artiste, lieu, date, places_disponibles) values ('good bye tour','Johnny Hallyday','Olympia','2018-02-01','3');

insert into client (nom, prenom, email, adresse, code_postal, ville) values ('curie', 'marie', 'curiemarie@gmail.com', '4 boulevard du cirque', 44000, 'nantes');
insert into client (nom, prenom, email, adresse, code_postal, ville) values ('poulain', 'amelie', 'poulainamelie@gmail.com', '41 place vandome', 44000, 'nantes');
insert into client (nom, prenom, email, adresse, code_postal, ville) values ('hugo', 'victor', 'hugovictor@gmail.com', '3 rue de la loire', 44000, 'nantes');
insert into client (nom, prenom, email, adresse, code_postal, ville) values ('gabin', 'jean', 'gabinjean@gmail.com', '12 avenue des rois', 44000, 'nantes');

insert into reservation(code_reservation, spectacle_id, client_id, nombre_places, date_reservation) values ('45FZ53D1',1,1,1,'2017-09-21');
 --fin jeu d'essai--

update spectacle set places_disponibles = ? /* enlever une reservation -> rajouter places disponibles */


