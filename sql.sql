Create Database bibliotecamuscial;
use bibliotecamusical;

create table musicas(
	Id_Musica int Primary Key auto_increment not null,
    Nome_Musica varchar(60) not null,
    Lancamento date not null,
    Album varchar(60) not null,
    Ouvintes int(11) not null
);

create table artistas(
	Id_Artista int(11) auto_increment Primary Key, 
	Nome_Artista varchar(60) not null,
	Alter_Ego varchar(60) not null,
	Data_Nasc date not null,
	Ouvintes int(11) not null
);