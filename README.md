# KD-Electronics CRUD - Sistema de Gestión de Inventario

## Descripción del proyecto

Este proyecto consiste en el desarrollo de un sistema de gestión de inventario para la empresa KD-Electronics, implementado en Java bajo el paradigma de Programación Orientada a Objetos.

El objetivo principal es permitir la administración de productos electrónicos mediante operaciones CRUD (Create, Read, Update, Delete), cumpliendo con los requerimientos planteados en el taller académico.

A lo largo del desarrollo, se buscó estructurar el código de manera clara y organizada, separando responsabilidades en diferentes capas para facilitar su comprensión, mantenimiento y escalabilidad.

---

## Objetivo

Diseñar e implementar un módulo de gestión de productos que permita:

* Registrar nuevos productos en el inventario
* Consultar productos a partir de su código
* Actualizar la información de los productos (sin modificar el código)
* Realizar eliminación lógica de productos, conservando su historial en el sistema

---

## Estructura del proyecto

El proyecto se encuentra organizado en paquetes, siguiendo una separación por capas que facilita la lectura y mantenimiento del código:

* `kdelectronics.model`: Contiene las clases del dominio, como Product y Category
* `kdelectronics.repository`: Define la capa de acceso a datos, incluyendo la interfaz y su implementación en memoria
* `kdelectronics.service`: Implementa la lógica de negocio y validaciones
* `kdelectronics.ui`: Gestiona la interacción con el usuario a través de consola
* `kdelectronicscrud`: Contiene la clase principal desde donde se ejecuta la aplicación

---

## Funcionalidades implementadas

### Registro de productos (Create)

Permite ingresar nuevos productos al sistema, solicitando información como código, nombre, descripción, precios, categoría y cantidad disponible. El sistema valida que no existan códigos duplicados.

### Consulta de productos (Read)

Permite buscar productos por su código y visualizar toda su información, incluyendo su estado actual (activo o eliminado).

### Actualización de productos (Update)

Permite modificar los atributos de un producto existente, exceptuando el código, el cual se mantiene como identificador único.

### Eliminación lógica (Delete)

Los productos no se eliminan físicamente del sistema. En su lugar, se marcan como eliminados, lo que permite conservar la información y evitar inconsistencias en el inventario.

---

## Ejecución del proyecto

### Requisitos

* Java JDK 8 o superior
* Entorno de desarrollo como NetBeans o Eclipse

### Instrucciones

1. Abrir el proyecto en el entorno de desarrollo
2. Ejecutar la clase principal:

   ```
   KDElectronicsCRUD.java
   ```
3. Interactuar con el menú en consola para probar las funcionalidades del sistema

---

## Pruebas realizadas

Durante la ejecución del sistema se verificaron las siguientes operaciones:

* Registro de múltiples productos
* Consulta individual por código
* Actualización de datos de productos existentes
* Eliminación lógica de productos
* Validación del estado de los productos (activo o eliminado)
* Listado general de productos registrados

---

## Consideraciones técnicas

El sistema utiliza una estructura en memoria para el almacenamiento de los datos, mediante listas, lo que permite enfocarse en la lógica del CRUD sin depender de una base de datos externa.

Se implementaron validaciones para garantizar la integridad de la información, tales como:

* Campos obligatorios
* Valores numéricos positivos
* Coherencia entre precio base y precio de venta

Además, se aplicaron principios básicos de la programación orientada a objetos, como encapsulamiento y separación de responsabilidades.

---

## Repositorio

(Agregar aquí el enlace del repositorio en GitHub)

---

## Autor

John Edinson Rojas Ordoñez

---

## Fecha

2/03/2026
