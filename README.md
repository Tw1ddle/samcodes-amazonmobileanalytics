# Haxe Amazon Mobile Analytics

Unofficial Amazon mobile analytics support for Haxe OpenFL Android and iOS targets.

## Features
* Use the Amazon mobile analytics SDK from Haxe code.
* Track key app usage metrics.
* Track custom app events.

See the official Amazon [documentation](https://aws.amazon.com/mobileanalytics/).

If there is something you would like adding let me know. Pull requests welcomed too.

## Install

```bash
haxelib install samcodes-amazonmobileanalytics
```

## Usage

Include the haxelib through Project.xml:
```xml
<haxelib name="samcodes-amazonmobileanalytics" />
```

## Notes
Use ```#if (android || ios)``` conditionals around your imports and calls to this library for cross platform projects, as there is no stub/fallback implementation included in the haxelib.

If you need to rebuild the iOS or simulator ndlls navigate to ```/project``` and run ```rebuild_ndlls.sh```.

## License
The [Amazon Mobile Analytics SDK](https://aws.amazon.com/mobileanalytics/) included in this repository is copyright 2010-2016 Amazon.com, Inc. or its affiliates, and is licensed under the Apache License, Version 2.0.

The rest of this haxelib is provided under the MIT license, copyright Sam Twidale 2016.