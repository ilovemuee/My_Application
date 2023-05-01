import openai
import pyttsx3
#Intelligent Response and Interaction System
import requests
import re 
from datetime import datetime
# def timetable(date):
#    URL = 'http://localhost/date'
#    mmatched = re.search(r'\d{2}-\d{2}-\d{4}',date)
#    res = datetime.strptime(mmatched.group(), '%d-%m-%Y').date()
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":res}
#    r = requests.post(url = URL, params = dic)
# def current_attendence(current):
#    URL = 'http://localhost/current_attendence'
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":regno}
#    r = requests.post(url = URL, params = dic)
#    return r
#
# def attendece(regno):
#    URL = 'http://localhost/attendence'
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":regno}
#    r = requests.post(url = URL, params = dic)
#    return "ur attendence has been marked"
# def connect(connec,device,status):
#    URL = 'http://192.168.43.205/'+connec
#    username ="chatgpt"
#    password = "adminPasswordSKM"
#    dic = {"username":username,"password":password,"data":device,"status":status}
#    r = requests.post(url = URL, params = dic)
def jarvis(text):
    openai.api_key = "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"
    a = text
    prompt = (a)
    tokenized_text = text.split()
    message = ""
    for token in tokenized_text:
        if token.lower() == "open" and "fan" in [t.lower() for t in tokenized_text]:
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
        # URL = "http://192.168.43.205/comment"
        # username ="chatgpt"
        # password = "verysecurepassword"
        # comment=message
        # dic = {"username":username,"password":password,"comment":comment}
        # r = requests.post(url = URL, params = dic)
    return message
def ums_assistant(query):
        # o = jarvis(f"here is the sentence '{a}' and tell me which date i have mentioned in this sentence and convert it into this format dd-mm-yyyy")
        a = jarvis(f"here is the sentence '{query}' and tell me which date i have mentioned in this sentence and convert it into this format dd-mm-yyyy and if no give me output as no")
        b = jarvis(f"here is the sentence '{query}' tell me if this sentence is taking about the getting the current information about the  attedence using the app")
        c = jarvis(f"here is the sentence '{query}' tell me if this sentence is taking about  the marking of student attendnce but not asking information about it using the app")
        a1 = True if "No" not in a else False  
        b1 = True if "Yes" in b else False
        c1 = True if "Yes" in c else False
        if c1:
            #  attendece(reg_no) 
            return "ur attendence have been marked"    
        elif b1:
            # current_attendence(reg_no)
            return "ur current attedence 86%"
        elif a1:
            # timetable(a)
            return "timetable has been successfully fetched"
        else:
            return "please select a valid option"
