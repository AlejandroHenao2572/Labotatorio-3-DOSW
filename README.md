# Labotatorio-3-DOSW

## Integrantes

- David Alejandro Patacon Henao
- Laura Alejandra Venegas Piraban

 >**Nota:** Segun lo acordado en la sesion de laborario nuestro grupo tiene plazo de entregar hasta el viernes 5 de septiembre.

## RETO #1: Identificando los Requerimientos

### Reglas de negocio
-  Las cuentas deben tener exactamente 10 dígitos. 

-  Para que una cuenta sea válida, los dos primeros dígitos corresponden a bancos registrados en el sistema. 

-  Las cuantas no permiten letras ni caracteres especiales, SOLO números. 

-  No es posible hacer depositos con valores negativos.

### Funcionalidades principales:
 
-  Crear una cuenta: validando número de cuenta(único), banco existente y formato. 

-  Validar una cuenta: Confirmar que el número de cuenta existe y cumple con las reglas. 

-  Consultar saldo: Mostrar el saldo actual de la cuenta. 

-  Depositar Dinero: Incrementar el saldo de la cuenta a la que se le hace el depósito con el monto dado.
  
-  Rechazar depósitos en cuentas no válidas. 

-  Rechazar depósitos con valores negativos y que no corresponden a cantidades numéricas. 

### Actores Principales

A continuación se describen las entidades que interactúan con el sistema:

-   **Cliente/Usuario Final:** Es la persona que posee la cuenta bancaria. Es el actor principal y el beneficiario final de los servicios del sistema.

-   **Administrador del Sistema:** Es el responsable de la gestión, la lógica de negocio y la configuración interna del sistema.

-   **Equipo Técnico:** Es el equipo encargado de la construcción, testing, despliegue y mantenimiento del software.

-   **Sistema Bancario:** Bankify, al ser un sistema financiero, debe interactuar con otros sistemas bancarios externos para procesar transacciones y validar información.

### Precondiciones del Sistema

Las siguientes condiciones deben cumplirse para que el sistema opere correctamente:

-   **Registro de Entidades Aprobadas:** El sistema debe contar con una base de datos o un registro que asocie los códigos de dos dígitos con los nombres de las entidades bancarias autorizadas.
    *   *Ejemplo:* `01` -> BANCOLOMBIA, `02` -> DAVIVIENDA.

-   **Infraestructura Operativa:** El software debe estar correctamente desplegado en un servidor, con acceso a su base de datos y con los servicios necesarios en ejecución.
    > **Nota:** Aunque es una precondición para un entorno de producción, para el caso del laboratorio no se realizará esta condición.

-   **Correcto Funcionamiento del Software:** El software debe estar correctamente desarrollado, aplicando buenas prácticas de programación, patrones de diseño, documentación, código de calidad y pruebas en todas sus funcionalidades.

-   **Validación de Cuenta del Cliente:** Para que un cliente pueda consultar su saldo o realizar un depósito, primero debe existir una cuenta válida y activa a su nombre en el sistema.

## RETO #2

### Diagrama de Contexto:  
En el centro del diagrama de contexto se puede observar el sistema Bankify, el cual representa el software que estamos desarrollando, recibe órdenes y decide que hacer según las reglas de negocio.  

- **Actores**
**cliente/Usuario Final** : Este es al actor principal del sistema pues es la persona que usa el banco.
  
    -> Le pide a Bankify Crear una cuenta, ver su saldo o hacer un depósito.  
    -> Bankify le responde con confirmaciones, mostrándole su saldo o avisándole, por ejemplo, si se quedó sin dinero.
  
- **Administrador**: Es el jefe que supervisa todo.  

    -> Le dice a Bankify qué bancos existen (ej: "el código 01 es para BANCOLOMBIA") y cambia las reglas del juego.  
    -> Bankify le manda reportes y alertas para que sepa cómo está funcionando el sistema.  
    
- **Equipo Técnico**: Son los programadores que crean y arreglan el software.  

    -> Le instalan cosas a Bankify como nuevas versiones del programa y hacen pruebas para asegurarse de que todo funcione bien.  
    -> Bankify les devuelve reportes técnicos que les dicen si el código es de buena calidad y si las pruebas son completas, para que ellos puedan mejorarlo.  

### Historias de Usuario
Teniendo en cuenta los diagramas de casos de uso del punto anterior, se relizaron las siguientes historias de usuario.

- **Cliente**:  
1) COMO cliente QUIERO crear una cuenta bancaria proporcionando un número de 10 dígitos PARA PODER empezar a usar los servicios financieros de Bankify.  
2) COMO cliente QUIERO consultar el saldo actual de mi cuenta en cualquier momento PARA PODER conocer mi disponibilidad de fondos.  
3)  COMO cliente QUIERO depositar dinero en mi cuenta PARA PODER incrementar mi saldo y poder realizar futuras transacciones.      

- **Administrador**:  
1) COMO administrador QUIERO registrar nuevos bancos en la lista de bancos autorizados y sus códigos PARA PODER ampliar nuestra oferta.  
2) COMO administrador QUIERO consultar la lista de bancos autorizados y sus códigos PARA PODER asegurar que la validación de nuevas cuentas sea precisa y esté al día.
3) COMO administrador QUIERO que el sistema rechace automáticamente cualquier intento de depósito a una cuenta que no exista o no sea válida PARA PODER mantener la integridad de los datos y prevenir fraudes.  

- **Sistema**:  
1) COMO sistema QUIERO validar que cada nuevo número de cuenta tenga exactamente 10 dígitos y solo contenga números PARA PODER cumplir con las reglas de negocio y mantener la consistencia de los datos.  
2) COMO sistema QUIERO rechazar cualquier depósito que tenga un monto negativo PARA PODER garantizar la lógica y la validez de las transacciones financieras.  
  
- **Equipo Técnico**:  
1) COMO desarrollador QUIERO que el código sea analizado automáticamente por SonarQube tras cada cambio PARA PODER identificar y corregir vulnerabilidades y problemas de calidad de manera proactiva.  
2) COMO desarrollador QUIERO generar un reporte de cobertura de código con JaCoCo después de ejecutar las pruebas PARA PODER asegurar que las funcionalidades críticas del sistema están adecuadamente cubiertas por pruebas automatizadas. 

## Reto #3

- **patron de diseño y Principios utilizados**:  
    El patrón de diseño que se usó fue Strategy, donde se aplica en la interfaz VoteStrategy y sus implementaciones en FibonacciVoteStrategy. Se usa por la lógica del votación, secuencia de fibonacci en este caso. 
     
    En este diseño se aplican algunos de los principios SOLID: cada clse tiene una única responsabilidad, como Player para los jugadore, Story para las historias y PlanninPokerGame para la lógica central; el sistema está abiero para extensión pero cerrado para su modificación, ya que se pueden agregar nuevas formas de votación sin dañar el resto y se cumple con la sustitución de Liskov, pues cualquier estrategia que implemente VoteStrategy puede reemplazar a otra sin romper el programa.

## Reto #4

Principios SOLID aplicados:    

Principio de Responsabilidad Única (S):  

- **CuentaValidator:** Responsable solo de validar cuentas
- **CuentaGestor:** Responsable de gestionar operaciones de cuentas
- **BancoService:** Responsable de gestionar operaciones de bancos

Principio Abierto/Cerrado (O):  

La validación de cuentas está diseñada para ser extensible sin modificar el código existente


Patrones de Diseño Utilizados:  

- **Patrón Fachada:** Bankify actúa como una fachada que proporciona una interfaz simplificada para los clientes.
- **Patrón Servicio:** Se utilizan clases de servicio especializadas (BancoService, CuentaGestor) para encapsular la lógica de negocio.
- **Patrón Validator:** CuentaValidator encapsula y centraliza la lógica de validación.

## Reto #5  

### Cobertura inicial:  
<img width="1101" height="187" alt="image" src="https://github.com/user-attachments/assets/33d443ba-3ecd-4305-b7c0-a00965240f88" />  
<img width="413" height="552" alt="image" src="https://github.com/user-attachments/assets/2889555e-c589-4105-9eb9-560bba09f116" />  

**¿Cuál es el porcentaje de cobertura de instrucciones y de ramas?**  
El porcentaje de cobertura inicial fue del 56% y el de ramas 72%.  

**¿Hay alguna línea o condición que las pruebas no cubrieron?**    
En la funcionalidad del planing poker no cubrimos las clases Main y PlanningPokerGame, ya que estas clases requieren la entrada de datos de los usuarios para hacer la actividad y para esto no se pueden realizar pruebas unitarias.  

### Cobertura final:
<img width="1087" height="183" alt="image" src="https://github.com/user-attachments/assets/c53b0797-238e-4be8-9c4a-ba743ff7455b" />
Tuvimos que agregar mas casos de prueba, para las clases Banco, Deposito y Usuario, principalmente para los metodos set y get de estas clases.  
Esta metrica es importante para probar que todo el codigo funcione correctamente. 

## Reto #6

### Reporte de Analsis estatico con SonarQube:
<img width="1863" height="851" alt="image" src="https://github.com/user-attachments/assets/f33c377c-6172-4b0e-b777-db2f3647c151" />

## Reflexion: David Patacon

¿Por qué considera que es importante realizar pruebas a un software diseñado?   
Considero que las pruebas son una herramienta que permite evitar problemas costosos, garantiza calidad y permite evolucionar el software.  





