# Radio Garden - Android App

A modern Android application that allows users to listen to radio stations from around the world, inspired by [Radio Garden](https://radio.garden/). The app provides both mobile and Android Auto support for a seamless listening experience.

## Features

### 🌍 Global Radio Streaming
- Listen to radio stations from countries worldwide
- High-quality audio streaming with ExoPlayer
- Support for multiple audio formats (MP3, AAC, etc.)

### 📱 Mobile Experience
- Modern Material Design 3 UI with Jetpack Compose
- Search functionality to find stations by name, city, or country
- Favorites system to save preferred stations
- Recent stations history
- Tabbed interface for easy navigation

### 🚗 Android Auto Integration
- Full Android Auto support for safe driving
- Voice-controlled playback
- Car-optimized UI with large touch targets
- Background audio playback

### 🎵 Audio Features
- Play/pause/stop controls
- Volume control
- Buffering status indicators
- Error handling and retry mechanisms

## Architecture

The app follows Clean Architecture principles with the following modules:

- **app**: Main application module with UI and navigation
- **core**: Shared business logic, data models, and repositories
- **car**: Android Auto specific implementation

### Tech Stack

- **UI**: Jetpack Compose with Material Design 3
- **Architecture**: MVVM with ViewModels and StateFlow
- **Dependency Injection**: Hilt
- **Audio Streaming**: ExoPlayer (Media3)
- **Database**: Room for local storage
- **Networking**: Retrofit for API calls
- **Navigation**: Navigation Compose
- **Android Auto**: Car App Library

## Project Structure

```
app/
├── src/main/java/com/radiogarden/
│   ├── MainActivity.kt
│   ├── RadioGardenApplication.kt
│   └── ui/
│       ├── screens/
│       ├── components/
│       ├── viewmodel/
│       └── theme/

core/
├── src/main/java/com/radiogarden/core/
│   ├── domain/
│   │   ├── model/
│   │   └── usecase/
│   ├── data/
│   │   ├── database/
│   │   ├── repository/
│   │   └── sample/
│   ├── audio/
│   └── di/

car/
├── src/main/java/com/radiogarden/car/
│   ├── CarService.kt
│   ├── presentation/
│   └── session/
```

## Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 24+ (Android 7.0)
- Kotlin 1.9.20+

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd radio-garden
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or device

### Building for Android Auto

To test Android Auto functionality:

1. Enable Developer Options on your Android device
2. Enable "Unknown Sources" for Android Auto
3. Install the app on your device
4. Connect to a car or use the Android Auto desktop head unit

## Usage

### Mobile App

1. **Browse Stations**: Use the "All Stations" tab to see available radio stations
2. **Search**: Tap the search icon to find specific stations
3. **Play Music**: Tap the play button on any station card
4. **Favorites**: Tap the heart icon to add stations to favorites
5. **Recent**: View recently played stations in the "Recent" tab

### Android Auto

1. Connect your phone to your car's Android Auto system
2. Launch Radio Garden from the Android Auto interface
3. Use voice commands or touch controls to navigate
4. Enjoy hands-free radio listening while driving

## Sample Data

The app includes sample radio stations from various countries:

- **BBC Radio 1** (London, UK) - Pop music
- **France Inter** (Paris, France) - Talk radio
- **Deutschlandfunk** (Cologne, Germany) - News
- **NHK World Radio Japan** (Tokyo, Japan) - International news
- **ABC Classic** (Sydney, Australia) - Classical music
- **RTP Antena 1** (Lisbon, Portugal) - Pop music

## Permissions

The app requires the following permissions:

- `INTERNET` - For streaming radio content
- `ACCESS_NETWORK_STATE` - To check network connectivity
- `ACCESS_FINE_LOCATION` - For location-based station discovery
- `ACCESS_COARSE_LOCATION` - For approximate location services
- `CAR_CONNECTIVITY` - For Android Auto functionality
- `CAR_ACCESSORY` - For car integration features

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by [Radio Garden](https://radio.garden/) - a web-based radio streaming platform
- Built with modern Android development practices
- Uses open-source libraries and frameworks

## Roadmap

- [ ] Add map view for visual station discovery
- [ ] Implement location-based station recommendations
- [ ] Add station categories and genres
- [ ] Support for custom station URLs
- [ ] Offline mode for cached stations
- [ ] Social features (sharing, ratings)
- [ ] Chromecast support
- [ ] Widget for quick access