BedWarsPlugin – Plugin BedWars Multiarena

BedWarsPlugin es un plugin totalmente personalizado, con soporte multiarena, compatible con PlaceholderAPI y diseñado para servidores 1.8.x a 1.16.x.

Incluye sistema de arenas, shop, scoreboard dinámico, listeners optimizados, comandos personalizables y soporte completo para PlaceholderAPI.

⭐ Características principales

✔ Soporte Multiarena

✔ Compatible con PlaceholderAPI

✔ Sistema de Shop (GUI)

✔ Scoreboard dinámico

✔ Equipos y manejo de jugadores

✔ Spawners configurables

✔ Listeners optimizados

✔ Comandos claros y configurables

✔ Integración completa con config.yml, plugin.yml y shop.yml

🛠️ Requisitos

Java 8 o superior

Spigot / Paper 1.8.8 – 1.16.x

PlaceholderAPI (obligatorio)

📥 Instalación

Descarga el archivo .jar desde la pestaña Releases.

Colócalo en la carpeta /plugins/ de tu servidor.

Reinicia el servidor.

Configura tus arenas y ajustes como prefieras.

📦 Comandos
/bw

Comando principal del plugin.

Subcomando	Descripción
/bw	Muestra información de ayuda
/bw join <arena>	Unirse a una arena
/bw leave	Salir de la arena
/bw setarena <nombre>	Crear/editar arenas (admin)
/bw reload	Recargar configuraciones

(Agrega más si tu plugin los maneja.)

🔑 Permisos

Los permisos definidos en plugin.yml:

Permiso	Descripción	Default
bedwars.use	Permite usar el comando /bw	✔ Sí
bedwars.player	Permite jugar BedWars	✔ Sí
bedwars.admin	Permite administrar BedWars	🔒 Solo OP
🧩 Placeholders disponibles (PlaceholderAPI)

Ejemplos:

Placeholder	Devuelve
%bedwars_player_kills%	Kills del jugador
%bedwars_player_deaths%	Muertes del jugador
%bedwars_arena%	Arena actual

(Puedo añadir el resto si me envías tu clase de placeholders.)

🏗️ Estructura del proyecto
src/
 └── main/java/tu/paquete/...
 └── main/resources/config.yml
 └── main/resources/plugin.yml
 └── main/resources/shop.yml

📜 Changelog
v4.0

Soporte completo para multiarena

Mejoras en el sistema de scoreboard

Shop actualizada y más estable

Optimización general del plugin

v3.0

Añadido soporte para PlaceholderAPI

Nuevos listeners añadidos

Corregido el error ArrayIndexOutOfBoundsException en stringToLoc() que impedía iniciar el plugin en v2.0.
Este error ocurría porque algunas locaciones estaban mal formateadas o incompletas en la configuración.
Se implementó validación de coordenadas y manejo seguro del método de conversión.

v2.0

Corregido el error donde plugin.yml estaba mal colocado y el servidor no lo detectaba.

Mejoras internas de rendimiento

Nuevos comandos agregados

v1.0

Primera versión pública del plugin

🤝 Contribuciones

Si deseas aportar mejoras:

Haz un Fork del repositorio

Crea una rama nueva

Envía un Pull Request

📧 Contacto

Autor: rodashuamaniarian
GitHub: https://github.com/rodashuamaniarian-droid

🚀 ¡Gracias por usar BedWarsPlugin!
