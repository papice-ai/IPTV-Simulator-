# IPTV Simulator

A feature-rich IPTV video player, playlist manager, and streaming client for Android devices.

## Features

🎬 **Video Player**
- Built on Media3 (ExoPlayer)
- Support for HLS, DASH, and HTTP streams
- Hardware acceleration
- Fullscreen mode
- Gesture controls

📺 **Playlist Management**
- Import M3U playlists
- Create custom playlists
- Channel organization
- Favorite channels
- Export/Import functionality

🌐 **Streaming Support**
- IPTV streaming
- VOD (Video on Demand)
- Live TV channels
- Multiple stream formats
- Adaptive bitrate streaming

## System Requirements

- **Android Version**: API 21 (Android 5.0) and above
- **RAM**: Minimum 2GB
- **Storage**: 50MB for app
- **Network**: Stable internet connection

## Installation

### From GitHub Releases
1. Download the latest APK from [Releases](https://github.com/papice-ai/IPTV-Simulator-/releases)
2. Enable "Unknown Sources" in Android settings
3. Install the APK

### Build from Source

#### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Android SDK
- Gradle 8.1+

#### Building on Linux/Mac
```bash
# Clone the repository
git clone https://github.com/papice-ai/IPTV-Simulator-.git
cd IPTV-Simulator-

# Build the APK
./gradlew assembleRelease

# The APK will be at: app/build/outputs/apk/release/app-release.apk
```

#### Building on Termux (Android Terminal)
```bash
# Install dependencies
pkg update
pkg install openjdk-17 android-sdk gradle

# Clone and build
git clone https://github.com/papice-ai/IPTV-Simulator-.git
cd IPTV-Simulator-

# Run Termux build script
chmod +x build_termux.sh
./build_termux.sh

# Or manually
./gradlew assembleRelease -Dorg.gradle.jvmargs="-Xmx512m"
```

## Usage

### Adding a Playlist
1. Open the app
2. Go to **Playlist** section
3. Tap **Add Playlist**
4. Enter playlist URL (M3U format)
5. Load and enjoy

### Playing Channels
1. Select a playlist
2. Choose a channel from the list
3. Video will start playing
4. Use gesture controls or on-screen buttons

### Keyboard Shortcuts (if connected)
- `Space` - Play/Pause
- `<` / `>` - Seek backward/forward
- `+` / `-` - Volume control
- `F` - Fullscreen
- `Q` - Exit fullscreen

## Project Structure

```
IPTV-Simulator-/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── kotlin/
│   │       │   └── com/papice/iptvsimulator/
│   │       │       ├── MainActivity.kt
│   │       │       ├── PlaylistActivity.kt
│   │       │       ├── ChannelDetailActivity.kt
│   │       │       └── ui/
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   ├── values/
│   │       │   └── drawable/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── .github/
│   └── workflows/
│       └── build-apk.yml
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Dependencies

### Core
- `androidx.appcompat:appcompat` - Android compatibility
- `androidx.media3:media3-exoplayer` - Video player
- `androidx.lifecycle:lifecycle-runtime` - Lifecycle management

### Networking
- `com.squareup.okhttp3:okhttp` - HTTP client
- `com.squareup.retrofit2:retrofit` - REST client
- `com.google.code.gson:gson` - JSON parsing

### UI
- `com.google.android.material:material` - Material Design
- `androidx.constraintlayout:constraintlayout` - Layout engine

## Building & CI/CD

### GitHub Actions
Automatic APK building on push/PR:
```yaml
.github/workflows/build-apk.yml
```

### Manual Build
```bash
./gradlew assembleRelease   # Release build
./gradlew assembleDebug     # Debug build
```

### Gradle Properties (Termux Optimized)
```properties
org.gradle.jvmargs=-Xmx512m    # Memory limit for Termux
org.gradle.daemon=true          # Use daemon (faster builds)
org.gradle.parallel=true        # Parallel compilation
```

## Configuration

### Permissions Required
```xml
android.permission.INTERNET              - For streaming
android.permission.ACCESS_NETWORK_STATE  - Network detection
android.permission.READ_EXTERNAL_STORAGE - File access
android.permission.WRITE_EXTERNAL_STORAGE- Playlist export
```

## Troubleshooting

### Termux Build Issues

**Issue**: "Java not found"
```bash
# Solution:
pkg install openjdk-17
export JAVA_HOME=$PREFIX/opt/openjdk
```

**Issue**: "Android SDK not found"
```bash
# Solution:
pkg install android-sdk
# Setup SDK tools in Termux:
sdkmanager --sdk_root=$PREFIX/opt/android-sdk "platforms;android-34"
sdkmanager --sdk_root=$PREFIX/opt/android-sdk "build-tools;34.0.0"
```

**Issue**: "Gradle daemon error"
```bash
# Solution:
./gradlew --stop
./gradlew assembleRelease
```

**Issue**: Build runs out of memory
```bash
# Solution: Increase heap size or use lower-end device settings
export GRADLE_OPTS="-Xmx256m"
./gradlew assembleRelease
```

### Runtime Issues

**Streams not playing**
- Check internet connection
- Verify playlist URL is valid
- Try a different stream
- Check if stream requires authentication

**App crashes on startup**
- Clear app cache: Settings > Apps > IPTV Simulator > Clear Cache
- Reinstall the app
- Check Android version compatibility (API 21+)

## Development

### Adding Features
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Kotlin conventions
- Material Design principles
- MVVM architecture (recommended for future)

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions:
- 🐛 [Report a Bug](https://github.com/papice-ai/IPTV-Simulator-/issues/new?template=bug_report.md)
- ✨ [Request Feature](https://github.com/papice-ai/IPTV-Simulator-/issues/new?template=feature_request.md)
- 💬 [Discussions](https://github.com/papice-ai/IPTV-Simulator-/discussions)

## Changelog

### v1.0.0 (Initial Release)
- ✅ Basic video player with ExoPlayer
- ✅ Playlist management
- ✅ Channel list display
- ✅ M3U playlist support
- ✅ Termux build support

## Roadmap

- [ ] Local M3U file support
- [ ] EPG (Electronic Program Guide)
- [ ] Favorites/Bookmarks
- [ ] Recently watched
- [ ] Search functionality
- [ ] Dark/Light theme toggle
- [ ] Subtitle support
- [ ] Picture-in-Picture mode
- [ ] Chromecast support
- [ ] Database persistence (Room)

## Disclaimer

This app is for educational and legitimate streaming purposes only. Users are responsible for ensuring they have proper licenses and permissions to stream content. The developer is not responsible for any misuse.

---

Made with ❤️ by [papice-ai](https://github.com/papice-ai)
