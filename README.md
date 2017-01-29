# About
This project is based on the idea that microservices can be developed and deployed as packages.
Microservice packages contain network facing endpoints as well as the client implementations for using these endpoints.

Packages are stored in an organisations private/internal package repository such as maven as well as being deployed as microservices.
Developers can then use the interfaces defined in such packages to integrate with the corresponding microservice.
This allows for versioned microservice dependency management as well as fine grained control over microservice use and change tracking.

# MicroPackage
MicroPackage is a discovery server for the ackage based microservice management as described above.
Microservice packages can register with this discovery server using the name and version of the package they expose.
Dependencies can be supplied as part of the registration process allowing for complete transparency of dependencies between microservices.

Clients can query MicroPackage using a package name and version to find the next available MicroService implementing that package.

All instances of microservices and the corosponding packages/dependencies are tracked within micropackage.
This would allow for dependency graphs to be constructed highlighting deprecated, critical and unused microservices within deployments.