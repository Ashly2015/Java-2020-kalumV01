use kalum2020;
select * from alumno;
select * from carrera_tecnica;
insert into modulo(modulo_id,carrera_id,nombre_modulo,numero_seminarios)
values(UUID(),"9cb4c965-713e-11ea-84be-107d1a251b20",'NOMBRE 1',5);
select * from modulo;
insert into seminario(seminario_id,modulo_id,nombre_seminario,fecha_inicio,fecha_fin)
values(UUID(),"c1462166-a844-11ea-96e3-107d1a251b20",'seminario 1','2020-01-06 ','2020-06-06');
insert into seminario(seminario_id,modulo_id,nombre_seminario,fecha_inicio,fecha_fin)
values(UUID(),"d2a86999-a844-11ea-96e3-107d1a251b20",'seminario 1','2020-06-06 ','2020-11-06');
select * from seminario;
insert into detalle_actividad(detalle_actividad_id,seminario_id,nombre_actividad,nota_actividad,fecha_creacion,fecha_entrega,fecha_postergacion,estado)
values(UUID(),"a0ee8d65-b43e-11ea-a95d-107d1a251b20",'evaluacion 1',67,'2020-03-06 ','2020-03-10','2020-03-20',"A");
insert into detalle_actividad(detalle_actividad_id,seminario_id,nombre_actividad,nota_actividad,fecha_creacion,fecha_entrega,fecha_postergacion,estado)
values(UUID(),"a0ee8d65-b43e-11ea-a95d-107d1a251b20",'tarea 1',88,'2020-03-15 ','2020-03-29','2020-04-05',"A");
select * from detalle_actividad;
insert into detalle_nota(detalle_nota_id,detalle_actividad_id,carne,valor_nota)
values(UUID(),"6dd39ba3-b43f-11ea-a95d-107d1a251b20","9116c1dd-6bbc-11ea-8f27-107d1a251b20",88);
insert into detalle_nota(detalle_nota_id,detalle_actividad_id,carne,valor_nota)
values(UUID(),"909dc32e-b43f-11ea-a95d-107d1a251b20","9116c1dd-6bbc-11ea-8f27-107d1a251b20",88);
select * from detalle_nota;
select * from asignacion_alumno;
select * from clase;