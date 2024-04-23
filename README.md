# NetWatch

![Java](https://img.shields.io/badge/Java-11_+-green.svg?style=just-the-message&labelColor=gray)

NetWatch is a Java-based library/agent designed for server-side network observability for
microservices. It provides real-time
monitoring and visualization of network connectivity, enabling you to identify and address issues
proactively.

**NOTE**: This project is a work in progress.

## Features

- Standalone service for machine status notification
- Integration with Spring Boot apps for app status notification
- Network connectivity setup with other dependent services
- Definition of targets for reachability checks
- Visualization of service connections and network issues

## Installation

### Server

#### Run the NetWatch Server

```shell
java -jar netwatch-server.jar --spring.config.location=/path/to/config.yaml
```

Refer to the
sample [config.yaml](https://github.com/amithkoujalgi/NetWatch/blob/main/server/src/main/resources/application.yaml).

After running the above command, NetWatch server's UI is accessible at http://localhost:8080

### Client

#### Case 1: Using it in Java App

Added as lib in Java and configured to connect to a few services (database, another Java app, REST
API, etc).
The lib does a reachability check to all the services, collects the data and reports to the master.

```mermaid
flowchart LR
    cfg["Config"]
    lib["NetWatch"]
    lib -. reachability check .-> Svc1
    lib -. reachability check .-> Svc2
    lib -. Report to .-> Master
    subgraph app["Java App"]
        lib
    end
    cfg --> lib
```

To use NetWatch in your Java application, follow these steps:

1. Add the NetWatch Agent dependency to your project:

```xml

<dependency>
  <groupId>io.github.amithkoujalgi</groupId>
  <artifactId>netwatch-agent</artifactId>
  <version>1.0.0</version>
</dependency>
```

2. Configure NetWatch in your Java application:

```java
import io.github.amithkoujalgi.netwatch.client.NetWatchAgent;
import java.util.Collections;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Connection c = new Connection();
    c.setName("C1");
    c.setHost("google.com");
    c.setPort(80);
    c.setType(ConnectionType.HTTP);

    NetWatchAgent netWatchAgent = new NetWatchAgent("localhost", 8990);
    netWatchAgent.setConnections(Collections.singletonList(c));
    netWatchAgent.start();
    netWatchAgent.join();
  }
}
```

#### Case 2: Using it as a standalone agent

The agent does a reachability check to all the configured services, collects the data and reports to
the master.

### Build

```shell
mvn clean install
```

## Usage

Once NetWatch is integrated into your application, it will automatically post heartbeat messages to
notify the status of
your machine and application.
You can visualize this data using the NetWatch dashboard to identify any failure points or network
connectivity issues.

## Contributing

Contributions to NetWatch are welcome! If you have any ideas for new features, improvements, or bug
fixes, please open
an issue or submit a pull request on GitHub.

