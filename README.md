# online-test
Online Test


# Instrucciones

## Clonar el proyecto (desde el terminal)

Debido a la extensión del proyecto se pueden presentar errores debido al largo nombre de los ficheros. Es recomendable, **abrir el terminar con permisos de Administrador** y ejecutar el comando:\
```
git config --system core.longpaths true 
```
Hay que recordar que el proyecto es uno compuesto por módulos. Por ese motivo, se clona con el siguiente comando:\
```
git clone --recursive https://github.com/EspeSoft/salud_espe.git
```
Para actualizar el branch al que apuntamos en los repositorios, es necesario entrar a la carpeta salud_espe/:
```
cd salud_espe/
```
Después, apuntar a la rama develop es todos los submódulos con:
```
git submodule foreach --recursive "git checkout develop || true"
```
## Ejecutar el Backend
[Instrucciones](https://github.com/EspeSoft/salud_espe/tree/develop/backend#ejecutar-el-backend)


## Actualizar salud-espe y todos los submódulos
Para esto es necesario correr el siguiente comando desde el directorio padre(salud-espe):
```
git pull --recurse-submodules
```
Al correr este comando es necesario cambiarse a develop nuevamente.

## Actualizar un submodulo (Carpeta vacia)
Para hacer esto es necesario correr el siguiente comando dentro del subrepositorio.
```
git submodule init
git submodule update
```
## Trabajar en el proyecto
Revisar las instrucciones que se encuentran en cada repositorio.
