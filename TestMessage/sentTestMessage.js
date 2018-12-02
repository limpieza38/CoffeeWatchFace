var admin = require("firebase-admin");
var serviceAccount = require("C:/Token/serviceAccountKey.json");

app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://coffee-page-moc.firebaseio.com"
});

var message = {
    data: {
        type:"coffee_ready",
        timestamp:"2018-11-20T18:21Z",
        fillLevel: "80"
    }
}

var messaging = admin.messaging(app);

messaging.sendToTopic("coffee",message)
    .then((response) => {
        // Response is a message ID string.
        console.log('Successfully sent message:', response);
    })
    .catch((error) => {
        console.log('Error sending message:', error);
    });

