# clo$er services:

## REQUIREMENTS:
- java 1.8
- sbt 0.13.8
- typesafe-activator-1.3.7

## LOCAL:
### Running application:
- To run project locally:

```
sbt run
```

By default sbt scripts will use **application.conf** configuration file.
To run sbt with another configs should be used:

```
sbt -Denv=${prefix}
```

In this case SBT will use **application-${prefix}.conf** configuration.

## MONGO PLUGIN:
To use plugin firstly need to run SBT with appropriate config file.
Plugin use following properties from config:

```
mongo.collection = ...
mongo.user = ...
mongo.password = ...
mongo.host = ...
mongo.port = ...
mongo.dbname = ...
```

Name of collection should be the same as name of json file with test data in folder "testdata", ie:

```
mongo.collection = "regions"
...
testdata/regions.json
```

Plugin define following tasks:

```
mongoDrop // Drop ${mongo.collection}
mongoImport // Import data from testdata/${mongo.collection}.json to collection ${mongo.collection}
mongoExport // Export data from collection ${mongo.collection} to testdata/${mongo.collection}.json
```


## HEROKU:
### Deploying application
- To deploy application run:

```
sbt stage deployHeroku
```

### Heroku environment info:
- https://dry-bastion-13599.herokuapp.com

## SERVICES:
### Regions:
- `GET::/api/regions` - Get all Regions
- `GET::/api/regions/:id` - Get Region by ID
- `DELETE::/api/regions/:id` - Delete Region by ID
- `POST::/api/regions` - Save or Update Region
- `POST::/api/regions/wraps` - Return all regions that wrap sent point.

```
// Example of Location RQ object that should be sent to  /api/regions/wraps endpoint
{
    "latitude": 53.9298819,
    "longitude": 27.5772205
}
```


### Promotions:
- `GET::/api/regions/:regionId/promotions` - Get all Promotions of particular Region
- `GET::/api/regions/:regionId/promotions/:id` - Get Promotion by ID
- `DELETE::/api/regions/:regionId/promotions/:id` - Delete Promotion by ID
- `POST::/api/regions/:regionId/promotions` - Save or Update Promotion