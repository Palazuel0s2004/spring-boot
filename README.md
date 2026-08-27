# spring-boot

Repositorio centralizado con los proyectos de desarrollo en Spring Boot (Sistema POS, Agenda Pediátrica y Oiko-CRM).

## 🛠️ Tecnologías y Configuración

* **URL base del servidor (Desarrollo):** `http://localhost:8080/snpp`
* **Documentación OpenAPI / Swagger Docs:** `/snpp/v3/api-docs`
* **Interfaz Swagger UI:** `/snpp/swagger-ui/index.html`

---

## 🚀 Módulos del Repositorio

### 👥 Clientes (`/api/clientes`)

Gestión de clientes del sistema POS.

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/clientes` | Listar todos los clientes |
| `POST` | `/api/clientes` | Registrar un nuevo cliente |
| `GET` | `/api/clientes/{id}` | Buscar un cliente por ID |
| `PUT` | `/api/clientes/{id}` | Actualizar datos de un cliente |
| `DELETE` | `/api/clientes/{id}` | Eliminar un cliente |

---

### 📦 Productos (`/api/productos`)

Gestión de inventario y productos.

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/productos` | Listar todos los productos |
| `POST` | `/api/productos` | Registrar un nuevo producto |
| `GET` | `/api/productos/{id}` | Buscar un producto por ID |
| `PUT` | `/api/productos/{id}` | Actualizar un producto |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto |

---

### 👨‍💼 Empleados (`/api/empleados`)

Gestión del personal y roles del sistema.

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/empleados` | Listar todos los empleados |
| `POST` | `/api/empleados` | Registrar un nuevo empleado |
| `GET` | `/api/empleados/{id}` | Buscar un empleado por ID |
| `PUT` | `/api/empleados/{id}` | Actualizar un empleado |
| `DELETE` | `/api/empleados/{id}` | Eliminar un empleado |

---

### 🛒 Ventas y Caja (`/api/ventas`)

Registro de transacciones, control de stock y balance general.

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/ventas` | Listar todas las ventas |
| `POST` | `/api/ventas` | Registrar una nueva venta |
| `GET` | `/api/ventas/{id}` | Buscar una venta por ID |
| `DELETE` | `/api/ventas/{id}` | Anular o eliminar una venta |

---

## 📋 Esquemas de Datos

La API maneja los siguientes modelos principales para solicitudes y respuestas:

* **Cliente:** Entidad con datos personales y de contacto.
* **Producto:** Control de stock, precios y descripción.
* **Empleado:** Datos de contratación, cargo y salario.
* **Venta:** Detalle de transacciones, métodos de pago y relación con empleados.

---

## 🧪 Pruebas e Interacción

Puedes probar directamente cada uno de los endpoints ejecutando la aplicación localmente mediante Maven y accediendo a la consola interactiva de Swagger UI en: `http://localhost:8080/snpp/swagger-ui/index.html`
