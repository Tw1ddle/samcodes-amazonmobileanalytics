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
void samcodesamazonmobileanalytics_init(HxString appId, HxString identityPoolId)
{
	init(appId.c_str(), identityPoolId.c_str());
}
DEFINE_PRIME2v(samcodesamazonmobileanalytics_init)

void samcodesamazonmobileanalytics_add_global_attribute(HxString attributeName, HxString attributeValue)
{
	addGlobalAttribute(attributeName.c_str(), attributeValue.c_str());
}
DEFINE_PRIME2v(samcodesamazonmobileanalytics_add_global_attribute)

void samcodesamazonmobileanalytics_add_global_attribute_for_event_type(HxString eventType, HxString attributeName, HxString attributeValue)
{
	addGlobalAttributeForEventType(eventType.c_str(), attributeName.c_str(), attributeValue.c_str());
}
DEFINE_PRIME3v(samcodesamazonmobileanalytics_add_global_attribute_for_event_type)

void samcodesamazonmobileanalytics_remove_global_attribute(HxString attributeName)
{
	removeGlobalAttribute(attributeName.c_str());
}
DEFINE_PRIME1v(samcodesamazonmobileanalytics_remove_global_attribute)

void samcodesamazonmobileanalytics_remove_global_attribute_for_event_type(HxString eventType, HxString attributeName)
{
	removeGlobalAttributeForEventType(eventType.c_str(), attributeName.c_str());
}
DEFINE_PRIME2v(samcodesamazonmobileanalytics_remove_global_attribute_for_event_type)

void samcodesamazonmobileanalytics_add_global_metric(HxString metricName, float metricValue)
{
	addGlobalMetric(metricName, metricValue);
}
DEFINE_PRIME2v(samcodesamazonmobileanalytics_add_global_metric)

void samcodesamazonmobileanalytics_add_global_metric_for_event_type(HxString eventType, HxString metricName, float metricValue)
{
	addGlobalMetricForEventType(eventType.c_str(), metricName.c_str(), metricValue);
}
DEFINE_PRIME3v(samcodesamazonmobileanalytics_add_global_metric)

void samcodesamazonmobileanalytics_remove_global_metric(HxString metricName)
{
	removeGlobalMetric(metricName);
}
DEFINE_PRIME1v(samcodesamazonmobileanalytics_remove_global_metric)

void samcodesamazonmobileanalytics_remove_global_metric_for_event_type(HxString eventType, HxString metricName)
{
	removeGlobalMetricForEventType(eventType, metricName);
}
DEFINE_PRIME2v(samcodesamazonmobileanalytics_remove_global_metric_for_event_type)

void samcodesamazonmobileanalytics_submit_events()
{
	submitEvents();
}
DEFINE_PRIME0v(samcodesamazonmobileanalytics_submit_events)

void samcodesamazonmobileanalytics_record_event(value eventType, value attributeNames, value attributeValues, value metricNames, value metricValues)
{
	int attributeNamesSize = val_array_size(attributeNames);
	int attributeValuesSize = val_array_size(attributeValues);
	int metricNamesSize = val_array_size(metricNames);
	int metricValuesSize = val_array_size(metricValues);
	
	if(attributeNamesSize != attributeValuesSize || metricNames != metricValuesSize)
	{
		return; // These should always be the same length (since they map as key => value pairs)
	}
	
	const char** arrAttributeKeys = new const char*[attributeNamesSize];
	const char** arrAttributeValues = new const char*[attributeNamesSize];
	const char** arrMetricKeys = new const char*[metricNamesSize];
	const char** arrMetricValues = new const char*[metricNamesSize];
	
	for(int i = 0; i < attributeNamesSize; i++)
	{
		value key = val_array_i(attributeNames, i);
		value v = val_array_i(attributeValues, i);
		arrAttributeKeys[i] = key;
		arrAttributeValues[i] = v;
	}
	for(int i = 0; i < metricNamesSize; i++)
	{
		value key = val_array_i(metricNames, i);
		value v = val_array_i(metricValues, i);
		arrMetricKeys[i] = key;
		arrMetricValues[i] = v;
	}
	
	recordEvent(val_get_string(eventType), arrAttributeKeys, arrAttributeValues, arrMetricKeys, arrMetricValues, attributeNamesSize, metricNamesSize);
	
	delete[] arrAttributeKeys;
	delete[] arrAttributeValues;
	delete[] arrMetricKeys;
	delete[] arrMetricValues;
	
	return alloc_null();
}
DEFINE_PRIM(samcodesamazonmobileanalytics_record_event, 5)

extern "C" void samcodesamazonmobileanalytics_main()
{
	
}
DEFINE_ENTRY_POINT(samcodesamazonmobileanalytics_main);

extern "C" int samcodesamazonmobileanalytics_register_prims()
{
	return 0;
}

#endif