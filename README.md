# UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS

## TÓPICOS EN CIENCIAS DE LA COMPUTACIÓN - CC58

### Trabajo Final

#### INTEGRANTES
- Diaz Huarcaya, Henry Josue - u20201c579
- Humbser Meza, Diego Fernando - u202012711
- Jurado Garay, Gonzalo Alejandro - u202010216
- Llaguento De La Cruz, Marlon Omar - u20201b055

#### SECCIÓN
CC82

#### DOCENTE
Luis Martin Canaval Sanchez

#### CICLO 2024-01

---

### Competencia específica del curso
**ABET 1**: La capacidad de analizar un problema complejo y aplicar principios de computación y otras disciplinas relevantes para identificar soluciones.

### Enunciado
El trabajo final consiste en elaborar un sistema multiagentes que permita simular la interacción de pokemones en un mundo abierto. Las características son las siguientes:

- Existen pokemones de todos los tipos, por ejemplo fuego, eléctricos, etc.
- Los pokemones existen en un mundo abierto y distribuidos según algún criterio a escoger por el grupo.
- Se enfrentan entre ellos y el ganador se define según una tabla de probabilidades definido por las características de ventaja y desventaja de cada tipo contra cada otro tipo, por ejemplo un pokemon de agua es fuerte contra pokemones tipo fuego, de modo que tiene una mayor probabilidad de ganar la batalla.
- Luego del enfrentamiento, un pokemon es elegido ganador y el otro queda fuera de combate saliendo de la simulación.
- Debe iniciar con una cantidad significativa de acuerdo al rendimiento, por ejemplo 5000 pokemones, puede ser menos si el rendimiento es un limitante.

---

## INTRODUCCIÓN
Los sistemas multiagente, compuestos por agentes autónomos que interactúan dentro de un entorno compartido, ofrecen un framework poderoso para simular interacciones complejas y dinámicas en escenarios del mundo real. Una aplicación popular de los sistemas multiagente es en el ámbito de las simulaciones de juegos, donde se pueden usar para modelar comportamientos e interacciones intrincadas entre diferentes entidades.

El trabajo final tiene como objetivo desarrollar un sistema multiagente utilizando JADE (Java Agent Development Framework) para simular las interacciones de Pokémon en un mundo abierto. Este sistema nos permitirá modelar el comportamiento dinámico de los Pokémon a medida que se encuentran y combaten entre sí, basándose en sus respectivos tipos y atributos.

## PROBLEMA
El problema principal de este trabajo es crear un sistema multiagente capaz de simular las interacciones de un gran número de Pokémon, cada uno con tipos y características únicas. La simulación debe reflejar con precisión la mecánica de las batallas vistas en el universo Pokémon, donde diferentes tipos de Pokémon tienen ventajas y desventajas distintas entre sí. Por ejemplo, un Pokémon de tipo agua tiene una mayor probabilidad de ganar una batalla contra un Pokémon de tipo fuego. El sistema debe manejar una población inicial grande de Pokémon, gestionar sus interacciones y reducir progresivamente el número de agentes activos a medida que se resuelven las batallas y los Pokémon son eliminados de la simulación. Asegurar que el sistema funcione eficientemente con un número significativo de agentes (por ejemplo, 5000 Pokémon) también es un aspecto crítico del problema.

## OBJETIVOS
- Utilizar el framework JADE para crear un sistema multiagente que modele con precisión el comportamiento y las interacciones de los Pokémon en un mundo abierto.
- Implementar la mecánica de las batallas donde los Pokémon se enfrenten entre sí, con el resultado determinado por una tabla de probabilidades predefinida basada en las ventajas y desventajas de los tipos.
- Distribuir los Pokémon en el mundo abierto según un criterio elegido y gestionar sus interacciones dinámicamente a medida que avanza la simulación.
- Asegurar que el sistema pueda comenzar con un gran número de Pokémon (por ejemplo, 5000) y funcione de manera eficiente, reduciendo el número de agentes con el tiempo a medida que ocurren las batallas y los Pokémon son eliminados.

## DISEÑO DE SOLUCIÓN
Se utiliza tres archivos tipo Java para correr el programa adecuadamente:
1. Primero se considera el código de controlador de agentes, el cual a solas contiene las funciones y razonamiento de cada pokemon, de tal manera que se encarga de la asignación de pokemon y su tipo, su movimiento y pelea contra otros pokemons.
2. Luego, se debe crear un manejador del GUI, que se apoya de lo importado del código de agentes para que defina el espacio de la simulación y cargue cada agente según su posición, estado y la acción a tomar si dos pokemons están cerca de cada uno.
3. Finalmente, se define el contenedor principal que utiliza los otros 2 archivos, el cual define una lista de pokemons para ser creados por el código de agentes al igual que inicia el GUI, en sí funcionando como el compilador de ambos códigos.

## HERRAMIENTAS
Se utilizó el IDE IntelliJ IDEA debido a su eficacia a la hora de manejar proyectos complejos y la opción de utilizar librerías externas que nos ayuden a desarrollar nuestro sistema multiagente. Además, nos proporcionó una depuración y manejo de errores para minimizar el tiempo de programación y concentrarnos en la lógica. Así mismo, usamos JADE para implementar el sistema multiagente, ya que permite la creación de agentes autónomos. También, permite definir sus comportamientos y mostrar cómo interactúan con otros agentes que se encuentren en su entorno.

## REQUISITOS
- Se debe visualizar 10 a más pokemons en la simulación.
- Cada pokémon debe tener nombre, tipo y fortaleza al igual que debilidad contra algún tipo.
- Por cada turno, los pokemons deben realizar al menos una acción, la cual involucra moverse y/o atacar a otro pokémon cercano a sí mismo.
- En el caso de batalla, debe haber un incremento o decremento al daño de cada pokémon según si el tipo de su oponente es uno que él es fuerte o débil contra.
- Cuando la vida de un pokémon llega a 0, son eliminados de la simulación.
