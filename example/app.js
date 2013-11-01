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
	width : 100,
	height : 100,
	backgroundColor : 'red'
});
win.add(label);

button.addEventListener('click', function() {
	var win = Ti.UI.createWindow({
		backgroundColor : 'white',
		fullscreen : false // Makes it heavyweight
	});

	var HTML5Video = require('tv.wappzapp.html5video');
	if (Ti.Platform.name == "android") {
		var proxy = HTML5Video.createView({
			width : 200,
			height : 100,
			url : 'http://www.youtube.com/embed/ETAEcomff7g'
		});

		win.add(proxy);
	}
	win.open();
});

