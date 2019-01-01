var admin = require("firebase-admin");
var serviceAccount = require("C:/Token/serviceAccountKey.json");

app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://coffee-page-moc.firebaseio.com"
});

var messageFillLevel = {
    data: {
        type:"coffee_fill_level",
        timestamp:"2019-01-01T08:21Z",
        fillLevel: "33"
    }
}

var messageReady = {
    data: {
        type:"coffee_ready",
        timestamp:"2019-01-01T08:21Z",
    }
}

var messageBrewing = {
    data: {
        type:"coffee_brewing",
        timestamp:"2019-01-01T08:21Z"
    }
}

var messaging = admin.messaging(app);

messaging.sendToTopic("coffee",messageFillLevel)
    .then((response) => {
        // Response is a message ID string.
        console.log('Successfully sent message:', response);
    })
    .catch((error) => {
        console.log('Error sending message:', error);
    });

