# VanillaCompat

A Fabric library for creating mods that work with Vanilla clients.

To use it, add this to your build.gradle

    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    
And then just include it as a dependency.

    dependencies {
        modImplementation 'com.github.SoapyXM:VanillaCompat:Tag'
        include 'com.github.SoapyXM:VanillaCompat:Tag' // optional
    }
    
VanillaCompat will automatically generate block/item translations for blocks/items that
are not translated. Register your items with CompatRegistry, prior to registering them.

If you are doing transformations for a mod other than your own, defer AutoCompat from transforming its items in a
static block. If you are doing this, be sure to transform *every* block/item in that namespace, or the client will crash.