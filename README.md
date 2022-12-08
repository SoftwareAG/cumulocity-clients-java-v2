# CumulocityCoreLibrary

## Usage

When using APIs, a new `WebTarget` needs to be created and passed using the constructor of each API class. Therefore, create a new `Client` and pass the resource target:

```Java
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

final Client client = ClientBuilder.newClient();
final WebTarget webTarget = client.target("endpoint");
final SystemOptionsApi service = new SystemOptionsApi(webTarget);
```

### Use your own domain model

The CumulocityCoreLibrary allows custom data models. The following classes are designed to be extendible:

- `Alarm`, `AuditRecord`, `CategoryOptions`, `CustomProperties`, `Event`, `ManagedObject`, `Measurement`, `Operation`

Those classes allow to add an arbitrary number of additional properties as a list of key-value pairs. These properties are known as custom fragments and can be of any type. Each custom fragment is identified by a unique name. Thus, developers can propagate their custom fragments using:

```Java
Alarm.Serialization.registerAdditionalProperty(String, Class<?>);
```

Each of the extensible objects contains a dictionary object holding instances of custom fragments. Use the custom fragment's key to access it's value.

### Basic Authentication

The client sends HTTP requests with the `Authorization` header that contains the word `Basic` word followed by a space and a base64-encoded string `username:password`.
Using Jersey, the `Authorization` header is added using a `HttpAuthenticationFeature`. 

```Java
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

final ClientConfig clientConfig = new ClientConfig();
final HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("userName", "password");
clientConfig.register(feature);
```

The `HttpAuthenticationFeature` will be used to create a new `Client` and afterwards the `Authorization` header is automatically added to each request.

```Java
final Client client = ClientBuilder.newClient(clientConfig);
```

## Contribution

If you've spotted something that doesn't work as you'd expect, or if you have a new feature you'd like to add, we're happy to accept contributions and bug reports.

For bug reports, please raise an issue directly in this repository by selecting the `issues` tab and then clicking the `new issue` button. Please ensure that your bug report is as detailed as possible and allows us to reproduce your issue easily.

In the case of new contributions, please create a new branch from the latest version of `main`. When your feature is complete and ready to evaluate, raise a new pull request.

---

These tools are provided as-is and without warranty or support. They do not constitute part of the Software AG product suite. Users are free to use, fork and modify them, subject to the license agreement. While Software AG welcomes contributions, we cannot guarantee to include every contribution in the master project.
