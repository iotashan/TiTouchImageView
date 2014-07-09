// This is a test harness for your module
// You should do something interesting in this harness
// to test out the module and to provide instructions
// to users on how to use it by example.


// open a single window
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});

var TiTouchImageView = require('org.iotashan.TiTouchImageView');
Ti.API.info("module is => " + TiTouchImageView);

var imageView = TiTouchImageView.createView({
	backgroundColor:'#0f0',
	top:0,
	left:0,
	right:0,
	bottom:0,
	image:'demo.jpg',
	zoom:0.5,
});

win.add(imageView);

var btn = Ti.UI.createButton({
	top:10,
	color:'#000',
	backgroundColor:'#fff',
	title:'2x Zoom',
});
btn.addEventListener('click',function(){
	imageView.zoom = 2;
});
win.add(btn);


var btn2 = Ti.UI.createButton({
	bottom:10,
	color:'#000',
	backgroundColor:'#fff',
	title:'Reset Zoom',
});
btn2.addEventListener('click',function(){
	imageView.resetZoom();
});
win.add(btn2);

win.open();
