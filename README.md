# CricRadio - Live Cricket Score App

## 📌 Project Overview
CricRadio is an Android application that provides real-time cricket match updates. It utilizes **Ktor** for networking, **Dagger Hilt** for dependency injection, and **MVVM** for clean architecture. This project demonstrates API integration, and modular design.

---

## 🚀 Tech Stack
- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Ktor Client
- **Dependency Injection:** Dagger Hilt
- **Build Tool:** Gradle (KTS)
- **UI Toolkit:** Jetpack Compose

---

## 📌 Android Version & Tools Used
- **Minimum SDK:** 24 (Android 7.0, Nougat)
- **Target SDK:** 34 (Android 14)
- **Android Studio:** Ladybug
- **Gradle Version:** 8.x (Latest)

---

## 📡 API Integration Details
### **Base URL:**
```
http://3.6.243.12:5001
```

### **Authentication Headers:**
```
Authorization: Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM=
```

### **Endpoints Used:**
#### 1️⃣ Mini Scorecard API
- **Endpoint:** `/api/v2/match/mini-match-card`
- **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`

#### 2️⃣ Venue Info API
- **Endpoint:** `/api/v2/match/venue-info`
- **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`

---

## 🔧 Setup & Installation
### **1️⃣ Clone the Repository**
```
git clone https://github.com/rahul00112233/RahulMaurya_CRICRADIO.git
cd cricradio2
```

### **2️⃣ Open in Android Studio**
- Open the project in **Android Studio**
- Sync Gradle and Build the project

### **3️⃣ Run the App**
- Connect a device or start an emulator
- Click **Run** ▶️ in Android Studio

---

## 🎯 Best Practices Followed
✅ **Dependency Injection** with Dagger Hilt for managing API clients and ViewModels.  
✅ **Clean Architecture** using MVVM for maintainability.  
✅ **Ktor Client** for efficient API handling.  
✅ **Secure API Handling** with Authorization headers.  

