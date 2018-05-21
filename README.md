# Haxe Amazon Mobile Analytics

[![Travis Build Status](https://img.shields.io/travis/Tw1ddle/samcodes-amazonmobileanalytics.svg?style=flat-square)](https://travis-ci.org/Tw1ddle/samcodes-amazonmobilyanalytics)
[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](https://github.com/Tw1ddle/samcodes-amazonmobileanalytics/blob/master/LICENSE)

Unofficial [Amazon Mobile Analytics](https://aws.amazon.com/mobileanalytics/) bindings for Haxe OpenFL Android and iOS targets.

*Deprecation note - Amazon Mobile Analytics was superseded by [Amazon Pinpoint](https://aws.amazon.com/pinpoint/).*

## Features
### Supports
* Automatically record key app usage stats.
* Record custom events with attributes and metrics.
* Global attributes and metrics.

### Doesn't Support
* Monetization events.

If there is something you would like adding let me know. Pull requests welcomed too.

## Install

```bash
haxelib install samcodes-amazonmobileanalytics
```

## Usage

On Android, define your app identifier and identity pool identifier in Project.xml:

```xml
<!-- Enter your app id and app signature here for Android! Enter your ids in the AmazonMobileAnalytics.init call on iOS -->
<setenv name="AmazonMobileAnalyticsAppId" value="long_hex_string_from_my_aws_console" />
<setenv name="AmazonMobileAnalyticsIdentityPoolId" value="long_hex_string_from_my_aws_console" />
```

After integrating and booting the app, you should see data appearing on the Amazon Mobile Analytics dashboards. From here you can create custom dashboards, or export the data and query it. Here it is in action:

![Screenshot](https://github.com/Tw1ddle/samcodes-amazonmobileanalytics/blob/master/screenshots/items.png?raw=true "Example app item uses")

![Screenshot of demo app](https://github.com/Tw1ddle/samcodes-amazonmobileanalytics/blob/master/screenshots/ratings.png?raw=true "Example app rating stats")

## Notes
Use ```#if (android || ios)``` conditionals around your imports and calls to this library for cross platform projects, as there is no stub/fallback implementation included in the haxelib.

If you need to rebuild the iOS or simulator ndlls navigate to ```/project``` and run ```rebuild_ndlls.sh```.

## License
The [Amazon Mobile Analytics SDK](https://aws.amazon.com/mobileanalytics/) included in this repository is copyright 2010-2016 Amazon.com, Inc. or its affiliates, and is licensed under the Apache License, Version 2.0.

Google [Gson](https://github.com/google/gson) is Copyright 2008 Google Inc, and is released under the Apache License, Version 2.0.

The rest of this haxelib is provided under the MIT license and is copyright 2016 Sam Twidale.