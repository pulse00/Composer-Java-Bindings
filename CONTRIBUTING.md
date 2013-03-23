Contributing guidelines
=======================

### Maven dependency management

When you need to add a dependency, please add them as a [maven](http://maven.apache.org/) dependency in `pom.xml`.

To make the maven dependency resolution work in eclipse, you need to add the [m2eclipse](http://eclipse.org/m2e/) plugin.

### OSGI bundles

It is generally preferred to use dependencies which are available as an osgi bundle, for example the ones provided in the 
orbit repository: http://download.eclipse.org/tools/orbit/downloads/