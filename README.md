# Employee Business Trip Management System (Perdin App)

Aplikasi pengelolaan perjalanan dinas (Perdin) pegawai berbasis web menggunakan Java Spring Boot.

Project ini dibuat untuk memenuhi technical test programming Akhdani.

---

## Features

### Authentication & Authorization
- Login menggunakan username dan password
- Role-based access:
    - ADMIN
    - PEGAWAI
    - DIVISI_SDM

### Admin
- CRUD Master Kota
- Mengelola data kota asal dan tujuan perjalanan dinas
- Mengelola user

### Pegawai
- Mengajukan perjalanan dinas
- Melihat histori pengajuan perjalanan dinas
- Kalkulasi uang saku otomatis
- Tracking status approval

### Divisi SDM
- Melihat seluruh pengajuan perjalanan dinas
- Approve / Reject pengajuan
- Melihat total uang saku yang harus dibayarkan

---

## Tech Stack

### Backend
- Java 8
- Spring Boot 2.7.18
- Spring Security
- Spring Data JPA (Hibernate)

### Database
- Oracle SQL

### Frontend
- Thymeleaf
- Bootstrap 5

### Build Tool
- Maven

---

## Business Rules

### Uang Saku Perjalanan Dinas

| Kondisi | Nominal |
|----------|----------|
| 0 - 60 KM | Tidak mendapat uang saku |
| > 60 KM, satu provinsi | Rp 200.000 / hari |
| > 60 KM, beda provinsi, satu pulau | Rp 250.000 / hari |
| > 60 KM, beda provinsi, beda pulau | Rp 300.000 / hari |
| Luar Negeri | USD 50 / hari |

### Perhitungan Jarak
Jarak dihitung menggunakan metode:
Haversine Formula


# Cara Menjalankan Aplikasi

## Prerequisite

Pastikan sudah terinstall:

- Java 8
- Maven
- Oracle Database
- IDE (IntelliJ IDEA / STS / VS Code)

Cek versi Java:
```bash
java -version
```

Expected:

```txt
java version "1.8.0_xxx"
```

Cek Maven:
```bash
mvn -version
```


---

## 1. Clone / Download Project

Jika menggunakan Git:

```bash
git clone <repository-url>
```

Atau download file project `.zip` lalu extract.

---

## 2. Setup Database Oracle

Pastikan Oracle database sudah running.

Contoh koneksi:
Host     : localhost
Port     : 1521
Service  : FREE
Username : your_username
Password : your_password

---

## 3. Configure Database
Buka file:
```txt
src/main/resources/application.yml
```

Sesuaikan konfigurasi database:
```yml
  datasource:
    url: jdbc:oracle:thin:@//localhost:1521/FREE
    username: your_username
    password: your_password
    hikari:
      idle-timeout: 10000
      maximum-pool-size: 5
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 4. Install Dependencies

Jalankan command:
```bash
mvn clean install
```
Tunggu hingga build success.

Expected result:

```txt
BUILD SUCCESS
```

---

## 5. Run Application

Jalankan aplikasi:

### Option 1 — Via Maven
```bash
mvn spring-boot:run
```

### Option 2 — Via IDE
Run class:

```txt
PerdinAppApplication.java
```
---

## 6. Access Application

Buka browser:

```txt
http://localhost:8080
```

## 7. Default Login User

### Admin

```txt
username : admin
password : admin123
```

### Pegawai

```txt
username : pegawai
password : pegawai123
```

### SDM

```txt
username : sdm
password : sdm123
```
