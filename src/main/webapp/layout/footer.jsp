<!DOCTYPE html>
<html>
<head>
    <title>Chatbot</title>
    <style>
        #chat-container {
            width: 300px;
            height: 400px;
            border: 1px solid #ccc;
            overflow-y: scroll;
            margin: 20px auto;
        }

        #chat-log {
            padding: 10px;
        }

        #chat-input {
            padding: 10px;
            border-top: 1px solid #ccc;
        }

        #user-input {
            width: 70%;
            padding: 5px;
            margin-right: 5px;
        }

        #send-button {
            padding: 5px 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="chat-container">
        <div id="chat-log"></div>
        <div id="chat-input">
            <input type="text" id="user-input" placeholder="Type your message here...">
            <button id="send-button">Send</button>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(document).ready(function() {
        $("#send-button").click(function() {
            var userInput = $("#user-input").val();
            sendMessage(userInput);
            $("#user-input").val(""); // Clear input field
        });
    });

    function sendMessage(message) {
        $("#chat-log").append("<div>You: " + message + "</div>");
        $.ajax({
            type: "POST",
            url: "/bot/chat",
            contentType: "application/json",
            data: JSON.stringify(message),
            success: function(response) {
            	var respon = JSON.parse(response);
            	console.log(respon.bubbles);
                if (respon.bubbles && respon.bubbles.length > 0) {
                    var botResponse = respon.bubbles[0].data.description;
        			console.log(botResponse);
                    $("#chat-log").append("<div>Bot: " + botResponse + "</div>");
                } else {
                    console.error("Error: Empty response or invalid format");
                }
                $("#chat-log").scrollTop($("#chat-log")[0].scrollHeight); // Scroll to bottom
            },
            error: function(xhr, status, error) {
                console.error("Error:", error);
            }
        });
    }

    </script>
</body>
</html>
