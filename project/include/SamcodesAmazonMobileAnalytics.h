#ifndef SAMCODESAMAZONMOBILEANALYTICSEXT_H
#define SAMCODESAMAZONMOBILEANALYTICSEXT_H

namespace samcodesamazonmobileanalytics
{
	void init(const char* appId, const char* identityPoolId);
	void addGlobalAttribute(const char* attributeName, const char* attributeValue);
	void addGlobalAttributeForEventType(const char* eventType, const char* attributeName, const char* attributeValue);
	void removeGlobalAttribute(const char* attributeName);
	void removeGlobalAttributeForEventType(const char* eventType, const char* attributeName);
	void addGlobalMetric(const char* metricName, float metricValue);
	void addGlobalMetricForEventType(const char* eventType, const char* metricName, float metricValue);
	void removeGlobalMetric(const char* metricName);
	void removeGlobalMetricForEventType(const char* eventType, const char* metricName);
	void submitEvents();
	void recordEvent(const char* eventType, const char** attributeKeys, const char** attributeValues, const char** metricKeys, const char** metricValues, int attributeCount, int metricCount);
}

#endif