# Haxe Amazon Mobile Analytics

WORK IN PROGRESS

Unofficial [Amazon Mobile Analytics](https://aws.amazon.com/mobileanalytics/) support for Haxe OpenFL Android and iOS targets.

## Features
### Supports
* Use the Amazon mobile analytics SDK from Haxe code.
* Record custom app events with attributes and metrics.
* Global attributes and metrics.

### Doesn't Support
* Monetization events.

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

On Android, define your app identifier and identity pool identifier in Project.xml:

```xml
<!-- Enter your app id and app signature here for Android! Enter your ids in the AmazonMobileAnalytics.init call on iOS -->
<setenv name="AmazonMobileAnalyticsAppId" value="long_hex_string_from_my_aws_console" />
<setenv name="AmazonMobileAnalyticsIdentityPoolId" value="long_hex_string_from_my_aws_console" />
```

## Notes
Use ```#if (android || ios)``` conditionals around your imports and calls to this library for cross platform projects, as there is no stub/fallback implementation included in the haxelib.

If you need to rebuild the iOS or simulator ndlls navigate to ```/project``` and run ```rebuild_ndlls.sh```.

## License
The [Amazon Mobile Analytics SDK](https://aws.amazon.com/mobileanalytics/) included in this repository is copyright 2010-2016 Amazon.com, Inc. or its affiliates, and is licensed under the Apache License, Version 2.0.

Google [Gson](https://github.com/google/gson) is Copyright 2008 Google Inc, and is released under the Apache License, Version 2.0.

The rest of this haxelib is provided under the MIT license and is copyright 2016 Sam Twidale.