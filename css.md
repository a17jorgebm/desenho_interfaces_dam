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
