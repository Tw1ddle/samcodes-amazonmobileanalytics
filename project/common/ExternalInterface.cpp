#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif

#if defined(HX_WINDOWS) || defined(HX_MACOS) || defined(HX_LINUX)
#define NEKO_COMPATIBLE
#endif

#include <hx/CFFIPrime.h>
#include "SamcodesAmazonMobileAnalytics.h"

using namespace samcodesamazonmobileanalytics;

#ifdef IPHONE
void samcodesamazonmobileanalytics_todo()
{
	
}
DEFINE_PRIME1v(samcodesamazonmobileanalytics_todo);

extern "C" void samcodesamazonmobileanalytics_main()
{
	
}
DEFINE_ENTRY_POINT(samcodesamazonmobileanalytics_main);

extern "C" int samcodesamazonmobileanalytics_register_prims()
{
	return 0;
}

#endif