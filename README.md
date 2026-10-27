# Heirella Android Starter

Starter native Android app for package `com.heirella.gf` using Kotlin, Jetpack Compose, and Material 3.

## Current scope
- Basic Heirella chat UI
- Light/dark toggle for the current session
- Local in-memory demo messages
- GitHub Actions workflow that builds and uploads a debug APK

## Not implemented yet
Supabase authentication/database/sync, secure AI provider proxy, actual AI responses, persistent chat history, profile/settings, notifications, usage monitoring, media downloads, and final branding assets are not implemented in this starter. The chat explicitly labels the missing AI connection instead of pretending to be connected.

## Build on GitHub from Android
1. Create or open your GitHub repository.
2. Upload/extract all files from this ZIP into the repository root (the `.github` folder must also be uploaded).
3. Commit the files to the `main` branch.
4. Open the repository's **Actions** tab and select **Build Heirella APK**.
5. If it hasn't run automatically, tap **Run workflow**.
6. Open the completed workflow run and download **Heirella-debug-apk** under Artifacts.
7. Extract the downloaded ZIP; it contains `app-debug.apk`. Install it on your Android phone.

This is a debug APK for testing, not a Play Store release. Never put Supabase service-role keys or raw AI provider secrets in the Android app.
