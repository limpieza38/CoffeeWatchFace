var admin = require("firebase-admin");
var serviceAccount = require("C:/Token/serviceAccountKey.json");

app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://coffee-page-moc.firebaseio.com"
});

var messageFillLevel = {
    data: {
        type:"coffee_fill_level",
        timestamp:"2019-02-10T16:58:41.214Z",
        fillLevel: "33"
    }
}

var messageReady = {
    data: {
        type:"coffee_ready",
        timestamp:"2019-02-10T16:58:41.214Z",
    }
}

var messageBrewing = {
    data: {
        type:"coffee_brewing",
        timestamp:"2019-02-10T16:58:41.214Z"
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

