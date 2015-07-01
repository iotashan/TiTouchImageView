# TiTouchImageView Module
================

Titanium native module wrapper for TouchImageView: https://github.com/MikeOrtiz/TouchImageView

Do you like pinching and zooming on iOS? Wish it just worked on Android too? Here you go!

## Get it [![gitTio](http://gitt.io/badge.png)](http://gitt.io/component/org.iotashan.TiTouchImageView)
Download the latest distribution ZIP-file and consult the [Titanium Documentation](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_a_Module) on how install it, or simply use the [gitTio CLI](http://gitt.io/cli):

`$ gittio install org.iotashan.TiTouchImageView`

## Referencing the module in your Ti mobile application 

Simply add the following lines to your `tiapp.xml` file:

```
	<modules>
		<module platform="android">org.iotashan.titouchimageview</module>
	</modules>
```

To use your module in code, you will need to require it.

```JavaScript
	var TiTouchImageView = require('org.iotashan.TiTouchImageView');
	var myView = TiTouchImageView.createView();
```

## API Properties

###image

Accepts a string path to a local file, or a TiBlob image object.

###maxZoom

Maximum zoom value, as a decimal. "5.5" means you can zoom in 550%

###minZoom

Minimum zoom value, as a decimal. "0.5" means you can zoom out to 50%

###zoom

Zoom value for the view, as a decimal. Want to zoom to 300%? Set the value to 3.

## API Methods

###createView(props)

Accepts a dictonary of properties. TiTouchImageView extends TiUIView, so you can set other properties like top/left, backgroundColor, etc. Returns the view.

### resetZoom()

Resets the zoom to the default value for the view.

### scrollTo(x,y)

Scrolls the view to the point specified.

### getCurrentZoom()

Returns the current zoom level as a float.

### getScrollPosition()

Returns the current scroll position as point co-ordinates (x,y)
