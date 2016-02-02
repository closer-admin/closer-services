# clo$er services:
 
## Environment requirements:
- java 1.8.+
- sbt 0.13.8 // optional 
- typesafe-activator-1.3.7 // optional

## Local:
### Running application:
- If full typesafe-activator installed and added to PATH, than: 
```
cd ${PROJECT_HOME}
activator run
```
- Else activator-launcher-*.jar can be used. In this case launcher will download all bunch of required dependencies 
(I would say that it is approximately 500-600 mb, so it can take long time).
```
cd ${PROJECT_HOME}
./activator run
```

## Heroku:
### Deploying application
- To deploy application run:
```
activator stage deployHeroku
```
### Heroku environment info:
- **UDL:** https://dry-bastion-13599.herokuapp.com
- **GIT:** https://git.heroku.com/dry-bastion-13599.git

## Services:

### GET ALL REGIONS:
- GET /findRegions
