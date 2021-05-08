Creating the config package is generally a good practice to store all
the related code that is config only and not part of your logic.

In Spring, there are bits of config that cannot be done via properties,
so we often need to create other files to write the config. The config
package is the place to put that.
