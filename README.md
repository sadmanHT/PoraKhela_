# Porakhela - English Learning App for Kids

## 🎮 Project Overview
Porakhela is a gamified English-language learning app designed for children aged 6-11 years old in Bangladesh. The app addresses education and parental trust crises through engaging interactive learning experiences with a Porapoints reward system.

## 🏗️ Architecture
Built using **Clean Architecture** principles with:
- **Presentation Layer**: UI components, ViewModels
- **Domain Layer**: Business logic, Use cases  
- **Data Layer**: Repositories, Local/Remote data sources

## 📱 Tech Stack
- **Language**: Kotlin (100%)
- **UI**: XML (ViewBinding enabled) - Material Design 3
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Component  
- **Async**: Coroutines + Flow
- **Database**: Room (for offline-first)
- **Network**: Retrofit + OkHttp
- **Logging**: Timber

## 🎨 Design System
- **Primary Color**: Blue #3A7DFF
- **Accent Color**: Yellow #FFCA28  
- **Background**: White #FFFFFF
- **Text**: Black #000000
- **Font**: Poppins (child-friendly)
- **Haptic Feedback**: Enabled throughout

## 📁 Project Structure
```
app/src/main/java/com/porakhela/
├── core/
│   ├── components/     # Base classes, custom views
│   ├── theme/          # Theme and styling
│   └── utils/          # Utility classes, constants
├── data/
│   ├── local/          # Room database, SharedPrefs
│   ├── remote/         # API services, DTOs  
│   └── repository/     # Repository implementations
├── domain/
│   ├── models/         # Domain models
│   └── usecases/       # Business logic
├── ui/
│   ├── onboarding/     # First-time user experience
│   ├── home/           # Main dashboard
│   ├── lessons/        # Learning modules
│   ├── rewards/        # Porapoints & achievements
│   ├── profile/        # User settings
│   └── parent/         # Parental controls
├── network/            # API configuration
└── di/                 # Dependency injection modules
```

## 🚀 Features Implemented
✅ **Foundation Layer**
- Complete project structure with Clean Architecture
- Hilt dependency injection setup
- Navigation Component with bottom navigation
- Material3 theming with Porakhela brand colors
- Base classes for Activities/Fragments/ViewModels
- Sealed UI state management
- Custom haptic feedback button
- Timber logging with release/debug configurations
- StrictMode for performance monitoring
- ProGuard rules for release optimization

✅ **Core Screens**
- Splash screen with app initialization
- Placeholder onboarding flow
- Main activity with bottom navigation
- Home dashboard with Porapoints display
- Lessons, Rewards, Profile, Parent Zone placeholders

## 🛠️ Build Configuration
- **Minimum SDK**: 21 (Android 5.0+)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34
- **Build Tools**: Android Gradle Plugin 8.2.0
- **Kotlin**: 1.9.20
- **Gradle**: 8.4

## 🔧 Next Steps for Development
1. **Lesson System**: Implement interactive English lessons
2. **Porapoints Engine**: Create reward calculation and storage
3. **User Management**: Add profile creation and progress tracking
4. **Content Management**: Build lesson content delivery system
5. **Parent Zone**: Implement parental controls and progress reports
6. **Applink API Integration**: Connect with subscription and SMS services
7. **Offline Content**: Implement content caching for offline learning
8. **Audio System**: Add pronunciation and speech recognition
9. **Analytics**: Implement learning progress tracking
10. **Testing**: Add comprehensive unit and UI tests

## 🏃‍♂️ Quick Start
1. Open project in Android Studio
2. Sync Gradle files
3. Run on device/emulator (API 21+)
4. Navigate through splash → main → bottom navigation tabs

## 📋 Performance & Safety Features
- Global exception handling with Timber logging
- StrictMode enabled in debug builds for leak detection
- ProGuard optimization for release builds
- Haptic feedback for enhanced user experience
- Offline-first architecture for reliability
- Clean Architecture for maintainability and testability

---
**Status**: Foundation complete ✅ Ready for feature development 🚀