# Enunciado Primer Reto Equipo Rosado
___
## **Integrantes**
* Tomas Felipe Ramirez Alvares
* Manuel Alejandro Guarnizo
* Julian Eduardo Arenas Alfonso
* Daniel Alejandro Rodriguez
* David Eduardo Salamanca Aguilar 

## Descripción del enunciado

* el proyecto FurniStore es una tienda de muebles reconocida que busca digitalizar toda su operacion para mejorar la 
experiencia de sus clientes y tambien optimizar sus procesos internos.Actualmente, la gestion se realiza mediante hojas de
calculos y facturas manuales provocando que hallan errores en los inventarios y la facturacion, demorando entregas y teniendo
faltas de trasabilidad en los pedidos y clientes.
Nuestra solucion tecnologica busca ofreser una api rest estable para la interracion con las facturas, centralizando la 
informacion de los clientes, productos,pedidos y facturacion, asegurando la calidad de software y su robustez aplicando
practicas pertinentes para lograrlo.

## Tecnologia utilizadas:
- java 17
- spring boot
- maven
- mongodb
- swagger
- lombok
- junit5
- jacoco
- mockito
- sonarqube

## Instrucciones de uso:

- clonar el repositorio comando:
  - "git clone url del repositorio"
  - "Entrar y usar maven mvn install"
  - "Ejecuntar la aplicacion con mvn spring-boot:run"

## Flujo de Ramas
___
Se utilizará una estrategia de ramas con la siguiente estructura:

- main: rama principal del proyecto.
- develop: rama de desarrollo.
- feature/: ramas destinadas a nuevas funcionalidades específicas. Por ejemplo, feature/doc se empleará para cambios en la documentación y el archivo README.


El formato de los commits será:
tipoCommit - descripción del cambio

- Los tipos de commit definidos son:
- fix: para la corrección de errores en el código.
- feat: para la incorporación de nuevas funcionalidades.
- doc: para modificaciones relacionadas con la documentación.

## Dependencias utilizadas para nuestro proyecto

![lombok.png](image/lombok.png)

Implementación de lombok

![swagger.png](image/swagger.png)

Implementación de swagger

![SpringBoot.png](image/SpringBoot.png)

Implementación Spring Boot

![Junit5.png](image/Junit5.png)

Implementación pruebas Junit5 

## Diagrama de contexto

El diagrama de contexto, representa la relación que hay entre los usuarios (proveedor y cliente), y el sistema para la 
organización de las ordenes y las facturas. Este es un diagrama simplificado, el cual muestra la interacción principal 
que tiene el sistema.

- **Elementos usados:**
  - Cliente
  - Proveedor
  - FurniStore
  - Sistema

![DiagramaContexto.png](image/DiagramaContexto.png)

## Diagrama de Casos de Uso y funcionalidades listadas

![img.png](image/diagramaCasosDeUso.png)

- Diagrama de Casos de Uso y funcionalidades listadas:

- Funcionalidades:

- CLIENTE
    - Registrar cuenta

Buscar productos y realizar compras

- VENDEDOR
    - Gestionar inventario
    - Procesar ventas
    - Generar facturas

- ADMINISTRADOR
    - Gestionar inventario
    - Ver reportes y estadísticas

- FUNCIONALIDADES
    - Búsqueda avanzada: Filtrar por categoría (sofás, camas, etc.) y estilo (clásico, moderno, etc.)
    - Gestión completa de inventario
    - Proceso de venta integrado con facturación
    - Sistema de reportes para administración

## Diagrama de Clases Preliminar

![img.png](image/diagrama_clases.png)


#### **1. Clase principal: FurniStore**

* Representa a la tienda.
* Contiene un **Inventory** y una lista de **Bills**.

---

#### **2. Inventory**

* Administra los muebles disponibles en la tienda junto con sus cantidades.
* Usa un `Map<Furniture, int>` para asociar cada mueble con su stock disponible.

---

#### **3. Bill y BillItem**

* Bill es un registro generado en cada venta.
* Contiene un identificador, fecha, cliente y una colección de **BillItems**.
* **BillItem**: representa cada línea de la factura.

---

#### **4. Client**

* Representa al comprador.
* Tiene atributos básicos: `idClient`, `name`, `address`.
* Está asociado directamente a la clase **Factura**, porque toda venta debe estar ligada a un cliente.

---

#### **5. Furniture**

* Clase abstracta **Furniture**:

  * Define los atributos comunes: `id`, `name`, `price` y el `style`.
* Subclases concretas:

  * **Couch, Bed, Chair, SofaBed**.
---

#### **6. Style**

* Implementado como un **enum**, donde cada mueble tiene exactamente un estilo asociado.
* Define categorías visuales de los muebles: `CLASSIC`, `MODERN`, `RUSTIC`.
 ---
## Patrones de Diseño
1. **Strategy Pattern**
    - Justificación: El diagrama de clases muestra que los muebles poseen diferentes estilos (CLASSIC, MODERN, RUSTIC) mediante el enum Style. Cada estilo podría requerir diferentes estrategias de cálculo de precio, políticas de descuento o métodos de visualización. El patrón Strategy permite encapsular estos comportamientos variables en clases separadas, facilitando la adición de nuevos estilos sin modificar las clases de muebles existentes.
    - Contexto en diagramas: La relación entre Furniture y Style en el diagrama de clases sugiere la necesidad de comportamientos diferenciados por estilo, ideal para Strategy.
2. **Factory Method Pattern**
    - Justificación: La estructura jerárquica con clase abstracta Furniture y subclases concretas (Couch, Bed, Chair, SofaBed) evidenciada en el diagrama de clases requiere un mecanismo centralizado de creación. Factory Method abstrae el proceso de instanciación, permitiendo que el sistema crece con nuevos tipos de muebles manteniendo el principio de open/closed.
    - Contexto en diagramas: La especialización de muebles en el diagrama de clases demanda un patrón de creación que maneje esta complejidad.
3. **Repository Pattern**
    - Justificación: El uso de MongoDB como base de datos y la necesidad de operaciones CRUD sobre entidades como Client, Furniture y Bills requiere una abstracción del acceso a datos. Repository Pattern separa la lógica de negocio de la persistencia, facilitando testing y mantenimiento.
    - Contexto en diagramas: Las entidades identificadas en el diagrama de clases (Client, Furniture, Bills) necesitan operaciones de persistencia consistentes.
4. **Observer Pattern**
    - Justificación: El diagrama de contexto muestra flujos entre Cliente, Proveedor y Sistema donde cambios en el inventario deben notificarse a múltiples interesados. Observer permite que objetos se suscriban a eventos específicos, como actualizaciones de stock, manteniendo bajo acoplamiento.
    - Contexto en diagramas: Las interacciones en el diagrama de contexto sugieren notificaciones automáticas entre componentes.
## Principios SOLID
1. **S - Single Responsibility Principle**
    - Cada clase en el diagrama tiene una responsabilidad bien definida:
    - Inventory: Gestión exclusiva del inventario
    - Bills: Manejo de facturación
    - Client: Administración de datos del cliente
    - Furniture: Representación de muebles sin lógica de negocio externa
2. **O - Open/Closed Principle**
    - La jerarquía de Furniture está cerrada para modificación pero abierta para extensión. Nuevos tipos de muebles se integran mediante herencia sin alterar el código base. El enum Style permite agregar estilos sin afectar clases existentes.
3. **L - Liskov Substitution Principle**
    - Todas las subclases de Furniture (Couch, Bed, Chair, SofaBed) son sustituibles por su clase base. Cada subclase mantiene el contrato behavioral definido por Furniture, garantizando consistencia en las operaciones del sistema.
4. **I - Interface Segregation Principle**
    - Las responsabilidades están segregadas en interfaces específicas rather than una interfaz general. Operaciones de inventario, facturación y gestión de clientes mantienen interfaces enfocadas, evitando que las clases implementen métodos irrelevantes.
5. **D - Dependency Inversion Principle**
    - Los módulos de alto nivel como FurniStore dependen de abstracciones (Furniture, Inventory) no de implementaciones concretas. La inyección de dependencias con Spring Boot facilita este principio, permitiendo cambiar implementaciones sin afectar consumidores.
## Beneficios de aplicar SOLID y Patrones de Diseño
1. **Mantenibilidad**: La aplicación de SOLID y patrones de diseño facilita la comprensión y modificación del código. Cada clase tiene una responsabilidad clara, lo que reduce la complejidad al realizar cambios.
2. **Escalabilidad**: La estructura modular permite agregar nuevas funcionalidades (nuevos tipos de muebles, estilos, métodos de facturación) sin afectar el sistema existente, cumpliendo con el principio Open/Closed.
3. **Reusabilidad**: Las clases y patrones implementados pueden reutilizarse en otros proyectos o módulos, reduciendo la duplicación de código y mejorando la eficiencia del desarrollo.
4. **Testabilidad**: La separación de responsabilidades y la inversión de dependencias facilitan la creación de pruebas unitarias, mejorando la calidad del software y reduciendo errores.
5. **Flexibilidad**: Los patrones como Strategy y Factory Method permiten cambiar comportamientos y crear objetos de manera dinámica, adaptándose a nuevos requisitos sin grandes refactorizaciones.
6. **Colaboración en equipo**: La claridad en la estructura del código y la definición de responsabilidades facilita el trabajo en equipo, permitiendo que varios desarrolladores trabajen en diferentes partes del sistema sin conflictos.
7. **Reducción de errores**: La implementación de patrones y principios sólidos reduce la probabilidad de introducir errores al modificar el código, ya que cada cambio está confinado a áreas específicas del sistema.