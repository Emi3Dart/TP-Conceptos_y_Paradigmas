# Intérprete con ANTLR4 — TP Conceptos y Paradigmas de Lenguajes de Programación

**Grupo T** — Variante 1: `while`

**Integrantes:**
- Ulises Justo Saucedo
- Emiliano Ginarte Delgado

---

## Descripción del lenguaje

Lenguaje imperativo simple con tipado estático. Soporta los tipos `int`, `float`, `string` y `bool`, con declaración y asignación de variables, expresiones aritméticas y lógicas, estructuras de control de flujo (`if-else`, `while`) e impresión por consola.

### Estructura de un programa

```
int x = 10;
float y = 3.14;
string mensaje = "hola";
bool flag = true;

print mensaje;

if (x > 5) {
    print "x es mayor a 5";
} else {
    print "x es menor o igual a 5";
}

while (x > 0) {
    print x;
    x = x - 1;
}
```

### Tipos de datos

| Tipo    | Literal ej. | Valor default |
|---------|-------------|---------------|
| `int`   | `42`        | `0`           |
| `float` | `3.14`      | `0.0`         |
| `string` | `"texto"`  | `""`          |
| `bool`  | `true` / `false` | `false` |

### Variables

- Declaración: `int x;` (inicializa con valor default)
- Declaración con asignación: `int x = 10;`
- Asignación: `x = 10;`
- No se puede redeclarar una variable
- No se puede usar una variable no declarada
- El tipo debe coincidir en asignaciones

### Expresiones

**Aritméticas:** `+`, `-`, `*`, `/` (soporta coerción `int` ↔ `float` y concatenación con `string`)

**Relacionales:** `>`, `<`, `>=`, `<=`, `==`, `!=` (devuelven `bool`)

**Lógicas:** `&&`, `||` (sobre `bool`)

### Comentarios

```
// comentario de línea
/* comentario
   multilínea */
```

### Estructuras de control

**if-else:**
```
if (condición) {
    ...
} else {
    ...
}
```

**while** (Variante 1):
```
while (condición) {
    ...
}
```

### Salida

```
print expresión;
```

---

## Decisiones de diseño

### Patrón Interpreter con AST

Se optó por el patrón Interpreter en lugar de ejecutar acciones embebidas durante el parseo. El parser construye un AST (Árbol Sintáctico Abstracto) compuesto por nodos que implementan la interfaz `ASTNode` con un método `execute(Map<String, Object> symbolTable)`. La ejecución se realiza recorriendo el árbol una vez que el parseo finalizó, lo que permite implementar correctamente estructuras de control de flujo como `if-else` y `while`.

### Acciones embebidas en la gramática

La construcción del AST se realiza mediante acciones Java embebidas en las reglas del `.g4`. Esto mantiene la lógica de construcción del árbol dentro de la gramática, simplificando el desarrollo.

### Manejo de tipos

Cada nodo del AST (`Addition`, `Gt`, `And`, etc.) verifica dinámicamente los tipos de sus operandos en tiempo de ejecución mediante `instanceof`, lanzando excepciones descriptivas si hay incompatibilidades. La coerción entre `int` y `float` se maneja mediante `((Number) v).floatValue()`.

### Tabla de símbolos

Se utiliza un `Map<String, Object>` que viaja como parámetro a través del árbol. Las validaciones semánticas (variable no declarada, redeclaración, type mismatch, división por cero) se realizan en los métodos `execute()` de los nodos correspondientes.

---

## Estructura del proyecto

```
Interprete/
├── pom.xml
└── src/
    ├── main/
    │   ├── antlr4/com/unla/grupo/t/Interprete/
    │   │   └── Language.g4          # Gramática ANTLR4
    │   └── java/com/unla/grupo/t/Interprete/
    │       ├── Main.java            # Punto de entrada
    │       ├── LanguageCustomVisitor.java
    │       ├── SymbolTable.java
    │       ├── ast/                 # Nodos del AST (patrón Interpreter)
    │       │   ├── ASTNode.java
    │       │   ├── Addition.java
    │       │   ├── And.java
    │       │   ├── Constant.java
    │       │   ├── Division.java
    │       │   ├── Eq.java
    │       │   ├── Geq.java
    │       │   ├── Gt.java
    │       │   ├── If.java
    │       │   ├── Leq.java
    │       │   ├── Lt.java
    │       │   ├── Multiplication.java
    │       │   ├── Neq.java
    │       │   ├── Or.java
    │       │   ├── Print.java
    │       │   ├── Subtraction.java
    │       │   ├── VariableAssignation.java
    │       │   ├── VariableDA.java
    │       │   ├── VariableDeclaration.java
    │       │   ├── VariableReference.java
    │       │   └── While.java
    │       └── types/               # Jerarquía Value (preservada)
    │           ├── BoolValue.java
    │           ├── FloatValue.java
    │           ├── IntValue.java
    │           ├── StringValue.java
    │           └── Value.java
    └── test/resources/
        ├── example.lang             # Programa de ejemplo
        └── test.lang                # Especificación de requerimientos
```

---

## Compilación y ejecución

### Requisitos

- Java 17+
- Apache Maven 3+

### Compilar

```bash
mvn clean compile
```

### Ejecutar

Por defecto corre `example.lang`:

```bash
mvn exec:java -Dexec.mainClass="com.unla.grupo.t.Interprete.Main"
```

O especificando un archivo:

```bash
mvn exec:java -Dexec.mainClass="com.unla.grupo.t.Interprete.Main" -Dexec.args="test.lang"
```

### Desde Eclipse / IntelliJ

Ejecutar `Main.java` como Java Application. Pasar el nombre del archivo como argumento de programa (ej: `test.lang`) o dejar vacío para usar `example.lang` por defecto.

---

## Ejemplo completo

Ver `src/test/resources/example.lang` para un programa de ejemplo que demuestra todas las funcionalidades del lenguaje.

---

## Validaciones semánticas implementadas

- **Variable no declarada** — error al usar o asignar una variable que no fue declarada
- **Redeclaración** — error al declarar una variable ya existente
- **Type mismatch** — error al asignar un valor de tipo distinto al declarado
- **Operaciones inválidas** — error al aplicar operaciones sobre tipos incompatibles (ej: `bool + int`, `string - string`)
- **División por cero** — error al dividir un entero por cero
