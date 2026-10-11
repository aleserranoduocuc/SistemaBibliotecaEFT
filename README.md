# 📚 Sistema de Gestión de Biblioteca Escolar

Proyecto de **Evaluación Final Transversal (EFT)**  
Asignatura: **Desarrollo Orientado a Objetos II**  
Institución: **Duoc UC - Modalidad Online**

---

## 📖 Descripción del proyecto

Sistema de escritorio desarrollado en **Java** con interfaz gráfica **JFrame (Swing)** para la gestión integral de una biblioteca escolar. Permite administrar libros, estudiantes, préstamos, devoluciones y generar reportes, aplicando patrones de diseño, concurrencia y conexión a base de datos MySQL mediante JDBC.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión |
| :--- | :--- |
| Java | 17 |
| Maven | 3.x |
| MySQL | 8.0 |
| MySQL Connector/J | 8.3.0 |
| Swing (JFrame) | JDK 17 |

---

## 🧩 Patrones de diseño aplicados

- **MVC (Modelo - Vista - Controlador):** separación de la lógica de negocio, interfaz gráfica y control de eventos.
- **DAO (Data Access Object):** clases dedicadas exclusivamente a la comunicación con la base de datos.
- **Singleton:** clase `DatabaseConnection` que garantiza una única instancia de conexión a MySQL.

---

## ⚙️ Concurrencia y sincronización

- **Hilos (`Thread`):** el registro de préstamos se ejecuta en un hilo independiente (`TareaPrestamo implements Runnable`) para evitar el bloqueo de la interfaz gráfica.
- **`synchronized`:** se aplica un bloqueo estático (`LOCK_STOCK`) en el proceso de préstamo para evitar condiciones de carrera cuando varios usuarios solicitan el mismo libro al mismo tiempo.
- **Consistencia:** antes de registrar un préstamo, se verifica el stock real; luego se descuenta de forma atómica.

---

## 📦 Estructura del proyecto
