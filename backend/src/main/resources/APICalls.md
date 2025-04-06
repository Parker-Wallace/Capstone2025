# 📟 Capstone Insurance API Guide

---

## 👤 Customer Endpoints

### ➕ Create Customer
**POST** `http://localhost:8080/api/customers`

```json
{
  "username": "bilbo_baggins",
  "age": 22
}
```

---

### 📄 Get All Customers
**GET** `http://localhost:8080/api/customers`

---

### 🔍 Get Customer By ID
**GET** `http://localhost:8080/api/customers/1`

---

## 🧑‍💼 Representative Endpoints

### ➕ Create Representative
**POST** `http://localhost:8080/api/representatives`

```json
{
  "username": "rep003"
}
```

---

### 📄 Get All Representatives
**GET** `http://localhost:8080/api/representatives`

---

### 🔍 Get Representative By ID
**GET** `http://localhost:8080/api/representatives/1`

---

## 🏠 Home Quote

### ➕ Create Home Quote
**POST** `http://localhost:8080/api/homequotes`

```json
{
  "quoteId": "HOME-TEST-003",
  "startDate": "2025-04-06",
  "hasAutoPolicyDiscount": false,
  "home": {
    "yearBuilt": 2000,
    "homeValue": 300000,
    "liabilityLimit": 100000,
    "dwellingType": "standalone",
    "heatingType": "gas",
    "locationType": "urban"
  },
  "customerId": 1
}
```

---

## 🚗 Auto Quote

### ➕ Create Auto Quote
**POST** `http://localhost:8080/api/autoquotes`

```json
{
  "quoteId": "TEST-AUTO-001",
  "startDate": "2025-04-06",
  "hasHomePolicyDiscount": true,
  "automobile": {
    "vehicleMake": "Hyundai",
    "vehicleModel": "Elantra",
    "vehicleYear": 2010,
    "numberofAccidents": 1
  },
  "customerId": 3
}
```

---

## 🔁 Convert Quote to Policy

### ➕ Convert (by Representative)
**POST** `http://localhost:8080/api/representatives/1/convert-quote`

```json
{
  "customerId": 1,
  "quoteId": "HOME-TEST-003"
}
```

---
