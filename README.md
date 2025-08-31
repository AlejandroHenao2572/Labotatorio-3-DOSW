# Labotatorio-3-DOSW

## Integrantes

- David Alejandro Patacon Henao
- Laura Alejandra Venegas Piraban

## RETO #1: Identificando los Requerimientos

###Reglas de negocio
-  **Las cuentas deben tener exactamente 10 dígitos. 

-  **Para que una cuenta sea válida, los dos primeros dígitos 	corresponden a bancos registrados en el sistema. 

-  **Las cuantas no permiten letras ni caracteres especiales, SOLO números. 

-  **si el saldo es negativo notificar al cliente.

###Funcionalidades principales:
 
-  **Crear una cuenta: validando número de cuenta(único), banco existente y formato. 

-  **Validar una cuenta: Confirmar que el número de cuenta existe y cumple con las reglas. 

-  **Consultar saldo: Mostrar el saldo actual de la cuenta. 

-  **Depositar Dinero: Incrementar el saldo de la cuenta a la que se le hace el depósito con el monto dado. 
-  **Rechazar depósitos en cuentas no válidas. 

-  **Rechazar depósitos con valores negativos y que no corresponden a cantidades numéricas. 

-  **Rechazar depósitos de una cuenta a otra si la cuenta que lo envía no tiene el saldo suficiente. 



###Reglas de negocio
-  **Las cuentas deben tener exactamente 10 dígitos. 

-  **Para que una cuenta sea válida, los dos primeros dígitos 	corresponden a bancos registrados en el sistema. 

-  **Las cuantas no permiten letras ni caracteres especiales, SOLO números. 

-  **si el saldo es negativo notificar al cliente.

###Funcionalidades principales:
 
-  **Crear una cuenta: validando número de cuenta(único), banco existente y formato. 

-  **Validar una cuenta: Confirmar que el número de cuenta existe y cumple con las reglas. 

-  **Consultar saldo: Mostrar el saldo actual de la cuenta. 

-  **Depositar Dinero: Incrementar el saldo de la cuenta a la que se le hace el depósito con el monto dado. 
-  **Rechazar depósitos en cuentas no válidas. 

-  **Rechazar depósitos con valores negativos y que no corresponden a cantidades numéricas. 

-  **Rechazar depósitos de una cuenta a otra si la cuenta que lo envía no tiene el saldo suficiente. 

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

