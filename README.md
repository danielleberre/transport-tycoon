# Java solution to transport tycoon exercise

Here is a Java solution for the [exercises on transport-tycoon](https://github.com/Softwarepark/exercises).

AFAIK it is correct (see [tests cases](tst/tycoon/FactoryTest.java)). Let me know if it is not the case.

The code can be built using gradle:

```
$ ./gradlew jar
```

To run the program, use

```
java -jar build/libs/tycoon.jar <pattern>
```

- [First exercise solution](https://github.com/danielleberre/transport-tycoon/tree/exercice1)
- [Second exercise solution](https://github.com/danielleberre/transport-tycoon/tree/exercice2)

Note that for the second exercise, the time slots are ok, but the events do not display properly the cargos shipping between the port and the warehouse A (only the first one is shown).
