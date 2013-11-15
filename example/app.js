// This is a test harness for your module
// You should do something interesting in this harness
// to test out the module and to provide instructions
// to users on how to use it by example.

// open a single window

// exmaple

var home = Ti.UI.createWindow({
	backgroundColor : 'white'
});

var button = Ti.UI.createView({
	width : 300,
	height : 100,
	backgroundColor : 'red'
});
home.add(button);
home.open();

button.addEventListener('click', function() {
	var win = Ti.UI.createWindow({
		backgroundColor : 'white',
		fullscreen : true // Makes it heavyweight
	});

	var HTML5Video = require('tv.wappzapp.html5video');
	var proxy = HTML5Video.createWebVideo({
		width : 600,
		height : 600,
		url : 'http://www.youtube.com/embed/zDl31YBlbE8'
	});

	win.add(proxy);

	win.addEventListener('close', function() {
		proxy.url = "http://www.google.nl";
	});

	win.open();
});
