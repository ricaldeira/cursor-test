# Car Mobile App

A comprehensive Android application that provides both mobile and car interfaces for vehicle management and navigation.

## Project Structure

This project follows a modular architecture with the following modules:

### Core Module (`:core`)
Contains shared business logic, domain models, and repository interfaces:
- **Domain Models**: User, Vehicle, Location, Route, NavigationSession
- **Repository Interfaces**: UserRepository, VehicleRepository, NavigationRepository
- **Repository Implementations**: Mock implementations for development
- **Dependency Injection**: Hilt modules for repository bindings

### Mobile Module (`:mobile`)
Standard Android mobile interface using Jetpack Compose:
- **Navigation**: Mobile-specific navigation with bottom navigation
- **Screens**: Home, Navigation, Vehicles, Profile
- **ViewModels**: MVVM architecture with Hilt dependency injection
- **UI Components**: Material 3 design system

### Car Module (`:car`)
Android Auto integration for in-car experience:
- **Car Service**: Android Auto service implementation
- **Car Screens**: Grid-based interface optimized for car displays
- **Navigation**: Car-specific navigation templates
- **Session Management**: Car session handling

### App Module (`:app`)
Main application module that ties everything together:
- **Application Class**: Hilt-enabled application
- **Main Activity**: Entry point for mobile interface
- **Manifest**: Permissions and service declarations
- **Theme**: Material 3 theming

## Features

### Mobile Interface
- **Home Screen**: Quick access to main features
- **Navigation**: Search destinations and start navigation
- **Vehicle Management**: Add, edit, and manage vehicles
- **Profile**: User settings and preferences

### Car Interface (Android Auto)
- **Grid Layout**: Touch-friendly interface for car displays
- **Navigation**: Car-optimized navigation experience
- **Quick Actions**: Voice commands and shortcuts
- **Safety First**: Minimal distractions while driving

## Technology Stack

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Android Auto**: Car integration framework
- **Hilt**: Dependency injection
- **MVVM**: Architecture pattern
- **Material 3**: Design system
- **Coroutines**: Asynchronous programming

## Getting Started

1. **Prerequisites**:
   - Android Studio Hedgehog or later
   - Android SDK 24+ (Android 7.0)
   - Kotlin 1.9.20+

2. **Setup**:
   ```bash
   git clone <repository-url>
   cd CarMobileApp
   ```

3. **Build**:
   ```bash
   ./gradlew build
   ```

4. **Run**:
   - For mobile: Run the app on an Android device or emulator
   - For car: Connect to Android Auto compatible head unit

## Development

### Adding New Features

1. **Domain Layer**: Add models and repository interfaces in `:core`
2. **Data Layer**: Implement repositories in `:core`
3. **Presentation Layer**: Add screens and ViewModels in respective modules
4. **Navigation**: Update navigation graphs
5. **Testing**: Add unit and UI tests

### Architecture Guidelines

- **Single Responsibility**: Each module has a clear purpose
- **Dependency Inversion**: Depend on abstractions, not implementations
- **Separation of Concerns**: UI, business logic, and data are separated
- **Testability**: All components are easily testable

## Permissions

The app requires the following permissions:
- `INTERNET`: For API calls and location services
- `ACCESS_FINE_LOCATION`: For precise location data
- `ACCESS_COARSE_LOCATION`: For approximate location data
- `CAR_CONNECTIVITY`: For Android Auto integration
- `CAR_ACCESSORY`: For vehicle accessory access

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.