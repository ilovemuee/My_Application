import openai
import pyttsx3
import requests
def connect(connec,device,status):
   URL = 'http://192.168.43.205/'+connec
   username ="chatgpt"
   password = "adminPasswordSKM"
   dic = {"username":username,"password":password,"data":device,"status":status}
   r = requests.post(url = URL, params = dic)
def jarvis(text):
    openai.api_key = "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"
    a = text
    prompt = (a)
    tokenized_text = text.split()
    message = ""
    for token in tokenized_text:
        if token.lower() == "open" and "devices" in [t.lower() for t in tokenized_text]:
            message = "-1"
            break
        elif token.lower() == "what" and "name" in [t.lower() for t in tokenized_text]:
            message = "My name is IRIS"
            break
        elif token.lower() == "open" and "door" in [t.lower() for t in tokenized_text]:
            message = "Opening the door."
            break
        elif token.lower() == "open" and "fan" in [t.lower() for t in tokenized_text]:
                    message = "Opening the fan."
                    break
    if not message:
        openai.api_key = "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"
        prompt = (text)
        response = openai.Completion.create(
            engine="text-davinci-003",
            prompt=prompt,
            max_tokens=1024,
            temperature=0.5,
        )
#         def SpeakText(command):
#                 engine = pyttsx3.init()
#                 engine.say(command)
#                 engine.runAndWait()

        message = response.choices[0].text
#         URL = "http://192.168.43.199/comment"
#         username ="chatgpt"
#         password = "verysecurepassword"
#         comment=message
#         dic = {"username":username,"password":password,"comment":comment}
#         r = requests.post(url = URL, params = dic)
    return message

