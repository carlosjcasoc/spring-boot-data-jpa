insert into clientes(id, nombre, apellido, email, create_at, photo) values (1, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (2, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at ,photo) values (3, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (4, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (5, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (6, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (7, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (8, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (9, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (10, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (11, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (12, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (13, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (14, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (15, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (16, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (17, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (18, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (19, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (20, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (21, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (22, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (23, 'Carlos', 'Caso', 'carlos@gmail.com', '2022-12-21', '');
insert into clientes(id, nombre, apellido, email, create_at, photo) values (24, 'Giordano', 'Grillo', 'giordano@gmail.com', '2022-12-21', '');

/*Poblar tabla productos*/
insert into productos(nombre, precio, create_at) values ('Panasonic Pantalla LCD', 259990, NOW());
insert into productos(nombre, precio, create_at) values ('Sony camara digital DSC-W3220B', 123490, NOW());
insert into productos(nombre, precio, create_at) values ('Apple iPod shuffle', 1499990, NOW());
insert into productos(nombre, precio, create_at) values ('Sony Notebook Z110', 37990, NOW());
insert into productos(nombre, precio, create_at) values ('Hewlett Packard Multifunctional', 69990, NOW());
insert into productos(nombre, precio, create_at) values ('Bianchi Bicicleta Aro 26', 69990, NOW());
insert into productos(nombre, precio, create_at) values ('Mica Comoda 5 cajones', 299990, NOW());

/* creamos algunas facturas*/
insert into facturas(descripcion, observacion, cliente_id, create_at) values ('Factura equipos de oficina', null, 1, NOW());
insert into facturas_item(cantidad, factura_id, producto_id) values (1, 1, 1);
insert into facturas_item(cantidad, factura_id, producto_id) values (2, 1, 4);
insert into facturas_item(cantidad, factura_id, producto_id) values (1, 1, 5);
insert into facturas_item(cantidad, factura_id, producto_id) values (1, 1, 7);

insert into facturas(descripcion, observacion, cliente_id, create_at) values ('Factura Bicicleta', 'Alguna nota Importante!', 1, NOW());
insert into facturas_item(cantidad, factura_id, producto_id) values (3, 2, 6);
