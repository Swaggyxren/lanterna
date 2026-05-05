# Lanterna 🔥

**Host a Minecraft Java Edition server — right from your Android phone.** No Termux. No proot. No root.

Lanterna is a native Android app that spawns a real JVM (Eclipse Temurin OpenJDK) and runs standard Minecraft server JARs — Vanilla, Paper, Purpur, Fabric, Forge, NeoForge, and more — entirely within the APK's process.

> **Status: SCAFFOLD.** The project skeleton is in place (Compose + M3, Hilt, Ktor, Room). No server runtime yet. Milestones tracked in the plan doc.

---

## Features (planned)

- **One-tap server start/stop** — foreground service + wake lock keeps the server alive even when you lock your screen
- **MOTD, name, icon editor** — live preview mimics the Minecraft client server list
- **Java Runtime installer** — download Temurin JRE 8/17/21/25 on-device, or import your own
- **Per-server Java version** — auto-recommends by MC version, with manual override
- **RAM slider** — tiered warnings (green / amber / red) based on device total RAM
- **Flavors** — Vanilla, Paper, Purpur, Fabric, Forge, NeoForge, Quilt, Folia, Velocity
- **Live console** — colored log output, command input with autocomplete
- **OEM battery-killer mitigation** — deep links for Xiaomi/MIUI, OPPO/ColorOS, Vivo, Huawei/EMUI, Samsung/OneUI, OnePlus
- **Material 3 (Material You)** — dynamic color, expressive bouncy motion, adaptive layouts for phones/tablets/foldables
- **First-run onboarding** — guided permission requests + JRE install + server creation in one flow

---

## Tech stack

| Layer | Choice |
|---|---|
| Language | Kotlin 2.0 |
| UI | Jetpack Compose + Material 3 |
| DI | Hilt |
| Networking | Ktor + kotlinx.serialization |
| Persistence | Room + DataStore |
| Image | Coil 3 |
| Archive | commons-compress (tar.xz/gz/zip) |
| Min SDK | 26 (Android 8.0) |
| Target SDK | 35 (Android 15) |

---

## Building

```bash
git clone https://github.com/Swaggyxren/lanterna.git
cd lanterna
./gradlew assembleDebug
```

The debug APK lands at `app/build/outputs/apk/debug/app-debug.apk`.

---

## License

GPL-3.0. See [LICENSE](./LICENSE).
