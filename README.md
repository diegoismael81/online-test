# online-test
Online Test


# Instrucciones

## Clonar el proyecto

```
git clone https://github.com/diegoismael81/online-test.git
```
Para actualizar el branch al que apuntamos en los repositorios, es necesario entrar a la carpeta salud_espe/:
```
cd salud_espe/
```
Después, apuntar a la rama develop es todos los submódulos con:
```
git submodule foreach --recursive "git checkout develop || true"
```

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
