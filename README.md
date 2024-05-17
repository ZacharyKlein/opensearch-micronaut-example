# Micronaut OpenSearch Example

Instructions:
- Start the application with `./gradlew run`
- Test Resources will automatically start an OpenSearch container (requires Docker to be running)
- Use the POST "/api/widgets" endpoint to add documents to the OpenSearch "widget" index
```
POST http://localhost:8080/api/widget
Content-Type: application/json

{
  "id": "2",
  "name": "Pump Ultra",
  "brandName": "ADMIN",
  "manufacturerYear": 2018,
  "serialNumber": "422312313"
}
```
- Use the GET "/api/widgets/search" endpoint to perform a search query on the "widget" index (name field only)
```
GET http://localhost:8080/api/widget/search?query=pump
```
- To run the tests, use `./gradlew test`

## Micronaut 4.4.2 Documentation

- [User Guide](https://docs.micronaut.io/4.4.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.4.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.4.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)

## Feature testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)

## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)

## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)

## Feature opensearch-restclient documentation

- [Micronaut OpenSearch REST Client documentation](https://micronaut-projects.github.io/micronaut-opensearch/latest/guide/)

- [https://opensearch.org/docs/latest/clients/java/](https://opensearch.org/docs/latest/clients/java/)


