package extension.amazonmobileanalytics;

import haxe.ds.StringMap;

#if android
import openfl.utils.JNI;
#end

#if ios
import extension.amazonmobileanalytics.PrimeLoader;
import flash.Lib;
#end

#if (android || ios)
class AmazonMobileAnalytics {
	// On Android set the appId and identityPoolId in Project.xml
	public static function init(?appId:String, ?identityPoolId:String):Void {
		#if ios
		if (appId == null || identityPoolId == null) {
			throw "appId or identityPoolId not passed. On iOS these must be passed in AmazonMobileAnalytics.init";
		}
		_init.call(appId, identityPoolId);
		#end
	}
	
	public static function addGlobalAttribute(attributeName:String, value:String):Void {
		#if android
		add_global_attribute(attributeName, value);
		#elseif ios
		add_global_attribute.call(attributeName, value);
		#end
	}
	
	public static function addGlobalAttributeForEventType(eventType:String, attributeName:String, value:String):Void {
		#if android
		add_global_attribute_for_event_type(eventType, attributeName, value);
		#elseif ios
		add_global_attribute_for_event_type.call(eventType, attributeName, value);
		#end
	}
	
	public static function removeGlobalAttribute(attributeName:String):Void {
		#if android
		remove_global_attribute(attributeName);
		#elseif ios
		remove_global_attribute.call(attributeName);
		#end
	}
	
	public static function removeGlobalAttributeForEventType(eventType:String, attributeName:String):Void {
		#if android
		remove_global_attribute_for_event_type(eventType, attributeName);
		#elseif ios
		remove_global_attribute_for_event_type.call(eventType, attributeName);
		#end
	}
	
	public static function addGlobalMetric(metricName:String, value:Float):Void {
		#if android
		add_global_metric(metricName, value);
		#elseif ios
		add_global_metric.call(metricName, value);
		#end
	}
	
	public static function addGlobalMetricForEventType(eventType:String, metricName:String, value:Float):Void {
		#if android
		add_global_metric_for_event_type(eventType, metricName, value);
		#elseif ios
		add_global_metric_for_event_type.call(eventType, metricName, value);
		#end
	}
	
	public static function removeGlobalMetric(metricName:String):Void {
		#if android
		remove_global_metric(metricName);
		#elseif ios
		remove_global_metric.call(metricName);
		#end
	}
	
	public static function removeGlobalMetricForEventType(eventType:String, metricName:String):Void {
		#if android
		remove_global_metric_for_event_type(eventType, metricName);
		#elseif ios
		remove_global_metric_for_event_type.call(eventType, metricName);
		#end
	}
	
	public static function recordEvent(eventType:String, ?attributes:StringMap<String>, ?metrics:Map<String, Float>):Void {
		var attributeNames:Array<String> = [];
		var attributeValues:Array<String> = [];
		var metricNames:Array<String> = [];
		var metricValues:Array<Float> = [];
		
		if(attributes != null) {
			for (key in attributes.keys()) {
				attributeNames.push(key);
				attributeValues.push(attributes.get(key));
			}
		}
		
		if(metrics != null) {
			for (key in metrics.keys()) {
				metricNames.push(key);
				metricValues.push(metrics.get(key));
			}
		}
		
		#if android
		record_event(eventType, attributeNames, attributeValues, metricNames, metricValues);
		#elseif ios
		record_event(eventType, attributeNames, attributeValues, metricNames, metricValues); // NOTE using CFFI so no need to use .call() syntax
		#end
	}
	
	public static function submitEvents():Void {
		#if android
		submit_events();
		#elseif ios
		submit_events.call();
		#end
	}
	
	#if android
	private static inline var packageName:String = "com/samcodes/amazonmobileanalytics/AmazonMobileAnalyticsExtension";
	private static inline function bindJNI(jniMethod:String, jniSignature:String) {
		return JNI.createStaticMethod(packageName, jniMethod, jniSignature);
	}
	private static var add_global_attribute = bindJNI("addGlobalAttribute", "(Ljava/lang/String;Ljava/lang/String;)V");
	private static var add_global_attribute_for_event_type = bindJNI("addGlobalAttributeForEventType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	private static var remove_global_attribute = bindJNI("removeGlobalAttribute", "(Ljava/lang/String;)V");
	private static var remove_global_attribute_for_event_type = bindJNI("removeGlobalAttributeForEventType", "(Ljava/lang/String;Ljava/lang/String;)V");
	private static var add_global_metric = bindJNI("addGlobalMetric", "(Ljava/lang/String;F)V");
	private static var add_global_metric_for_event_type = bindJNI("addGlobalMetricForEventType", "(Ljava/lang/String;Ljava/lang/String;F)V");
	private static var remove_global_metric = bindJNI("removeGlobalMetric", "(Ljava/lang/String;)V");
	private static var remove_global_metric_for_event_type = bindJNI("removeGlobalMetricForEventType", "(Ljava/lang/String;Ljava/lang/String;)V");
	private static var record_event = bindJNI("recordEvent", "(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[F)V");
	private static var submit_events = bindJNI("submitEvents", "()V");
	#elseif ios
	private static var _init = PrimeLoader.load("samcodesamazonmobileanalytics_init", "ssv");
	private static var add_global_attribute = PrimeLoader.load("samcodesamazonmobileanalytics_add_global_attribute", "ssv");
	private static var add_global_attribute_for_event_type = PrimeLoader.load("samcodesamazonmobileanalytics_add_global_attribute_for_event_type", "sssv");
	private static var remove_global_attribute = PrimeLoader.load("samcodesamazonmobileanalytics_remove_global_attribute", "sv");
	private static var remove_global_attribute_for_event_type = PrimeLoader.load("samcodesamazonmobileanalytics_remove_global_attribute_for_event_type", "ssv");
	private static var add_global_metric = PrimeLoader.load("samcodesamazonmobileanalytics_add_global_metric", "sfv");
	private static var add_global_metric_for_event_type = PrimeLoader.load("samcodesamazonmobileanalytics_add_global_metric_for_event_type", "ssfv");
	private static var remove_global_metric = PrimeLoader.load("samcodesamazonmobileanalytics_remove_global_metric", "sv");
	private static var remove_global_metric_for_event_type = PrimeLoader.load("samcodesamazonmobileanalytics_remove_global_metric_for_event_type", "ssv");
	private static var submit_events = PrimeLoader.load("samcodesamazonmobileanalytics_submit_events", "v");
	
	private static var record_event = Lib.load("samcodesamazonmobileanalytics", "samcodesamazonmobileanalytics_record_event", 5); // Note using old CFFI since some of the parameters are "complex" types (string arrays)
	#end
}
#end