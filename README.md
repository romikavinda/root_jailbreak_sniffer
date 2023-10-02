# root_jailbreak_sniffer


Flutter plugin to detect jailbreak on iOS and root detection (Magisk hide to some extent) on Android.

Based on
[RootBeer](https://github.com/scottyab/rootbeer) ,
[RootInspector](https://github.com/devadvance/rootinspector) ,
[DetectMagiskHide](https://github.com/darvincisec/DetectMagiskHide)
for Android,
and
[IOSSecuritySuite](https://github.com/securing/IOSSecuritySuite)
for iOS.

## Getting Started

# Install
```
$ flutter pub add root_jailbreak_sniffer

```

or add the below line to your package's pubspec.yaml:

```
dependencies:
  root_jailbreak_sniffer: ^1.0.3

```

## Platform Based Configurations

# Android
No Configurations Needed.

# iOS
Add following lines to the Info.plist file in /ios/Runner/ folder.

```
<key>LSApplicationQueriesSchemes</key>
    <array>
        <string>undecimus</string>
        <string>sileo</string>
        <string>zbra</string>
        <string>filza</string>
        <string>activator</string>
    </array>

```

## Usage

```
import 'package:root_jailbreak_sniffer/rjsniffer.dart';

    bool amICompromised = await Rjsniffer.amICompromised() ?? false;     //Detect JailBreak and Root
    bool amIEmulator = await Rjsniffer.amIEmulator() ?? false;           //Detect Emulator Environment
    bool amIDebugged = await Rjsniffer.amIDebugged() ?? false;           //Detect being Debugged

```

