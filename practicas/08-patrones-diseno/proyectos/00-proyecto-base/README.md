# Proyecto base: Gestor de tareas

Aplicación Java 17 de consola, construida con Maven. No requiere bibliotecas externas.

## Ejecutar
    mvn clean compile
    mvn exec:java -Dexec.mainClass="es.umu.gestor.Main"

Si no se dispone del plugin exec configurado, puede ejecutarse `Main` desde el IDE.

## Estructura
- `model`: clases del dominio.
- `service`: lógica de gestión.
- `Main`: demostración por consola.

El proyecto mantiene los datos en memoria: al terminar la ejecución se pierden.
