create database kalum2020;
use kalum2020;
create table detalle_nota(
detalle_nota_id varchar(128) not null,
detalle_actividad_id varchar(128) not null,
carne varchar(16),
valor_nota int(11),

constraint FK_actividad foreign key(detalle_actividad_id) references detalle_actividad(detalle_actividad_id),
constraint FK_Alumno foreign key(carne) references alumno(id)
)engine=innodb;