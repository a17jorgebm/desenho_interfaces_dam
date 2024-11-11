# Grid layout
Organiza o contenido en filas e columnas definidas previamente

## Conceptos
* Grid **Container**: elemento que conten os items do grid
* Grid **Items**: elementos que son hijos directos del grid container
* Grid **Lines**: as lineas que dividen o grid en filas e columnas. Numeranse `a partir de 1`
* Grid **Areas**: areas dentro do grid que poden abarcar varias celdas.

## Grid Explicito🛠️  vs Implicito 🤖
O grid `explicito` son as filas e columnas que nos definimos previamente. (template)

No caso de que os elementos non quepan dentro das filas e columnas definidas, grid creará outras automaticamente para metelos. Esto é o grid `implicito` (auto)

## Atributos
Ten atributos para o contedor pai e os contedores fillos.

### Atributos contedor pai 🟦
* `display: grid` -> pon o display grid
* `gap` -> espacio entre filas e columnas
#### Explicito
* `grid-template-columns` -> asigna a cantidade e tamaño das columnas
* `grid-template-rows` -> asigna a cantidade e tamaño das filas
#### Implicito
* `grid-auto-rows` -> indica o height das filas adicionales

### Atributos contedor fillo
* `grid-column` -> define en que columna empeza e acaba o item
* `grid-row` -> define en que fila empeza e acaba o item


##### CODIGO QUE TOU USANDO PA ESTUDIAR
````html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejemplo de CSS Grid</title>
    <style>
        /* Estilos del contenedor grid */
        .grid-container {
            display: grid; /* Activamos el grid layout */
            grid-template-columns: repeat(3, 1fr); /* Tres columnas iguales */
            grid-template-rows: 100px 200px; /* Dos filas con diferentes alturas */
            gap: 10px; /* Espacio entre filas y columnas */
            padding: 10px; /* Espacio interno del contenedor */
            background-color: #f4f4f4; /* Color de fondo del grid */
        }

        /* Estilos para los ítems del grid */
        .grid-item {
            background-color: #8ebf42; /* Color de fondo de los ítems */
            border: 2px solid #333; /* Borde de los ítems */
            display: flex; /* Centramos el texto con flexbox */
            justify-content: center; /* Centrado horizontal */
            align-items: center; /* Centrado vertical */
            font-size: 20px; /* Tamaño del texto */
            color: white; /* Color del texto */
        }

        /* Ítem específico que ocupa varias filas y columnas */
        .item2 {
            grid-column: 2 / 4; /* Ocupa de la columna 2 a la 4 */
            grid-row: 1 / 3; /* Ocupa de la fila 1 a la 3 */
        }
    </style>
</head>
<body>

    <div class="grid-container">
        <div class="grid-item">1</div>
        <div class="grid-item item2">2</div> <!-- Este ítem ocupa más espacio -->
        <div class="grid-item">3</div>
        <div class="grid-item">4</div>
        <div class="grid-item">5</div>
        <div class="grid-item">6</div>
    </div>

</body>
</html>
````