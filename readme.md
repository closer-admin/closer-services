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
mongo.user = ...
mongo.password = ...
mongo.host = ...
mongo.port = ...
mongo.dbname = ...
```

Plugin define following tasks:

```
mongoDrop ${collection} // Drop ${collection}
mongoImport ${collection} // Import data from testdata/${collection}.json to collection ${collection}
mongoExport ${collection} // Export data from collection ${collection} to testdata/${collection}.json
```

Name of collection should be the same as name of json file with test data in folder "testdata", ie:

```
collection = "regions"
...
testdata/regions.json
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

- `GET      /api/regions` - Get all Regions
- `GET      /api/regions/:id` - Get Region by ID
- `DELETE   /api/regions/:id` - Delete Region by ID
- `PUT      /api/regions/:id` - Update Region
- `POST     /api/regions` - Save Region
- `POST     /api/regions/cover` - Return all regions that cover sent point.

```
// Example of Location RQ object that should be sent to  /api/regions/cover endpoint
{
    "latitude": 53.9298819,
    "longitude": 27.5772205
}
```

### ServiceProviders:
- `GET      /api/providers` - Get all Providers
- `GET      /api/providers/q?profileId=:profileId` - Get Provider by his profile ID (user_id)
- `GET      /api/providers/:id` - Get Provider by ID
- `DELETE   /api/providers/:id` - Delete Provider by ID
- `PUT      /api/providers/:id` - Update Provider
- `POST     /api/providers` - Save Provider
- `POST     /api/providers/nearest` - Return nearest providers.

```
// Example of RQ object that should be sent to /api/providers/nearest endpoint.
// center - center point of region for search
// radius - radius of region for search (DEFAULT 500m)
// num - num records to return (DEFAULT 10 records)
{
    center: {
        "latitude": 53.9298819,
        "longitude": 27.5772205
    },
    radius: 500,
    num: 10
}
```

### Promotions:
- `GET      /api/promotions` - Get all Promotions
- `GET      /api/promotions/q?serviceId=:serviceId` - Get all Promotions by ServiceId
- `GET      /api/promotions/:id` - Get Promotion by ID
- `DELETE   /api/promotions/:id` - Delete Promotion by ID
- `PUT      /api/promotions/:id` - Update Promotion
- `POST     /api/promotions` - Save Promotion
- `POST     /api/promotions/nearest` - Return nearest promotions.
- `GET      /api/regions/:regionId/promotions` - Get all Promotions of particular Region

