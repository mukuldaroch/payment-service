```bash
pgcli -h localhost -p 5436 -U postgres -d paymentdb
```

```java
curl -X POST http://localhost:8080/bookings \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <jwt-token>" \
  -d '{
    "eventId": "e076518b-76fb-4e92-934f-1616b3e73afa",
    "items": [
      {
        "ticketTypeId": "76f87518-5b3d-457a-97f2-3403dbee1860",
        "quantity": 2
      },
      {
        "ticketTypeId": "494846a2-72ae-41ed-acf0-a1537fad7f6f",
        "quantity": 3
      }
    ]
  }'
```

```bash
docker run -d \
  --name keycloak \
  --network event-mesh \
  --add-host auth.local:host-gateway \
  -p 8080:8080 \
  -e KC_HOSTNAME=auth.local \
  -e KC_HOSTNAME_STRICT=false \
  -e KC_HTTP_ENABLED=true \
  -e KC_BOOTSTRAP_ADMIN_USERNAME=admin \
  -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
  -v keycloak-data:/opt/keycloak/data \
  quay.io/keycloak/keycloak:latest \
  start-dev
```
