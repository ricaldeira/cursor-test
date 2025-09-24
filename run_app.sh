#!/bin/bash
# run_app.sh

cd /home/rcaldeira/teste

echo "Building the project..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "Build successful! Starting emulator..."
    
    # Start emulator in background
    $ANDROID_HOME/emulator/emulator -avd Pixel_7_API_34 &
    EMULATOR_PID=$!
    
    echo "Waiting for emulator to start (30 seconds)..."
    sleep 30
    
    echo "Installing app on emulator..."
    ./gradlew installDebug
    
    if [ $? -eq 0 ]; then
        echo "App installed successfully!"
        echo "Launching app..."
        adb shell am start -n com.carmobileapp/.MainActivity
        
        echo "App launched! You can now test the Car Mobile App."
        echo "To test Android Auto integration, you'll need to connect to a car or use Android Auto for Phone Screens."
    else
        echo "Failed to install app on emulator"
    fi
else
    echo "Build failed!"
fi
