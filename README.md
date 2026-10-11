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
SistemaBibliotecaEFT/
├── src/main/java/
│ ├── Main.java
│ ├── controlador/
│ │ ├── LoginController.java
│ │ ├── MenuBibliotecarioController.java
│ │ ├── GestionLibrosController.java
│ │ ├── GestionEstudiantesController.java
│ │ ├── GestionPrestamosController.java
│ │ └── ReportesController.java
│ ├── dao/
│ │ ├── UsuarioDAO.java
│ │ ├── LibroDAO.java
│ │ ├── EstudianteDAO.java
│ │ ├── PrestamoDAO.java
│ │ └── ReporteDAO.java
│ ├── hilos/
│ │ └── TareaPrestamo.java
│ ├── modelo/
│ │ ├── Usuario.java
│ │ ├── Estudiante.java
│ │ ├── Categoria.java
│ │ ├── Libro.java
│ │ └── Prestamo.java
│ ├── util/
│ │ └── DatabaseConnection.java
│ └── vista/
│ ├── LoginView.java
│ ├── MenuBibliotecarioView.java
│ ├── GestionLibrosView.java
│ ├── GestionEstudiantesView.java
│ ├── GestionPrestamosView.java
│ └── ReportesView.java
└── pom.xml

text

---

## 🚀 Cómo ejecutar el proyecto

### 1. Requisitos previos
- JDK 17 o superior.
- MySQL 8.0 instalado y en ejecución.
- IntelliJ IDEA (recomendado).

### 2. Configurar la base de datos
Ejecutar en MySQL Workbench los siguientes scripts (incluidos en el proyecto):

1. `Script_crea_tablas_biblioteca.sql` → crea la base de datos y las 5 tablas.
2. `Script_poblado_tablas_biblioteca.sql` → inserta los datos de prueba.

### 3. Configurar la contraseña de MySQL
Abrir el archivo `src/main/java/util/DatabaseConnection.java` y reemplazar:

```java
private static final String PASSWORD = "TU_CONTRASEÑA_AQUI";
por tu contraseña real de MySQL.

4. Ejecutar
Ejecutar el archivo Main.java desde IntelliJ. Se abrirá la ventana de Login.

👤 Usuarios de prueba
RUT	Contraseña	Rol
12345678-9	clave123	Bibliotecario
98765432-1	clave123	Estudiante
11222333-4	clave123	Estudiante
22334455-6	clave123	Estudiante
🧪 Funcionalidades implementadas
✅ Autenticación de usuarios con roles (bibliotecario / estudiante).

✅ CRUD completo de Libros.

✅ CRUD completo de Estudiantes.

✅ Registro de préstamos con hilos y sincronización.

✅ Registro de devoluciones.

✅ Reportes: libros más prestados, historial por estudiante, libros en préstamo.

✅ Conexión a MySQL con JDBC y patrón Singleton.

✅ Arquitectura MVC + DAO.

👨‍💻 Autor
Alejandro Serrano
Estudiante de Duoc UC - Modalidad Online
Asignatura: Desarrollo Orientado a Objetos II
Año: 2026
