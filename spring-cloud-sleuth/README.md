# Sleuth
Spring Cloud Sleuth provides Spring Boot auto-configuration for distributed tracing.

## Endpoint
* http://localhost:8080

## Example of trace without sleuth
2020-07-21 23:01:26.683  INFO 23990 --- [nio-8080-exec-1] com.example.demo.DemoApplication         : Handling home

## Example of trace with sleuth
2020-07-21 22:56:56.777  INFO [Sleuth example,9793a11db12abe03,9793a11db12abe03,true] 23552 --- [nio-8080-exec-1] com.example.demo.DemoApplication         : Handling home
* [application name, traceId, spanId, export]
  * Application name:  set in the properties file. It can be used to aggregate logs from multiple instances of the same application.
  * TraceId: This is an id that is assigned to a single request, job, or action. Something like each unique user initiated web request will have its own traceId.
  * SpanId: Tracks a unit of work. Think of a request that consists of multiple steps. Each step could have its own spanId and be tracked individually. By default, any application flow will start with same TraceId and SpanId.
  * Export: This property is a boolean that indicates whether or not this log was exported to an aggregator like Zipkin. Zipkin is beyond the scope of this article but plays an important role in analyzing logs created by Sleuth.

## Sleuth + logback
If you use a custom logback-spring.xml, you must pass the spring.application.name in the bootstrap rather than the application property file. Otherwise, your custom logback file does not properly read the property.

## More information
* https://spring.io/projects/spring-cloud-sleuth
* https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/2.2.3.RELEASE/reference/html/
