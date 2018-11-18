var admin = require("firebase-admin");

var serviceAccount = require("C:/Token/serviceAccountKey.json");

app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://coffee-page-moc.firebaseio.com"
});

var message = {
    token: "dSTkmwJTRb0:APA91bG8Gc8qI3L7TpU8iYCVjn57PLz2hIDaLzc9cUuLbdafVTdgYxydTWco5w7_KLF7TF6w1TuFqA9BPklVoAkVgYjYVrkv3GRwhuDAxzuz-H23cGwcz3jBTmA7iM8Oy6C97urIw98e",
    data: {
        type:"fillLevel",
        timestamp:"2018-11-14T16:20:15+00:00",
        fillLevel: "0.80"
    }
}

var messaging = admin.messaging(app);

messaging.send(message)
    .then((response) => {
        // Response is a message ID string.
        console.log('Successfully sent message:', response);
    })
    .catch((error) => {
        console.log('Error sending message:', error);
    });

