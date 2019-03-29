# Vodafone Malta

Endpoints for Vodafone Malta Code Challange

# Endpoints

### Get list of mobile numbers
```sh
GET /api/mobiles
```
***Response***
```json
{
    "error": null,
    "data": [
        {
            "id": 1,
            "mobileNumber": "+380991234567",
            "ownerId": 1,
            "userId": 2,
            "serviceType": "MOBILE_PREPAID"
        },
        {
            "id": 2,
            "mobileNumber": "+380990000000",
            "ownerId": 3,
            "userId": 4,
            "serviceType": "MOBILE_POSTPAID"
        }
    ]
}
```

### Search
```sh
GET /api/mobiles/search
```
***Request param***
```json
number: string
```
***Response***
```json
{
    "error": null,
    "data": [
        {
            "id": 1,
            "mobileNumber": "+380991234567",
            "ownerId": 1,
            "userId": 2,
            "serviceType": "MOBILE_PREPAID"
        }
    ]
}
```

### Add mobile number
```sh
POST /api/mobiles
```
***Request object***
```json
{
    "mobileNumber": string,
    "ownerId": integer,
    "userId": intgeter,
    "serviceType": string['MOBILE_PREPAID', 'MOBILE_POSTPAID']
}
```
***Response***
```json
{
    "error": null,
    "data": {
            "id": 1,
            "mobileNumber": "+380991234567",
            "ownerId": 1,
            "userId": 2,
            "serviceType": "MOBILE_PREPAID"
        }

}
```

### Change mobile number values
```sh
PUT /api/mobiles/{id}
```
***Request param***
```json
id: integer
```
***Request object***
```json
{
    "ownerId": integer,
    "userId": intgeter,
    "serviceType": string['MOBILE_PREPAID', 'MOBILE_POSTPAID']
}
```
***Response***
```json
{
    "error": null,
    "data": {
            "id": 1,
            "mobileNumber": "+380991234567",
            "ownerId": 1,
            "userId": 2,
            "serviceType": "MOBILE_PREPAID"
        }

}
```

### Delete mobile number
```sh
DELETE /api/mobiles/{id}
```
***Request param***
```json
id: integer
```

***Response***
```json
{
    "error": null,
    "data": null
}
```
### Error response
```json
{
    "error": {
        "code": 400,
        "message": "Error message"
    },
    "data": null
}
```

License
----

MIT
